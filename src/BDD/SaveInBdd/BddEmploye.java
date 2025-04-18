package BDD.SaveInBdd;

import BDD.*;
import GestionEmploye.Employe;
import GestionEmploye.metier.*;
import java.sql.*;
import java.util.*;

public class BddEmploye {
    public static void ajouterEmploye(Employe employe) {
        Connection conn = DatabaseConnection.getConnection(BddSetup.geturlBdd(),
                DatabaseConnection.getUser(),
                DatabaseConnection.getPassword());
        String query = "INSERT INTO employes (nom, prenom, role) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, employe.getNom());
            stmt.setString(2, employe.getPrenom());
            stmt.setString(3, employe.getRole());
            stmt.executeUpdate();
            System.out.println("Employé ajouté : " + employe.getNom() + " " + employe.getPrenom());
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'employé : " + e.getMessage());
        }
    }

    public static List<Employe> chargerEmployes() {
        Connection conn = DatabaseConnection.getConnection(BddSetup.geturlBdd(),
                DatabaseConnection.getUser(),
                DatabaseConnection.getPassword());

        List<Employe> employes = new ArrayList<>();
        String query = "SELECT nom, prenom, role FROM employes";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String role = rs.getString("role");

                Employe employe = null;
                switch (role.toLowerCase()) {
                    case "serveur":
                        employe = new Serveur(nom, prenom);
                        break;
                    case "cuisinier":
                        employe = new Cuisinier(nom, prenom);
                        break;
                    case "gerant":
                        employe = new Gerant(nom, prenom);
                        break;
                    default:
                        System.out.println("Rôle inconnu : " + role);
                }

                if (employe != null) {
                    employes.add(employe);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors du chargement des employés : " + e.getMessage());
        }

        return employes;
    }

    public static void afficherTousLesEmployes() {
        List<Employe> employes = chargerEmployes();

        if (employes.isEmpty()) {
            System.out.println("Aucun employé trouvé dans la base de données.");
        } else {
            System.out.println("Liste des employés :");
            for (Employe employe : employes) {
                System.out.println("Nom : " + employe.getNom() + ", Prénom : " + employe.getPrenom() + ", Rôle : " + employe.getRole());
            }
        }
    }

    public static void supprimerEmployeParNomPrenom(String nom, String prenom) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("⚠Confirmation : Voulez-vous vraiment supprimer l'employé " + nom + " " + prenom + " ? (oui/non)");
        String confirmation = scanner.nextLine();

        if (!confirmation.equalsIgnoreCase("oui")) {
            System.out.println("❌ Suppression annulée.");
            return;
        }

        Connection conn = DatabaseConnection.getConnection(BddSetup.geturlBdd(),
                DatabaseConnection.getUser(),
                DatabaseConnection.getPassword());

        String query = "DELETE FROM employes WHERE nom = ? AND prenom = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Employé " + nom + " " + prenom + " supprimé avec succès.");
            } else {
                System.out.println("❗ Aucun employé trouvé avec ce nom et prénom.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'employé : " + e.getMessage());
        }
    }


}
