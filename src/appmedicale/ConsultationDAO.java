/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appmedicale;


import java.util.Date;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ConsultationDAO {
    
    // Ajouter une consultation
    public boolean ajouterConsultation(Consultation consultation) {
        String sql = "INSERT INTO Consultation (date_consultation, description, prix, patient_id, categorie_id) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setTimestamp(1, new Timestamp(consultation.getDateConsultation().getTime()));
            pstmt.setString(2, consultation.getDescription());
            pstmt.setDouble(3, consultation.getPrix());
            pstmt.setInt(4, consultation.getPatient().getId());
            pstmt.setInt(5, consultation.getCategorie().getId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la consultation: " + e.getMessage());
            return false;
        }
    }
    
    // Obtenir les consultations d'une date spécifique
    public List<Consultation> getConsultationsDuJour(Date date) {
        List<Consultation> consultations = new LinkedList<>();
        String sql = "SELECT c.*, p.nom, p.telephone, p.email, cat.designation, cat.description " +
                     "FROM Consultation c " +
                     "JOIN Patient p ON c.patient_id = p.id " +
                     "JOIN Categorie cat ON c.categorie_id = cat.id " +
                     "WHERE DATE(c.date_consultation) = ? " +
                     "ORDER BY c.date_consultation";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Patient patient = new Patient(
                    rs.getInt("patient_id"),
                    rs.getString("nom"),
                    rs.getString("telephone"),
                    rs.getString("email")
                );
                
                Categorie categorie = new Categorie(
                    rs.getInt("categorie_id"),
                    rs.getString("designation"),
                    rs.getString("description")
                );
                
                Consultation consultation = new Consultation(
                    rs.getInt("id"),
                    new Date(rs.getTimestamp("date_consultation").getTime()),
                    rs.getString("description"),
                    rs.getDouble("prix"),
                    patient,
                    categorie
                );
                
                consultations.add(consultation);
            }
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des consultations: " + e.getMessage());
        }
        
        return consultations;
    }
    
    // Récupérer toutes les consultations
public List<Consultation> getToutesConsultations() {
    List<Consultation> consultations = new LinkedList<>();
    
    String sql = "SELECT c.*, p.nom, p.telephone, p.email, cat.designation, cat.description " +
                 "FROM Consultation c " +
                 "JOIN Patient p ON c.patient_id = p.id " +
                 "JOIN Categorie cat ON c.categorie_id = cat.id " +
                 "ORDER BY c.date_consultation DESC";
    
    try (Connection conn = DatabaseConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        
        while (rs.next()) {
            Patient patient = new Patient(
                rs.getInt("patient_id"),
                rs.getString("nom"),
                rs.getString("telephone"),
                rs.getString("email")
            );
            
            Categorie categorie = new Categorie(
                rs.getInt("categorie_id"),
                rs.getString("designation"),
                rs.getString("description")
            );
            
            Consultation consultation = new Consultation(
                rs.getInt("id"),
                new Date(rs.getTimestamp("date_consultation").getTime()),
                rs.getString("description"),
                rs.getDouble("prix"),
                patient,
                categorie
            );
            
            consultations.add(consultation);
        }
        
    } catch (SQLException e) {
        System.err.println("Erreur récupération consultations: " + e.getMessage());
    }
    
    return consultations;
}

// Mettre à jour le statut de paiement
public boolean mettreAJourStatutPaiement(int consultationId, String statut) {
    String sql = "UPDATE Consultation SET statut_paiement = ? WHERE id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, statut);
        pstmt.setInt(2, consultationId);
        
        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0;
        
    } catch (SQLException e) {
        System.err.println("Erreur mise à jour statut paiement: " + e.getMessage());
        return false;
    }
}

public Consultation trouverParId(int id) {
    String sql = "SELECT c.*, p.nom, p.telephone, p.email, cat.designation, cat.description " +
                 "FROM Consultation c " +
                 "JOIN Patient p ON c.patient_id = p.id " +
                 "JOIN Categorie cat ON c.categorie_id = cat.id " +
                 "WHERE c.id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            Patient patient = new Patient(
                rs.getInt("patient_id"),
                rs.getString("nom"),
                rs.getString("telephone"),
                rs.getString("email")
            );
            
            Categorie categorie = new Categorie(
                rs.getInt("categorie_id"),
                rs.getString("designation"),
                rs.getString("description")
            );
            
            Consultation consultation = new Consultation(
                rs.getInt("id"),
                new Date(rs.getTimestamp("date_consultation").getTime()),
                rs.getString("description"),
                rs.getDouble("prix"),
                patient,
                categorie,
                rs.getString("statut_paiement")
            );
            
            return consultation;
        }
        
    } catch (SQLException e) {
        System.err.println("Erreur recherche consultation: " + e.getMessage());
    }
    
    return null;
}

public boolean modifierConsultation(Consultation consultation) {
    String sql = "UPDATE Consultation SET date_consultation = ?, description = ?, prix = ?, patient_id = ?, categorie_id = ? WHERE id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setTimestamp(1, new Timestamp(consultation.getDateConsultation().getTime()));
        pstmt.setString(2, consultation.getDescription());
        pstmt.setDouble(3, consultation.getPrix());
        pstmt.setInt(4, consultation.getPatient().getId());
        pstmt.setInt(5, consultation.getCategorie().getId());
        pstmt.setInt(6, consultation.getId());
        
        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0;
        
    } catch (SQLException e) {
        System.err.println("Erreur modification consultation: " + e.getMessage());
        return false;
    }
}

