<%@ page contentType="text/html;charset=ISO-8859-1" language="java" %>
<%@ page import="tbo.soap.tp5.HelloWorldService_Service" import="tbo.soap.tp5.HelloWorldService" %>

<%
//String name = request.getParameter("name");
//String result = "NULL";

//if (name == null || name.length() == 0) {
 //   result = "INVALID PARAM";
//} else {
    HelloWorldService_Service service = new HelloWorldService_Service();
    HelloWorldService IBonjour = service.getHelloWorldPort();
    String result = IBonjour.helloWorld("name");   
//}

%>

<%= result %>
<form action="">
    <input name="name" type="text"/>
    <input type="submit"/>
</form>