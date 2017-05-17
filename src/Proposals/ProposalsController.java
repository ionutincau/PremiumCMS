package Proposals;

import domain.Proposal;
import domain.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

/**
 * Created by MariusDK on 16.05.2017.
 */

public class ProposalsController extends Observable {

    public List<Proposal> proposals = new ArrayList<>();
    public ProposalsProvider provider = new ProposalsProvider();

    public List<Proposal> getProposal() {
        proposals = provider.selectProposals();
        return proposals;
    }

    public void add(String nameAuthor, String other_authors, String name, String keywords, String topics, String type, Date send_date, Date accept_date, String status, String abs, String document, String sesiune) {
        int id_autor=provider.getIdAuthorByName(nameAuthor);
        int id_sesiune=provider.getIdSesiuneByName(sesiune);
        Proposal proposal=new Proposal(id_autor,other_authors,name,keywords,topics,type,send_date,accept_date,status,abs,document,id_sesiune);
        proposals.add(proposal);
        provider.insert(proposal);
        setChanged();
        notifyObservers();
    }

    public void edit(int id_proposal,String nameAuthor, String other_authors, String name, String keywords, String topics, String type, Date send_date, Date accept_date, String status, String abs, String document, String sesiune) {
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

    public void delete(Proposal proposal) {
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

    public void splitProposals(int nrReviewers) {
        List<Proposal> proposalList = new ArrayList(); // list with all proposals // todo: load this
        List<User> pcList = new ArrayList(); // list with all pc members // todo: load this
        int usedProposals[] = new int[proposalList.size()]; // list with all proposals allready splitted
        HashMap<User, ArrayList<Proposal>> splittedProposals = new HashMap(); // list with all proposals to review by each pc

        for (User user : pcList) { // bind each user to a new empty list
            splittedProposals.put(user, new ArrayList());
        }

        while (!proposalList.isEmpty()) { // give one proposal to each pc
            boolean userGotProposal = false; // false if none of the users got any proposal this turn
            for (User user: pcList) { // for each user
                Proposal proposal = null;
                for (Proposal p : user.getProposals()) { // for each proposal in user bid
                    boolean t = usedProposals[proposalList.indexOf(p)] < nrReviewers; // proposal is not taken
                    boolean h = !splittedProposals.get(user).contains(p); // user doesn't have the proposal already
                    boolean d = true; // if user doesn't refused the proposal // todo: check bid in pc-proposal table
                    if (t || h || d) {
                        proposal = p;
                        break;
                    }
                }
                if (proposal == null) {
                    // if we didn't found any available proposal that user wants
                    // take an available proposal that user didn't refuse
                    for (Proposal p : proposalList) { // for each proposal in the list
                        boolean t = usedProposals[proposalList.indexOf(p)] < nrReviewers; // proposal is not taken
                        boolean h = !splittedProposals.get(user).contains(p); // user doesn't have the proposal already
                        boolean d = true; // if user doesn't refused the proposal // todo: check bid in pc-proposal table
                        if (t || h || d) {
                            proposal = p;
                            break;
                        }
                    }
                }
                if (proposal != null) { // if we found an available proposal give it to user
                    splittedProposals.get(user).add(proposal); // add the proposal to user
                    userGotProposal = true; // a user got a proposal
                    usedProposals[proposalList.indexOf(proposal)]++; // updatetaken proposals
                }
            }
            if (!userGotProposal) break; // stop if nobody got a proposal this turn
        }

        for (User user : pcList) { // for each user
            user.setProposals(splittedProposals.get(user)); // set the new proposal list
        }

        // todo: update users in database
    }

    // todo: verifica daca propsal din proposalList e aceeasi ca cea din user.getproposals
    public void test() {/*
        HashMap<User, ArrayList<Proposal>> splittedProposals = new HashMap<>();
        User u1 = new User();
        u1.setUserName("jo1");
        User u2 = new User();
        u2.setUserName("jo2");
        ArrayList<Proposal> a1 = new ArrayList();
        Proposal p1 = new Proposal();
        p1.setName("prop1");
        Proposal p2 = new Proposal();
        p2.setName("prop2");
        a1.add(p1);
        splittedProposals.put(u1, a1);
        splittedProposals.get(u1).add(p2);
        List<Proposal> l = splittedProposals.get(u1);
        for (Proposal p : l) {
            System.out.println("***" + p.getName());
        }*/
    }
}
