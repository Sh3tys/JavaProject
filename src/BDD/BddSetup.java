package BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BddSetup {
    private static final String DATABASE_NAME = "restaurant";

    BddSetup() {}

    // Getter pour obtenir l'URL complète de la base de données restaurant
    public static String geturlBdd() {
        return DatabaseConnection.geturlMysql() + "/" + DATABASE_NAME;
    }

    // Méthode pour créer la base de données
    public static void createDatabase() {
        try (Connection conn = DriverManager.getConnection(DatabaseConnection.geturlMysql(), DatabaseConnection.getUser(), DatabaseConnection.getPassword());
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME);
            System.out.println("Base de données '" + DATABASE_NAME + "' créée avec succès (ou déjà existante).");

        } catch (SQLException e) {
            System.err.println("Erreur lors de la création de la base de données : " + e.getMessage());
        }
    }

    // Méthode pour créer toutes les tables
    public static void createTables() {
        try (Connection conn = DatabaseConnection.getConnection(BddSetup.geturlBdd(), DatabaseConnection.getUser(), DatabaseConnection.getPassword());
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS plats (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nom VARCHAR(100) NOT NULL, " +
                    "prix DECIMAL(10,2) NOT NULL)");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS stocks (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "ingredient VARCHAR(100) NOT NULL UNIQUE," +
                    "quantite INT NOT NULL)");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS commandes (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "plat_id INT NOT NULL, " +
                    "date_commande TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY (plat_id) REFERENCES plats(id))");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS employes (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nom VARCHAR(100) NOT NULL, " +
                    "role ENUM('Serveur', 'Cuisinier', 'Gérant') NOT NULL)");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS plat_ingredients (" +
                    "plat_id INT, " +
                    "ingredient VARCHAR(100), " +
                    "quantite_requise INT, " +
                    "PRIMARY KEY (plat_id, ingredient), " +
                    "FOREIGN KEY (plat_id) REFERENCES plats(id), " +
                    "FOREIGN KEY (ingredient) REFERENCES stocks(ingredient))");

            System.out.println("Toutes les tables ont été créées avec succès.");

        } catch (SQLException e) {
            System.err.println("Erreur lors de la création des tables : " + e.getMessage());
        }
    }

    // Méthode main pour tester la création
    public static void main(String[] args) {
        createDatabase();
        createTables();
    }
}
