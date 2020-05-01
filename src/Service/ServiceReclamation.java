/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Reclamation;
import Entity.User;
import static GUI.AfficheRecForm.objj;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author amine
 */
public class ServiceReclamation {
     public void ajoutReclamation(Reclamation rec) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/final/Pi/web/app_dev.php/reclamation/new?idsource=" + User.membre.getUsername()+ "&destinationReclamation=" + rec.getDestinationReclamation()+ "&objetReclamation=" + rec.getObjetReclamation()+"&descReclamation="+rec.getDescReclamation()+"&rolesource="+User.membre.getRole();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        System.out.println(Url);
        con.addResponseListener((e) -> {
            System.out.println("t3addaaa");
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
     }
     
      public ArrayList<Reclamation> parseListReclamtionJson(String json) {

        ArrayList<Reclamation> listrec = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                Reclamation rec = new Reclamation();
                rec.setIdsource(obj.get("idsource").toString());
                rec.setReponseReclamation(obj.get("reponseReclamation").toString());
                rec.setDestinationReclamation(obj.get("destinationReclamation").toString());
                rec.setObjetReclamation(obj.get("objetReclamation").toString());
                rec.setDescReclamation(obj.get("descReclamation").toString());
                rec.setDateReclamation(obj.get("dateReclamation").toString());
                rec.setStatusReclamation(obj.get("statusReclamation").toString());
                rec.setRolesource(obj.get("rolesource").toString());
                             
                System.out.println(rec);                
                listrec.add(rec);
            }
        } catch (IOException ex) {
        }
        System.out.println(listrec);
        return listrec;
    }  
    ArrayList<Reclamation> listr = new ArrayList<>();
    
    public ArrayList<Reclamation> getListRec2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/final/Pi/web/app_dev.php/reclamation/all/"+User.membre.getUsername());  
        
        //System.out.println("Url : " + Url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceReclamation ser = new ServiceReclamation();
                listr = ser.parseListReclamtionJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listr;
    }
    
    
    
    public ArrayList<Reclamation> parseListReclamtionObjJson(String json) {

        ArrayList<Reclamation> listreco = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                Reclamation rec = new Reclamation();
//                rec.setIdsource(obj.get("idsource").toString());
                rec.setReponseReclamation(obj.get("reponseReclamation").toString());
                rec.setDestinationReclamation(obj.get("destinationReclamation").toString());
                rec.setObjetReclamation(obj.get("objetReclamation").toString());
                rec.setDescReclamation(obj.get("descReclamation").toString());
                rec.setDateReclamation(obj.get("dateReclamation").toString());
                rec.setStatusReclamation(obj.get("statusReclamation").toString());
     //           rec.setRolesource(obj.get("rolesource").toString());
                             
                System.out.println(rec);                
                listreco.add(rec);
            }
        } catch (IOException ex) {
        }
        System.out.println(listreco);
        return listreco;
    }  
    ArrayList<Reclamation> listrecObj = new ArrayList<>();
    
    public ArrayList<Reclamation> getListRecObj2(){   
       // Reclamation rec= new Reclamation();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/final/Pi/web/app_dev.php/reclamation/objet/"+objj);  
        
        //System.out.println("Url : " + Url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceReclamation ser = new ServiceReclamation();
                listrecObj = ser.parseListReclamtionObjJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listrecObj;
    }
    
}
