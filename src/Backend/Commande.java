package Backend;
import java.util.*;


public class Commande {
    private ArrayList<Plat> listPlats;
    private double total = 0.0;

    public Commande(){
        this.listPlats = new ArrayList<>();
        this.total = 0.0;
    }

    public double getTotal(){return this.total;}
    public void setTotal(double total){ this.total = total; }

    public ArrayList<Plat> getListPlats(){return this.listPlats;}
    public void setListPlats(){ this.listPlats = this.listPlats;}

    public void ajouterPlat(Plat plat){
        listPlats.add(plat);
        total += plat.getPrix();
    }

    public void afficher(){
        System.out.println("La commande: \n");
        for (Plat plat : listPlats) {
            System.out.println(plat);
        }
        System.out.println("Le total (en euros): " + total + "€");
    }
}
