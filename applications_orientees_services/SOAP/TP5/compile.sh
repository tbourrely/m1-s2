#!/bin/zsh
asadmin undeploy SOAPTP5
javac -d WEB-INF/classes HelloWorld.java HelloWorldInterface.java
wsgen -cp WEB-INF/classes:$CLASSPATH -d WEB-INF/classes -wsdl tbo.soap.tp5.HelloWorld
jar cvf SOAPTP5.war WEB-INF/* index.jsp
asadmin deploy SOAPTP5.war