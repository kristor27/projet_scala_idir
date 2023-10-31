import scala.collection.mutable.ListBuffer
import java.io.{File, PrintWriter, BufferedReader, FileReader}

// Classe Bibliothèque
class Bibliothèque {
  private val livres: ListBuffer[Livre] = ListBuffer()

  def ajouterLivre(livre: Livre): Unit = {
    livres += livre
    println(s"Le livre '$livre' a été ajouté à la bibliothèque.")
  }

  def emprunterLivre(titre: String): Unit = {
    livres.find(_.titre == titre) match {
      case Some(livre) => livre.emprunter()
      case None => println(s"Le livre '$titre' n'a pas été trouvé dans la bibliothèque.")
    }
  }

  def rendreLivre(titre: String): Unit = {
    livres.find(_.titre == titre) match {
      case Some(livre) => livre.rendre()
      case None => println(s"Le livre '$titre' n'a pas été trouvé dans la bibliothèque.")
    }
  }

  def rechercherParTitre(titre: String): Unit = {
    val livresTrouvés = livres.filter(_.titre.contains(titre))
    if (livresTrouvés.nonEmpty) {
      livresTrouvés.foreach(println)
    } else {
      println(s"Aucun livre avec le titre '$titre' n'a été trouvé.")
    }
  }

  def rechercherParAuteur(auteur: String): Unit = {
    val livresTrouvés = livres.filter(_.auteur.contains(auteur))
    if (livresTrouvés.nonEmpty) {
      livresTrouvés.foreach(println)
    } else {
      println(s"Aucun livre de l'auteur '$auteur' n'a été trouvé.")
    }

  def sauvegarderBibliothèque(nomFichier: String): Unit = {
    val writer = new PrintWriter(new File(nomFichier))
    livres.foreach(livre => writer.println(s"${livre.titre},${livre.auteur},${livre.annéeDePublication},${livre.toString}"))
    writer.close()
    println(s"La bibliothèque a été sauvegardée dans le fichier '$nomFichier'.")
  }

  def chargerBibliothèque(nomFichier: String): Unit = {
    val reader = new BufferedReader(new FileReader(nomFichier))
    var line: String = reader.readLine()
    while (line != null) {
      val parts = line.split(",")
      if (parts.length == 4) {
        val titre = parts(0)
        val auteur = parts(1)
        val année = parts(2).toInt
        val estEmprunté = parts(3).toBoolean
        val livre = new Livre(titre, auteur, année)
        if (estEmprunté) livre.emprunter()
        livres += livre
      }
      line = reader.readLine()
    }
    reader.close()
    println(s"La bibliothèque a été chargée depuis le fichier '$nomFichier'.")
  }
}
