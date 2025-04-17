package GestionStock;
import BDD.SaveIngredient.*;

public class Stock {

    //=========== Constructeur ================
    public Stock(){}

    // Permet d'ajouter un ingrédient avec sa quantité
    public static void ajouterIngredient(String ingredient, double quantite) {
        // Appel à la méthode dans BddIngredient pour gérer l'ajout de l'ingrédient
        BddIngredient.ajouterIngredient(ingredient, quantite);
    }

    // Vérifie si un ingrédient est disponible en quantité suffisante
    public static boolean verifierStock(String ingredient, double quantiteRequise) {
        // Appel à la méthode dans BddIngredient pour vérifier le stock
        return BddIngredient.verifierStock(ingredient, quantiteRequise);
    }

    // Retire une certaine quantité d'un ingrédient du stock
    public static void retirerIngredient(String ingredient, double nbrIngredient) {
        // Appel à la méthode dans BddIngredient pour retirer l'ingrédient
        BddIngredient.retirerIngredient(ingredient, nbrIngredient);
    }

    // Affiche l'état actuel du stock : ingrédients et leurs quantités
    public static void afficherStock() {
        // Appel à la méthode dans BddIngredient pour afficher le stock
        BddIngredient.afficherStock();
    }
}
