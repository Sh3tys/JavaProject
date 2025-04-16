package GestionEmploye.metier;
import GestionEmploye.Employe;
import GestionStock.*;
import Backend.Plat;
import java.util.Map;

public class Cuisinier extends Employe {

    public Cuisinier(int id, String nom) {
        super(id, nom, "Cuisinier");
    }

    public void preparerCommande(Commande commande) {
        // Supposons que les stocks sont suffisants au départ
        boolean stockSuffisant = true;

        // Vérification des stocks pour chaque plat de la commande
        for (Plat plat : commande.getListPlats()) {
            System.out.println("Vérification du plat : " + plat.getNom());
            Map<String, Double> ingredients = plat.getIngredients();

            for (Map.Entry<String, Double> entry : ingredients.entrySet()) {
                String nomIngredient = entry.getKey();
                double quantiteRequise = entry.getValue();

                if (!Stock.verifierStock(nomIngredient, quantiteRequise)) {
                    System.out.println("Stock insuffisant pour l'ingrédient : " + nomIngredient +
                            " (besoin de " + quantiteRequise + ")");
                    stockSuffisant = false;
                    break; // Arrête la vérification dès qu’un ingrédient manque
                }
            }
            if (!stockSuffisant) {
                break;
            }
        }

        // Si le stock est suffisant, retirer TOUS les ingrédients
        if (stockSuffisant) {
            System.out.println("Tous les ingrédients sont disponibles. Préparation en cours...");

            for (Plat plat : commande.getListPlats()) {
                System.out.println(plat);
                Map<String, Double> ingredients = plat.getIngredients();
                System.out.println(ingredients);

                for (Map.Entry<String, Double> entry : ingredients.entrySet()) {
                    String nomIngredient = entry.getKey();
                    double quantite = entry.getValue();

                    Stock.retirerIngredient(nomIngredient, quantite);
                    System.out.println("Ingrédient retiré : " + nomIngredient + " x" + quantite);
                }
            }

            System.out.println("Commande préparée avec succès !");
            System.out.println("Total à payer : " + commande.getTotal() + "€");

        } else {
            System.out.println("Commande annulée. Stock insuffisant pour un ou plusieurs plats.");
        }
    }



    @Override
    public void effectuerTache(Commande commande) {
        preparerCommande(commande);  // Utiliser la méthode de préparation de la commande
    }
}
