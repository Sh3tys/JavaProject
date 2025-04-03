package BDD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Stock {

    //=========== Constructeur ================
    public Stock(){}


    //--------- PROGRAMME PRINCIPAL----------------
    //---------------------------------------------

    //Permet d'ajouter une ingredient avec sa quantité
    public static void ajouterIngredient(String ingredient, int quantite) {
        String query = "INSERT INTO stocks (ingredient, quantite) VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE quantite = quantite + ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, ingredient);
            stmt.setInt(2, quantite);
            stmt.setInt(3, quantite);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean verifierStock(String ingredient, int quantiteRequise) {
        String query = "SELECT quantite FROM stocks WHERE ingredient = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, ingredient);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("quantite") >= quantiteRequise;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void retirerIngredient(String ingredient, int quantite) {
        String query = "UPDATE stocks SET quantite = quantite - ? WHERE ingredient = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, quantite);
            stmt.setString(2, ingredient);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

