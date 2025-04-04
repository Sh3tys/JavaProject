package GestionEmploye.metier;
import GestionEmploye.Employe;
import GestionStock.Commande;
import Backend.Menu;
import Backend.Plat;
import GestionStock.Stock;

import java.util.*;

public class Gerant extends Employe {
    public static Scanner sc = new Scanner(System.in);

    public Gerant(int id, String nom) {
        super(id, nom, "Gérant");
    }

    // Ajouter des ingrédients au stock
    public static void ajouterIngredients(String ingredient, int quantite) {
        System.out.println("Le gérant ajoute " + quantite + " unités de " + ingredient + " au stock.");
        Stock.ajouterIngredient(ingredient, quantite);
    }

    public static void ajouterIngredientPlat(Plat plat, String ingredient) {
        plat.ajouterIngredient(ingredient.trim(), 1);  // Ajouter l'ingrédient avec quantité de 1
    }

    // Ajouter un plat au menu si le plat n'existe pas déjà
    public static void ajoutPlat(Menu menu, String nom, double prix, String type) {
        if (platEstPresent(menu.getListPlats(), nom)) {
            System.out.println("Erreur : le plat existe déjà dans le menu !");
        } else {
            Plat plat = new Plat(nom, prix, type);
            menu.addPlat(plat);  // Ajouter le plat à la liste du menu
            System.out.println("Le plat " + nom + " a été ajouté au menu !");

            if (type.equals("pizza")) {
                System.out.print("Ingrédients nécessaires pour ce plat (séparés par des virgules) : ");
                String ingredients = sc.next();
                String[] ingredientList = ingredients.split(",");
                for (String ingredient : ingredientList) {
                    Gerant.ajouterIngredientPlat(plat, ingredient.trim());
                }
            }
        }
    }

    // Vérifier si un plat est déjà présent dans le menu
    private static boolean platEstPresent(List<Plat> listPlats, String nom) {
        for (Plat plat : listPlats) {
            if (plat.getNom().equals(nom)) {
                return true; // Le plat existe déjà
            }
        }
        return false; // Le plat n'existe pas encore
    }

    // Consulter l'état des stocks
    public void consulterStocks() {
        Stock.afficherStock();
    }

    @Override
    public void effectuerTache(Commande commande) {
        // Le gérant effectue la tâche de gestion (ajouter des plats, vérifier les stocks, etc.)
        System.out.println(nom + " effectue sa tâche.");
    }
}
