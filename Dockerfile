# ---------- BUILD STAGE ----------
FROM eclipse-temurin:24-alpine AS builder
WORKDIR /workspace

# Install Maven (lightweight)
ARG MAVEN_VERSION=3.9.6
RUN apk add --no-cache curl tar \
    && curl -fsSL https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
    | tar -xz -C /opt \
    && ln -s /opt/apache-maven-${MAVEN_VERSION}/bin/mvn /usr/bin/mvn

# Copy Maven settings first for dependency caching
COPY pom.xml ./
COPY .mvn/ .mvn/


RUN mvn -B -Dmaven.test.skip=true dependency:go-offline

# Copy source and build with layered JAR
COPY src ./src
RUN mvn -B -Dmaven.test.skip=true clean package spring-boot:repackage

# Extract layers
RUN mkdir -p target/extracted && \
    java -Djarmode=layertools -jar target/*.jar extract --destination target/extracted

# =========================
#  RUNTIME STAGE
# =========================
FROM eclipse-temurin:24-jre-alpine AS production
WORKDIR /app

# Install minimal tools for health checks (use wget instead of curl - smaller)
RUN apk add --no-cache wget

# Create a non-root user
RUN addgroup --system app && adduser --system --ingroup app app

# Copy Spring Boot layers
COPY --from=builder /workspace/target/extracted/dependencies/ ./
COPY --from=builder /workspace/target/extracted/spring-boot-loader/ ./
COPY --from=builder /workspace/target/extracted/snapshot-dependencies/ ./
COPY --from=builder /workspace/target/extracted/application/ ./

# Set ownership to non-root user
RUN chown -R app:app /app
USER app

# Configure JVM memory limits
ENV JAVA_OPTS="-Xmx256m -Xms128m -XX:MaxMetaspaceSize=128m -XX:+UseG1GC -XX:MaxGCPauseMillis=100 -Djava.security.egd=file:/dev/./urandom"

# Expose app port
EXPOSE 9090

# Run with JarLauncher
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS org.springframework.boot.loader.launch.JarLauncher"]