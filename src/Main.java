import BDD.SaveInBdd.BddCommande;
import BDD.SaveInBdd.BddEmploye;
import BDD.SaveInBdd.BddPlat;
import Backend.Menu;
import GestionEmploye.Employe;
import GestionEmploye.metier.Cuisinier;
import GestionEmploye.metier.Gerant;
import GestionEmploye.metier.Serveur;
import GestionStock.Commande;
import GestionStock.Stock;
import MainRole.*;
import java.util.List;
import java.util.Scanner;

public class Main {
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

                    System.out.println("1. Affiche le Menu du Serveur");
                    System.out.println("2. Affiche le Menu de la gestion des Roles");
                    System.out.println("3. Affiche le Menu de la gestion du Menu");
                    System.out.println("4. Affiche le Menu de la gestion des Stocks");
                    System.out.println("-1. Quitter");

                    int choix = sc.nextInt();
                    switch (choix) {
                        case 1:
                            MainServeur.afficheMenuServeur(serveur, menu, commande, cuisinier);
                            break;

                        case 2:
                            MainGerant.afficheMenuRole(gerant);
                            break;

                        case 3:
                            MainGerant.afficheMenuMenu(serveur, menu);
                            break;

                        case 4:
                            MainGerant.afficheMenuStock(gerant);
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
