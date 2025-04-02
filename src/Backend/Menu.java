package Backend;

import java.util.*;

public class Menu {
    private ArrayList<Plat> listPlats;

    public Menu(){
        this.listPlats = new ArrayList<>();
    }

    public ArrayList<Plat> getListPlats(){return this.listPlats;}
    public void setListPlats(){ this.listPlats = this.listPlats;}

    public void triePlatParType(ArrayList<Plat> listPlats, String type) {
        List<Plat> platsFiltre = listPlats.stream()
                .filter(plat -> plat.getType().equals(type))
                .toList();

        platsFiltre.forEach(plat -> System.out.println(plat));
    }

    public List<Plat> creerListeUniqueType(ArrayList<Plat> listPlats, String type) {
        List<Plat> platsFiltre = listPlats.stream()
                .filter(plat -> plat.getType().equals(type))
                .toList();

        return platsFiltre;
    }

    public void addPlat(Plat plat) { listPlats.add(plat); }

    public void afficherMenu(){
        System.out.println("Le Menu: \n");

        System.out.println("---------- Nos Pizza ----------\n");
        triePlatParType(listPlats, "pizza");
        System.out.println("==========================================");

        System.out.println("---------- Nos Pates ----------\n");
        triePlatParType(listPlats, "pates");
        System.out.println("==========================================");

        System.out.println("---------- Nos Desserts ----------\n");
        triePlatParType(listPlats, "dessert");
        System.out.println("==========================================");

        System.out.println("---------- Nos Boissons ----------\n");
        triePlatParType(listPlats, "boisson");
        System.out.println("==========================================");
    }

    public boolean estPresent(ArrayList<Plat> listPlats, String nom) {
        for (Plat plat : listPlats) {
            if (plat.getNom().equals(nom)) {
                return true;
            }
        }
        return false;
    }


}