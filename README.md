# Proyecto Gestión de Contactos (CRUD) con Java y Spring (MVC, Boot, Security, Batch, REST ...)

Proyecto de ejemplo para comenzar con este framework.


## Servidor: Spring Boot

Deberás montar el proyecto en un IDE con un servidor Spring Boot o montar un servidor standalone donde configurar y desplegar el proyecto previamente compilado.


## Base de datos: MySQL

El proyecto está montado y configurado (application.yml) para trabajar con una BBDD pero podrás configurarlo y trabajar con cualquier otra BBDD.


## Estructura del proyecto backend

* **BackendApplication**: se trata de la clase base donde se configura el proyecto como aplicación de Spring Boot y además se establece la disponibilidad de servicios batch (schedule).

### *Component:*

* **ContactConverter**: se trata de convertir modelo a entidad y viceversa, controlando que las capas superiores no trabajen con entidades y trabajen con modelo. Con ello, entre otras cosas, podemos generar beans de modelo acotados de una entidad.

* **RequestTimeInterceptor**: se trata de un interceptor y conseguiremos controlar el instante antes de entrar a un controlador y en el instante anterior al return del mismo.

* **TaskComponent**: se trata de obtener un componente batch que se ejecute cada cierto tiempo. En este caso tenemos un método que se ejecuta cada 5 segundos.

* **TestCrypt**: se trata de una clase para hashear un password de usuario y poder probar nuestra aplicación. Se puede ejecutar, por ejemplo, con un Java Application y obtener el hash por consola para guardarlo en BBDD o implementar un servicio que lo guarde en BBDD.

### *Configuration:*

* **SecurityConfiguration**: se trata de configurar la parte de seguridad con Spring Security. Configuramos tanto la parte de http security para los recursos y los endpoints, como la parte del AuthenticationManagerBuilder para el cifrado del usuario.

* **WebMvcConfiguration**: se trata de configurar todo lo necesario para la parte de MVC. En este caso, se configura el interceptor creado en el paquete *Component*.

### *Constants:*

* **ViewConstants**: se declaran las vistas en constantes para tener centralizadas todas las vistas en una sola clase.

### *Controller:*

* **ContactController**: controlador del formulario de gestion de contactos.

* **LoginController**: controlador del formulario de login de usuarios.

* **RestController**: controlador de un servicio REST donde se fuerza el devolver un contacto en un JSON, a modo de ejemplo.

### *Entity:*

* **ContactEntity**: entidad de contacto, será el bean de acceso a BBDD. Con este bean podremos realizar el CRUD de contactos.

* **Log**: entidad de log, será el bean de acceso a BBDD. Con este bean guardaremos en BBDD todos los logs de peticiones y acciones de los usuarios.

* **User**: entidad de usuario, será el bean de acceso a BBDD. Con este bean tendremos acceso a la tabla de usuarios donde se guarda el user/pass y si está o no disponible.

* **UserRole**: entidad de rol de usuario, será el bean de acceso a BBDD. Con este bean obtendremos los roles de usuario para, por ejmplo, limitar acciones a ciertos roles de usuario como eliminar contactos si no eres administrador o similar.

### *Model:*

* **ContactModel**: modelo de la entidad de contacto. Con este bean podemos ocultar campos que no queremos que se trabaje con ellos, así tendremos un bean completo pero sin trabajar, por ejemplo, con el id del contacto.

* **UserCredentials**: modelo de la entidad de credenciales de usuario.

### *Repository:*

Todas nuestras interfaces Repository extenderán de JpaRepository para tener acceso a todos los métodos que nos ofrece JPA, además de añadirle los que necesitemos a medida.

* **ContactRepository**: repositorio de acceso a la entidad de contacto. Generamos un método para encontrar un registro por el campo id (identificador auto generado en la entidad).

* **LogRepository**: repositorio de acceso a la entidad de log.

* **UserRepository**: repositorio de acceso a la entidad de usuario. Generamos un método además encontrar un registro por el campo username (nombre de usuario).

### *Service:*

* **ContactService**: se trata de una interfaz donde se crearan los métodos del servicio de contacto.

* **ContactServiceImpl**: se trata de la implementación de la interfaz del servicio de contacto. Se hará uso del componente de conversión entre modelo y entidad de contactos, también se hará uso del repositorio para el acceso a datos.

* **UserServiceImpl**:  es la implementación de UserDetailsService de Spring Security para toda la gestión de las credenciales de usuario en el login y que quede resuelto con el módulo de Spring Security.


## Estructura del proyecto frontend

Este proyecto está montado con Thymeleaf para las vistas, CSS y png para los recursos de imágenes.

### *Resources / static / css:*

* **login**:  clase css para estilo de formularios.

* **style**:  clase css para estilo general del body y de las cabeceras h1.

### *Resources / static / imgs:*

* **403**:  recurso estático para el error 403.

* **404**:  recurso estático para el error 404.

* **500**:  recurso estático para el error 500.

### *Resources / templates:*

* **contactform**:  formulario HTML de contacto.

* **contacts**:  formulario HTML de gestión de contactos.

* **login**:  formulario HTML de login de usuario.

### *Resources / templates / error:*

Spring es capaz de detectar las vistas de error si están en este FileSystem.

* **403**:  formulario HTML para el error 403.

* **404**:  formulario HTML para el error 404.

* **500**:  formulario HTML para el error 500.
