package Backend;

public class Plat {
    private String nom;
    private double prix;
    private String type;

    Plat(){};
    public Plat(String nom, double prix, String type){
        this.nom = nom;
        this.prix = prix;
        this.type = type;
    }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix;}

    public String getType() { return type; }
    public void setType(String type) { this.type = type;}



    @Override
    public String toString() {
        return "Nom: " +nom + " ; Prix: " + prix + "€ ; Type: " + type;
    }

}