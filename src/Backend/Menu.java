package Backend;
import java.util.*;

public class Menu {
    private ArrayList<Plat> listPlats;

    //=========== Constructeur ================
    public Menu(){
        this.listPlats = new ArrayList<>();
    }

    //-------------GETTER & SETTER----------------------
    public ArrayList<Plat> getListPlats(){return this.listPlats;}
    public void setListPlats(){ this.listPlats = this.listPlats;}


    //--------- PROGRAMME PRINCIPAL----------------
    //---------------------------------------------


    //Permet de trier la liste des plats au menu
    // par rapport a leur type (pizza, boisson, pates, ...)
    // et affiches tout les plats du même type
    public void triePlatParType(ArrayList<Plat> listPlats, String type) {
        List<Plat> platsFiltre = listPlats.stream()
                .filter(plat -> plat.getType().equals(type))
                .toList();

        platsFiltre.forEach(plat -> System.out.println(plat));
    }

    //Permet de creer une liste qui va être temporaire pour stocker
    // une liste qui contiendra seulement les plats de meme type
    // demandé en paramêtre
    // et renvoi une list de Plat
    public List<Plat> creerListeUniqueType(ArrayList<Plat> listPlats, String type) {
        List<Plat> platsFiltre = listPlats.stream()
                .filter(plat -> plat.getType().equals(type))
                .toList();

        return platsFiltre;
    }

    //Permet d'ajouter un plat a la liste du menu
    public void addPlat(Plat plat) { listPlats.add(plat); }

    //Permet d'afficher toute la liste de plats
    // de façon trié par type
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

    //Permet de vérifier si le nom d'un plat est déjà présent ou non
    // dans la liste de plat du menu
    // et renvoi un boolean (true/false)
    public boolean estPresent(ArrayList<Plat> listPlats, String nom) {
        for (Plat plat : listPlats) {
            if (plat.getNom().equals(nom)) {
                return true;
            }
        }
        return false;
    }


}