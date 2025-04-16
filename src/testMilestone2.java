import GestionStock.Commande;
import Backend.Menu;
import BDD.*;
import GestionStock.Stock;
import GestionEmploye.metier.Cuisinier;
import GestionEmploye.metier.Gerant;
import GestionEmploye.metier.Serveur;
import java.util.*;

public class testMilestone2 {
    public static void main(String[] args) {

        BddSetup.createDatabase();
        BddSetup.createTables();

        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();
        Commande commande = new Commande();
        Stock stock = new Stock();

        // Initialiser les employés
        Serveur serveur = new Serveur(1, "Jean");
        Cuisinier cuisinier = new Cuisinier(2, "Luc");
        Gerant gerant = new Gerant(3, "Marie");

        // Initialiser le stock avec des ingrédients
        Gerant.ajouterIngredients("Pâte", 10);
        Gerant.ajouterIngredients("Tomate", 5);
        Gerant.ajouterIngredients("Fromage", 3);
        Gerant.ajouterIngredients("Crème", 7);
        Gerant.ajouterIngredients("Miel", 6);
        Gerant.ajouterIngredients("Oasis", 10);


        //Initialise les plats
        Gerant.ajoutPlat(menu, "pizaa", 5.99, "pizza");

        while (true) {
            System.out.println("");
            System.out.println("Bienvenue dans notre restaurant, veuillez choisir ce que vous voulez faire :");
            System.out.println("");

            System.out.println("1. Voir le menu");
            System.out.println("2. Ajouter un plat au Menu");
            System.out.println("3. Ajouter un plat à la commande");
            System.out.println("4. Voir la commande");
            System.out.println("5. Valider commande");
            System.out.println("6. Demander les stocks");
            System.out.println("7. Quitter");

            int choix = sc.nextInt();
            switch (choix) {
                case 1:
                    serveur.afficherMenu(menu);  // Le serveur affiche le menu
                    break;

                case 2:
                    // Le Gérant ajoute un plat au menu
                    System.out.print("Nom du Plat : ");
                    String nom = sc.next();
                    System.out.print("Prix du Plat (ex: 19,99) : ");
                    double prix = sc.nextDouble();
                    System.out.print("Type du Plat : ");
                    String type = sc.next();

                    Gerant.ajoutPlat(menu, nom, prix, type);

                    break;

                case 3:
                    serveur.prendreCommande(menu, commande);  // Le serveur prend la commande
                    break;

                case 4:
                    serveur.afficherCommande(commande);  // Afficher la Commande
                    break;

                case 5: // Confirmer la commande
                    serveur.afficherCommande(commande);
                    System.out.print("Confirmez votre commande (oui/non) : ");
                    String confirmation = sc.next();
                    if (confirmation.equalsIgnoreCase("oui")) {
                        System.out.print("Votre commande est en préparation...");
                        cuisinier.preparerCommande(commande); // Le cuisinier prépare la commande
                        System.out.println("Votre commande est prête !");
                        commande = new Commande();
                    } else {
                        System.out.println("Votre commande n'est pas prise en charge. Vous pouvez continuer à commander !");
                    }
                    break;

                case 6:
                    gerant.consulterStocks();  // Le Gérant consulte les stocks
                    break;

                case 7:
                    Serveur.bye(); // le serveur salut l'utilisateur en lui disant en revoir
                    return;

                default:
                    System.out.println("Ce choix n'existe pas, veuillez en choisir un existant.");
                    break;
            }
        }
    }
}
