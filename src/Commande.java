import java.util.*;

public class Commande {
    List<Plat> listPlats = new ArrayList<>();
    double total = 0.0;

    Commande(){};

    public void ajouterPlat(Plat plat){
        listPlats.add(plat);
        total += plat.prix;
    }

    public void afficher(){
        System.out.println("La commande: ");
        for (Plat plat : listPlats) {
            System.out.println(plat);
        }
        System.out.println("Le total (en euros): " + total);
    }
}
