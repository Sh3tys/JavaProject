import java.util.*;

public class Menu {
    List<Plat> listPlats = new ArrayList<>();

    Menu(){}
    Menu(){}

    public void addPlat(Plat plat) {
        listPlats.add(plat);
    }

    public void afficher(){
        System.out.println("Le Menu: ");
        for (Plat plat : listPlats) {
            System.out.println(plat);
        }
    }
}