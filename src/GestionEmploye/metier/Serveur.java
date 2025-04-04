package GestionEmploye.metier;
import GestionEmploye.Employe;

public class Serveur extends Employe {

    //=========== Constructeur ================
    public Serveur(int id, String nom) {
        super(id, nom, "Serveur");
    }

    //Utilise une methode abstraite de Employe
    //Affiche le nom du serveur la concaténation de "prend une commande."
    @Override
    public void effectuerTache() {
        System.out.println(nom + " prend une commande.");
    }
}