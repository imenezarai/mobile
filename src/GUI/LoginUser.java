/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.User;
import static Entity.User.membre;
import Service.ServiceUser;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author amine
 */
public class LoginUser {

    Form f;
    Form f2 = new Form(new BoxLayout(BoxLayout.Y_AXIS));
    public static User next;

    public static TextField tusername;
    public static TextField tpassword;
    Button btnlogin;
    Button btninscri;
    Label lbouv;

    public LoginUser() {
        f = new Form(new FlowLayout(CENTER, CENTER));
        f.setTitle("FIXIT");
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        tusername = new TextField("", "username");
        tpassword = new TextField("", "password");
        lbouv = new Label("     Vous êtes ouvrier ? s'inscrire ici");

        tpassword.setConstraint(TextField.PASSWORD);
        btnlogin = new Button("login");
        btninscri = new Button("S'inscrire");
        c.add(tusername);
        c.add(tpassword);
        c.add(btnlogin);
        c.add(btninscri);
        c.add(lbouv);
        f.add(c);

        btnlogin.addActionListener((e) -> {

            Service.ServiceUser seru = new ServiceUser();
            ArrayList<User> liste = seru.getListAll2();

            int x = 0;
            for (Iterator<User> iterator = liste.iterator(); iterator.hasNext();) {
                User next = iterator.next();
                if ((next.getUsername().equals(tusername.getText())) && (next.getPassword().equals(tpassword.getText()))) {

                    membre = next;
                    x = 1;
                    System.out.println("username : " + next.getUsername());
                    //Form f2 = new Form(new FlowLayout());
                    try {
                        MenuForm mf = new MenuForm();
                        mf.getFjdida().show();
                    } catch (IOException ex) {

                    }

                }

            }
            if (x == 0) {
                Dialog.show("Attention", "Username ou mot de passe erroné", "Ok", null);
            }

        });
        btninscri.addActionListener((evt) -> {
            InscriClient i = new InscriClient();
            i.getF().show();

        });
        lbouv.addPointerPressedListener((evt) -> {
            InscriOuvrier o = new InscriOuvrier();
            o.getF().show();
        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public Form getF2() {
        return f2;
    }

    public void setF2(Form f2) {
        this.f2 = f2;
    }

}
