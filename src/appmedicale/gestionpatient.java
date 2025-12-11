/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package appmedicale;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URI;
import java.awt.Desktop;
/**
 *
 * @author HP
 */
public class gestionpatient extends javax.swing.JFrame {

    /**
     * Creates new form gestionpatient
     */
    public gestionpatient() {
        initComponents();
    }
    
    // Méthode pour valider le format d'email
private boolean isValidEmail(String email) {
    if (email == null) return false;
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    return email.matches(emailRegex);
}

// Méthode principale pour envoyer l'email
private boolean envoyerEmailRelance(String destinataire, String nomPatient, String message) {
    try {
        // Validation améliorée de l'email
        if (!isValidEmail(destinataire)) {
            JOptionPane.showMessageDialog(null,
                "❌ Adresse email invalide: " + destinataire,
                "Erreur de destination",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Configuration SMTP améliorée
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        // Timeouts
        props.put("mail.smtp.connectiontimeout", "15000");
        props.put("mail.smtp.timeout", "15000");
        props.put("mail.smtp.writetimeout", "15000");

        Session session = Session.getInstance(props,
            new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    // REMPLACER par votre mot de passe d'application Gmail
                    return new PasswordAuthentication("cheickkone110@gmail.com", "wfcv hyyz ottu lgin");
                }
            });

        // Debug (optionnel)
        session.setDebug(true);

        // Création du message
        Message email = new MimeMessage(session);
        email.setFrom(new InternetAddress("cheickkone110@gmail.com", "Centre Médical"));
        email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinataire));
        email.setSubject("🔔 Rappel de rendez-vous médical - " + nomPatient);
        email.setSentDate(new Date());
        
        // Utiliser MimeMultipart pour un meilleur formatage
        MimeMultipart multipart = new MimeMultipart();
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(message, "UTF-8");
        multipart.addBodyPart(messageBodyPart);
        email.setContent(multipart);

        // Envoi avec timeout
        Transport transport = session.getTransport("smtp");
        try {
            transport.connect();
            transport.sendMessage(email, email.getAllRecipients());
        } finally {
            transport.close();
        }

        // Confirmation
        JOptionPane.showMessageDialog(null,
            "✅ ENVOI AUTOMATIQUE RÉUSSI!\n\n" +
            "✓ Email transmis à Gmail\n" +
            "✓ Destinataire: " + destinataire + "\n" +
            "✓ Patient: " + nomPatient,
            "Envoi automatique terminé",
            JOptionPane.INFORMATION_MESSAGE);

