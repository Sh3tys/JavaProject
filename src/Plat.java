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

    System.out.println("Test Back")

    @Override
    public String toString() {
        return nom + " " + prix + " " + type + " " + type;
    }

}
