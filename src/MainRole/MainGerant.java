package MainRole;

import BDD.SaveInBdd.*;
import Backend.*;
import GestionEmploye.metier.*;
import java.util.*;

public class MainGerant {
    public static void afficheMenuRole(Gerant gerant){
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. afficher employe");//gerant
            System.out.println("2. Ajouter employer");//gerant
            System.out.println("3. supprimé un employe");//gerant
            System.out.println("-1. Pour revenir au menu principale");

            int choix = sc.nextInt();
            switch (choix) {
                case 1:
                    gerant.afficherEmploye();
                    break;
                case 2:
                    System.out.print("Le métier du nouvelle employe (serveur, cuisinier, gerant) : ");
                    String metier = sc.next();

                    System.out.print("Le nom : ");
                    String nom = sc.next();
                    System.out.print("Le prenom : ");
                    String prenom = sc.next();

                    if (metier == "serveur") {
                        Serveur serveur2 = new Serveur(nom, prenom);
                        BddEmploye.ajouterEmploye(serveur2);
                    } else if (metier == "cuisinier") {
                        Cuisinier cuisinier2 = new Cuisinier(nom, prenom);
                        BddEmploye.ajouterEmploye(cuisinier2);
                    } else if (metier == "gerant") {
                        Gerant gerant2 = new Gerant(nom, prenom);
                        BddEmploye.ajouterEmploye(gerant2);
                    } else {
                        System.out.println("Ce metier n'est pas possible veuillez réessayer !");
                    }
                    break;

                case 3:
                    gerant.afficherEmploye();
                    System.out.print("Le nom de l'employé a supprimer : ");
                    nom = sc.next();
                    System.out.print("Le prenom de l'employé a supprimer : ");
                    prenom = sc.next();
                    gerant.deleteEmploye(nom, prenom);
                    break;

                case -1:
                    System.out.println("Vous revenez au menu principal");
                    return;

                default:
                    System.out.println("Cette commande n'est pas possible");
            }
        }
    }
    public static void afficheMenuMenu(Serveur serveur, Menu menu){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("0. Affiche le Menu");//gerant
            System.out.println("1. Ajouter un plat au Menu");//gerant
            System.out.println("2. supprimé plat du menu");//gerant
            System.out.println("-1. Pour revenir au menu principale");

            int choix = sc.nextInt();
            switch (choix) {
                case 0 :
                    Serveur.afficherMenu(menu);
                    break;
                case 1:
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

                case 2:
                    Gerant.supprimerPlatParNom(menu);
                    BddPlat.chargeMenu(menu);
                    break;

                case -1:
                    System.out.println("Vous revenez au menu principal");
                    return;

                default:
                    System.out.println("Cette commande n'est pas possible");
            }
        }
    }
    public static void afficheMenuStock(Gerant gerant) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Affiche les stocks");//gerant
            System.out.println("2. Retirer ingredient");//gerant
            System.out.println("3. Ajouter ingrédient");//gerant
            System.out.println("-1. Pour revenir au menu principale");

            int choix = sc.nextInt();
            switch (choix) {
                case 1:
                    gerant.consulterStocks();  // Le Gérant consulte les stocks
                    break;
                case 2:
                    System.out.print("Nom de l'ingredient a réduire : ");
                    String nom = sc.next();
                    System.out.print("quantite a supprime : ");
                    Double quantite = sc.nextDouble();
                    gerant.deleteIngredient(nom, quantite);
                    break;
                case 3:
                    System.out.print("nom de l'ingredient : ");
                    nom = sc.next();
                    System.out.print("quantite a ajouter : ");
                    quantite = sc.nextDouble();

                    Gerant.ajouterIngredients(nom, quantite);
                    break;
                case -1:
                    System.out.println("Vous revenez au menu principal");
                    return;

                default:
                    System.out.println("Cette commande n'est pas possible");
            }
        }
    }

}