        return true;

    } catch (AuthenticationFailedException e) {
        JOptionPane.showMessageDialog(null,
            "❌ ERREUR: Authentification Gmail échouée\n\n" +
            "Solutions possibles:\n" +
            "1. Activer l'authentification 2 facteurs sur Gmail\n" +
            "2. Générer un mot de passe d'application\n" +
            "3. Vérifier que 'Accès moins sécurisé' est activé\n\n" +
            "Détails: " + e.getMessage(),
            "Échec authentification",
            JOptionPane.ERROR_MESSAGE);
        return false;

    } catch (MessagingException e) {
        JOptionPane.showMessageDialog(null,
            "❌ ERREUR: Échec de livraison automatique\n\n" +
            "Causes possibles:\n" +
            "• Problème de connexion Internet\n" +
            "• Paramètres SMTP incorrects\n" +
            "• Blocage par le firewall/antivirus\n\n" +
            "Détails techniques: " + e.getMessage(),
            "Échec de livraison",
            JOptionPane.ERROR_MESSAGE);
        return false;

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null,
            "❌ ERREUR: Problème technique\n\n" +
            e.getMessage(),
            "Erreur technique",
            JOptionPane.ERROR_MESSAGE);
        return false;
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(43, 201, 153));
        jLabel1.setFont(new java.awt.Font("Stencil", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(43, 201, 153));
        jLabel1.setText("gestion des patients ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 290, -1));

        jLabel2.setFont(new java.awt.Font("SimSun", 1, 12)); // NOI18N
        jLabel2.setText("NOM");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 160, -1));

        jLabel3.setFont(new java.awt.Font("SimSun", 1, 12)); // NOI18N
        jLabel3.setText("TELEPHONE");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 160, -1));

        jLabel4.setFont(new java.awt.Font("SimSun", 1, 12)); // NOI18N
        jLabel4.setText("EMAIL");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 160, -1));

        jButton1.setBackground(new java.awt.Color(43, 201, 152));
        jButton1.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("ajouter patient");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 140, -1));

        jButton2.setBackground(new java.awt.Color(43, 201, 152));
        jButton2.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("modifier patient ");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, -1, -1));

        jButton3.setBackground(new java.awt.Color(43, 201, 152));
        jButton3.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("supprimer patient");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 140, 20));

        jButton4.setBackground(new java.awt.Color(43, 201, 152));
        jButton4.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("fermer");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 360, 130, 20));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "nom", "telephone", "email"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 10, 530, 260));

        jButton5.setBackground(new java.awt.Color(43, 201, 152));
        jButton5.setFont(new java.awt.Font("SimSun", 0, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("lister patients et rafraichir");
        jButton5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, 220, 30));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setBackground(new java.awt.Color(43, 201, 153));
        jLabel5.setFont(new java.awt.Font("Stencil", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(31, 153, 88));
        jLabel5.setText("gestion des patients ");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 290, -1));

        jLabel6.setFont(new java.awt.Font("SimSun", 1, 12)); // NOI18N
        jLabel6.setText("NOM");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));
        jPanel2.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 160, -1));

        jLabel7.setFont(new java.awt.Font("SimSun", 1, 12)); // NOI18N
        jLabel7.setText("TELEPHONE");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 160, -1));

        jLabel8.setFont(new java.awt.Font("SimSun", 1, 12)); // NOI18N
        jLabel8.setText("EMAIL");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));
        jPanel2.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 160, -1));

        jButton6.setBackground(new java.awt.Color(31, 153, 88));
        jButton6.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("ajouter patient");
        jButton6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 140, -1));

        jButton7.setBackground(new java.awt.Color(31, 153, 88));
        jButton7.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("modifier patient ");
        jButton7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, -1, -1));

        jButton8.setBackground(new java.awt.Color(31, 153, 88));
        jButton8.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("supprimer patient");
        jButton8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 140, 20));

        jButton9.setBackground(new java.awt.Color(31, 153, 88));
        jButton9.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("fermer");
        jButton9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 360, 130, 20));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "nom", "telephone", "email"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 10, 530, 260));

        jButton10.setBackground(new java.awt.Color(31, 153, 88));
        jButton10.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("lister patients et rafraichir");
        jButton10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, 240, 30));

        jButton11.setBackground(new java.awt.Color(31, 153, 88));
        jButton11.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("relancer patient");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 350, 200, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 940, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         
        
         
          String nom = jTextField1.getText().trim();
            String telephone = jTextField2.getText().trim();
            String email = jTextField3.getText().trim();
            
             if (nom.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Le nom est obligatoire", 
                    "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
             Patient nouveauPatient = new Patient(nom, telephone, email);
             PatientDAO patientDAO = new PatientDAO();
            boolean succes= patientDAO.ajouterPatient(nouveauPatient);
            
             if (succes) {
                JOptionPane.showMessageDialog(this, "Patient ajouté avec succès!");
                
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout du patient", 
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            }
             
             jTextField1.setText("");
             jTextField2.setText("");
             jTextField3.setText("");
             
             
    
            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       this.dispose();
            new DashboardAssistante().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String[] columns = {"ID", "Nom", "Téléphone", "Email"};
         DefaultTableModel  tableModel = new DefaultTableModel(columns, 0);
         
         try {
        // Récupérer tous les patients
        PatientDAO patientDAO = new PatientDAO();
        List<Patient> patients = patientDAO.listerPatients();
        
        // Ajouter chaque patient à la table
        for (Patient patient : patients) {
            Object[] row = {
                patient.getId(),
                patient.getNom(),
                patient.getTelephone(),
                patient.getEmail()
            };
            tableModel.addRow(row);
        }
        
        jTable1.setModel(tableModel);
        
        
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Erreur lors du chargement des patients: " + e.getMessage(),
            "Erreur", JOptionPane.ERROR_MESSAGE);
    }
         
         
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Vérifier si un patient est sélectionné
    int selectedRow = jTable1.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, 
            "Veuillez sélectionner un patient à modifier dans la table",
            "Aucune sélection", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
     try {
         
         // Récupérer l'ID du patient sélectionné
        int id = (int) jTable1.getValueAt(selectedRow, 0);
        
         // Récupérer les données des champs du formulaire
        String nouveauNom = jTextField1.getText().trim();
        String nouveauTelephone = jTextField2.getText().trim();
        String nouveauEmail = jTextField3.getText().trim();
        
         // Validation
        if (nouveauNom.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Le nom est obligatoire", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
         // Demander confirmation
        int confirmation = JOptionPane.showConfirmDialog(this, 
            "Êtes-vous sûr de vouloir modifier ce patient?\n\n" +
            "ID: " + id + "\n" +
            "Nouveau nom: " + nouveauNom + "\n" +
            "Nouveau téléphone: " + nouveauTelephone + "\n" +
            "Nouvel email: " + nouveauEmail,
            "Confirmation de modification",
            JOptionPane.YES_NO_OPTION);
        
        if (confirmation == JOptionPane.YES_OPTION) {
            // Créer l'objet Patient modifié
            Patient patientModifie = new Patient(id, nouveauNom, nouveauTelephone, nouveauEmail);
            
            // Appeler le DAO
            PatientDAO patientDAO = new PatientDAO();
            boolean succes = patientDAO.modifierPatient(patientModifie);
            
                if (succes) {
                JOptionPane.showMessageDialog(this, 
                    "Patient modifié avec succès!", 
                    "Succès", 
                    JOptionPane.INFORMATION_MESSAGE);}
                else {
                JOptionPane.showMessageDialog(this, 
                    "Erreur lors de la modification", 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
             jTextField1.setText("");
             jTextField2.setText("");
             jTextField3.setText("");
             
        
        
            
        
        
     
     } catch (Exception e) {
         JOptionPane.showMessageDialog(this, 
            "Erreur: " + e.getMessage(), 
            "Erreur", 
            JOptionPane.ERROR_MESSAGE);
         
     }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Vérifier si un patient est sélectionné
    int selectedRow = jTable1.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, 
            "Veuillez sélectionner un patient à supprimer dans la table",
            "Aucune sélection", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    try {
        
        // Récupérer les données du patient sélectionné
        int id = (int) jTable1.getValueAt(selectedRow, 0);
        String nom = (String) jTable1.getValueAt(selectedRow, 1);
        String telephone = (String) jTable1.getValueAt(selectedRow, 2);
        
        // Demander confirmation avec les détails du patient
        int confirmation = JOptionPane.showConfirmDialog(this, 
            "Êtes-vous sûr de vouloir supprimer ce patient ?\n\n" +
            "ID: " + id + "\n" +
            "Nom: " + nom + "\n" +
            "Téléphone: " + telephone + "\n\n" +
            "⚠️  Cette action est irréversible !",
            "Confirmation de suppression",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
         if (confirmation == JOptionPane.YES_OPTION) {
            // Appeler le DAO pour supprimer
            PatientDAO patientDAO = new PatientDAO();
            boolean succes = patientDAO.supprimerPatient(id);
            
            if (succes) {
                JOptionPane.showMessageDialog(this, 
                    "Patient supprimé avec succès!", 
                    "Succès", 
                    JOptionPane.INFORMATION_MESSAGE);}
            else {
                JOptionPane.showMessageDialog(this, 
                    "Erreur lors de la suppression du patient.\n" +
                    "Le patient a peut-être des consultations enregistrées.",
                    "Erreur de suppression", 
                    JOptionPane.ERROR_MESSAGE);
            }
         
         
         
         }
    
    
    }catch (Exception e){
         JOptionPane.showMessageDialog(this, 
            "Erreur lors de la suppression: " + e.getMessage(), 
            "Erreur", 
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        
      }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
        // Récupérer les valeurs des champs
        String nom = jTextField4.getText().trim();
        String telephone = jTextField5.getText().trim();
        String email = jTextField6.getText().trim();
        
        // Validation
        if (nom.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Le nom est obligatoire", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Créer l'objet Patient
        Patient nouveauPatient = new Patient(nom, telephone, email);
        
        // Ajouter dans la base de données
        PatientDAO patientDAO = new PatientDAO();
        boolean succes = patientDAO.ajouterPatient(nouveauPatient);
        
        if (succes) {
            JOptionPane.showMessageDialog(this, 
                "Patient ajouté avec succès!", 
                "Succès", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Vider les champs
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            
            
            
        } else {
            JOptionPane.showMessageDialog(this, 
                "Erreur lors de l'ajout du patient", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
        }
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Erreur: " + e.getMessage(), 
            "Erreur", 
            JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       try {
        // Vérifier si un patient est sélectionné
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez sélectionner un patient à modifier", 
                "Aucune sélection", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Récupérer l'ID du patient sélectionné
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        int idPatient = (int) model.getValueAt(selectedRow, 0);
        
        // Récupérer les nouvelles valeurs des champs
        String nouveauNom = jTextField4.getText().trim();
        String nouveauTelephone = jTextField5.getText().trim();
        String nouveauEmail = jTextField6.getText().trim();
        
        // Validation
        if (nouveauNom.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Le nom est obligatoire", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Créer l'objet Patient modifié
        Patient patientModifie = new Patient(idPatient, nouveauNom, nouveauTelephone, nouveauEmail);
        
        // Modifier dans la base de données
        PatientDAO patientDAO = new PatientDAO();
        boolean succes = patientDAO.modifierPatient(patientModifie);
        
        if (succes) {
            JOptionPane.showMessageDialog(this, 
                "Patient modifié avec succès!", 
                "Succès", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Vider les champs
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            
            
            
        } else {
            JOptionPane.showMessageDialog(this, 
                "Erreur lors de la modification du patient", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
        }
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Erreur: " + e.getMessage(), 
            "Erreur", 
            JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
       try {
        // Vérifier si un patient est sélectionné
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez sélectionner un patient à supprimer", 
                "Aucune sélection", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Récupérer l'ID et le nom du patient sélectionné
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        int idPatient = (int) model.getValueAt(selectedRow, 0);
        String nomPatient = (String) model.getValueAt(selectedRow, 1);
        
        // Demander confirmation
        int confirmation = JOptionPane.showConfirmDialog(this,
            "Êtes-vous sûr de vouloir supprimer le patient : " + nomPatient + " ?\n" +
            "Cette action est irréversible!",
            "Confirmation de suppression",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirmation == JOptionPane.YES_OPTION) {
            // Supprimer de la base de données
            PatientDAO patientDAO = new PatientDAO();
            boolean succes = patientDAO.supprimerPatient(idPatient);
            
            if (succes) {
                JOptionPane.showMessageDialog(this, 
                    "Patient supprimé avec succès!", 
                    "Succès", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                
                
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Erreur lors de la suppression du patient", 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Erreur: " + e.getMessage(), 
            "Erreur", 
            JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
                     this.dispose();
                     new DashboardAssistante().setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
       try {
        // Récupérer tous les patients
        PatientDAO patientDAO = new PatientDAO();
        List<Patient> patients = patientDAO.listerPatients();
        
        // Vider la table
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        
        // Ajouter chaque patient à la table
        for (Patient patient : patients) {
            Object[] row = {
                patient.getId(),
                patient.getNom(),
                patient.getTelephone(),
                patient.getEmail()
            };
            model.addRow(row);
        }
        
        // Message de confirmation
        JOptionPane.showMessageDialog(this,
            patients.size() + " patients chargés avec succès!",
            "Chargement réussi",
            JOptionPane.INFORMATION_MESSAGE);
            
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
            "Erreur lors du chargement des patients: " + e.getMessage(),
            "Erreur",
            JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
   
         try {
        // Vérifier qu'un patient est sélectionné dans la table des PATIENTS
        int selectedRow = jTable2.getSelectedRow();
       
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez sélectionner un patient à relancer", 
                "Aucune sélection", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Récupérer les informations du patient sélectionné
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        int idPatient = (int) model.getValueAt(selectedRow, 0);
        String nomPatient = (String) model.getValueAt(selectedRow, 1);
        String email = (String) model.getValueAt(selectedRow, 3); 

        // Vérifier que le patient a une adresse email
        if (email == null || email.trim().isEmpty() || !isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, 
                "Ce patient n'a pas d'adresse email valide enregistrée", 
                "Email manquant ou invalide", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Récupérer le patient complet depuis la base de données
        PatientDAO patientDAO = new PatientDAO();
        Patient patient = patientDAO.trouverParId(idPatient);
        
        if (patient == null) {
            JOptionPane.showMessageDialog(this, 
                "Patient non trouvé dans la base de données", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Vérifier si le patient a des rendez-vous à relancer
        ConsultationDAO consultationDAO = new ConsultationDAO();
        List<Consultation> rendezVous = consultationDAO.getRendezVousProchains(patient.getId());
        
        if (rendezVous.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Aucun rendez-vous à relancer pour " + nomPatient, 
                "Aucun rendez-vous", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Préparer le contenu de l'email
        StringBuilder messageRelance = new StringBuilder();
        messageRelance.append("Bonjour ").append(nomPatient).append(",\n\n");
        messageRelance.append("Nous vous rappelons vos prochains rendez-vous :\n\n");
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 'à' HH'h'mm");
        for (Consultation rdv : rendezVous) {
            messageRelance.append("• ").append(sdf.format(rdv.getDateConsultation()))
                   .append(" - ").append(rdv.getCategorie().getDesignation())
                   .append("\n");
        }
        
        messageRelance.append("\nMerci de nous contacter en cas d'empêchement.\n\n");
        messageRelance.append("Cordialement,\nVotre centre médical");

        // Afficher un aperçu du message
        int confirmation = JOptionPane.showConfirmDialog(this,
            "Aperçu de l'email à envoyer :\n\n" +
            "Destinataire : " + email + "\n" +
            "Patient : " + nomPatient + "\n" +
            rendezVous.size() + " rendez-vous à rappeler\n\n" +
            "Voulez-vous envoyer cet email de relance?",
            "Confirmation d'envoi d'email",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirmation == JOptionPane.YES_OPTION) {
            // Envoyer l'email de relance
            boolean emailEnvoye = envoyerEmailRelance(email, nomPatient, messageRelance.toString());
            
            if (emailEnvoye) {
                JOptionPane.showMessageDialog(this,
                    "✅ Email de relance envoyé avec succès!\n\n" +
                    "Patient : " + nomPatient + "\n" +
                    "Email : " + email + "\n" +
                    rendezVous.size() + " rendez-vous rappelés",
                    "Email envoyé",
                    JOptionPane.INFORMATION_MESSAGE);
                    
            } else {
                JOptionPane.showMessageDialog(this,
                    "❌ Échec de l'envoi de l'email de relance\n" +
                    "Vérifiez la configuration email et la connexion internet",
                    "Erreur d'envoi",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
            "❌ Erreur: " + e.getMessage(),
            "Erreur",
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }

    
       
 

    }//GEN-LAST:event_jButton11ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(gestionpatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gestionpatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gestionpatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gestionpatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gestionpatient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
