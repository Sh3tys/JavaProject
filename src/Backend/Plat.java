package Backend;

import java.util.*;

public class Plat {
    private int id; // Ajout de l'ID du plat qui sera récupéré après insertion dans la BDD
    private String nom;
    private double prix;
    private String type;
    private Map<String, Double> ingredients = new HashMap<>();

    //=========== Constructeurs ================
    Plat() {}

    public Plat(int id, String nom, double prix, String type, Map<String, Double> ingredients) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.type = type;
        this.ingredients = (ingredients != null) ? new HashMap<>(ingredients) : new HashMap<>();
    }

    //-------------GETTER & SETTER----------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Double> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<String, Double> ingredients) {
        this.ingredients = ingredients;
    }

    // Méthode pour ajouter un ingrédient
    public void ajouterIngredient(String ingredient, double quantite) {
        ingredients.put(ingredient, quantite);
    }

    //---------Affichage du plat----------------
    @Override
    public String toString() {
        return "Nom: " + nom + " ; Prix: " + prix + "€ ; Type: " + type + " ; Ingrédients: " + ingredients;
    }
}
