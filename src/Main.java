import java.awt.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();
        Commande commande = new Commande();

        //PIZZA
        menu.addPlat(new Plat("3 fromages", 10.0, "pizza"));
        menu.addPlat(new Plat("Calzon", 18.99, "pizza"));
        menu.addPlat(new Plat("Peperoni", 9.85, "pizza"));
        menu.addPlat(new Plat("Royal", 12.5, "pizza"));

        //PATES
        menu.addPlat(new Plat("Pates Carbonara", 15.25, "pates"));
        menu.addPlat(new Plat("Bolognaise", 13.69, "pates"));
        menu.addPlat(new Plat("Pates au Pesto", 19.90, "pates"));
        menu.addPlat(new Plat("Pates au Caviare", 39.99, "pates"));

        //DESSERT
        menu.addPlat(new Plat("Creme bruler", 25.10, "dessert"));
        menu.addPlat(new Plat("Mousse au Chocolat", 11.86, "dessert"));
        menu.addPlat(new Plat("Banana split", 4.99, "dessert"));
        menu.addPlat(new Plat("Café liégeois", 7.35, "dessert"));

        //BOISSON
        menu.addPlat(new Plat("Oasis", 2.5, "boisson"));
        menu.addPlat(new Plat("Grappa", 12.89, "boisson"));
        menu.addPlat(new Plat("Mojito", 8.35, "boisson"));
        menu.addPlat(new Plat("Eau", 1.99, "boisson"));


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