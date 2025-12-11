/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appmedicale;



import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CategorieDAO {
    
    // Lister toutes les catégories
    public List<Categorie> listerCategories() {
        List<Categorie> categories = new LinkedList<>();
        String sql = "SELECT * FROM Categorie ORDER BY designation";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Categorie categorie = new Categorie(
                    rs.getInt("id"),
                    rs.getString("designation"),
                    rs.getString("description")
                );
                categories.add(categorie);
            }
            
        } catch (SQLException e) {
            System.err.println("Erreur lors du listage des catégories: " + e.getMessage());
        }
        
        return categories;
    }
    
    // Trouver une catégorie par ID
    public Categorie trouverParId(int id) {
        String sql = "SELECT * FROM Categorie WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Categorie(
                    rs.getInt("id"),
                    rs.getString("designation"),
                    rs.getString("description")
                );
            }
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche de la catégorie: " + e.getMessage());
        }
        
        return null;
    }

    
}
