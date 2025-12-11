/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appmedicale;


import java.sql.Connection;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class PatientDAO {
    
    // Ajouter un patient
    public boolean ajouterPatient(Patient patient) {
        String sql = "INSERT INTO Patient (nom, telephone, email) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, patient.getNom());
            pstmt.setString(2, patient.getTelephone());
            pstmt.setString(3, patient.getEmail());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du patient: " + e.getMessage());
            return false;
        }
    }
    
    // Modifier un patient
    public boolean modifierPatient(Patient patient) {
        String sql = "UPDATE Patient SET nom = ?, telephone = ?, email = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, patient.getNom());
            pstmt.setString(2, patient.getTelephone());
            pstmt.setString(3, patient.getEmail());
            pstmt.setInt(4, patient.getId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification du patient: " + e.getMessage());
            return false;
        }
    }
    
    // Supprimer un patient
    public boolean supprimerPatient(int id) {
        String sql = "DELETE FROM Patient WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du patient: " + e.getMessage());
            return false;
        }
    }
    
    // Lister tous les patients
    public List<Patient> listerPatients() {
        List<Patient> patients = new LinkedList<>();
        String sql = "SELECT * FROM Patient ORDER BY nom";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Patient patient = new Patient(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("telephone"),
                    rs.getString("email")
                );
                patients.add(patient);
            }
            
        } catch (SQLException e) {
            System.err.println("Erreur lors du listage des patients: " + e.getMessage());
        }
        
        return patients;
    }
    
    // Trouver un patient par ID
    public Patient trouverParId(int id) {
        String sql = "SELECT * FROM Patient WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Patient(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("telephone"),
                    rs.getString("email")
                );
            }
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche du patient: " + e.getMessage());
        }
        
        return null;
    }
    
    // Rechercher des patients par nom
    public List<Patient> rechercherParNom(String nom) {
        List<Patient> patients = new LinkedList<>();
        String sql = "SELECT * FROM Patient WHERE nom LIKE ? ORDER BY nom";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + nom + "%");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Patient patient = new Patient(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("telephone"),
                    rs.getString("email")
                );
                patients.add(patient);
            }
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des patients: " + e.getMessage());
        }
        
        return patients;
    }
    

}
