/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

import com.mycompany.myapp.entities.Avis;
import com.mycompany.myapp.services.AvisService;
import java.util.ArrayList;

/**
 *
 * @author Nihel
 */
public class ListAvisForm extends Form{
    public ListAvisForm(Form previous) {
        setTitle("List Ais");
        setLayout(BoxLayout.y());
         Label label = new Label("Liste des avis :");
        add(label);
        ArrayList<Avis> avis = AvisService.getInstance().getAllAvis();

        for (Avis a : avis) {
            addElement(a);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
    
     public void addElement(Avis avis) {
        AvisService as = new AvisService();
    

        Label commentaire = new Label("Commentaire : " + avis.getCommenraire());
        
        Button detail = new Button("Détails");
        detail.addActionListener(e -> {
            Dialog.show("Détails", "Commentaire :"+avis.getCommenraire(), "OK", null);
        });
        
        Button supprimer =new Button("Supprimer");
            supprimer.addActionListener(e -> {
               Dialog alert = new Dialog("Confirmation");
                SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cet avis?");
                alert.add(message);
                Button ok = new Button("Confirmer");
                Button cancel = new Button(new Command("Annuler"));
                //User clicks on ok to delete account
                ok.addActionListener((ActionListener) (ActionEvent evt) -> {
                    as.deleteAvis(avis.getId());
                   
                    alert.dispose();
                    refreshTheme();
                     
               });
                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();
                       
               
             });
            
            
         Button modifier = new Button("Modifier");
            modifier.addActionListener(e-> new EditAvisForm(this,avis).show());
        

        
      
        addAll(commentaire,detail,modifier,supprimer);
        
       

    }
    
}
