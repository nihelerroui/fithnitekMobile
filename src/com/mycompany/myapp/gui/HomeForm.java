/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Nihel
 */
public class HomeForm extends Form{
    public HomeForm() {
        
        setTitle("page home");
        setLayout(BoxLayout.y());
        
        Button btnAddReclamation = new Button("Ajouter Reclamation");
        Button btnListReclamation  = new Button("Liste reclamations");
        Button btnAddAvis  = new Button("Ajouter avis");
        Button btnListAvis  = new Button("Liste des avis");
        
        
        btnListReclamation.addActionListener(e-> new ListReclamationForm(this).show());
        btnAddReclamation.addActionListener(e-> new AddReclamationForm(this).show());
        btnListAvis.addActionListener(e-> new ListAvisForm(this).show());
        btnAddAvis.addActionListener(e-> new AddAvisForm(this).show());
         addAll(btnListReclamation,btnAddReclamation,btnAddAvis,btnListAvis);
        
       
        
    }
    
    
}
