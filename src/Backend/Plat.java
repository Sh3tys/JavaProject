package Backend;

import java.util.HashMap;
import java.util.Map;

public class Plat {
    private String nom;
    private double prix;
    private String type;
    private Map<String, Integer> ingredients = new HashMap<>();

    //=========== Constructeurs ================
    Plat() {}
    public Plat(String nom, double prix, String type){
        this.nom = nom;
        this.prix = prix;
        this.type = type;
    }

    //-------------GETTER & SETTER----------------------
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Map<String, Integer> getIngredients() { return ingredients; }
    public void setIngredients(Map<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public void ajouterIngredient(String ingredient, int quantite) {
        ingredients.put(ingredient, quantite);
    }

    //---------Affichage du plat----------------
    @Override
    public String toString() {
        return "Nom: " + nom + " ; Prix: " + prix + "€ ; Type: " + type + " ; Ingrédients: " + ingredients;
    }
}
