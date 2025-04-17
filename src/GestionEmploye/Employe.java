package GestionEmploye;

import GestionStock.*;

public abstract class Employe {
    protected String nom;
    protected String prenom;
    protected String role;

    public Employe(String nom, String prenom, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    // Permet au sous-classe de Employe d'utiliser cette méthode
    public abstract void effectuerTache(Commande commande);
}
