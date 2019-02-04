<%

String username = (String)session.getAttribute("username");

Integer age = 0;
String city = "";

if (session.getAttribute("age") != null) {
    age = (Integer)session.getAttribute("age");
} else if(request.getParameterMap().containsKey("age")) {
    age = Integer.parseInt(request.getParameter("age"));
}

if (session.getAttribute("city") != null) {
    city = (String)session.getAttribute("city");
} else if(request.getParameterMap().containsKey("city")) {
    city = request.getParameter("city");
}



session.setAttribute("age", age);
session.setAttribute("city", city);


String editURL = response.encodeURL("/tp3/edit.jsp");
String page1URL = response.encodeURL("/tp3/page1.jsp");
String quitURL = response.encodeURL("/tp3/quitsession");

%>

<html>
    <head></head>
    <body>
        <table>
            <tr>
                <td>username</td>
                <td><%= username %></td>
            </tr>
            <tr>
                <td>age</td>
                <td><%= age %></td>
            </tr>
            <tr>
                <td>city</td>
                <td><%= city %></td>
            </tr>
        </table>
        
        <a href="<%= editURL %>">Editer le profil</a><br>
        <a href="<%= page1URL %>">Page 1</a><br>
        <a href="<%= quitURL %>">Quitter la session</a>
    </body>
</html>