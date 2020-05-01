/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.User;
import static GUI.LoginUser.tpassword;
import static GUI.LoginUser.tusername;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;

/**
 *
 * @author amine
 */
public class MenuForm {

    Form fjdida = new Form(new FlowLayout());
    Form toolbar;
    Button btnrec;
    Button btnaffrec;

    public MenuForm() throws IOException {
        fjdida.setTitle("welcome : " + User.membre.getUsername());

        fjdida.getToolbar().addCommandToRightBar("logout", null, (ev) -> {
            LoginUser a = new LoginUser();
            a.getF().showBack();
            tusername.clear();
            tpassword.clear();
        });
        btnrec = new Button("Reclamation");
        btnaffrec = new Button("RecEnvoyees");
        fjdida.add(btnrec);
        fjdida.add(btnaffrec);
        btnrec.addActionListener((evt) -> {
            RecForm aa = new RecForm();
            aa.getRecForm().show();

        });
        btnaffrec.addActionListener((evt) -> {
            AfficheRecForm afff = new AfficheRecForm();
            afff.getAffrec().show();
        });
        
//        Container C2 = new Container(new FlowLayout(Component.CENTER));
//        Image img = Image.createImage("/show.jpg");
//        toolbar.setBgImage(img);
//        toolbar.add(C2);

        
        Toolbar t = fjdida.getToolbar();
        t.addMaterialCommandToSideMenu("Envoyer Reclamation", FontImage.MATERIAL_HOME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                RecForm a;
                a = new RecForm();
                a.getRecForm().show();
            }
        });
        t.addMaterialCommandToSideMenu("Mes Reclamations", FontImage.MATERIAL_HOME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                AfficheRecForm a;
                a = new AfficheRecForm();
                a.getAffrec().show();
            }
        });

    }

    public Form getFjdida() {
        return fjdida;
    }

    public void setFjdida(Form fjdida) {
        this.fjdida = fjdida;
    }

}
