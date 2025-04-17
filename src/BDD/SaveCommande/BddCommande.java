package BDD.SaveCommande;

import java.sql.*;
import java.util.*;
import BDD.*;
import Backend.*;
import GestionStock.*;

public class BddCommande {
    public static void sauvegarderDansBdd(Commande commande) {
        Connection conn = DatabaseConnection.getConnection(
                BddSetup.geturlBdd(),
                DatabaseConnection.getUser(),
                DatabaseConnection.getPassword());

        try {
            // Insère la commande
            String insertCommandeSQL = "INSERT INTO commandes (date_commande) VALUES (NOW())";
            PreparedStatement psCommande = conn.prepareStatement(insertCommandeSQL, Statement.RETURN_GENERATED_KEYS);
            psCommande.executeUpdate();

            // Récupère l'ID de la commande insérée
            ResultSet rs = psCommande.getGeneratedKeys();
            int commandeId = -1;
            if (rs.next()) {
                commandeId = rs.getInt(1);
            }

            // Insère les plats liés à la commande
            String insertCommandePlatSQL = "INSERT INTO commande_plat (commande_id, plat_id) VALUES (?, ?)";
            PreparedStatement psCommandePlat = conn.prepareStatement(insertCommandePlatSQL);
            for (Plat plat : commande.getListPlats()) {
                psCommandePlat.setInt(1, commandeId);
                psCommandePlat.setInt(2, plat.getId()); // Assure-toi que Plat a bien un getId()
                psCommandePlat.executeUpdate();
            }

            System.out.println("Commande sauvegardée avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
