import java.awt.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();
        Commande commande = new Commande();


        menu.addPlat(new Plat("3 fromages", 10.0, "pizza"));
        menu.addPlat(new Plat("Pates Carbonara", 15.0, "pates"));
        menu.addPlat(new Plat("Creme bruler", 25.0, "dessert"));
        menu.addPlat(new Plat("Oasis", 2.5, "boisson"));


        while (true) {
            System.out.println("");
            System.out.println("Bienvenu dans notre restaurant veuillez choisir ce que vous voulez faire:");
            System.out.println("");

            System.out.println("1. Voir le menu");
            System.out.println("2. Ajouter un plat à la commande");
            System.out.println("3. Voir la commande");
            System.out.println("4. Quitter");

            int choix = sc.nextInt();
            switch (choix) {
                case 1:
                    menu.afficher();
                    break;

                case 2:
                    System.out.println("Entrer le numero du plat :");
                    int index = sc.nextInt() - 1;
                    if (index >= 0 && index < menu.listPlats.size()) {
                        commande.ajouterPlat(menu.listPlats.get(index));
                    } else {
                        System.out.println("Ce plat n'existe pas !");
                    }
                    break;

                 case 3:
                     commande.afficher();
                     break;

                 case 4:
                     System.out.println("Au plaisir de vous revoir prochainement !");
                     sc.close();
                     break;

                  default:
                      System.out.println("Ce choix n'existe pas !");
                      System.out.println("Veuillez en choisir un existant");
                      break;
            }
        }
    }
}