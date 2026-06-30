# Curso Spring Boot

Repositorio con varios proyectos de aprendizaje en Spring Boot. Si quieres arrancar el proyecto principal de este
repositorio, ahora mismo el foco está en `portfolio/`.

## Portfolio: arranque rápido

### Requisitos

- Java 21
- Maven 3.9+ (o usar `./mvnw`)
- Servidor PostgreSQL 15+ (local o remoto)

### 1. Entrar en el proyecto

Desde la raíz del repositorio:

```bash
cd portfolio
```

### 2. Crear el archivo `.env`

Copia la plantilla incluida:

```bash
cp .env.example .env
```

Después, completa tus credenciales de PostgreSQL en `.env`:

```env
PG_URL_DATABASE=jdbc:postgresql://YOUR_PG_HOST/YOUR_PG_DATABASE?sslmode=require&channelBinding=require
PG_USER=YOUR_PG_USER
PG_PASSWORD=YOUR_PG_PASSWORD
```

> `application.properties` importa este archivo con `spring.config.import=optional:file:.env[.properties]`.

### 3. Arrancar la aplicación

Con Maven Wrapper:

```bash
./mvnw spring-boot:run
```

O, si tienes Maven instalado de forma global:

```bash
mvn spring-boot:run
```

### 4. Abrir la aplicación

Cuando arranque correctamente, estará disponible en:

```text
http://localhost:8080
```

## Estructura rápida del repositorio

- `portfolio/` — aplicación principal del portfolio
- `cv-springboot/` — ejercicios y proyecto adicional
- `spring-boot-junit-console-maven/` — pruebas y ejemplos con JUnit
- `spring-boot-learning-mockito/` — prácticas con Mockito
- `spring-boot-validator/` — ejemplos de validación

## Notas

- El archivo `.env` es local y no debe subirse al repositorio.
- La plantilla versionada es `.env.example`.
- Más adelante se documentará por separado la parte de API REST dentro de `portfolio/`.

## Curso base

[Java: Spring Boot - Guía definitiva](https://www.udemy.com/course/java-spring-boot-guia-definitiva/)
