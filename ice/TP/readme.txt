Bourrely Thomas

= TP1 =

La définition slice réalisée dans le fichier 'ice/MP3Collection.ice' permet l'ajout, la suppression et la recherche d'une musique.

Cette interface est utilisée par :
- le serveur 'server.py'
- les clients 'client.js' et 'client.py'

Les languages d'implémentations choisis sont donc Python et JavaScript.


== Partage des musiques à un ensemble d'utilisateurs ==

La définition slice 'ice/Partage.ice' permet de répondre à cette question.


== Jouer un morceau référencé par un autre serveur ==

Diagrammes :
1) 'diagrams/Architecture.png' -> Architecture du système
2) 'diagrams/ICE Player Sequence Diagram' -> Diagramme de séquence du processus de lecture

=== Architecture (diagramme 1) === 

Le coordinateur est l'élément central de l'architecture. C'est lui qui fait le lien entre 
le mobile et les gestionnaires de librairies de musiques.
Il se charge trouver quel gestionaire de librairies peut fournir la musique demandée au client.

=== Processus de lecture (diagramme 2) ===

Dans un premier temps, le coordinateur valide le fait que le client soit bien authentifié.
Dans le cas où il l'est, le client peut demander à jouer une musique. Le coordinateur se charge
de trouver quel gestionnaire de librairie est à même de satisfaire la demande. Ce dernier
renvoi l'adresse vers laquelle le fichier est streamé.
Le client peut donc accèder au média grâce à l'url reçue.


= TP2 =

=== Y-a-t-il un intérêt à passer par un méta-serveur ? Que faire, dans ce cas, pour limiter le trafic sur le réseau ? ===

L'intérêt est de déléguer le processus de recherche de serveur pouvant répondre à la réquête au méta-serveur.

=== Est-ce qu'inclure la vidéo change quelque chose à l'application développée? ===

L'adaptation du système de streaming afin de supporter la vidéo. Cependant le système de recherche, ajout, suppression reste le même.

=== Quelle stratégie d'enregistrement des serveurs ? Comment faire pour découvrir à chaud de nouveaux serveurs ? Pour sécuriser l'accès aux serveurs ? ===

Les serveurs sont enregistrés dans une base de données, seul le méta-serveur y accède et donc connait la liste des serveurs.
Un serveur peut demander au méta-serveur d'être ajouté dans cette base de données.

La sécurisation peut être faite par l'ajout d'un système d'authentification au niveau du méta-serveur.
On peut très bien imaginer l'implémentation du protocole OAuth2.
