# Spring boot application + PostgreSQL
Base application made with spring boot and postgres to serve as example.

## Starting Application using Docker Compose
Download the application using git:  

```shell
$ git clone https://github.com/DanielMachadoVasconcelos/base-application.git
$ cd /base-application
```

Build the application using maven
```shell
$ mvn clean install
```

Start the database, and the application, using Docker Compose:
```shell
$ docker-compose up
```

## Initial database contents
Any files in the `initdb` directory will be used to initialize the database
when the Postgres container is first started. The sample `init.sql` file
shows how to create a database called `sampledb`.

## Application running
The compose file will run the spring boot application.
It will listen on your local system's port 8888.

Execute the command bellow to fetch the application endpoint:
```shell
$ curl --location --request GET 'http://localhost:8888/summary'
```

