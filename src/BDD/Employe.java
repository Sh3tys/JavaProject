package BDD;

public abstract class Employe {
    protected int id;
    protected String nom;
    protected String role;

    public Employe(int id, String nom, String role) {
        this.id = id;
        this.nom = nom;
        this.role = role;
    }

    public abstract void effectuerTache();
}
