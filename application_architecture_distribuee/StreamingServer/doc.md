Diagramme de séquence : 
https://drive.google.com/file/d/1obrmBH6uTyVcCgUCJaXJWm31FLRJ8bIX/view

## Composants

MongoDB
-> authorized_managers
-> tokens

Python serveur

## Requête de token par un manager de server de streaming

Un gestionnaire de serveurs de streaming ne peut demander l'attribution d'un token uniquement si le serveur de streaming connait la clé d'API passée dans la requête.

Un token est attibué à une url, soit un fichier, si le token n'est pas bon, la lecture ne fonctionnera pas.

Une interface WEB doit pouvoir permettre la création de nouvelles clés d'API
