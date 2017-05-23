package Events;

import domain.Event;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by MariusDK on 16.05.2017.
 */

public class EventsController extends Observable{
    public EventsProvider provider = new EventsProvider();
    public List<Event> events = new ArrayList<>();

    public List getAllEvents() {
        events=provider.select();
        return events;
    }

    public void add(String name, Date start, Date end, String web_page, String location, String description, Date d_abstract, Date d_proposal, Date d_evaluation, Date d_taxes) {
        Event e=new Event(name,start,end,web_page,location,description,d_abstract,d_proposal,d_evaluation,d_taxes);
        events.add(e);
        provider.insert(e);
        setChanged();
        notifyObservers();
    }

    public void edit(int id_event,String name, Date start, Date end, String web_page, String location, String description, Date d_abstract, Date d_proposal, Date d_evaluation, Date d_taxes) {
        Event e=new Event(name,start,end,web_page,location,description,d_abstract,d_proposal,d_evaluation,d_taxes);
        e.setId_event(id_event);
        int nr=0;
        for (Event o:events)
        {
            if (o.getId_event()!=id_event)
            {
                nr++;
            }
        }
        events.remove(nr);
        //pentru probleme la lista events=provider.select();
        events.add(nr,e);
        provider.update(e);
        setChanged();
        notifyObservers();
    }

    public void delete(Event e) {
        events.remove(e);
        provider.delete(e);
        setChanged();
        notifyObservers();
    }
}
