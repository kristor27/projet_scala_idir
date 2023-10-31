import scala.io.StdIn
import java.io.File

// Fonction principale
object GestionBibliothèque {
  def main(args: Array[String]): Unit = {
    val bibliothèque = new Bibliothèque()

    // Charger la bibliothèque depuis un fichier (si le fichier existe)
    val fichierBibliothèque = "bibliotheque.txt"
    if (new File(fichierBibliothèque).exists()) {
      bibliothèque.chargerBibliothèque(fichierBibliothèque)
    }

    var continuer = true
    while (continuer) {
      println("\nMenu de la bibliothèque:")
      println("1. Ajouter un livre")
      println("2. Emprunter un livre")
      println("3. Rendre un livre")
      println("4. Rechercher par titre")
      println("5. Rechercher par auteur")
      println("6. Sauvegarder la bibliothèque")
      println("7. Quitter")
      print("Choisissez une option : ")

      val choix = StdIn.readInt()

      choix match {
        case 1 =>
          println("Entrez le titre du livre :")
          val titre = StdIn.readLine()
          println("Entrez l'auteur du livre :")
          val auteur = StdIn.readLine()
          println("Entrez l'année de publication du livre :")
          val année = StdIn.readInt()
          val livre = new Livre(titre, auteur, année)
          bibliothèque.ajouterLivre(livre)
        case 2 =>
          println("Entrez le titre du livre à emprunter :")
          val titre = StdIn.readLine()
          bibliothèque.emprunterLivre(titre)
        case 3 =>
          println("Entrez le titre du livre à rendre :")
          val titre = StdIn.readLine()
          bibliothèque.rendreLivre(titre)
        case 4 =>
          println("Entrez le titre à rechercher :")
          val titre = StdIn.readLine()
          bibliothèque.rechercherParTitre(titre)
        case 5 =>
          println("Entrez l'auteur à rechercher :")
          val auteur = StdIn.readLine()
          bibliothèque.rechercherParAuteur(auteur)
        case 6 =>
          bibliothèque.sauvegarderBibliothèque(fichierBibliothèque)
        case 7 =>
          continuer = false
        case _ =>
          println("Option invalide. Veuillez choisir une option valide.")
      }
    }
  }
}
