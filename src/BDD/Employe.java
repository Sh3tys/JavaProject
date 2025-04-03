package BDD;

public abstract class Employe {
    protected int id;
    protected String nom;
    protected String role;

    //=========== Constructeur ================
    public Employe(int id, String nom, String role) {
        this.id = id;
        this.nom = nom;
        this.role = role;
    }


    //--------- PROGRAMME PRINCIPAL----------------
    //---------------------------------------------

    // Permet au sous class de Employe d'utiliser cette méthode
    public abstract void effectuerTache();
}
