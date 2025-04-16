package GestionStock;

import Backend.Plat;
import java.util.*;

public class Commande {
    private ArrayList<Plat> listPlats;
    private double total = 0.0;

    public Commande() {
        this.listPlats = new ArrayList<>();
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ArrayList<Plat> getListPlats() {
        return this.listPlats;
    }

    public void setListPlats() {
        this.listPlats = this.listPlats;
    }

    // Méthode pour ajouter un plat à la commande
    public void ajouterPlat(Plat plat) {
        Map<String, Double> ingredients = plat.getIngredients();

        // Vérification des ingrédients en stock
        boolean tousDisponibles = true;
        for (Map.Entry<String, Double> entry : ingredients.entrySet()) {
            if (!Stock.verifierStock(entry.getKey(), entry.getValue())) {
                System.out.println("❌ Commande refusée : ingrédient manquant ou insuffisant : " + entry.getKey());
                tousDisponibles = false;
                break;
            }
        }

        // Si tous les ingrédients sont disponibles, on ajoute le plat et met à jour le stock
        if (tousDisponibles) {
            // Mise à jour du stock en retirant les ingrédients nécessaires
            for (Map.Entry<String, Double> entry : ingredients.entrySet()) {
                Stock.retirerIngredient(entry.getKey(), entry.getValue());
            }

            // Ajout du plat à la commande et mise à jour du total
            listPlats.add(plat);
            total += plat.getPrix();
            System.out.println("✅ Plat ajouté : " + plat.getNom());
        }
    }

    // Méthode pour afficher la commande
    public void afficher() {
        System.out.println("La commande : ");
        for (Plat plat : listPlats) {
            System.out.println(plat);
        }
        System.out.println("Total à payer : " + total + "€");
    }
}
