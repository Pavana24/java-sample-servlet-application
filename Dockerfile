FROM tomcat:8.0-alpine
COPY /target/servlet-sample-1.0.0.war /usr/local/tomcat/webapps/myapp.war
