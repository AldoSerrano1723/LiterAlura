# 📚 LiterAlura - Catálogo de Libros

**LiterAlura** es una aplicación de consola desarrollada en Java con Spring Boot que permite buscar libros, consultar información sobre autores y filtrar resultados mediante el consumo de la API externa [Gutendex](https://gutendex.com/). Además, persiste la información consultada en una base de datos local PostgreSQL para crear un catálogo personalizado.

## 🚀 Funcionalidades

El proyecto cuenta con un menú interactivo en consola que permite realizar las siguientes operaciones:

1. **Buscar libro por título:** Consulta la API de Gutendex, muestra los datos del libro y, si no existe en la base de datos local, lo guarda junto con su autor.
2. **Listar libros registrados:** Muestra todos los libros que han sido guardados previamente en la base de datos local.
3. **Listar autores registrados:** Muestra la lista de autores guardados en la base de datos.
4. **Listar autores vivos en un determinado año:** Permite ingresar un año y muestra los autores que estaban vivos en esa fecha (basado en su año de nacimiento y fallecimiento).
5. **Listar libros por idioma:** Filtra los libros registrados por su idioma (ES, EN, FR, PT, etc.).

## 🛠️ Tecnologías Utilizadas

* **Java 17**: Lenguaje de programación principal.
* **Spring Boot 3**: Framework para el desarrollo de la aplicación.
* **Spring Data JPA**: Para la persistencia de datos y manejo de repositorios.
* **PostgreSQL**: Base de datos relacional.
* **Maven**: Gestor de dependencias.
* **Jackson**: Para la deserialización de datos JSON provenientes de la API.
* **Gutendex API**: Fuente de datos externa de libros.

## 📂 Estructura del Proyecto

El código está organizado en los siguientes paquetes:

* `model`: Contiene las Entidades (`Libro`, `Autor`) y los Records (DTOs) para mapear los datos de la API (`DatosLibro`, `DatosAutor`, `RespuestaAPI`).
* `repository`: Interfaces que extienden de `JpaRepository` para la comunicación con la base de datos (`LibroRepository`, `AutorRepository`).
* `service`: Lógica para el consumo de API (`ConsumoAPI`) y conversión de datos (`ConvertirDatos`).
* `principal`: Clase que contiene la lógica del menú y la interacción con el usuario (`Principal`).

## ⚙️ Configuración y Ejecución

### Prerrequisitos

1. Tener instalado **Java 17**.
2. Tener instalado **PostgreSQL**.
3. Tener **Maven** instalado (o usar el wrapper incluido `mvnw`).

### Pasos para ejecutar

1. **Clonar el repositorio:**
```bash
git clone https://github.com/tu-usuario/LiterAlura.git

```


2. **Configurar la Base de Datos:**
Crea una base de datos en PostgreSQL llamada `literalura`.
```sql
CREATE DATABASE literalura;

```


3. **Configurar Credenciales:**
Abre el archivo `src/main/resources/application.properties` y verifica la conexión. Asegúrate de poner tu usuario y contraseña de PostgreSQL:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=tu_usuario_postgres
spring.datasource.password=tu_contraseña_postgres

```


4. **Ejecutar la aplicación:**
Puedes ejecutarla desde tu IDE (IntelliJ IDEA, Eclipse) corriendo la clase `LiterAluraApplication.java` o mediante la terminal:
```bash
./mvnw spring-boot:run

```



## 🔍 Ejemplo de Uso

Al iniciar la aplicación, verás el siguiente menú en la consola:

```text
----- MENU PRINCIPAL -----

1- BUSCAR LIBRO POR TITULO
2- LISTA DE TODOS LOS LIBROS
3- LISTA DE AUTORES
4- LISTADO DE AUTORES VIVOS EN DETERMINADO AÑO
5- LISTADO DE LIBROS POR IDIOMAS
9- SALIR

INGRESE EL NUMERO DE UNA DE LAS OPCIONES:

```

Simplemente ingresa el número de la opción deseada y sigue las instrucciones en pantalla.

---

Hecho con ☕ y Java por Aldo Antonio Serrano Ramirez para el desafío de Alura Latam.
