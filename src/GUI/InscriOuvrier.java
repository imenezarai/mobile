/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.User;
import Service.ServiceUser;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

/**
 *
 * @author amine
 */
public class InscriOuvrier {

    Form f;
    TextField tusername;
    TextField tnom;
    TextField tprenom;
    TextField tpassword;
    TextField tpassword1;
    TextField tadresse;
    TextField temail;
    TextField temail1;
    TextField ttel;
    TextField tprofession;
    TextField tdescription;

    Button btnajout;

    public InscriOuvrier() {
        f = new Form("Inscription Ouvrier", new BoxLayout(BoxLayout.Y_AXIS));
      //  Container c1 = new Container(new FlowLayout(CENTER, CENTER));
        f.getToolbar().addCommandToRightBar("back", null, (ev) -> {
            LoginUser a = new LoginUser();
            a.getF().showBack();
            tusername.clear();
            tpassword.clear();
        });
        tnom = new TextField("", "nom");
        tprenom = new TextField("", "prenom");
        tusername = new TextField("", "username");
        tpassword = new TextField("", "password");
        tpassword.setConstraint(TextField.PASSWORD);
        tpassword1 = new TextField("", "password");
        tpassword1.setConstraint(TextField.PASSWORD);
        temail = new TextField("", "email");
        temail1 = new TextField("", "email");
        ttel = new TextField("", "tel");
        tadresse = new TextField("", "adresse");
        tprofession = new TextField("", "profession");
        tdescription = new TextField("", "description");

        btnajout = new Button("ajouter");

        f.add(tnom);
        f.add(tprenom);
        f.add(tusername);
        f.add(tpassword);
        f.add(tpassword1);
        f.add(temail);
        f.add(temail1);
        f.add(ttel);
        f.add(tadresse);
        f.add(tprofession);
        f.add(tdescription);

        f.add(btnajout);
        
        btnajout.addActionListener((e) -> {
            if ((tnom.getText().equals("")) & (tprenom.getText().equals("")) & (tusername.getText().equals("")) & (tpassword.getText().equals(""))
                    & (tpassword1.getText().equals("")) & (temail.getText().equals("")) & (temail1.getText().equals(""))
                    & (ttel.getText().equals("")) & (tadresse.getText().equals("")) & (tdescription.getText().equals(""))
                    & (tdescription.getText().equals(""))) {
                Dialog.show("Attention", "Veuillez remplir tous les champs", "Ok", null);

            } else {
                ServiceUser ser = new ServiceUser();
                User c = new User();
                c.setUsername(tusername.getText());
                c.setEmail(temail.getText());
                c.setPassword(tpassword.getText());
                c.setNom(tnom.getText());
                c.setPrenom(tprenom.getText());
                c.setTel(Integer.parseInt(ttel.getText()));
                c.setAdresse(tadresse.getText());
                c.setProfession(tprofession.getText());
                c.setDescription(tdescription.getText());
                if ((tpassword.getText().equals(tpassword1.getText())) && (temail.getText().equals(temail1.getText()))) {
                    ser.ajoutOuvrier(c);
                    LoginUser l = new LoginUser();
                    l.getF().show();
                } else {
                    Dialog.show("Attention", "Verifier votre mot de passe et votre email", "Ok", null);

                }

            }

        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
