/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appmedicale;


import java.util.Date;

public class Consultation {
    private int id;
    private Date dateConsultation;
    private String description;
    private double prix;
    private Patient patient;
    private Categorie categorie;
     private String statutPaiement;
    
    // Constructeurs
    public Consultation() {}
    
    public Consultation(Date dateConsultation, String description, double prix, Patient patient, Categorie categorie) {
        this.dateConsultation = dateConsultation;
        this.description = description;
        this.prix = prix;
        this.patient = patient;
        this.categorie = categorie;
    }
    
    public Consultation(int id, Date dateConsultation, String description, double prix, Patient patient, Categorie categorie) {
        this.id = id;
        this.dateConsultation = dateConsultation;
        this.description = description;
        this.prix = prix;
        this.patient = patient;
        this.categorie = categorie;
    }
    
     public Consultation(int id, Date dateConsultation, String description, double prix, 
                       Patient patient, Categorie categorie, String statutPaiement) {
        this.id = id;
        this.dateConsultation = dateConsultation;
        this.description = description;
        this.prix = prix;
        this.patient = patient;
        this.categorie = categorie;
        this.statutPaiement = statutPaiement;
    }
    
    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public Date getDateConsultation() { return dateConsultation; }
    public void setDateConsultation(Date dateConsultation) { this.dateConsultation = dateConsultation; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
    
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    
    public Categorie getCategorie() { return categorie; }
    public void setCategorie(Categorie categorie) { this.categorie = categorie; }
    
     public String getStatutPaiement() { return statutPaiement; }
    public void setStatutPaiement(String statutPaiement) { this.statutPaiement = statutPaiement; }
    
    @Override
    public String toString() {
        return "Consultation du " + dateConsultation + " - " + patient.getNom() +"- "+ categorie.getDesignation();
    }

    
}
