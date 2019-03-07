| Thomas Bourrely | Raphael Loussinian |
|-|-|

# Tests

## T1

Un mot de passe est nécessaire. Il correspond au nom du service en minuscule.

1. Se connecter sans mot de passe
2. Se connecter avec un mot de passe aléatoire

## T2

Le budget et les stocks sont réinitialisés à chaque connexion.
Budget initial : 100€.
Stocks : 
* 50
* 60
* 70
* 80

1. Vider les stocks de chaque produit
2. Se deconnecter
3. Vérifier que les stocks sont réinitialisés en faisant une nouvelle commande
4. Vérifier que le budget est bien de 100€

## T3

Si un ou plusieurs éléments ne sont plus en stock, cela est signalé et la commande **n'est pas** validée.

Pour chaque produit : 
1. Vider le stock
2. Refaire une commande
3. Valider le fait qu'une erreur s'affiche et que la commande n'est pas validée
4. Se deconnecter, recommencer avec le produit suivant

## T4

Le budget restant après commande correspond au budget avant commande moins le montant de la commande.

1. Prendre 10 de chaque produit
2. Valider la commande
3. Le budget restant doit correspondre à 100 - 113 = -13

## T5

Il est possible d'effectuer plusieurs commandes.

1. Effectuer une commande non valide
2. Il doit être possible de refaire une commande
3. Se deconnecter
4. Effectuer une commande valide
5. Il doit etre possible de refaire une commande

# Rapport de tests

## 0.1.1

### T1

:x:

Il est possible de se connecter avec n'importe quel mot de passe, y compris aucun.

### T2

:white_check_mark:

### T3

:x:

Lorsque l'on sélectionne uniquement Crayon Rouge le message d'erreur affiche Crayon Bleu.

Lorsque l'on sélectionne Crayon Bleu le message affiche Bloc de Bureau.

Lorsque l'on sélectionne Bloc de Bureau le message affiche Ramette Papier.

La ramette papier n'atteint jamais la limite du stock.

### T4

:x:

Lorsque l'on sélectionne 10 de chaque, le budget restant est -1700 au lieu de -13.

### T5

:white_check_mark:

## 0.1.2

### T1

:white_check_mark:

### T2

:white_check_mark:

### T3

:x:

Lorsque plusieurs éléments de la commande ne sont plus en stock. Seul le dernier choisi apparait dans le message.
Par exemble si on choisit Crayon Rouge et Crayon Bleu (les deux ne sont plus en stock), seul Crayon Bleu sera indiqué comme non en stock.

### T4

:x:

Le budget restant est de -10 au lieu de -13.

### T5

:white_check_mark:

## 0.1.3

### T1

:white_check_mark:

### T2

:white_check_mark:

### T3

:x:

Lorsque plusieurs éléments de la commande ne sont plus en stock. Seul le dernier choisi apparait dans le message.
Par exemble si on choisit Crayon Rouge et Crayon Bleu (les deux ne sont plus en stock), seul Crayon Bleu sera indiqué comme non en stock.

### T4

:x:

Le budget obtenu après avoir sélectionné 10 de chaque produit est -2100 au lieu de -13.

### T5

:x:

Après une commande avec des produits qui ne sont plus en stock, il n'est pas possible de refaire une commande. Alors que c'est possible si il est valide.

## 0.1.4

### T1

:white_check_mark:

### T2

:white_check_mark:

### T3

:white_check_mark:

### T4

:white_check_mark:

### T5

:white_check_mark:

# Tableau synthèse

(D) Test de détection de défauts
(C) Test de confirmation de correction des défauts
(R) Test de régression

| Test | 0.1.1 | 0.1.2 | 0.1.3 | 0.1.4 |
|------|-------|-------|-------|-------|
|  T1  | D :x: | C :white_check_mark: | R :white_check_mark: | R :white_check_mark: |
|  T2  | D :white_check_mark: | R :white_check_mark: | R :white_check_mark: | R :white_check_mark: |
|  T3  | D :x: | C :x: | C :x: | C :white_check_mark: |
|  T4  | D :x:| C :x: | C :x: | C :white_check_mark: |
|  T5  | D :white_check_mark: | R :white_check_mark: | R :x: | C :white_check_mark: |

