/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Reclamation;
import Service.ServiceReclamation;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author amine
 */
public class AfficheRecForm {

    Form affrec;
    public static String objj;

    public AfficheRecForm() {
        affrec = new Form(new BoxLayout(BoxLayout.Y_AXIS));
        affrec.setTitle("Mes reclamations");
        affrec.getToolbar().addCommandToRightBar("back", null, (ev) -> {
            try {
                MenuForm mf = new MenuForm();
                mf.getFjdida().showBack();
                ;
            } catch (IOException ex) {

            }
        });

        Service.ServiceReclamation ser = new ServiceReclamation();
        ArrayList<Reclamation> al = new ArrayList<Reclamation>();
        al = ser.getListRec2();
        for (Reclamation r : al) {

            Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label lobj = new Label("" + r.getObjetReclamation());
            //SpanLabel ldesc = new SpanLabel(""+r.getDescReclamation());
            //  SpanLabel ldate = new SpanLabel(""+r.getDateReclamation());
            //  SpanLabel lreponse = new SpanLabel(""+r.getReponseReclamation());
            // SpanLabel lstatus = new SpanLabel(""+r.getStatusReclamation());

            c1.add(lobj);
            //  c1.add(ldesc);
            //  c1.add(ldate);
            // c1.add(lreponse);
            // c1.add(lstatus);

            affrec.add(c1);

            lobj.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
//                    afficheeReec aff = new afficheeReec();
//                    aff.getF().show();
                    objj = ("" + lobj.getText());
                    System.out.println("c bon " + lobj.getText());
                    Form f4 = new Form(new BoxLayout(BoxLayout.Y_AXIS));
                    f4.getToolbar().addCommandToRightBar("back", null, (ev) -> {
                        AfficheRecForm ar;
                        ar = new AfficheRecForm();
                        ar.getAffrec().showBack();
                        ;
                    });
                    Service.ServiceReclamation ser = new ServiceReclamation();
                    ArrayList<Reclamation> al = new ArrayList<Reclamation>();
                    al = ser.getListRecObj2();

                    for (Reclamation r : al) {
                        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        Label lbbobj = new Label("Ojet de la reclamation : ");
                        SpanLabel lobj = new SpanLabel("" + r.getObjetReclamation());
                        Label lbbdesc = new Label("Description de la reclamtion : ");
                        SpanLabel ldesc = new SpanLabel("" + r.getDescReclamation());
                        Label lbbdate = new Label("Date de l'envoi : ");
                        SpanLabel ldate = new SpanLabel("" + r.getDateReclamation());
                        Label lbreponse = new Label("Reponse : ");
                        SpanLabel lreponse = new SpanLabel("" + r.getReponseReclamation());
                        Label lbstatus = new Label("Satatus : ");
                        SpanLabel lstatus = new SpanLabel("" + r.getStatusReclamation());

                        c1.add(lbbobj);
                        c1.add(lobj);
                        c1.add(lbbdesc);
                        c1.add(ldesc);
                        c1.add(lbbdate);
                        c1.add(ldate);
                        c1.add(lbstatus);
                        c1.add(lstatus);
                        c1.add(lbreponse);
                        c1.add(lreponse);

                        f4.add(c1);
                    }
                    f4.show();

                }
            });
        }
    }

    public Form getAffrec() {
        return affrec;
    }

    public void setAffrec(Form affrec) {
        this.affrec = affrec;
    }

}
