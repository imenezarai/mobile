/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.User;
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
public class ServiceUser {
     public void ajoutClient(User c) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/final/Pi/web/app_dev.php/client/new?username=" + c.getUsername()+ "&password=" + c.getPassword()+ "&email=" + c.getEmail()+"&adresse=" + c.getAdresse() + "&nom=" + c.getNom()+ "&prenom="+ c.getPrenom()+ "&tel="+ c.getTel();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
     public void ajoutOuvrier(User c) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/final/Pi/web/app_dev.php/ouvrier/new?username=" + c.getUsername()+ "&password=" + c.getPassword()+ "&email=" + c.getEmail()+"&adresse=" + c.getAdresse() + "&nom=" + c.getNom()+ "&prenom="+ c.getPrenom()+ "&tel="+ c.getTel()+ "&profession="+ c.getProfession()+ "&description="+ c.getDescription();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
     
     public ArrayList<User> parseListClientJson(String json) {

        ArrayList<User> listClient = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                User e = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                float tel = Float.parseFloat(obj.get("tel").toString());
                float nombreAvertissements = Float.parseFloat(obj.get("nombreAvertissements").toString());
                e.setId((int) id);
                e.setTel((int) tel);
                e.setPrenom(obj.get("prenom").toString());
                e.setNom(obj.get("nom").toString());
                e.setUsername(obj.get("username").toString());
                e.setPassword(obj.get("password").toString());
                e.setEmail(obj.get("email").toString());
                e.setRole(obj.get("role").toString());
                e.setAdresse(obj.get("adresse").toString());
                e.setNombreAvertissements((int) nombreAvertissements);
                e.setImageUser(obj.get("imageUser").toString());               
                System.out.println(e);                
                listClient.add(e);
            }
        } catch (IOException ex) {
        }
        System.out.println(listClient);
        return listClient;
    }  
    ArrayList<User> listcli = new ArrayList<>();
    
    public ArrayList<User> getListClient2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/final/Pi/web/app_dev.php/client/all");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                listcli = ser.parseListClientJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listcli;
    }
    
    
    public ArrayList<User> parseListOuvrierJson(String json) {

        ArrayList<User> listOuvrier = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                User e = new User();
               float id = Float.parseFloat(obj.get("id").toString());
                float tel = Float.parseFloat(obj.get("tel").toString());
                float nombreAvertissements = Float.parseFloat(obj.get("nombreAvertissements").toString());
                e.setId((int) id);
                e.setTel((int) tel);
                e.setPrenom(obj.get("prenom").toString());
                e.setNom(obj.get("nom").toString());
                e.setUsername(obj.get("username").toString());
                e.setPassword(obj.get("password").toString());
                e.setEmail(obj.get("email").toString());
                e.setRole(obj.get("role").toString());
                e.setAdresse(obj.get("adresse").toString());
                e.setNombreAvertissements((int) nombreAvertissements);
                e.setProfession(obj.get("profession").toString());
                e.setDescription(obj.get("description").toString());
                e.setImageUser(obj.get("imageUser").toString());
                System.out.println(e);               
                listOuvrier.add(e);
            }
        } catch (IOException ex) {
        }
        System.out.println(listOuvrier);
        return listOuvrier;
    }
    
    
    ArrayList<User> listouv = new ArrayList<>();
    
    public ArrayList<User> getListOuvrier2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/final/Pi/web/app_dev.php/ouvrier/all");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                listouv = ser.parseListOuvrierJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listouv;
    }
    
    
    
    
    
    
    
    public ArrayList<User> parseListAllUserJson(String json) {

        ArrayList<User> listUser = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                User e = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                float tel = Float.parseFloat(obj.get("tel").toString());
//                float nombreAvertissements = Float.parseFloat(obj.get("nombreAvertissements").toString());
//                e.setId((int) id);
//                e.setTel((int) tel);
//                e.setPrenom(obj.get("prenom").toString());
//                e.setNom(obj.get("nom").toString());
                e.setUsername(obj.get("username").toString());
                e.setPassword(obj.get("password").toString());
//                e.setEmail(obj.get("email").toString());
                e.setRole(obj.get("role").toString());
//                e.setAdresse(obj.get("adresse").toString());
//                e.setNombreAvertissements((int) nombreAvertissements);
//                e.setProfession(obj.get("profession").toString());
//                e.setDescription(obj.get("description").toString());
//                e.setImageUser(obj.get("imageUser").toString());
               // System.out.println(e);                
                listUser.add(e);
            }
        } catch (IOException ex) {
        }
       // System.out.println(listUser);
        return listUser;

    }
    
    
    ArrayList<User> listus = new ArrayList<>();
    
    public ArrayList<User> getListAll2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/final/Pi/web/app_dev.php/all");  
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                
                listus = ser.parseListAllUserJson(new String(con.getResponseData()));
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listus;
    }
    
}
