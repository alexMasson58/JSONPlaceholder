# JSONPlaceholder


Implémentation Android MVP de l'API REST
mise à disposition sur https://jsonplaceholder.typicode.com/

Implementations :  
 - liste des utilisateurs. https://jsonplaceholder.typicode.com/users
 - Vue profile utilisateur :
  - Onglet profil, avec les infos utilisateurs. https://jsonplaceholder.typicode.com/{id}
  - Onglet albums, avec la liste des albums d'un utilisateur. https://jsonplaceholder.typicode.com/{id}/albums
  - Onglet posts, avec la liste des posts d'un utilisateur. https://jsonplaceholder.typicode.com/{id}/posts
 - Vue détails d'un album: https://jsonplaceholder.typicode.com/albums/{id}/photos
  - Liste des photos d'un album
 - Vue détails d'un post: https://jsonplaceholder.typicode.com/posts/{id}/comments
  - Liste des commentaires d'un post
  - Fonctionnalités d'ajout de commentaire.

Notes : 

Refonte du repository des Commentaires :
 - l'API mock les appels POST, et répond avec un HTTP 200 et un nouvel élément, mais ne persiste pas le nouvel élément coté serveur.
 - Si on se base uniquement sur l'API, au refresh nous perdons les commentaires "locaux" (postés localement)
 
 - Création d'une interface CommentRepository qui va contractualiser les échanges.
 - Implémentation d'un repo qui utilise retrofit pour récupérer les commentaires de l'api, qui implémente l'interface.
 - Implémentation d'un repo qui garde les commentaires postés localement et les persiste, qui implémente l'interface.
 - Implémentation d'un super-repo qui implémente l'interface, qui merge les résultats des deux sous-repo.
 
Fin des notes.  

Librairies utilisées : 
 - Chargement des images de photos : Glide ( https://github.com/bumptech/glide )
 - Interaction avec l'API REST : Retrofit ( http://square.github.io/retrofit/ )
 - Binding des vues : Butterknife ( http://jakewharton.github.io/butterknife/ )
 - Interaction entre les fragments : EventBus ( https://github.com/greenrobot/EventBus )
