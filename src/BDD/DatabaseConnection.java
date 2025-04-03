package BDD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// ou import java.sql.*;

public class DatabaseConnection {
    private static String url = "jdbc:mysql://localhost:3306"; //Connexion a la base de donnée
    private static String user = "root"; // Utilisation du root pour tout les pouvoirs
    private static String password = "TM26/04/2006"; // mot de passe du root

    //=========== Constructeur ================
    public DatabaseConnection(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    //-------------GETTER & SETTER----------------------
    public static String getUrl() {return url;}
    public void setUrl() {this.url = url;}

    public static String getUser() {return user;}
    public void setUser() {this.user = user;}

    public static String getPassword() {return password;}
    public void setPassword() {this.password = password;}


    //--------- PROGRAMME PRINCIPAL----------------
    //---------------------------------------------

    // Permet de se connecter a la base de donnée sur l'url donné ci-dessus
    // renvoi null si la connexion a échoué
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Permet de vérifier/tester la connexion à la base de donnée
    //affiche "Connexion à la base de donnée réussie !", si la connexion a été un succès
    //affiche "Échec de la connexion à la base de donnée", si la connexion à échoué
    public static void main(String[] args) {
        System.out.println("Connexion en cours ...");
        Connection conn = DatabaseConnection.getConnection();
        if (conn != null) {
            System.out.println("Connexion à la base de donnée réussie !");
        } else {
            System.out.println("Échec de la connexion à la base de donnée");
        }
    }
}
