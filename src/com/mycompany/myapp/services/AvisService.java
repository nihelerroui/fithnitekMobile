/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Avis;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nihel
 */
public class AvisService {
    public ArrayList<Avis> avis;
    
      public static  AvisService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public AvisService() {
        req = new ConnectionRequest();
    }

    public static AvisService getInstance()
    {
        if(instance==null)
        {
            instance = new  AvisService();
        }
        return instance ;
    }
    
     public boolean addAvis(Avis a) {

        String commentaire = a.getCommenraire();

        String url = Statics.BASE_URL + "/addavisJSON?commenraire=" + commentaire ;
        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     public void updateAvis(Avis avis) {
        String url = Statics.BASE_URL + "/modifierAvisJSON/" + avis.getId()+"&commenraire=" +avis.getCommenraire() ;
        req.setUrl(url);
        req.setPost(false);
       
        req.addArgument("id", String.valueOf(avis.getId()));
        req.addArgument("commenraire", avis.getCommenraire());

        req.addResponseListener((NetworkEvent evt) -> {
            byte[] data = (byte[]) req.getResponseData();
            String s = new String(data);
            System.out.println("Result: " + s);
        });

        NetworkManager.getInstance().addToQueue(req);
    }
     
      public boolean  deleteAvis(int id){
       String url = Statics.BASE_URL + "/deleteavis/" +id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
      
      
      }
      
       public ArrayList<Avis> parseAvis(String jsonText) throws ParseException {
        try {
            avis = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> reclamationsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) reclamationsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Avis a = new Avis();
                float id = Float.parseFloat(obj.get("id").toString());
                a.setId((int) id);
                a.setCommenraire((String) obj.get("commentaire".toString()));
                

                avis.add(a);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return avis;
    }
       
        public ArrayList<Avis> getAllAvis() {
        String url = Statics.BASE_URL + "/avisJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    avis = parseAvis(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return avis;
    }
    
    
    
    
}
