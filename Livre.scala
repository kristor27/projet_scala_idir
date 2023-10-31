// Classe Livre
class Livre(val titre: String, val auteur: String, val annéeDePublication: Int) {
  private var estEmprunté: Boolean = false

  def emprunter(): Unit = {
    if (!estEmprunté) {
      estEmprunté = true
      println(s"Le livre '$titre' a été emprunté.")
    } else {
      println(s"Le livre '$titre' est déjà emprunté.")
    }
  }

  def rendre(): Unit = {
    if (estEmprunté) {
      estEmprunté = false
      println(s"Le livre '$titre' a été rendu.")
    } else {
      println(s"Le livre '$titre' n'est pas emprunté.")
    }

  override def toString: String = {
    s"Titre: $titre, Auteur: $auteur, Année de publication: $annéeDePublication, Est emprunté: $estEmprunté"
  }
}
