Les utilisateurs sont :
- "Client Joe"
- "Admin Jack"
- "Ouvrier Bob"
- "Ouvrier Jane"

Le mot de passe de tous les utilisateurs est : **passpass**

----------------------------------------------------------

Méthode pour lister les chantiers : **/chantier/list**

Méthode pour connaitre le temps total d'un chantier : **/chantier/temps/{id}**

Méthode pour lister les opérations qu'un employé doit réaliser : **/operation/list**

Il est possible de lister tous les chantiers en tant qu'admin avec : **/admin/chantier/list**
De même avec les opérations : **/admin/operation/list**

----------------------------------------------------------
La gestion des rôles et des utilisateurs est réservée aux admins. Les ouvriers peuvent quand même lister les utilisateurs.

L'entièreté des routes réservées aux admins commence par **/admin/**

Les routes de gestions (delete, put, post) sont réservé aux admins.

----------------------------------------------------------

Route pour créer un utilisateur : **/sign-in**

Route pour se connecter : **/login**

----------------------------------------------------------
Voici les attributs du body pour la création et connexion d'un utilisateur
- pseudo
- motDePasse
- role *(non nécessaire pour connexion)*

----------------------------------------------------------
De même pour les roles :
- nom
- admin *(booléen)*