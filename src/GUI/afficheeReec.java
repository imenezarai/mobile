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
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/**
 *
 * @author amine
 */
public class afficheeReec {
    Form f ;
    Label lbobj;
    SpanLabel lobjj;
    Label ldesc ;
    SpanLabel ldescc ;
    Label lbreponse;
    SpanLabel lbreponsee;
    Label lbstatus;
    Label lbstatuss;
    Label lbdate;
    SpanLabel lbdatee;

    public afficheeReec() {
        f = new Form(new BoxLayout(BoxLayout.Y_AXIS));
        Service.ServiceReclamation ser = new ServiceReclamation();
        ArrayList<Reclamation> al = new ArrayList<Reclamation>();
        al= ser.getListRecObj2();
        for (Reclamation r : al) {
            Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            SpanLabel lobj = new SpanLabel(""+r.getObjetReclamation());
            SpanLabel ldesc = new SpanLabel(""+r.getDescReclamation());
            SpanLabel ldate = new SpanLabel(""+r.getDateReclamation());
            SpanLabel lreponse = new SpanLabel(""+r.getReponseReclamation());
            SpanLabel lstatus = new SpanLabel(""+r.getStatusReclamation());
          
            
            c1.add(lobj);
            c1.add(ldesc);
            c1.add(ldate);
            c1.add(lreponse);
            c1.add(lstatus);
            
            f.add(c1);
        }
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
}
