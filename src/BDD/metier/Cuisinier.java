package BDD.metier;
import BDD.Employe;

public class Cuisinier extends Employe {

    //=========== Constructeur ================
    public Cuisinier(int id, String nom) {
        super(id, nom, "Cuisinier");
    }

    //Utilise une methode abstraite de Employe
    //Affiche le nom du cuisinier avec la concaténation de "préparer un plat"
    @Override
    public void effectuerTache() {
        System.out.println(nom + " prépare un plat.");
    }
}