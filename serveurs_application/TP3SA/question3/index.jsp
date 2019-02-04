<%
String formUrl = response.encodeURL("/tp3/userdata.jsp");
%>


<html>
    <head></head>
    <body>
        <form method="POST" action="<%= formUrl %>">
            <input type="text" name="username" required>
            <input type="submit" value="Valider">
        </form>
    </body>
</html>