package GestionStock;
import Backend.Plat;
import java.util.*;


public class Commande {
    private ArrayList<Plat> listPlats;
    private double total = 0.0;

    //=========== Constructeur ================
    public Commande(){
        this.listPlats = new ArrayList<>();
        this.total = 0.0;
    }

    //-------------GETTER & SETTER----------------------
    public double getTotal(){return this.total;}
    public void setTotal(double total){ this.total = total; }

    public ArrayList<Plat> getListPlats(){return this.listPlats;}
    public void setListPlats(){ this.listPlats = this.listPlats;}

    //--------- PROGRAMME PRINCIPAL----------------
    //---------------------------------------------

    //Permet d'ajouter un plat a la liste des plats de la commande
    // et d'augmenter le total du prix de la commande
    public void ajouterPlat(Plat plat){
        listPlats.add(plat);
        total += plat.getPrix();
    }

    // Permet d'afficher la commande de l'utilisateur
    // ainsi que le total du prix qu'il doit payer
    public void afficher(){
        System.out.println("La commande: \n");
        for (Plat plat : listPlats) {
            System.out.println(plat);
        }
        System.out.println("Le total (en euros): " + total + "€");
    }
}
