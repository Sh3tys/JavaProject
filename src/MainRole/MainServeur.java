package MainRole;

import BDD.SaveInBdd.*;
import Backend.*;
import GestionEmploye.metier.*;
import GestionStock.*;
import java.util.*;

public class MainServeur {
    public static void afficheMenuServeur(Serveur serveur, Menu menu, Commande commande, Cuisinier cuisinier) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Voir le menu");//serveur
            System.out.println("2. Ajouter un plat à la commande");//serveur
            System.out.println("3. Supprimer un plat à la commande");//serveur
            System.out.println("4. Voir la commande");//serveur
            System.out.println("5. Valider commande");//serveur

            System.out.println("-1. Quitter");

            int choix = sc.nextInt();
            switch (choix) {
                case 1:
                    if (serveur != null) {
                        serveur.afficherMenu(menu);  // Le serveur affiche le menu
                    }

                    break;

                case 2:
                    serveur.prendreCommande(menu, commande);  // Le serveur prend la commande
                    break;

                case 3:
                    // REFAIRE
                    serveur.supprimerPlatDeCommande(commande);// Le serveur prend la commande
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

                case -1:
                    serveur.bye();
                    return;
                default:
                    System.out.println("Votre commande n'existe pas !");
            }
        }
    }
}
