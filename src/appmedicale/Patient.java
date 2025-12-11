/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appmedicale;



public class Patient {
    private int id;
    private String nom;
    private String telephone;
    private String email;
    
    // Constructeurs
    public Patient() {}
    
    public Patient(String nom, String telephone, String email) {
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
    }
    
    public Patient(int id, String nom, String telephone, String email) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
    }
    
    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    @Override
    public String toString() {
        return nom + " - " + telephone;
    }
}
