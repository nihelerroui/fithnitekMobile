/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Avis;
import com.mycompany.myapp.services.AvisService;

/**
 *
 * @author Nihel
 */
public class EditAvisForm extends Form{
     private Avis avis; 

    private TextField tfCommentaire;
    
    public EditAvisForm(Form previous, Avis avis) {
        setTitle("Modifier Avis");
        setLayout(BoxLayout.y());
        this.avis = avis;
        
        tfCommentaire = new TextField(avis.getCommenraire(), "Commentaire");
        
         addAll(tfCommentaire);

        // Ajouter un bouton pour enregistrer les modifications
        Button modifier = new Button("Modifier");
        modifier.addActionListener(e -> {
            // Mettre à jour les données de la rec
            avis.setCommenraire(tfCommentaire.getText());
            AvisService.getInstance().updateAvis(avis);

            // Retourner à la liste des promos
            new ListAvisForm(previous).showBack();
        });
        add(modifier);
        
        // Ajouter un bouton pour annuler les modifications
        Button cancelButton = new Button("Annuler");
        cancelButton.addActionListener(e -> new ListAvisForm(previous).showBack());
        add(cancelButton);
            
    }    
}

