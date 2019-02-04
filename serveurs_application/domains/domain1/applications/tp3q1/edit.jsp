<%
if (session.getAttribute("username") == null) {
    response.sendRedirect(request.getContextPath() + "/index.jsp");
}

String username = (String)session.getAttribute("username");
Integer age = (Integer)session.getAttribute("age");
String city = (String)session.getAttribute("city");

String formURL = response.encodeURL("/tp3/editsession");

%>

<html>
    <body>
        <form method="POST" action="<%= formURL %>">
            <input type="text" name="username" value="<%= username %>">
            <input type="number" name="age" value="<%= age %>">
            <input type="text" name="city" value="<%= city %>">
            <input type="submit">
        </form>
    </body>
</html>