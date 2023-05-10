/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Avis;
import com.mycompany.myapp.services.AvisService;

/**
 *
 * @author Nihel
 */
public class AddAvisForm extends Form  {
    public AddAvisForm(Form previous) {
        setTitle("Ajouter Avis");
        setLayout(BoxLayout.y());
        
        Label commentaire = new Label("Commentaire :");
        TextField tfCommentaire = new TextField("", "Commentaire");
        
        Button btnValider = new Button("Ajouter Avis");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfCommentaire.getText().length()==0) )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    Avis a = new Avis();
                    a.setCommenraire(tfCommentaire .getText().toString());
                     if( AvisService.getInstance().addAvis(a))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                }
               new ListReclamationForm(previous).showBack(); 
                
            }
              });
        
        addAll(commentaire,tfCommentaire , btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    
}
}
