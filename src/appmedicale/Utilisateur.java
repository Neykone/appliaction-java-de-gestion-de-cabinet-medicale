/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appmedicale;



public class Utilisateur {
    private int id;
    private String login;
    private String password;
    private String type; // "MEDECIN" ou "ASSISTANT"
    
    // Constructeurs
    public Utilisateur(int id,String login, String password, String type) {
        this.id=id;
        this.login = login;
        this.password = password;
        this.type = type;
    }
    
    public Utilisateur(String login, String password, String type) {
        this.login = login;
        this.password = password;
        this.type = type;
    }
    
    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public boolean isMedecin() {
        return "MEDECIN".equals(type);
    }
    
    public boolean isAssistante() {
        return "ASSISTANT".equals(type);
    }
}
