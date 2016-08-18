Java Sample Application
------------------------------------------------------------
A sample application with user login/logout with sessions and MySQL database.

Used technologies:
- Maven
- Docker (tomcat, nginx and mysql)
- Java Servlets
- JTwig (template engine)


Build and Deploy
------------------------------------------------------------

    mvn package tomcat7:deploy


Docker - Useful commands
------------------------------------------------------------

    # Start servers
    docker-compose up -d
    # Stop servers
    docker-composer stop
    # Run command inside a specific container
    docker-compose run java_tomcat_1 YOUR-COMMAND
    # Access container via bash
    docker exec -it java_tomcat_1 bash
