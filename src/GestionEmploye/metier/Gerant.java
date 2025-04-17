package GestionEmploye.metier;

import BDD.SaveEmploye.BddEmploye;
import BDD.SavePlat.BddPlat;
import GestionEmploye.*;
import GestionStock.*;
import Backend.*;

import java.util.*;

public class Gerant extends Employe {
    public static Scanner sc = new Scanner(System.in);

    public Gerant(String nom, String prenom) {
        super(nom, prenom,"Gerant");
    }

    // Ajouter des ingrédients au stock
    public static void ajouterIngredients(String ingredient, double quantite) {
        System.out.println("Le gérant ajoute " + quantite + " unités de " + ingredient + " au stock.");
        Stock.ajouterIngredient(ingredient, quantite);
    }

    public static void deleteIngredient(String ingredient, double quantite) {
        Stock.retirerIngredient(ingredient, quantite);
    }

    public static void ajouterIngredientPlat(Plat plat, String ingredient, double quantite) {
        System.out.println("Test quantité" + quantite);
        plat.ajouterIngredient(ingredient.trim(), quantite);  // Ajouter l'ingrédient avec quantite
    }

    // Ajouter un plat au menu si le plat n'existe pas déjà
    public static void ajoutPlat(Menu menu, int id, String nom, double prix, String type) {
        String ingredients = "";
        double quantite = 0.0;

        if (platEstPresent(menu.getListPlats(), nom)) {
            System.out.println("Erreur : le plat existe déjà dans le menu !");
        } else {
            Plat plat = new Plat(id, nom, prix, type,null);
            menu.addPlat(plat);  // Ajouter le plat à la liste du menu


            while (ingredients != "fin") {
                System.out.print("Ingrédient nécessaires pour ce plat (mettre 'fin' si finis): ");
                ingredients = sc.next();
                if (ingredients.equals("fin")) {
                    break;
                }

                System.out.print("La quantité de cette ingrédient : ");
                quantite = sc.nextDouble();

                Gerant.ajouterIngredientPlat(plat, ingredients.trim(), quantite);

            }
            BddPlat.sauvegarderPlat(plat); // Sauvegarde le plat dans la base de donnée

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

    public void supprimerPlatParNom(Menu menu) {
        Serveur.afficherMenu(menu);

        System.out.println("Entrez le nom du plat à supprimer :");
        String nom = sc.nextLine();

        BddPlat.supprimerPlatParNom(nom);
    }

    public static void afficherEmploye(){
        BddEmploye.afficherTousLesEmployes();
    }

    @Override
    public void effectuerTache(Commande commande) {
        // Le gérant effectue la tâche de gestion (ajouter des plats, vérifier les stocks, etc.)
        System.out.println(nom +" " + prenom + " est :" + role);
    }
}
