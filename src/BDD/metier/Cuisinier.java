package BDD.metier;
import BDD.Employe;

public class Cuisinier extends Employe {
    public Cuisinier(int id, String nom) {
        super(id, nom, "Cuisinier");
    }

    @Override
    public void effectuerTache() {
        System.out.println(nom + " prépare un plat.");
    }
}