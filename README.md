# Proyecto de Gestión de Alumnos 📚

Este proyecto es una aplicación escrita en **Java** que permite gestionar información de alumnos, materias y calificaciones. Está diseñado siguiendo principios de orientación a objetos y cuenta con pruebas unitarias utilizando **JUnit 5** y **Mockito** para garantizar su correcto funcionamiento.

---

## **Características**

1. **Gestión de Alumnos:**
   - Crear nuevos alumnos con datos como RUT, nombre y apellido.
   - Listar todos los alumnos registrados.

2. **Gestión de Materias:**
   - Agregar materias y calificaciones/notas para cada alumno.
   - Ver las materias asociadas a un alumno específico.

3. **Cálculo de Promedios:**
   - Calcula el promedio de notas por materia.

4. **Exportación de Datos (Opcional):**
   - Exportar la información de alumnos y calificaciones a un archivo `.txt`.

5. **Pruebas Unitarias:**
   - Pruebas de funcionalidades clave como la creación de alumnos, la asociación de materias y la validación de excepciones.

---

## **Requisitos**

### **Tecnologías Utilizadas**
- **Java 21**
- **Maven** (para gestionar dependencias y ejecutar pruebas)
- **JUnit 5** (para pruebas unitarias)
- **Mockito** (para simular comportamientos en las pruebas)

### **Dependencias**
Asegúrate de incluir estas dependencias en tu archivo `pom.xml`:
```xml
<dependencies>
    <!-- JUnit 5 -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.9.1</version>
        <scope>test</scope>
    </dependency>

    <!-- Mockito -->
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <version>5.14.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```
---

## **Estructura del Proyecto**
```tree
colegioNotas/
├── exports/
│   └── promedios.txt        # Archivo de exportación de promedios
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org.example/
│   │   │       ├── model/    # Modelos de datos (Alumno, Materia, etc.)
│   │   │       ├── service/  # Servicios para lógica de negocio
│   │   │       ├── util/     # Utilidades generales (colores, scanner, etc.)
│   │   │       └── view/     # Interfaz de usuario en consola
│   │   │           └── App   # Punto de entrada principal
│   ├── test/
│       ├── java/
│       │   └── org.example/
│       │       └── service/  # Pruebas unitarias de servicios
├── target/                  # Carpeta generada por Maven
├── pom.xml                  # Archivo de configuración de Maven
└── .gitignore               # Archivos ignorados por Git


```
---

## **Autor**

Claudio Cabrera

Desarrollador y Estudiante Bootcamp Java 🚀.

