/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Reclamation;
import Entity.User;
import static GUI.LoginUser.tpassword;
import static GUI.LoginUser.tusername;
import Service.ServiceReclamation;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;


/**
 *
 * @author amine
 */
public class RecForm {
    //TextField destRec;

    TextField objetRec;
    Label lbobj;
    Label lbdesc;
    TextArea descRec;
    Form recForm;
    Button Envoyer;
    Button Reset;

    public RecForm() {
        recForm = new Form(new BoxLayout(BoxLayout.Y_AXIS));
        recForm.setTitle("Envoyer une reclamation : ");
        recForm.getToolbar().addCommandToRightBar("back", null, (ev) -> {
            MenuForm mf;
            try {
                mf = new MenuForm();
                mf.getFjdida().showBack();
            } catch (IOException ex) {
               
            }
            
            ;
        });
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        objetRec = new TextField("", "objet Reclamation");
        // destRec = new TextField("", "destination Reclamation");
        descRec = new TextArea("");
        Envoyer = new Button("Envoyer");
        Reset = new Button("Reset");
        lbobj = new Label("Objet Reclamation :");
        lbdesc = new Label("Description Reclamation :");
        // recForm.add(destRec);
        c.add(lbobj);
        c.add(objetRec);
        c1.add(lbdesc);

        c1.add(descRec);
        recForm.add(c);
        recForm.add(c1);
        recForm.add(Envoyer);
        recForm.add(Reset);

        Envoyer.addActionListener((evt) -> {
            if (!(objetRec.getText().equals(""))&& !(descRec.getText().equals(""))){
            Service.ServiceReclamation serRec = new ServiceReclamation();
            Reclamation reclamation = new Reclamation();
            reclamation.setDestinationReclamation("ADMIN");
            reclamation.setObjetReclamation(objetRec.getText());
            reclamation.setDescReclamation(descRec.getText());

            reclamation.setIdsource(User.membre.getUsername());
            reclamation.setRolesource(User.membre.getRole());

            System.out.println("rec OBJ " + reclamation.getObjetReclamation());
            System.out.println("rec DEST " + reclamation.getDestinationReclamation());
            System.out.println("rec DESC " + reclamation.getDescReclamation());
            System.out.println("rec IDSOURCE " + reclamation.getIdsource());
            System.out.println("rec role source " + User.membre.getRole());
            serRec.ajoutReclamation(reclamation);
            System.out.println("test");
            AfficheRecForm aff = new AfficheRecForm();
            aff.getAffrec().show();
            }
            else {
                Dialog.show("Attention", "Veuillez remplir tous les champs", "Ok", null);
            }

        });
        Reset.addActionListener((evt) -> {
            RecForm h = new RecForm();
            objetRec.clear();
            descRec.clearClientProperties();
            h.getRecForm().show();
        });

    }

    public Form getRecForm() {
        return recForm;
    }

    public void setRecForm(Form recForm) {
        this.recForm = recForm;
    }

}
