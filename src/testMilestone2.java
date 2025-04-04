import Backend.Plat;
import GestionStock.Commande;
import GestionStock.Stock;

public class testMilestone2 {
    public static void main(String[] args) {
        // Ajouter des ingrédients au stock
        Stock.ajouterIngredient("Pâte", 10);
        Stock.ajouterIngredient("Tomate", 5);
        Stock.ajouterIngredient("Fromage", 3);

        // Créer un plat
        Plat pizza = new Plat("Pizza Margherita", 12.5, "Italien");
        pizza.ajouterIngredient("Pâte", 1);
        pizza.ajouterIngredient("Tomate", 2);
        pizza.ajouterIngredient("Fromage", 1);

        // Commande
        Commande commande = new Commande();
        commande.ajouterPlat(pizza);  // Devrait réussir

        // Un autre plat qui échoue si pas assez d’ingrédients
        Plat pizza2 = new Plat("Pizza XXL", 20, "Italien");
        pizza2.ajouterIngredient("Pâte", 2);
        pizza2.ajouterIngredient("Tomate", 10);  // Trop !

        commande.ajouterPlat(pizza2);  // Devrait échouer

        // Affichage
        commande.afficher();
    }
}
