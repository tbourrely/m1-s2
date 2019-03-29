| Thomas Bourrely | Raphael Loussinian |
|-|-|

# Leçons

## Access Control Flaws > Using an Access Control Matrix

Larry [User, Manager] a accès à la resource `Account Manager`.
Cependant Curly[Public, Manager] n'a pas accès.

Le problème vient donc des droits attribués au rôle `User`.

### Éléments utilisés

* énoncé

### Code à l'origine de la vulnérabilité

```java
62      private final static String[] roles = { "Public", "User", "Manager", "Admin" };
59      private final static String[] resources = { "Public Share", "Time Card Entry", "Performance Review",
60              "Time Card Approval", "Site Manager", "Account Manager" };

174      private List getResources(List rl)
175      {
176          // return the resources allowed for these roles
177          ArrayList<String> list = new ArrayList<String>();
178  
179          if (rl.contains(roles[0]))
180          {
181              list.add(resources[0]);
182          }
183  
184          if (rl.contains(roles[1])) <-- la liste contient le rôle 'User'
185          {
186              list.add(resources[1]);
187              list.add(resources[5]); <-- ERREUR !!!!
188          }
189  
190          if (rl.contains(roles[2]))
191          {
192              list.add(resources[2]);
193              list.add(resources[3]);
194          }
195  
196          if (rl.contains(roles[3]))
197          {
198              list.add(resources[4]);
199              list.add(resources[5]);
200          }
201  
202          return list;
203      }
```

## Code Quality > Discover Clues in the HTML

Identifiant : admin
Mot de passe : adminpw

### Éléments utilisés

* énoncé

### Code à l'origine de la vulnérabilité

```html
<!-- FIXME admin:adminpw  --><!-- Use Admin to regenerate database  -->
```

## Injection Flaws > String SQL Injection

Texte injecté : `';SELECT * FROM user_data;--`

### Éléments utilisés

* énoncé

### Code à l'origine de la vulnérabilité

```java
String query = "SELECT * FROM user_data WHERE last_name = '" + accountName + "'";
```

## Injection Flaws > Database Backdoors > Stage 1

Texte injecté : `101;UPDATE employee SET salary=10 WHERE userid=101`

### Éléments utilisés

* énoncé

### Code à l'origine de la vulnérabilité

```java
134                  userInput = SELECT_ST + userInput;
```

## Improper Error Handling > Fail Open Authentication Scheme

Outil utilisé : `Postman`
Il a fallu supprimer le champs `Password` de la requête.

### Éléments utilisés

* énoncé

### Code à l'origine de la vulnérabilité

```java
 85              } catch (Exception e)
 86              {
 87                  // The parameter was omitted. set fail open status complete
 88                  if (username.length() > 0 && e.getMessage().indexOf("not found") != -1)
 89                  {
 90                      if ((username != null) && (username.length() > 0))
 91                      {
 92                          makeSuccess(s);
 93                          return (makeUser(s, username, "Fail Open Error Handling"));
 94                      }
 95                  }
 96              }
```

## Parameter Tampering > Bypass HTML Field Restrictions

Outil utilisé : `Postman`
Il a fallu modifier tous les champs du formulaire de manière à envoyer des données non conformes aux restrictions HTML.

Valeurs utilisées : 
```text
select:123455
radio:123455
checkbox:123455
shortinput:123456
disabledinput:notdisabled
```

### Éléments utilisés

* énoncé

### Code à l'origine de la vulnérabilité

Non validation côté back-end.

## Cross-Site Scripting (XSS) > Cross Site Request Forgery (CSRF)

Le code ci-dessous a été inséré dans le textarea du formulaire.
L'ajout de l'image permet de faire une requete vers une URL malveillante, car elle est appelée lorsque le DOM est affiché.

```html
<img src="http://localhost:8080/WebGoat-5.4/attack?Screen=33&menu=900&transferFunds=4000" width="1px" height="1px">
```

### Éléments utilisés

* énoncé

### Code à l'origine de la vulnérabilité

## Session Management Flaws > Spoof an Authentication Cookie

Le but est de modifier la valeur du cookie `AuthCookie` afin d'être connecté en tant que l'utilisateur `Alice`.

Lorsque l'on se connecte avec les identifiants `webgoat:webgoat`, la valeur du cookie est : `65432ubphcfx`.

Lorsque l'on se connecte avec les identifiants `aspect:aspect`, la valeur du cookie est : `65432udfqtb`.

On peut constater que la valeur est proche pours les deux identifiants.

Après quelques essais, on peut constater que la valeur du cookie se base sur l'identifiant.
La valeur du cookie est `65432` + les lettres de l'identfiant décalée de 1 à l'envers.
```text
w -> x
e -> f
b -> c
g -> h
o -> p
a -> b
t -> u

cookie : 65432ubphcfx
```

On peut donc construire la valeur du cookie pour l'utilisateur cible.
```text
a -> b
l -> m
i -> j
c -> d
e -> f

cookie : 65432fdjmb
```

Une fois connecté avec un des deux identifiants fournis, il suffit de modifier le cookie pour qu'il ait la valeur : `65432fdjmb`. Suite à cela nous sommes connectés en tant qu'Alice.

### Éléments utilisés

* énoncé

### Code à l'origine de la vulnérabilité

## Malicious Execution > Malicious File Execution

Ici il s'agit d'uploader un fichier qui sera executé par le serveur. On profite du fait que le format du fichier n'est pas vérifié à l'upload.

1. Créer un fichier `.jsp` qui a le contenu suivant : 
```java
<%@ page import="java.io.File"%>

<%
        File file = new File("/Users/thomasbourrely/Documents/m1-s2/techniques de tests/TP3/apache-tomcat-7.0.59/webapps/WebGoat-5.4/mfe_target/guest.txt");
        file.createNewFile();
%>
```

2. Uploader ce fichier dans webgoat

3. Se rendre à l'adresse suivante afin d'executer le code java : `http://localhost:8080/WebGoat-5.4/uploads/malicious_file_exec.jsp`

### Éléments utilisés

* énoncé

### Code à l'origine de la vulnérabilité
