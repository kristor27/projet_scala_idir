import scala.collection.mutable.ListBuffer
import scala.io.StdIn
import scala.util.{Try, Success, Failure}
import java.io.{File, PrintWriter, BufferedReader, FileReader}

case class Bibliothèque(livres: List[Livre] = List()) {
  def ajouterLivre(livre: Livre): Bibliothèque = Bibliothèque(livre :: livres)

  def emprunterLivre(titre: String): Either[String, Bibliothèque] = {
    livres.find(_.titre == titre) match {
      case Some(livre) if !livre.estEmprunté =>
        val updatedLivre = livre.copy(estEmprunté = true)
        val updatedLivres = livres.map(l => if (l.titre == titre) updatedLivre else l)
        Right(Bibliothèque(updatedLivres))
      case Some(_) =>
        Left(s"Le livre '$titre' est déjà emprunté.")
      case None =>
        Left(s"Le livre '$titre' n'a pas été trouvé dans la bibliothèque.")
    }
  }

  def rendreLivre(titre: String): Either[String, Bibliothèque] = {
    livres.find(_.titre == titre) match {
      case Some(livre) if livre.estEmprunté =>
        val updatedLivre = livre.copy(estEmprunté = false)
        val updatedLivres = livres.map(l => if (l.titre == titre) updatedLivre else l)
        Right(Bibliothèque(updatedLivres))
      case Some(_) =>
        Left(s"Le livre '$titre' n'est pas emprunté.")
      case None =>
        Left(s"Le livre '$titre' n'a pas été trouvé dans la bibliothèque.")
    }
  }

  def rechercherParTitre(titre: String): List[Livre] =
    livres.filter(_.titre.contains(titre))

  def rechercherParAuteur(auteur: String): List[Livre] =
    livres.filter(_.auteur.contains(auteur))
}