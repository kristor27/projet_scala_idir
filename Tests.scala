import org.scalatest._

class LivreTest extends FunSuite {
  test("Un livre nouvellement créé n'est pas emprunté") {
    val livre = new Livre("Le Seigneur des Anneaux", "J.R.R. Tolkien", 1954)
    assert(!livre.estEmprunté)
  }

  test("Emprunter un livre le met dans un état emprunté") {
    val livre = new Livre("Harry Potter", "J.K. Rowling", 1997)
    livre.emprunter()
    assert(livre.estEmprunté)
  }

  test("Rendre un livre le met dans un état non emprunté") {
    val livre = new Livre("1984", "George Orwell", 1949)
    livre.emprunter()
    livre.rendre()
    assert(!livre.estEmprunté)
  }
}