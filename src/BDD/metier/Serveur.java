package BDD.metier;
import BDD.Employe;

public class Serveur extends Employe {
    public Serveur(int id, String nom) {
        super(id, nom, "Serveur");
    }

    @Override
    public void effectuerTache() {
        System.out.println(nom + " prend une commande.");
    }
}