# Enchainement des pages
**SVG ! Ne pas activer l'option sanitize**

<!DOCTYPE svg PUBLIC "-//W3C//DTD SVG 1.1//EN" "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd">
<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" width="384px" height="164px" viewBox="-0.5 -0.5 384 164" content="&lt;mxfile modified=&quot;2019-03-06T14:43:24.622Z&quot; host=&quot;www.draw.io&quot; agent=&quot;Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36&quot; version=&quot;10.3.5&quot; etag=&quot;wztOGPMgZeE1ZfFoXM8d&quot; type=&quot;device&quot;&gt;&lt;diagram id=&quot;d5AGn8eAC9jbYOIk4LAr&quot;&gt;7VjLjtsgFP2abCtsIJMsO55pu6lUKYu2S2QzMaoTLEwSu19fHMA2kFczk8lEyibyPdzL45xjwBnBZFF/FaTMv/OMFqMYZPUIPo3ieBpN1G8LNBrA06kG5oJlGop6YMb+UgMCg65YRisnUXJeSFa6YMqXS5pKByNC8I2b9sILd9SSzGkAzFJShOhPlslcoxMMevwbZfPcjhwB07IgNtkAVU4yvhlA8HkEE8G51E+LOqFFy53lRdd92dPaTUzQpTylINYFa1KsqDsx2djVqgpFrAoeNzmTdFaStG3ZKGkVlstFoaJIPZKq1Gy/sJqqAR4rKfgfmvCCi21XcDxOEsWFbbHkIYWYiVAhab13MVFHkbIW5QsqRaNSbMHUTN7YKoYm3vQiWY3ygT4WI8YW867nnjn1YMjbTSQMiEzq22USgusxiUIms9tlEkXXYxIHTM5ul0gcX4/IcUBkyOMy+9yeLCpKC1JVLHXp0wU08w6Wiq9ESp29WBIxp9LZVU5g6UQWBC2IZGt3EruoMSP84EwNvHeHhT67ej2manjueB3FyOsIeB1pFoKOtkp1yz5JvId3EQ+G4qG7eK8Wb/Iu4qFQPHwX79XiTY+Ll67Euj1NtkodU1IpIZpfKgCfsA1/D9ue2jWCLmpsVDM5KFNRV6We+6I2sDUnuAaHronvrnE7UnqSZpBWtgnV/gkj7I3zAA7Py89HR/IjvHMdvcn1jM+1vL3tvZ3njXejgXPBQefue0vAkbfkvEPuo91Qgi+X+FzH+x+T6DKOtw7vxgGHHQy9e7B9xd/MwdGFHOzuvlfz8I5d+6Nd1DD0LBGd6WE88bwyvoyHMfo/DyN/Xq/zsAr7f8x0ev+3I3z+Bw==&lt;/diagram&gt;&lt;/mxfile&gt;"><defs/><g><ellipse cx="27" cy="61" rx="25" ry="25" fill="#ffffff" stroke="#66cc00" stroke-width="4" pointer-events="none"/><g transform="translate(22.5,54.5)"><switch><foreignObject style="overflow:visible;" pointer-events="all" width="7" height="12" requiredFeatures="http://www.w3.org/TR/SVG11/feature#Extensibility"><div xmlns="http://www.w3.org/1999/xhtml" style="display: inline-block; font-size: 12px; font-family: Helvetica; color: rgb(0, 0, 0); line-height: 1.2; vertical-align: top; width: 7px; white-space: nowrap; overflow-wrap: normal; text-align: center;"><div xmlns="http://www.w3.org/1999/xhtml" style="display:inline-block;text-align:inherit;text-decoration:inherit;">0</div></div></foreignObject><text x="4" y="12" fill="#000000" text-anchor="middle" font-size="12px" font-family="Helvetica">0</text></switch></g><ellipse cx="137" cy="61" rx="25" ry="25" fill="#ffffff" stroke="#66cc00" stroke-width="4" pointer-events="none"/><g transform="translate(128.5,54.5)"><switch><foreignObject style="overflow:visible;" pointer-events="all" width="15" height="12" requiredFeatures="http://www.w3.org/TR/SVG11/feature#Extensibility"><div xmlns="http://www.w3.org/1999/xhtml" style="display: inline-block; font-size: 12px; font-family: Helvetica; color: rgb(0, 0, 0); line-height: 1.2; vertical-align: top; width: 15px; white-space: nowrap; overflow-wrap: normal; text-align: center;"><div xmlns="http://www.w3.org/1999/xhtml" style="display:inline-block;text-align:inherit;text-decoration:inherit;">Cx</div></div></foreignObject><text x="8" y="12" fill="#000000" text-anchor="middle" font-size="12px" font-family="Helvetica">Cx</text></switch></g><ellipse cx="247" cy="61" rx="25" ry="25" fill="#ffffff" stroke="#66cc00" stroke-width="4" pointer-events="none"/><g transform="translate(238.5,54.5)"><switch><foreignObject style="overflow:visible;" pointer-events="all" width="15" height="12" requiredFeatures="http://www.w3.org/TR/SVG11/feature#Extensibility"><div xmlns="http://www.w3.org/1999/xhtml" style="display: inline-block; font-size: 12px; font-family: Helvetica; color: rgb(0, 0, 0); line-height: 1.2; vertical-align: top; width: 17px; white-space: nowrap; overflow-wrap: normal; text-align: center;"><div xmlns="http://www.w3.org/1999/xhtml" style="display:inline-block;text-align:inherit;text-decoration:inherit;">Cd</div></div></foreignObject><text x="8" y="12" fill="#000000" text-anchor="middle" font-size="12px" font-family="Helvetica">Cd</text></switch></g><ellipse cx="357" cy="61" rx="25" ry="25" fill="#ffffff" stroke="#66cc00" stroke-width="4" pointer-events="none"/><g transform="translate(351.5,54.5)"><switch><foreignObject style="overflow:visible;" pointer-events="all" width="9" height="12" requiredFeatures="http://www.w3.org/TR/SVG11/feature#Extensibility"><div xmlns="http://www.w3.org/1999/xhtml" style="display: inline-block; font-size: 12px; font-family: Helvetica; color: rgb(0, 0, 0); line-height: 1.2; vertical-align: top; width: 9px; white-space: nowrap; overflow-wrap: normal; text-align: center;"><div xmlns="http://www.w3.org/1999/xhtml" style="display:inline-block;text-align:inherit;text-decoration:inherit;">S</div></div></foreignObject><text x="5" y="12" fill="#000000" text-anchor="middle" font-size="12px" font-family="Helvetica">S</text></switch></g><path d="M 52 61 L 105.63 61" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 110.88 61 L 103.88 64.5 L 105.63 61 L 103.88 57.5 Z" fill="#000000" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 162 61 L 215.63 61" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 220.88 61 L 213.88 64.5 L 215.63 61 L 213.88 57.5 Z" fill="#000000" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 272 61 L 325.63 61" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 330.88 61 L 323.88 64.5 L 325.63 61 L 323.88 57.5 Z" fill="#000000" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 357 86 Q 262 176 162 161 Q 62 146 44.5 126 Q 27 106 27 92.37" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 27 87.12 L 30.5 94.12 L 27 92.37 L 23.5 94.12 Z" fill="#000000" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 155 43 Q 182 6 157 1 Q 132 -4 136.21 29.68" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 136.86 34.89 L 132.52 28.38 L 136.21 29.68 L 139.47 27.51 Z" fill="#000000" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 357 36 Q 352 6 322 1 Q 292 -4 251.76 31.77" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 247.84 35.26 L 250.74 27.99 L 251.76 31.77 L 255.39 33.22 Z" fill="#000000" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/></g></svg>

| Etat | Transition | Etat |
|------|------------|------|
|   0  | Lancement  | Connexion |
| Connexion | Connexion invalide | Connexion |
| Connexion | Connexion validée | Commande |
| Commande | Commande valide / non valide | Synthese |
| Synthese | Nouvelle commande | Commande |
| Synthese | Deconnexion | 0 |
