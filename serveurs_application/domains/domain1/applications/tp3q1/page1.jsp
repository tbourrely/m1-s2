<% 
    if (session.getAttribute("username") == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    String detailsURL = response.encodeURL("/tp3/details.jsp");
%>

<html>
<body>

<a href="<%= detailsURL %>">Details</a>
<br>


L'islandais est un cheval de selle de petite taille qui forme l'unique race chevaline originaire d'Islande. Ces animaux sont vraisemblablement les descendants directs des montures amenées en bateau par les Vikings lors de la colonisation de l'Islande. Les Islandais sont toujours restés très fiers de leurs chevaux qu'ils citent régulièrement dans leurs sagas. Les importations de chevaux sont interdites sur l'île depuis le xe siècle et de ce fait, l'islandais n'a pas subi de croisements depuis les années 900. Il resta très longtemps une race exclusive à l'île d'Islande et la sélection naturelle lui permit d'acquérir une grande résistance aux conditions climatiques en se contentant d'une nourriture pauvre.

Bien qu'il y ait une relation étroite entre ces chevaux et des poneys, surtout celtiques, les Islandais ont gardé le nom de « cheval » pour désigner leurs montures. Ces animaux sont caractérisés par leur taille réduite, leur grande robustesse et rusticité, leurs robes très variées et leur particularité de posséder fréquemment cinq allures, soit le tölt et l'amble en plus des trois allures habituelles du cheval. Leur utilisation est multiple puisqu'ils servent encore au gardiennage des moutons sur leur île d'origine. Des courses et des concours d'allures spéciaux leur sont totalement réservés, ils peuvent également être élevés pour leur viande bien qu'ils soient employés comme montures de loisir.

L'islandais ne fut exporté que tardivement, au xxe siècle. Depuis, son succès en a fait une race représentée par des associations dans 19 pays, particulièrement en Europe de l'Ouest, en Scandinavie et en Amérique du Nord. Près de la moitié des chevaux islandais exportés se trouvent en Allemagne.
</body>
</html>