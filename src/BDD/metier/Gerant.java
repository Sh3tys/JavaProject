package BDD.metier;
import BDD.Employe;

public class Gerant extends Employe {
    public Gerant(int id, String nom) {
        super(id, nom, "Gérant");
    }

    @Override
    public void effectuerTache() {
        System.out.println(nom + " surveille l'activité.");
    }
}