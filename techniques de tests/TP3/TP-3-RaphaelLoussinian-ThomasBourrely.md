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

Non validation côté back.

## Cross-Site Scripting (XSS) > Cross Site Request Forgery (CSRF)

### Éléments utilisés

### Code à l'origine de la vulnérabilité

## Session Management Flaws > Spoof an Authentication Cookie

### Éléments utilisés

### Code à l'origine de la vulnérabilité

## Malicious Execution > Malicious File Execution

### Éléments utilisés

### Code à l'origine de la vulnérabilité