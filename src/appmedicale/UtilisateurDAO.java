/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appmedicale;


import java.sql.*;

public class UtilisateurDAO {
    
    // Authentifier un utilisateur
    public Utilisateur authentifier(String login, String password) {
        String sql = "SELECT * FROM Utilisateur WHERE login = ? AND password = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, login);
            pstmt.setString(2, password); // En production, il faudrait hasher le mot de passe
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Utilisateur(
                    rs.getInt("id"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("type")
                );
            }
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'authentification: " + e.getMessage());
        }
        
        return null;
    }

    
}
