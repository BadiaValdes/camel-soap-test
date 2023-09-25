# Spring boot Camel Soap Test

Este proyecto surge como parte de la práctica de Apache Camel con Spring Boot. La idea es exponer mediante soap una serie de endpoints para capturar y mostrar datos. En este caso estamos utilizando un WSDL que simula la creación de generos de videojuegos y juegos.

## Paquetes y versiones

- Java 17
- Spring Boot 3.1.4
- Apache Camel 4.0.0
- CXF 4.0.0

## Como iniciar el proyecto

- Clonar proyecto

`git clone git@github.com:BadiaValdes/camel-soap-test.git`

- Instalar dependencias

`mvn clean install -DskipTests`

- Generar clases desde WSDL

> En este proyecto utilizamos el plugin `cxf-codegen-plugin` para generar clases JAVA desde un WSDL.

`mvn clean compile -e`

## Estado actual

Actualmente solo se exponen 2 rutas principales. La primera se encarga de la creación de generos y la segunda de obtenerlo mediante el id. Más adelante se agregarán más funcionalidades, pero esto es lo que tenemos por el momento.

## Acceso a base de datos

Para este proyecto estamos utilizando jdbc, por lo que cada vez que se inicie el proyecto se borrarán las tablas; por lo que despidete de los datos que tengas guardados.

> Ojo, si no crean la base de datos `soap_test` este proyecto no va a funcionar.


## Palabras finales

Espero que este pequeño tutorial haya sido de su agrado. Si encuentran algún error o demora, no duden enviar un mensaje por github para realizar los cambios pertinentes. No dejen de aprender y nos vemos pronto.