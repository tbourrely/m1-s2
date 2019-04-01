<%@ page contentType="text/html;charset=ISO-8859-1" language="java" %> 
<%@ page import="tbo.soap.tp5.HelloWorld" %>

<%
    String name = request.getParameter("name");
    HelloWorld test = new HelloWorld();  
    
    String output = "NULL";

    if (name != null && name.length() != 0) {
        output = test.helloWorld(name);    
    }

%>

<%= output %>

<form>
    <input name="name" type="text">
    <input type="submit">
</form>