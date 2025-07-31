# Spring Boot CRUD Operations Q&A

## Annotations in Spring Boot

### Q: What is the `@Entity` annotation?
**A:** The `@Entity` annotation marks a Java class as a JPA entity, meaning it will be mapped to a database table. Each instance of the entity represents a row in that table.

**Example with Student model:**
```java
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    private LocalDate dob;
}
```

### Q: What is the `@Repository` annotation?
**A:** The `@Repository` annotation indicates that the class serves as a repository - a mechanism for encapsulating storage, retrieval, and search behavior that emulates a collection of objects.

**Example:**
```java
@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findByNameContaining(String name);
}
```

### Q: What is the `@Service` annotation?
**A:** The `@Service` annotation marks a class as a service layer component that handles business logic.

**Example:**
```java
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    public List<Student> getAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }
}
```

### Q: What is the `@RestController` annotation?
**A:** The `@RestController` combines `@Controller` and `@ResponseBody` annotations. It indicates that the class handles HTTP requests and returns data directly in the response body (typically as JSON).

**Example:**
```java
@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;
    
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
}
```

### Q: What is the `@RequestMapping` annotation?
**A:** The `@RequestMapping` annotation maps HTTP requests to handler methods in controller classes. It can be applied at class level or method level.

**Example:**
```java
@RestController
@RequestMapping("/api/students")  // Base path for all methods
public class StudentController {
    // Class content
}
```

### Q: What are the HTTP method-specific mapping annotations?
**A:** Spring provides specialized annotations for different HTTP methods:

**Examples:**
```java
@GetMapping("/{id}")
public Student getStudent(@PathVariable Long id) {
    return studentService.getStudent(id);
}

@PostMapping
public Student createStudent(@RequestBody Student student) {
    return studentService.saveStudent(student);
}

@PutMapping("/{id}")
public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
    return studentService.updateStudent(id, student);
}

@DeleteMapping("/{id}")
public void deleteStudent(@PathVariable Long id) {
    studentService.deleteStudent(id);
}
```

### Q: What is the `@PathVariable` annotation?
**A:** The `@PathVariable` annotation binds method parameters to URI template variables (parts of the URL path).

**Example:**
```java
@GetMapping("/{id}")
public Student getStudent(@PathVariable Long id) {
    return studentService.getStudent(id);
}
```

### Q: What is the `@RequestBody` annotation?
**A:** The `@RequestBody` annotation binds the HTTP request body to a method parameter, typically to receive JSON data.

**Example:**
```java
@PostMapping
public Student createStudent(@RequestBody Student student) {
    return studentService.saveStudent(student);
}
```

### Q: What is the `@RequestParam` annotation?
**A:** The `@RequestParam` annotation binds query parameters to method parameters.

**Example:**
```java
@GetMapping("/search")
public List<Student> searchStudents(@RequestParam String name) {
    return studentService.findStudentsByName(name);
}
```

## JPA Repository Benefits

### Q: What are the benefits of using CrudRepository?
**A:** CrudRepository provides ready-to-use methods for CRUD operations:

- `findById(ID)`: Retrieves a student by ID
- `findAll()`: Gets all students
- `save(Entity)`: Creates or updates a student
- `deleteById(ID)`: Deletes a student by ID

These methods eliminate the need to write SQL queries manually, improving developer productivity.

### Q: Can CrudRepository be extended with custom queries?
**A:** Yes, you can add custom query methods:

```java
@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findByEmail(String email);
    List<Student> findByDobBefore(LocalDate date);
    List<Student> findByNameContainingOrderByNameAsc(String name);
}
```

These methods will be automatically implemented by Spring Data JPA based on their names.