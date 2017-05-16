package Sessions;

import domain.Sesiune;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by MariusDK on 13.05.2017.
 */
public class SesiuneController extends Observable{
    public List<Sesiune> sesiunes=new ArrayList<>();
    public SesiuneProvider provider=new SesiuneProvider();
    public List<Sesiune> getSesiune()
    {
        sesiunes=provider.select();
        //System.out.println(sesiunes);
        return sesiunes;
    }

    public void add(Date date_in, Date date_out, String name_room, String name, String president)
    {
        //int next_id=provider.get_next_ID();
        //next_id++;
        //int id_room=provider.getIdRoomFromName(name_room);
        Sesiune sesiune=new Sesiune(name,0,date_in,date_out,president);
        sesiunes.add(sesiune);
        provider.insert(sesiune);
        setChanged();
        notifyObservers();
    }
    public void edit(int id_Sesiune,Date date_in, Date date_out, String name_room, String name, String president)
    {
        int id_room=provider.getIdRoomFromName(name_room);
        Sesiune sesiune=new Sesiune(name,id_room,date_in,date_out,president);
        sesiune.setId_session(id_Sesiune);
        provider.update(sesiune);
        int nr=0;
        for (Sesiune o:sesiunes)
        {
            if (o.getId_session()!=id_Sesiune)
            {
                nr++;
            }
        }
        sesiunes.remove(nr);
        sesiunes.add(nr,sesiune);
        setChanged();
        notifyObservers();
    }
    public void delete(Sesiune sesiune)
    {
        sesiunes.remove(sesiune);
        provider.delete(sesiune);
        setChanged();
        notifyObservers();
    }
    public List AllPc()
    {
        return provider.getC();
    }
    public List AllRoom()
    {
        return provider.getRoom();
    }
    public String getRoom(int id)
    {
        return provider.getRoomNameById(id);
    }
    //Merge naspa
}
