import java.util.*;

public class Menu {
    ArrayList<Plat> listPlats;

    public Menu(){
        this.listPlats = new ArrayList<>();
    }

    public List<Plat> getListPlats(){return this.listPlats;}
    public void setListPlats(){ this.listPlats = this.listPlats;}

    public void triePlatParType(ArrayList<Plat> listPlats, String type) {
        List<Plat> platsFiltre = listPlats.stream()
                .filter(plat -> plat.getType().equals(type))
                .toList();

        platsFiltre.forEach(plat -> System.out.println(plat));
    }

    public void addPlat(Plat plat) { listPlats.add(plat); }

    public void afficher(){
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
}