package BDD;

import java.sql.*;

public class BddSetup {
    private static final String DATABASE_NAME = "Bella_Tavola";

    private BddSetup() {}

    // Getter pour obtenir l'URL complète de la base de données
    public static String geturlBdd() {
        return DatabaseConnection.geturlMysql() + "/" + DATABASE_NAME;
    }

    // Création de la base de données
    public static void createDatabase() {
        try (Connection conn = DriverManager.getConnection(
                DatabaseConnection.geturlMysql(),
                DatabaseConnection.getUser(),
                DatabaseConnection.getPassword());
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME);
            System.out.println("Base de données '" + DATABASE_NAME + "' créée avec succès (ou déjà existante).\n");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la création de la base de données : " + e.getMessage());
        }
    }

    // Création des tables
    public static void createTables() {
        try (Connection conn = DatabaseConnection.getConnection(
                geturlBdd(),
                DatabaseConnection.getUser(),
                DatabaseConnection.getPassword());
             Statement stmt = conn.createStatement()) {

            // Table des plats
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS plats (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "nom VARCHAR(100) NOT NULL, " +
                            "prix DECIMAL(10,2) NOT NULL, " +
                            "type VARCHAR(50) NOT NULL" +
                            ")"
            );

            // Table des stocks
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS stocks (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "ingredient VARCHAR(100) NOT NULL UNIQUE, " +
                            "quantite INT NOT NULL" +
                            ")"
            );

            // Table des commandes, plat_id autorisé NULL pour initialisation sans plat
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS commandes (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "plat_id INT DEFAULT NULL, " +
                            "date_commande TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                            "FOREIGN KEY (plat_id) REFERENCES plats(id)" +
                            ")"
            );

            // Table des employés
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS employes (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "nom VARCHAR(100) NOT NULL, " +
                            "role ENUM('Serveur','Cuisinier','Gérant') NOT NULL" +
                            ")"
            );

            // Table des ingrédients par plat
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS plat_ingredients (" +
                            "plat_id INT NOT NULL, " +
                            "ingredient VARCHAR(100) NOT NULL, " +
                            "quantite_requise DOUBLE NOT NULL, " +
                            "PRIMARY KEY (plat_id, ingredient), " +
                            "FOREIGN KEY (plat_id) REFERENCES plats(id), " +
                            "FOREIGN KEY (ingredient) REFERENCES stocks(ingredient)" +
                            ")"
            );

            System.out.println("Toutes les tables ont été créées avec succès.\n");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la création des tables : " + e.getMessage());
        }
    }

    // Test de création
    public static void main(String[] args) {
        createDatabase();
        createTables();
    }
}
