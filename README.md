Aplicación de Gestión de Vehículos (Actividad29Agosto)

Este proyecto es una aplicación Android desarrollada para gestionar vehículos. Los usuarios pueden registrar, listar, editar y eliminar vehículos mediante una API RESTful. La aplicación realiza operaciones de CRUD (Crear, Leer, Actualizar, Eliminar) sobre los vehículos a través de solicitudes HTTP utilizando la biblioteca Volley.

Características

Registrar Vehículos: Permite al usuario ingresar los detalles de un vehículo (marca, modelo, color, precio, placa).

Listar Vehículos: Muestra todos los vehículos registrados en un RecyclerView.

Editar Vehículos: Permite editar los detalles de un vehículo existente.

Eliminar Vehículos: Permite eliminar un vehículo registrado de la base de datos.

Tecnologías Utilizadas

Android Studio: Entorno de desarrollo para la creación de la aplicación.

Java: Lenguaje de programación utilizado para el desarrollo de la aplicación.

Volley: Biblioteca para realizar solicitudes HTTP (GET, POST, PUT, DELETE).

JSON: Formato de intercambio de datos para comunicar la aplicación con la API.

Requisitos

Android Studio: Necesitas tener Android Studio instalado para ejecutar este proyecto.

Acceso a Internet: La aplicación requiere acceso a Internet para interactuar con la API REST.

API Backend: La aplicación interactúa con una API REST que debe estar en funcionamiento. La URL base de la API está configurada en el archivo MainActivity.java.