package GestionEmploye.metier;
import GestionEmploye.Employe;
import GestionStock.Stock;
import GestionStock.Commande;
import Backend.Plat;

import java.util.Map;

public class Cuisinier extends Employe {

    public Cuisinier(int id, String nom) {
        super(id, nom, "Cuisinier");
    }

    public void preparerCommande(Commande commande) {
        boolean stockSuffisant = true;

        // Vérifier les stocks avant préparation
        for (Plat plat : commande.getListPlats()) {
            Map<String, Integer> ingredients = plat.getIngredients();
            for (Map.Entry<String, Integer> entry : ingredients.entrySet()) {
                if (!Stock.verifierStock(entry.getKey(), entry.getValue())) {
                    System.out.println("Pas assez d'" + entry.getKey() + " pour préparer " + plat.getNom());
                    stockSuffisant = false;
                    break;
                }
            }
        }

        if (stockSuffisant) {
            // Retirer les ingrédients du stock si tout est bon
            for (Plat plat : commande.getListPlats()) {
                Map<String, Integer> ingredients = plat.getIngredients();
                for (Map.Entry<String, Integer> entry : ingredients.entrySet()) {
                    Stock.retirerIngredient(entry.getKey(), entry.getValue());
                }
            }
            System.out.println("Commande préparée, total à payer : " + commande.getTotal() + "€");
        } else {
            System.out.println("Commande annulée, stock insuffisant.");
        }
    }

    @Override
    public void effectuerTache(Commande commande) {
        preparerCommande(commande);  // Utiliser la méthode de préparation de la commande
    }
}
