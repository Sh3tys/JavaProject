import BDD.*;
import GestionEmploye.metier.*;
import Backend.*;
import BDD.SaveInBdd.*;

public class initTest {
    public static void main(String[] args) {
        Menu menu = new Menu();

        BddSetup.createDatabase();
        BddSetup.createTables();

        Serveur serveur = new Serveur("Viral","Diego");
        BddEmploye.ajouterEmploye(serveur);

        Cuisinier cuisinier = new Cuisinier( "Luc","Yluk");
        BddEmploye.ajouterEmploye(cuisinier);

        Gerant gerant = new Gerant("Marie", "Onette");
        BddEmploye.ajouterEmploye(gerant);

        // Initialiser le stock avec des ingrédients
        Gerant.ajouterIngredients("Pate", 100);
        Gerant.ajouterIngredients("Tomate", 100);
        Gerant.ajouterIngredients("Fromage", 100);
        Gerant.ajouterIngredients("Creme", 100);
        Gerant.ajouterIngredients("Miel", 100);
        Gerant.ajouterIngredients("Oasis", 100);

        Gerant.ajoutPlat(menu, 1, "pizaa", 5.99, "pizza");
        Gerant.ajoutPlat(menu, 2, "pasta", 9.99, "pates");



    }


}
