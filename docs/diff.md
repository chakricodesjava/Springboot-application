#### What's New
---

##### `GET` /api/addresses/{id}


##### `PUT` /api/addresses/{id}


##### `DELETE` /api/addresses/{id}


##### `GET` /api/addresses


##### `POST` /api/addresses


##### `GET` /api/students/all


#### What's Deleted
---

##### `GET` /actuator

> Actuator root web endpoint


##### `GET` /actuator/info

> Actuator web endpoint 'info'


##### `GET` /actuator/health

> Actuator web endpoint 'health'


##### `GET` /actuator/health/**

> Actuator web endpoint 'health-path'


#### What's Changed
---

##### `GET` /api/students/search


###### Parameters:

Added: `email` in `query`

Changed: `name` in `query`

###### Return Type:

Changed response : **200 OK**
> OK


* Changed content type : `*/*`

##### `GET` /api/students/


###### Return Type:

Changed response : **200 OK**
> OK


* Changed content type : `*/*`

##### `DELETE` /api/students/{id}


###### Parameters:

Changed: `id` in `path`

##### `GET` /api/students/{id}


###### Parameters:

Changed: `id` in `path`

###### Return Type:

Changed response : **200 OK**
> OK


* Changed content type : `*/*`

##### `PUT` /api/students/{id}


###### Parameters:

Changed: `id` in `path`

###### Request:

Changed content type : `application/json`

###### Return Type:

Changed response : **200 OK**
> OK


* Changed content type : `*/*`

##### `POST` /api/students/save


###### Request:

Changed content type : `application/json`

###### Return Type:

Changed response : **200 OK**
> OK


* Changed content type : `*/*`

