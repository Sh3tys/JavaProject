import GestionEmploye.Employe;
import GestionStock.*;
import BDD.*;
import GestionEmploye.metier.*;
import Backend.*;
import BDD.SaveCommande.*;
import BDD.SaveIngredient.*;
import BDD.SaveEmploye.*;
import BDD.SavePlat.*;

import java.sql.SQLOutput;
import java.util.*;

public class testMilestone3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();
        Commande commande = new Commande();
        Stock stock = new Stock();

        Serveur serveur = null;
        Cuisinier cuisinier = null;
        Gerant gerant = null;


        List<Employe> listeEmployes = BddEmploye.chargerEmployes();

        for (Employe employe : listeEmployes) {
            if (employe instanceof Serveur) {
                serveur = (Serveur) employe;
            } else if (employe instanceof Cuisinier) {
                cuisinier = (Cuisinier) employe;
            } else if (employe instanceof Gerant) {
                gerant = (Gerant) employe;
            }
        }

        BddPlat.chargeMenu(menu); //Permet de charger les plats de la bdd avec leur ingrédients

        while (true) {
            System.out.println("");
            System.out.println("Bienvenue dans notre restaurant, veuillez choisir ce que vous voulez faire :");
            System.out.println("");

            System.out.println("1. Voir le menu");
            System.out.println("2. Ajouter un plat au Menu");
            System.out.println("3. Supprimer un plat à la commande");
            System.out.println("4. Ajouter un plat à la commande");
            System.out.println("5. Voir la commande");
            System.out.println("6. Valider commande");
            System.out.println("7. Demander les stocks");
            System.out.println("8. Retirer ingredient");
            System.out.println("9. afficher employe");
            System.out.println("10. Ajouter employer");
            System.out.println("11. Ajouter ingrédient");
            System.out.println("12. supprimé plat du menu");
            System.out.println("-1. Quitter");

            int choix = sc.nextInt();
            switch (choix) {
                case 1:
                    if (serveur != null) {
                        serveur.afficherMenu(menu);  // Le serveur affiche le menu
                    }

                    break;

                case 2:
                    // Le Gérant ajoute un plat au menu
                    System.out.print("Donnée l'Id du plat : ");
                    int id = sc.nextInt();
                    System.out.print("Nom du Plat : ");
                    String nom = sc.next();
                    System.out.print("Prix du Plat (ex: 19,99) : ");
                    double prix = sc.nextDouble();
                    System.out.print("Type du Plat : ");
                    String type = sc.next();

                    Gerant.ajoutPlat(menu, id, nom, prix, type);

                    break;

                case 3:
                    gerant.supprimerPlatParNom(menu); // Le serveur prend la commande
                    break;

                case 4:
                    serveur.prendreCommande(menu, commande);  // Le serveur prend la commande
                    break;

                case 5:
                    serveur.afficherCommande(commande);  // Afficher la Commande
                    break;

                case 6: // Confirmer la commande
                    serveur.afficherCommande(commande);
                    System.out.print("Confirmez votre commande (oui/non) : ");
                    String confirmation = sc.next();
                    if (confirmation.equalsIgnoreCase("oui")) {
                        System.out.print("Votre commande est en préparation...");
                        boolean valide = cuisinier.preparerCommande(commande);// Le cuisinier prépare la commande

                        if (valide) {
                            BddCommande.sauvegarderDansBdd(commande);
                            System.out.println("Votre commande est prête !");
                            commande = new Commande();
                        }
                    } else {
                        System.out.println("Votre commande n'est pas prise en charge. Vous pouvez continuer à commander !");
                    }
                    break;

                case 7:
                    gerant.consulterStocks();  // Le Gérant consulte les stocks
                    break;

                case 8:
                    System.out.print("Nom de l'ingredient a réduire : ");
                    nom = sc.next();
                    System.out.print("quantite a supprime : ");
                    Double quantite = sc.nextDouble();
                    gerant.deleteIngredient(nom,quantite);
                    break;

                case 9:
                    gerant.afficherEmploye();
                    break;

                case 10:
                    System.out.print("Le métier du nouvelle employe (serveur, cuisinier, gerant) : ");
                    String metier = sc.next();

                    System.out.print("Le nom : ");
                    nom = sc.next();
                    System.out.print("Le prenom : ");
                    String prenom = sc.next();

                    if(metier == "serveur") {
                        Serveur serveur2 = new Serveur(nom, prenom);
                        BddEmploye.ajouterEmploye(serveur2);
                    } else if (metier == "cuisinier") {
                        Cuisinier cuisinier2 = new Cuisinier(nom, prenom);
                        BddEmploye.ajouterEmploye(cuisinier2);
                    } else if(metier == "gerant") {
                        Gerant gerant2 = new Gerant(nom, prenom);
                        BddEmploye.ajouterEmploye(gerant2);
                    } else {
                        System.out.println("Ce metier n'est pas possible veuillez réessayer !");
                    }
                    break;


                case 11:
                    System.out.print("nom de l'ingredient : ");
                    nom = sc.next();
                    System.out.print("quantite a ajouter : ");
                    quantite = sc.nextDouble();

                    Gerant.ajouterIngredients(nom, quantite);
                    break;

                case 12 :

                    gerant.supprimerPlatParNom(menu);
                    BddPlat.chargeMenu(menu);
                    break;

                case -1:
                    Serveur.bye(); // le serveur salut l'utilisateur en lui disant en revoir
                    return;

                default:
                    System.out.println("Ce choix n'existe pas, veuillez en choisir un existant.");
                    break;
            }
        }
    }
}

