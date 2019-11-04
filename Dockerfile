FROM tomcat:8.5.4-jre8
ADD target/servlet-sample-1.0.0.war /usr/local/tomcat/webapps/**.war
