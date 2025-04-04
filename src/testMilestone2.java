import Backend.*;
import GestionStock.Commande;
import java.util.*;

public class testMilestone2 {
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
            System.out.println("2. Ajouter un plat au Menu");
            System.out.println("3. Ajouter un plat à la commande");
            System.out.println("4. Voir la commande");
            System.out.println("5. Quitter");

            int choix = sc.nextInt();
            switch (choix) {
                case 1:
                    menu.afficherMenu();
                    break;

                case 2:
                    System.out.print("Nom du Plat: ");
                    String nom = sc.next();
                    System.out.print("Prix du Plat: ");
                    double prix = sc.nextDouble();
                    System.out.print("Type du Plat: ");
                    String type = sc.next();

                    if (menu.estPresent(menu.getListPlats(), nom )) {
                        System.out.println("Erreur le plat n'a pas était mis dans le menu, il existe déjà !");
                    }

                    menu.addPlat(new Plat(nom, prix, type));
                    System.out.println("Le plat : " + nom + " a correctement était mis dans le menu");

                    break;

                case 3:
                    System.out.println("Entrer la catégorie du plat (pizza, pates, boisson, dessert) :");
                    String cat = sc.next();

                    if (cat.equals("pizza")){
                        System.out.println("Choisir la pizza : \n");

                        System.out.println("---------- Nos Pizza ----------\n");
                        menu.triePlatParType(menu.getListPlats(), "pizza");
                        System.out.println("==========================================");

                        System.out.print("Numéro (1-" + menu.creerListeUniqueType(menu.getListPlats(), "pizza").size() + "): ");
                        int index = sc.nextInt() - 1;
                        if (index >= 0 && index < menu.creerListeUniqueType(menu.getListPlats(), "pizza").size()) {
                            commande.ajouterPlat(menu.creerListeUniqueType(menu.getListPlats(), "pizza").get(index));
                        } else {
                            System.out.println("Ce plat n'existe pas !");
                        }

                    } else if (cat.equals("pates")) {

                        System.out.println("Choisir les pates: \n");

                        System.out.println("---------- Nos Pates ----------\n");
                        menu.triePlatParType(menu.getListPlats(), "pates");
                        System.out.println("==========================================");

                        System.out.print("Numéro (1-" + menu.creerListeUniqueType(menu.getListPlats(), "pates").size() + "): ");
                        int index = sc.nextInt() - 1;
                        if (index >= 0 && index < menu.creerListeUniqueType(menu.getListPlats(), "pates").size()) {
                            commande.ajouterPlat(menu.creerListeUniqueType(menu.getListPlats(), "pates").get(index));
                        } else {
                            System.out.println("Ce plat n'existe pas !");
                        }

                    } else if (cat.equals("dessert")) {

                        System.out.println("Choisir un dessert : \n");

                        System.out.println("---------- Nos Desserts ----------\n");
                        menu.triePlatParType(menu.getListPlats(), "dessert");
                        System.out.println("==========================================");

                        System.out.print("Numéro (1-" + menu.creerListeUniqueType(menu.getListPlats(), "dessert").size() + "): ");
                        int index = sc.nextInt() - 1;
                        if (index >= 0 && index < menu.creerListeUniqueType(menu.getListPlats(), "dessert").size()) {
                            commande.ajouterPlat(menu.creerListeUniqueType(menu.getListPlats(), "dessert").get(index));
                        } else {
                            System.out.println("Ce plat n'existe pas !");
                        }

                    } else if (cat.equals("boisson")) {

                        System.out.println("Choisir une Boisson : \n");

                        System.out.println("---------- Nos Boissons ----------\n");
                        menu.triePlatParType(menu.getListPlats(), "boisson");
                        System.out.println("==========================================");

                        System.out.print("Numéro (1-" + menu.creerListeUniqueType(menu.getListPlats(), "boisson").size() + "): ");
                        int index = sc.nextInt() - 1;
                        if (index >= 0 && index < menu.creerListeUniqueType(menu.getListPlats(), "boisson").size()) {
                            commande.ajouterPlat(menu.creerListeUniqueType(menu.getListPlats(), "boisson").get(index));
                        } else {
                            System.out.println("Ce plat n'existe pas !");
                        }

                    } else {
                        System.out.println("Erreur du choix de la catégorie");
                    }
                    break;

                 case 4:
                     commande.afficher();
                     break;

                 case 5:
                     System.out.println("Au plaisir de vous revoir prochainement !");
                     break;

                  default:
                      System.out.println("Ce choix n'existe pas !");
                      System.out.println("Veuillez en choisir un existant");
                      break;
            }
        }
    }
}
