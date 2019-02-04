<%
String username = (String)session.getAttribute("username");
Integer age = (Integer)session.getAttribute("age");
String city = (String)session.getAttribute("city");

%>

<html>
    <body>
        <form method="POST" action="/tp3/editsession">
            <input type="text" name="username" value="<%= username %>">
            <input type="number" name="age" value="<%= age %>">
            <input type="text" name="city" value="<%= city %>">
            <input type="submit">
        </form>
    </body>
</html>