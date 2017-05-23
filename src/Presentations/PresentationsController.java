package Presentations;

import domain.Presentation;
import domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by ASUS on 23.May.2017.
 */
public class PresentationsController extends Observable {
    public PresentationsProvider provider;
    public List<Presentation> presentations = new ArrayList<Presentation>();

    public List getAllPresentations() {
        presentations=provider.select();
        return presentations;
    }

    public void add(int id_presentation, User speaker,String demo, String name){
        Presentation p=new Presentation(id_presentation,speaker,demo,name);
        presentations.add(p);
        provider.insert(p);
        setChanged();
        notifyObservers();
    }

    public void edit(int id_presentation,User speaker,String demo,String name) {
        Presentation p=new Presentation(id_presentation,speaker,demo,name);
        int nr=0;
        for (Presentation o:presentations)
        {
            if (o.getId_presentation()!=id_presentation)
            {
                nr++;
            }
        }
        presentations.remove(nr);
        presentations.add(nr,p);
        provider.update(p);
        setChanged();
        notifyObservers();
    }

    public void addDoc(int id_presentation,String demo){
        Presentation p=new Presentation();
        int nr=0;
        for (Presentation o:presentations)
        {
            if (o.getId_presentation()!=id_presentation)
            {
                nr++;
            }
        }
        presentations.get(nr).setDemo(demo);
        p=presentations.get(nr);
        presentations.remove(nr);
        presentations.add(nr,p);
        provider.update(p);
        setChanged();
        notifyObservers();
    }

    public void delete(Presentation p) {
        presentations.remove(p);
        provider.delete(p);
        setChanged();
        notifyObservers();
    }
}
