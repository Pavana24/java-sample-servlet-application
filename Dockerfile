FROM tomcat:8.5.4-jre8
ADD target/LoginWebApp.war /usr/local/tomcat/webapps/LoginWebApp.war
