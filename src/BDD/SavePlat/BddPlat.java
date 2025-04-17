package BDD.SavePlat;

import java.sql.*;

import BDD.*;
import Backend.*;
import java.util.*;

public class BddPlat {
    public static void sauvegarderPlat(Plat plat) {
        String insertPlatSQL = "INSERT INTO plats (nom, prix, type) VALUES (?, ?, ?)";
        String insertPlatIngredientSQL = "INSERT INTO plat_ingredients (plat_id, ingredient, quantite_requise) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(
                BddSetup.geturlBdd(), DatabaseConnection.getUser(), DatabaseConnection.getPassword())) {

            conn.setAutoCommit(false); // pour rollback en cas d’erreur

            // 1. Insertion du plat
            int platId;
            try (PreparedStatement stmtPlat = conn.prepareStatement(insertPlatSQL, Statement.RETURN_GENERATED_KEYS)) {
                stmtPlat.setString(1, plat.getNom());
                stmtPlat.setDouble(2, plat.getPrix());
                stmtPlat.setString(3, plat.getType());
                stmtPlat.executeUpdate();

                // Récupération de l'ID du plat inséré
                ResultSet rs = stmtPlat.getGeneratedKeys();
                if (rs.next()) {
                    platId = rs.getInt(1);
                    plat.setId(platId);  // Assigner l'ID récupéré à l'objet Plat
                } else {
                    throw new SQLException("Erreur lors de la récupération de l'ID du plat.");
                }
            }

            // 2. Insertion des ingrédients associés au plat
            for (Map.Entry<String, Double> entry : plat.getIngredients().entrySet()) {
                try (PreparedStatement stmtPlatIngredient = conn.prepareStatement(insertPlatIngredientSQL)) {
                    stmtPlatIngredient.setInt(1, plat.getId());  // Utilisation de l'ID du plat
                    stmtPlatIngredient.setString(2, entry.getKey());
                    stmtPlatIngredient.setDouble(3, entry.getValue());
                    stmtPlatIngredient.executeUpdate();
                }
            }

            conn.commit();
            System.out.println("✅ Plat sauvegardé avec succès.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Erreur lors de la sauvegarde du plat : " + e.getMessage());
        }
    }

}
