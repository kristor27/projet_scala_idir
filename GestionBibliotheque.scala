import scala.io.StdIn
import java.io.File
import scala.util.{Try, Success, Failure}

// Fonction principale
object GestionBibliothèque {
  def main(args: Array[String]): Unit = {
    var bibliothèque = Bibliothèque()

    var continuer = true
    while (continuer) {
      println("\nMenu de la bibliothèque:")
      println("1. Ajouter un livre")
      println("2. Emprunter un livre")
      println("3. Rendre un livre")
      println("4. Rechercher par titre")
      println("5. Rechercher par auteur")
      println("6. Quitter")
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
          val livre = Livre(titre, auteur, année)
          bibliothèque = bibliothèque.ajouterLivre(livre)
          println(s"Le livre '$titre' a été ajouté à la bibliothèque.")
        case 2 =>
          println("Entrez le titre du livre à emprunter :")
          val titre = StdIn.readLine()
          bibliothèque.emprunterLivre(titre) match {
            case Left(error) =>
              println(error)
            case Right(updatedBibliothèque) =>
              bibliothèque = updatedBibliothèque
              println(s"Le livre '$titre' a été emprunté.")
          }
        case 3 =>
          println("Entrez le titre du livre à rendre :")
          val titre = StdIn.readLine()
          bibliothèque.rendreLivre(titre) match {
            case Left(error) =>
              println(error)
            case Right(updatedBibliothèque) =>
              bibliothèque = updatedBibliothèque
              println(s"Le livre '$titre' a été rendu.")
          }
        case 4 =>
          println("Entrez le titre à rechercher :")
          val titre = StdIn.readLine()
          val livresTrouvés = bibliothèque.rechercherParTitre(titre)
          if (livresTrouvés.nonEmpty) {
            livresTrouvés.foreach(livre => println(livre))
          } else {
            println(s"Aucun livre avec le titre '$titre' a été trouvé.")
          }
        case 5 =>
          println("Entrez l'auteur à rechercher :")
          val auteur = StdIn.readLine()
          val livresTrouvés = bibliothèque.rechercherParAuteur(auteur)
          if (livresTrouvés.nonEmpty) {
            livresTrouvés.foreach(livre => println(livre))
          } else {
            println(s"Aucun livre de l'auteur '$auteur' a été trouvé.")
          }
        case 6 =>
          continuer = false
        case _ =>
          println("Option invalide. Veuillez choisir une option valide.")
      }
    }
  }
}