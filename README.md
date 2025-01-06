# TBD_Gupo3_Lab3

# Integrantes
* [Aylin Castillo](https://github.com/cvstleee)
* [Francisco Sanchez](https://github.com/Mellowzhong)
* [Alonso Sanhueza](https://github.com/AalSaa)
* [Victor Varas](https://github.com/victorvaras)
* [Agustin Vera](https://github.com/Agustin-Vera)

# Contenido
El repositorio incluye las siguientes carpetas principales:
* [Backend](https://github.com/Agustin-Vera/TBD_Gupo3_Lab3/tree/main/backend)
* [Frontend](https://github.com/Agustin-Vera/TBD_Gupo3_Lab3/tree/main/frontend)

# Tecnologías requeridas
* MongoDB v8.0.4
* JDK v17.0.13
* Spring Boot (Gradle + Java 17) v3.3.7
* Sql2o v1.6.0
* Spring Security v6.2.3
* Java-jwt v4.4.0
* Node.js v20.18.0 (LTS)
* Vite v5.4.8
* Vue v3.5.10
* Axios v1.7.7
* Google Maps JS API v1.16.8
* Intellij IDEA Ultimate 2024.2

# Pre-instalación
Asegúrese de tener instalados MongoDB 8, JDK 17, Node.js e IntelliJ IDEA. Se recomienda utilizar las versiones mencionadas en las tecnologías requeridas.

# Instrucciones de instalación
1. Clonar el repositorio. Puede hacerlo usando el siguiente comando:
   ```sh
   git clone https://github.com/Agustin-Vera/TBD_Gupo3_Lab3
   ```
2. Crear base de datos:
  * Para Windows / Linux:
    - Abrir cmd
    - Ejecutar estos comandos:
       ```sh
       mongod
       mongosh
      ```
    - Dentro de Mongo Shell ejecutar este comando:
       ```sh
       use e-commerce-db
      ```
3. Abrir IntelliJ IDEA y seleccionar la opción "Abrir proyecto". Buscar la dirección del repositorio y seleccionar la carpeta backend.
4. Buscar el archivo TbdBackendApplication.java e iniciarlo.
5. Abrir una terminal en la carpeta frontend y ejecutar los siguientes comandos:
  ```sh
  npm install
  ```
  Luego:
  ```sh
  npm run dev
  ```
6. Ir a su navegador web e ingresar la URL http://localhost:3000/
   
Con todos estos pasos, ya puede utilizar la aplicación web.

