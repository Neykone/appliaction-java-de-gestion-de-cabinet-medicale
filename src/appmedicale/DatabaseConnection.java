/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appmedicale;





import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/cabinet_medical";
    private static final String USER = "root";
    private static final String PASSWORD = "Neymar55$$";
    
    private static Connection connection;
    
    public static Connection getConnection() {
        try {
            // Vérifier si la connexion est nulle ou fermée
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connexion à la base de données réussie !");
            }
        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la base de données: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
    
    // NE PAS appeler cette méthode dans les DAO - la connexion restera ouverte
    public static void closeConnection() {
        // Nous ne fermerons la connexion qu'à la fermeture de l'application
    }
    
    // Méthode pour fermer proprement à la fin
    public static void fermerApplication() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture: " + e.getMessage());
            }
        }
    }
}
