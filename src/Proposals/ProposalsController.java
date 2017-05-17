package Proposals;

import domain.PCProposal;
import domain.Proposal;
import domain.User;

import java.sql.Date;
import java.util.*;

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
        int usedProposals[] = new int[proposalList.size()]; // list with all proposals already splitted
        List<PCProposal> splittedProposals = new ArrayList(); // list with all proposals to review by each pc

        while (!proposalList.isEmpty()) { // give one proposal to each pc
            boolean userGotProposal = false; // false if none of the users got any proposal this turn
            for (User user: pcList) { // for each user
                Proposal proposal = null;
                for (PCProposal p : user.getPcProps()) { // for each proposal in user bid
                    boolean t = usedProposals[proposalList.indexOf(p.getProposal())] < nrReviewers; // proposal is not taken // todo: check if proposal from proposal list is the same as user.pcproposal.proposal
                    boolean h = !foundRelation(splittedProposals, user, p.getProposal()); // user doesn't have the proposal already
                    boolean d = p.getBid() != 0; // if user doesn't refused the proposal // todo: check bid in pc-proposal table
                    if (t && h && d) {
                        proposal = p.getProposal();
                        break;
                    }
                }
                if (proposal == null) {
                    // if we didn't found any available proposal that user wants
                    // take an available proposal that user didn't refuse
                    for (Proposal p : proposalList) { // for each proposal in the list
                        boolean t = usedProposals[proposalList.indexOf(p)] < nrReviewers; // proposal is not taken
                        boolean h = !foundRelation(splittedProposals, user, p); // user doesn't have the proposal already
                        boolean d = !refusedProposal(user, p); // if user doesn't refused the proposal // todo: check bid in pc-proposal table
                        if (t && h && d) {
                            proposal = p;
                            break;
                        }
                    }
                }
                if (proposal != null) { // if we found an available proposal give it to user
                    PCProposal pcp = new PCProposal();
                    pcp.setUser(user);
                    pcp.setProposal(proposal);
                    splittedProposals.add(pcp); // add the proposal to user
                    userGotProposal = true; // a user got a proposal
                    usedProposals[proposalList.indexOf(proposal)]++; // update taken proposals
                }
            }
            if (!userGotProposal) break; // stop if nobody got a proposal this turn
        }

        for (User user : pcList) { // for each user
            user.setPcProps(getUserProposals(splittedProposals, user)); // set the new proposal list for user
        }
        for (Proposal proposal : proposalList) {
            proposal.setPCProps(getProposalUsers(splittedProposals, proposal)); // set the new proposal list for proposal
        }

        // todo: update users in database
    }

    /**
     * check splittedProposals if it has a PCProposal with user and proposal
     * @param splittedProposals
     * @param user
     * @param proposal
     * @return
     */
    private boolean foundRelation(List<PCProposal> splittedProposals, User user, Proposal proposal) {
        boolean found = false;
        for (PCProposal p : splittedProposals) {
            if (p.getUser() == user && p.getProposal() == proposal) found = true; //todo: check if user == user proposal == proposal
        }
        return found;
    }

    /**
     * check if user refused the proposal
     * @param user
     * @param proposal
     * @return
     */
    private boolean refusedProposal(User user, Proposal proposal) {
        Collection<PCProposal> pcp = user.getPcProps();
        for (PCProposal pc : pcp) {
            if (pc.getProposal() == proposal) { //todo: check if user == user proposal == proposal
                if (pc.getBid() == 0) return true;
            }
        }
        return false;
    }

    private Collection<PCProposal> getUserProposals(List<PCProposal> splittedProposals, User user) {
        ArrayList<PCProposal> new_pcp = new ArrayList();
        for (PCProposal pc : splittedProposals) {
            if (pc.getUser() == user) new_pcp.add(pc);
        }
        return new_pcp;
    }

    private Collection<PCProposal> getProposalUsers(List<PCProposal> splittedProposals, Proposal proposal) {
        ArrayList<PCProposal> new_pcp = new ArrayList();
        for (PCProposal pc : splittedProposals) {
            if (pc.getProposal() == proposal) new_pcp.add(pc);
        }
        return new_pcp;
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
