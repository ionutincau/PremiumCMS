package Proposals;

import domain.Proposal;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by MariusDK on 16.05.2017.
 */
public class ProposalsController extends Observable{
    public List<Proposal> proposals=new ArrayList<>();
    public ProposalsProvider provider=new ProposalsProvider();
    public List<Proposal> getProposal()
    {
        proposals=provider.selectProposals();
        return proposals;
    }
    public void add(String nameAuthor, String other_authors, String name, String keywords, String topics, String type, Date send_date, Date accept_date, String status, String abs, String document, String sesiune)
    {
        int id_autor=provider.getIdAuthorByName(nameAuthor);
        int id_sesiune=provider.getIdSesiuneByName(sesiune);
        Proposal proposal=new Proposal(id_autor,other_authors,name,keywords,topics,type,send_date,accept_date,status,abs,document,id_sesiune);
        proposals.add(proposal);
        provider.insert(proposal);
        setChanged();
        notifyObservers();
    }
    public void edit(int id_proposal,String nameAuthor, String other_authors, String name, String keywords, String topics, String type, Date send_date, Date accept_date, String status, String abs, String document, String sesiune)
    {
        int id_autor=provider.getIdAuthorByName(nameAuthor);
        int id_sesiune=provider.getIdSesiuneByName(sesiune);
        Proposal proposal=new Proposal(id_autor,other_authors,name,keywords,topics,type,send_date,accept_date,status,abs,document,id_sesiune);
        proposal.setId_proposal(id_proposal);
        int nr=0;
        for (Proposal o:proposals)
        {
            if (o.getId_proposal()!=id_proposal)
            {
                nr++;
            }
        }
        proposals.remove(nr);
        proposals.add(nr,proposal);
        provider.update(proposal);
        setChanged();
        notifyObservers();
    }
    public void delete(Proposal proposal)
    {
        proposals.remove(proposal);
        provider.delete(proposal);
        setChanged();
        notifyObservers();
    }
    public List SessionName()
    {
        return provider.getAllNameeSessions();
    }
    public String getSessionName(int id_session)
    {
        return provider.getSessionName(id_session);
    }

}
