package BDD.SaveInBdd;

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

    public static void chargeMenu(Menu menu) {
        String sqlPlats = "SELECT * FROM plats";
        String sqlIngredients = "SELECT ingredient, quantite_requise FROM plat_ingredients WHERE plat_id = ?";

        try (Connection conn = DatabaseConnection.getConnection(
                BddSetup.geturlBdd(), DatabaseConnection.getUser(), DatabaseConnection.getPassword());
             PreparedStatement psPlats = conn.prepareStatement(sqlPlats);
             ResultSet rsPlats = psPlats.executeQuery();
             PreparedStatement psIngredients = conn.prepareStatement(sqlIngredients)) {

            while (rsPlats.next()) {
                int id = rsPlats.getInt("id");
                String nom = rsPlats.getString("nom");
                double prix = rsPlats.getDouble("prix");
                String type = rsPlats.getString("type");

                // Récupération des ingrédients du plat
                Map<String, Double> ingredients = new HashMap<>();
                psIngredients.setInt(1, id);
                try (ResultSet rsIng = psIngredients.executeQuery()) {
                    while (rsIng.next()) {
                        String ingredient = rsIng.getString("ingredient");
                        double quantite = rsIng.getDouble("quantite_requise");
                        ingredients.put(ingredient, quantite);
                    }
                }

                // Création de l'objet Plat et ajout au menu
                Plat plat = new Plat(id, nom, prix, type, ingredients);
                menu.addPlat(plat);
            }

            System.out.println("✔️ Tous les plats ont été chargés depuis la base de données.");
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors du chargement des plats : " + e.getMessage());
        }
    }

    public static void supprimerPlatParNom(String nomPlat) {
        String selectPlatIdSQL = "SELECT id FROM plats WHERE nom = ?";
        String deleteCommandePlatSQL = "DELETE FROM commande_plat WHERE plat_id = ?";
        String deleteIngredientsSQL = "DELETE FROM plat_ingredients WHERE plat_id = ?";
        String deletePlatSQL = "DELETE FROM plats WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(
                BddSetup.geturlBdd(), DatabaseConnection.getUser(), DatabaseConnection.getPassword())) {

            conn.setAutoCommit(false); // Pour rollback en cas d’erreur

            int platId;

            // 1. Récupérer l'ID du plat
            try (PreparedStatement selectStmt = conn.prepareStatement(selectPlatIdSQL)) {
                selectStmt.setString(1, nomPlat);
                ResultSet rs = selectStmt.executeQuery();
                if (rs.next()) {
                    platId = rs.getInt("id");
                } else {
                    System.out.println("Aucun plat trouvé avec le nom : " + nomPlat);
                    return;
                }
            }

            // 2. Supprimer les lignes dans commande_plat
            try (PreparedStatement deleteCommandeStmt = conn.prepareStatement(deleteCommandePlatSQL)) {
                deleteCommandeStmt.setInt(1, platId);
                deleteCommandeStmt.executeUpdate();
            }

            // 3. Supprimer les ingrédients associés
            try (PreparedStatement deleteIngredientsStmt = conn.prepareStatement(deleteIngredientsSQL)) {
                deleteIngredientsStmt.setInt(1, platId);
                deleteIngredientsStmt.executeUpdate();
            }

            // 4. Supprimer le plat
            try (PreparedStatement deletePlatStmt = conn.prepareStatement(deletePlatSQL)) {
                deletePlatStmt.setInt(1, platId);
                deletePlatStmt.executeUpdate();
            }

            conn.commit();
            System.out.println("Plat supprimé avec succès.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
