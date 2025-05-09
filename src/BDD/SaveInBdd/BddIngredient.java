package BDD.SaveInBdd;

import BDD.*;
import java.sql.*;

public class BddIngredient {

    // Ajoute un ingrédient avec sa quantité ou met à jour la quantité si l'ingrédient existe déjà
    public static void ajouterIngredient(String ingredient, double quantite) {
        String query = "INSERT INTO stocks (ingredient, quantite) VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE quantite = quantite + ?";
        try (Connection conn = DatabaseConnection.getConnection(BddSetup.geturlBdd(),
                DatabaseConnection.getUser(),
                DatabaseConnection.getPassword());
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, ingredient);
            stmt.setDouble(2, quantite);
            stmt.setDouble(3, quantite);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Vérifie si un ingrédient est disponible en quantité suffisante
    public static boolean verifierStock(String ingredient, double quantiteRequise) {
        String query = "SELECT quantite FROM stocks WHERE ingredient = ?";
        try (Connection conn = DatabaseConnection.getConnection(BddSetup.geturlBdd(),
                DatabaseConnection.getUser(),
                DatabaseConnection.getPassword());
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, ingredient);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("quantite") >= quantiteRequise;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retire une certaine quantité d'un ingrédient du stock
    public static void retirerIngredient(String ingredient, double nbrIngredient) {
        String query = "UPDATE stocks SET quantite = quantite - ? WHERE ingredient = ?";
        try (Connection conn = DatabaseConnection.getConnection(BddSetup.geturlBdd(),
                DatabaseConnection.getUser(),
                DatabaseConnection.getPassword());
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, nbrIngredient/2);
            stmt.setString(2, ingredient);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Affiche l'état actuel du stock : ingrédients et leurs quantités
    public static void afficherStock() {
        String query = "SELECT ingredient, quantite FROM stocks";
        try (Connection conn = DatabaseConnection.getConnection(BddSetup.geturlBdd(),
                DatabaseConnection.getUser(),
                DatabaseConnection.getPassword());
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("État du stock :");
            while (rs.next()) {
                String ingredient = rs.getString("ingredient");
                int quantite = rs.getInt("quantite");
                System.out.println(ingredient + ": " + quantite + " unités");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
