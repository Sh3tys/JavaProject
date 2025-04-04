package GestionEmploye;

import GestionStock.Commande;

public abstract class Employe {
    protected int id;
    protected String nom;
    protected String role;

    public Employe(int id, String nom, String role) {
        this.id = id;
        this.nom = nom;
        this.role = role;
    }

    public String getNom() {
        return nom;
    }

    public String getRole() {
        return role;
    }

    // Permet au sous-classe de Employe d'utiliser cette méthode
    public abstract void effectuerTache(Commande commande);
}
