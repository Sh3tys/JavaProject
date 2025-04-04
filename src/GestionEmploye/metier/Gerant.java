package GestionEmploye.metier;
import GestionEmploye.Employe;

public class Gerant extends Employe {

    //=========== Constructeur ================
    public Gerant(int id, String nom) {
        super(id, nom, "Gérant");
    }

    //Utilise une methode abstraite de Employe
    //Affiche le nom du gerant avec la concaténation de "surveille l'activité."
    @Override
    public void effectuerTache() {
        System.out.println(nom + " surveille l'activité.");
    }
}