public boolean supprimerConsultation(int idConsultation) {
    String sql = "DELETE FROM Consultation WHERE id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, idConsultation);
        
        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0;
        
    } catch (SQLException e) {
        System.err.println("Erreur lors de la suppression de la consultation: " + e.getMessage());
        return false;
    }
}


public List<Consultation> getRendezVousProchains(int patientId) {
    List<Consultation> consultations = new LinkedList<>();
    
    System.out.println("🔍 Recherche RDV pour patient ID: " + patientId);
    
    String sql = "SELECT c.*, p.nom, p.telephone, p.email, cat.designation, cat.description " +
                 "FROM Consultation c " +
                 "JOIN Patient p ON c.patient_id = p.id " +
                 "JOIN Categorie cat ON c.categorie_id = cat.id " +
                 "WHERE c.patient_id = ? " +
                 "ORDER BY c.date_consultation"; // Supprimer les filtres temporairement
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, patientId);
        ResultSet rs = pstmt.executeQuery();
        
        int count = 0;
        while (rs.next()) {
            count++;
            Date rdvDate = new Date(rs.getTimestamp("date_consultation").getTime());
            System.out.println("📅 RDV trouvé: " + rdvDate + " - Statut: " + rs.getString("statut_paiement"));
            
            Patient patient = new Patient(
                rs.getInt("patient_id"),
                rs.getString("nom"),
                rs.getString("telephone"),
                rs.getString("email")
            );
            
            Categorie categorie = new Categorie(
                rs.getInt("categorie_id"),
                rs.getString("designation"),
                rs.getString("description")
            );
            
            Consultation consultation = new Consultation(
                rs.getInt("id"),
                rdvDate,
                rs.getString("description"),
                rs.getDouble("prix"),
                patient,
                categorie,
                rs.getString("statut_paiement")
            );
            
            consultations.add(consultation);
        }
        
        System.out.println("✅ " + count + " RDV(s) trouvé(s) au total");
        
    } catch (SQLException e) {
        System.err.println("❌ Erreur récupération rendez-vous: " + e.getMessage());
        e.printStackTrace();
    }
    
    return consultations;
}

public List<Consultation> getConsultationsAujourdhui() {
    List<Consultation> consultations = new LinkedList<>();
    
    String sql = "SELECT c.*, p.nom, p.telephone, p.email, cat.designation, cat.description " +
                 "FROM Consultation c " +
                 "JOIN Patient p ON c.patient_id = p.id " +
                 "JOIN Categorie cat ON c.categorie_id = cat.id " +
                 "WHERE DATE(c.date_consultation) = CURDATE() " +
                 "ORDER BY c.date_consultation ASC";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            Patient patient = new Patient(
                rs.getInt("patient_id"),
                rs.getString("nom"),
                rs.getString("telephone"),
                rs.getString("email")
            );
            
            Categorie categorie = new Categorie(
                rs.getInt("categorie_id"),
                rs.getString("designation"),
                rs.getString("description")
            );
            
            Consultation consultation = new Consultation(
                rs.getInt("id"),
                new Date(rs.getTimestamp("date_consultation").getTime()),
                rs.getString("description"),
                rs.getDouble("prix"),
                patient,
                categorie,
                rs.getString("statut_paiement")
            );
            
            consultations.add(consultation);
        }
        
    } catch (SQLException e) {
        System.err.println("Erreur récupération consultations aujourd'hui: " + e.getMessage());
    }
    
    return consultations;
}

// Dans ConsultationDAO.java - à ajouter
public List<Consultation> getConsultationsParMois(int mois, int annee) {
    List<Consultation> consultations = new LinkedList<>();
    
    String sql = "SELECT c.*, p.nom, p.telephone, p.email, cat.designation, cat.description " +
                 "FROM Consultation c " +
                 "JOIN Patient p ON c.patient_id = p.id " +
                 "JOIN Categorie cat ON c.categorie_id = cat.id " +
                 "WHERE MONTH(c.date_consultation) = ? AND YEAR(c.date_consultation) = ? " +
                 "ORDER BY c.date_consultation";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, mois);
        pstmt.setInt(2, annee);
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            Patient patient = new Patient(
                rs.getInt("patient_id"),
                rs.getString("nom"),
                rs.getString("telephone"),
                rs.getString("email")
            );
            
            Categorie categorie = new Categorie(
                rs.getInt("categorie_id"),
                rs.getString("designation"),
                rs.getString("description")
            );
            
            Consultation consultation = new Consultation(
                rs.getInt("id"),
                new Date(rs.getTimestamp("date_consultation").getTime()),
                rs.getString("description"),
                rs.getDouble("prix"),
                patient,
                categorie,
                rs.getString("statut_paiement")
            );
            
            consultations.add(consultation);
        }
        
    } catch (SQLException e) {
        System.err.println("Erreur getConsultationsParMois: " + e.getMessage());
    }
    
    return consultations;
}
}


