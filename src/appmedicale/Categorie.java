/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appmedicale;




public class Categorie {
    private int id;
    private String designation;
    private String description;
    
    // Constructeurs
    public Categorie() {}
    
    public Categorie(String designation, String description) {
        this.designation = designation;
        this.description = description;
    }
    
    public Categorie(int id, String designation, String description) {
        this.id = id;
        this.designation = designation;
        this.description = description;
    }
    
    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    @Override
    public String toString() {
        return designation;
    }

    
}
