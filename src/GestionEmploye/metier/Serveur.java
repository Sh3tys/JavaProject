package GestionEmploye.metier;

import BDD.SaveInBdd.BddPlat;
import GestionEmploye.*;
import GestionStock.*;
import Backend.*;
import java.util.*;

import static java.awt.SystemColor.menu;

public class Serveur extends Employe {

    public Serveur(String nom, String prenom) {
        super(nom, prenom, "Serveur");
    }


    public void prendreCommande(Menu menu, Commande commande) {
        System.out.println(nom + " prend une commande.");
        afficherMenu(menu);  // Afficher le menu

        // Code pour prendre la commande de l'utilisateur
        System.out.print("Veuillez entrer le nom du plat choisi : ");
        Scanner sc = new Scanner(System.in);
        String platNom = sc.next();
        Plat platChoisi = menu.trouverPlatParNom(platNom); // Utiliser la méthode pour trouver le plat par nom
        if (platChoisi != null) {
            commande.ajouterPlat(platChoisi);
            System.out.println("Le plat " + platChoisi.getNom() + " a été ajouté à la commande.");
        } else {
            System.out.println("Plat non trouvé.");
        }
    }

    public void supprimerPlatDeCommande(Commande commande) {
        System.out.println(nom + " souhaite supprimer un plat de la commande.");
        afficherCommande(commande);  // Afficher la commande actuelle

        // Demande du plat à supprimer
        System.out.print("Veuillez entrer le nom du plat à supprimer : ");
        Scanner sc = new Scanner(System.in);
        String platNom = sc.nextLine();

        // Recherche du plat dans la commande
        Plat platChoisi = Commande.trouverPlatParNomDansCommande(commande, platNom);

        if (platChoisi != null) {
            commande.supprimerPlat(platChoisi);
            System.out.println("✅ Le plat " + platChoisi.getNom() + " a été supprimé de la commande.");
        } else {
            System.out.println("❌ Plat non trouvé dans la commande.");
        }
    }


    public static void afficherMenu(Menu menu) {
        menu.afficherMenu();
    }

    public static void bye() {
        System.out.println("Merci et à bientôt ! Au revoir.");
    }

    public void afficherCommande(Commande commande) {
        commande.afficher();
    }

    @Override
    public void effectuerTache(Commande commande) {
        // Le serveur effectue la tâche de prendre la commande
        System.out.println(nom +" " + prenom + " est :" + role);
    }
}
