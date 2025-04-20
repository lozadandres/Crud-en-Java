# Sistema de Gestión de Clientes (CRUD)

Este proyecto es una aplicación de escritorio desarrollada en Java que implementa las operaciones CRUD (Crear, Leer, Actualizar y Eliminar) para la gestión de clientes. La información se almacena en un archivo CSV y cuenta con una interfaz gráfica de usuario.

<img src="https://media.licdn.com/dms/image/v2/D4E2DAQEPZ4E5Qh11Ig/profile-treasury-image-shrink_800_800/B4EZZEpxD6HEAY-/0/1744908522124?e=1745514000&v=beta&t=6XfOI1QI4CcnPhjeT-M6G6G3AAF84q9twzEjXkixnFA" width="100%" height="500" alt="App"/>

## Características

- Gestión completa de clientes (CRUD)
- Almacenamiento de datos en archivo CSV
- Interfaz gráfica de usuario intuitiva
- Validación de datos

## Estructura del Proyecto

- `src/crudarchivos/`
  - `Cliente.java`: Clase modelo para los datos del cliente
  - `ClienteServicios.java`: Servicios para operaciones CRUD
  - `CrudArchivos.java`: Clase principal de la aplicación
  - `FormularioCliente.java`: Interfaz de formulario
  - `VentanaPrincipal.java`: Ventana principal de la aplicación

## Datos del Cliente

La aplicación maneja los siguientes datos para cada cliente:
- Cédula (Identificador único)
- Nombre
- Teléfono

## Requisitos

- Java JDK 8 o superior
- NetBeans IDE (recomendado para desarrollo)

## Instalación

1. Clonar el repositorio:
```bash
git clone https://github.com/lozadandres/Crud-en-Java.git
```
2. Abrir el proyecto en NetBeans
3. Compilar y ejecutar la aplicación

## Uso

1. Ejecutar la aplicación
2. Usar la interfaz gráfica para:
   - Agregar nuevos clientes
   - Ver la lista de clientes
   - Actualizar información de clientes existentes
   - Eliminar clientes

## Almacenamiento

Los datos se almacenan en un archivo `Clientes.csv` en el directorio raíz del proyecto, utilizando punto y coma (;) como separador de campos.
