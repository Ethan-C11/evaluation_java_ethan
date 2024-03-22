Le mot de passe de tout les utilisateur est : **passpass**

----------------------------------------------------------

Methode pour lister les chantiers : **/chantier/list**

Methode pour connaitre le temps total d'un chantier : **/chantier/temps/{id}**

Methode pour lister les opérations qu'un employé doit réaliser : **/operation/list**

Il est possible de lister tout les chantiers en tant qu'admin avec : **/admin/chantier/list**
De même avec les opérations : **/admin/operation/list**

----------------------------------------------------------
La gestion des rôle et des utilisateurs est reservé aux admins. Les ouvrier peuvent quand même lister les utilisateurs.

L'entièreté des routes reservé aux admins commencent par **/admin/**

Les routes de gestions (delete, put, post) sont reservé aux admins.