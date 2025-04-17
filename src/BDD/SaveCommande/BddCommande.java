package BDD.SaveCommande;

import java.sql.*;
import java.util.*;
import BDD.*;
import Backend.*;
import GestionStock.*;

public class BddCommande {
    public static void sauvegarderDansBdd(Commande commande) {
        // SQL pour insérer une commande
        String insertCommandeSQL = "INSERT INTO commandes (date_commande) VALUES (NOW())";
        String insertCommandePlatSQL = "INSERT INTO commande_plat (commande_id, plat_id) VALUES (?, ?)";
        String insertIngredientSQL = "INSERT INTO plat_ingredients (plat_id, ingredient, quantite_requise) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(
                BddSetup.geturlBdd(), DatabaseConnection.getUser(), DatabaseConnection.getPassword())) {

            conn.setAutoCommit(false); // Pour rollback en cas d’erreur

            // 1. Insertion de la commande
            int commandeId;
            try (PreparedStatement stmtCommande = conn.prepareStatement(insertCommandeSQL, Statement.RETURN_GENERATED_KEYS)) {
                stmtCommande.executeUpdate();
                ResultSet rs = stmtCommande.getGeneratedKeys();
                if (rs.next()) {
                    commandeId = rs.getInt(1);
                } else {
                    throw new SQLException("Erreur lors de la récupération de l'ID de la commande.");
                }
            }

            // ✅ On utilise ici commande.getListPlats()
            for (Plat plat : commande.getListPlats()) {
                // Récupération de l'ID du plat (si ce n'est pas déjà disponible dans Plat)
                int platId;
                try (PreparedStatement stmtPlat = conn.prepareStatement("SELECT id FROM plats WHERE nom = ?")) {
                    stmtPlat.setString(1, plat.getNom());
                    ResultSet rsPlat = stmtPlat.executeQuery();
                    if (rsPlat.next()) {
                        platId = rsPlat.getInt("id");
                    } else {
                        throw new SQLException("Plat non trouvé : " + plat.getNom());
                    }
                }

                // Insertion de la relation entre commande et plat
                try (PreparedStatement stmtPlat = conn.prepareStatement(insertCommandePlatSQL)) {
                    stmtPlat.setInt(1, commandeId);
                    stmtPlat.setInt(2, platId);  // Utilisation de l'ID du plat
                    stmtPlat.executeUpdate();
                }

                // Ingrédients du plat
                for (Map.Entry<String, Double> entry : plat.getIngredients().entrySet()) {
                    // Insertion des ingrédients associés au plat
                    try (PreparedStatement stmtIngredient = conn.prepareStatement(insertIngredientSQL)) {
                        stmtIngredient.setInt(1, platId);  // Utilisation de l'ID du plat
                        stmtIngredient.setString(2, entry.getKey());  // Nom de l'ingrédient
                        stmtIngredient.setDouble(3, entry.getValue());  // Quantité requise
                        stmtIngredient.executeUpdate();
                    }
                }
            }

            conn.commit();
            System.out.println("✅ Commande sauvegardée avec succès.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
