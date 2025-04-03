package BDD;
import BDD.DatabaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BddSetup {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DatabaseConnection.getUrl(), DatabaseConnection.getUser(), DatabaseConnection.getPassword());
             Statement stmt = conn.createStatement()) {

            // Création de la base de données
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS restaurant");
            System.out.println("Base de données 'restaurant' créée avec succès (ou déjà existante).");

            // Connexion à la base de données créée
            try (Connection dbConn = DriverManager.getConnection(DatabaseConnection.getUrl() + "/restaurant", DatabaseConnection.getUser(), DatabaseConnection.getPassword());
                 Statement dbStmt = dbConn.createStatement()) {

                // Création de la table plats
                dbStmt.executeUpdate("CREATE TABLE IF NOT EXISTS plats (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "nom VARCHAR(100) NOT NULL, " +
                        "prix DECIMAL(10,2) NOT NULL)");

                // Création de la table stocks
                dbStmt.executeUpdate("CREATE TABLE IF NOT EXISTS stocks (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY," +
                                "ingredient VARCHAR(100) NOT NULL UNIQUE," +
                                "quantite INT NOT NULL)");

                // Création de la table commandes
                dbStmt.executeUpdate("CREATE TABLE IF NOT EXISTS commandes (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "plat_id INT NOT NULL, " +
                        "date_commande TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                        "FOREIGN KEY (plat_id) REFERENCES plats(id))");

                // Création de la table employes
                dbStmt.executeUpdate("CREATE TABLE IF NOT EXISTS employes (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "nom VARCHAR(100) NOT NULL, " +
                        "role ENUM('Serveur', 'Cuisinier', 'Gérant') NOT NULL)");

                // Création de la table plat_ingredients
                dbStmt.executeUpdate("CREATE TABLE IF NOT EXISTS plat_ingredients (" +
                        "plat_id INT, " +
                        "ingredient VARCHAR(100), " +
                        "quantite_requise INT, " +
                        "PRIMARY KEY (plat_id, ingredient), " +
                        "FOREIGN KEY (plat_id) REFERENCES plats(id), " +
                        "FOREIGN KEY (ingredient) REFERENCES stocks(ingredient))");

                System.out.println("Toutes les tables ont été créées avec succès.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
