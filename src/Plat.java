public class Plat {
    String nom;
    double prix;
    String type;

    Plat(){};
    Plat(String nom, double prix, String type){
        this.nom = nom;
        this.prix = prix;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Nom: " +nom + " ; Prix: " + prix + "€ ; Type: " + type;
    }

}