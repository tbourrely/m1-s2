<%
String username = request.getParameter("username");
session.setAttribute("username", username);
%>

<html>
    <head></head>
    <body>
        <h1>Saisie d'informations pour <%= username %></h1>
        <form method="POST" action="/tp3/details.jsp">
            <label for="age">Age</label>
            <input type="number" name="age" id="age" required>
            <label for="city">Ville</label>
            <input type="text" name="city" id="city" placeholder="ville" required>
            <input type="submit" value="OK">
        </form>
    </body>
</html>