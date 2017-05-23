package Proposals;

import Login.LoginController;
import domain.PCProposal;
import domain.Proposal;
import domain.ProposalEvalPC;
import domain.User;

import java.sql.Date;
import java.util.*;

/**
 * Created by MariusDK on 16.05.2017.
 */

public class ProposalsController extends Observable {

    public List<Proposal> proposals = new ArrayList<>();
    public ProposalsProvider provider = new ProposalsProvider();
    public List<ProposalEvalPC> evalProp = new ArrayList<>();

    public List<Proposal> getProposal() {
        return provider.selectProposals();
    }

    public List<Proposal> getUserProposals() {
        return provider.getUserProposals(LoginController.getInstance().getUser().getId_user());
    }

    public List<ProposalEvalPC> getPcProposalEvalList() {
        System.out.println(1);
        evalProp = provider.getEvaluationList(LoginController.getInstance().getUser().getId_user());
        System.out.println(2);
        return evalProp;
    }

    public ArrayList getCalificativList() {
        ArrayList<String> calificativList = new ArrayList();
        calificativList.add("strong accept");
        calificativList.add("accept");
        calificativList.add("weak accept");
        calificativList.add("borderline paper");
        calificativList.add("weak reject");
        calificativList.add("reject");
        calificativList.add("Strong reject");
        return calificativList;
    }

    public void add(User user, String other_authors, String name, String keywords, String topics, String type, Date send_date, Date accept_date, String status, String abs, String document, String sesiune) {
        int id_autor = user.getId_user();
        int id_sesiune = provider.getIdSesiuneByName(sesiune);
        Proposal proposal = new Proposal(id_autor, other_authors, name, keywords, topics, type, send_date, accept_date, status, abs, document, id_sesiune);

        proposals.add(proposal);
        provider.insert(proposal);
        setChanged();
        notifyObservers();
    }

    public void edit(Proposal proposal, User user, String other_authors, String name, String keywords, String topics, String type, Date send_date, Date accept_date, String status, String abs, String document, String sesiune) {
        int id_autor = user.getId_user();
        int id_sesiune = provider.getIdSesiuneByName(sesiune);

        proposal.setId_author(id_autor);
        proposal.setOther_authors(other_authors);
        proposal.setName(name);
        proposal.setKeywords(keywords);
        proposal.setTopics(topics);
        proposal.setType(type);
        proposal.setSend_date(send_date);
        proposal.setAccept_date(accept_date);
        proposal.setStatus(status);
        proposal.setAbs(abs);
        proposal.setDocument(document);
        proposal.setId_session(id_sesiune);

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

    public List SessionName() {
        return provider.getAllNameeSessions();
    }

    public String getSessionName(int id_session) {
        return provider.getSessionName(id_session);
    }

    public void splitProposals(int nrReviewers) {
        List<Proposal> proposalList = provider.selectProposals();
        List<User> pcList = provider.getPC();
        int usedProposals[] = new int[proposalList.size()]; // list with all proposals already splitted

        List<PCProposal> splittedProposals = new ArrayList(); // list with all proposals to review by each pc

        while (!proposalList.isEmpty()) { // give one proposal to each pc
            boolean userGotProposal = false; // false if none of the users got any proposal this turn
            for (User user : pcList) { // for each user
                Proposal proposal = null;
                for (PCProposal p : user.getPcProps()) { // for each proposal in user bid
                    boolean t = usedProposals[getProposalIndex(proposalList, p.getProposal())] < nrReviewers; // proposal is not taken
                    boolean h = !foundRelation(splittedProposals, user, p.getProposal()); // user doesn't have the proposal already
                    boolean d = p.getBid() != 0; // if user doesn't refused the proposal
                    if (t && h && d) {
                        proposal = p.getProposal();
                        break;
                    }
                }
                if (proposal == null) {
                    // if we didn't found any available proposal that user wants
                    // take an available proposal that user didn't refuse
                    for (Proposal p : proposalList) { // for each proposal in the list
                        boolean t = usedProposals[getProposalIndex(proposalList, p)] < nrReviewers; // proposal is not taken
                        boolean h = !foundRelation(splittedProposals, user, p); // user doesn't have the proposal already
                        boolean d = !refusedProposal(user, p); // if user doesn't refused the proposal
                        if (t && h && d) {
                            proposal = p;
                            break;
                        }
                    }
                }
                if (proposal != null) { // if we found an available proposal give it to user
                    PCProposal pcp = new PCProposal(user, proposal);
                    splittedProposals.add(pcp); // add the proposal to user
                    userGotProposal = true; // a user got a proposal
                    usedProposals[getProposalIndex(proposalList, proposal)]++; // update taken proposals
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
        provider.updatePCProposalTable(splittedProposals);
    }

    private int getProposalIndex(List<Proposal> proposalList, Proposal proposal) {
        for (Proposal p : proposalList) {
            if (p.getId_proposal() == proposal.getId_proposal()) return proposalList.indexOf(p);
        }
        return -1;
    }

    /**
     * check splittedProposals if it has a PCProposal with user and proposal
     *
     * @param splittedProposals
     * @param user
     * @param proposal
     * @return
     */
    private boolean foundRelation(List<PCProposal> splittedProposals, User user, Proposal proposal) {
        boolean found = false;
        for (PCProposal p : splittedProposals) {
            if (p.getUser().getId_user() == user.getId_user() && p.getProposal().getId_proposal() == proposal.getId_proposal())
                found = true;
        }
        return found;
    }


    /**
     * check if user refused the proposal
     *
     * @param user
     * @param proposal
     * @return
     */
    private boolean refusedProposal(User user, Proposal proposal) {
        Collection<PCProposal> pcp = user.getPcProps();
        for (PCProposal pc : pcp) {
            if (pc.getProposal().getId_proposal() == proposal.getId_proposal()) {
                if (pc.getBid() == 0) return true;
            }
        }
        return false;
    }

    private Collection<PCProposal> getUserProposals(List<PCProposal> splittedProposals, User user) {
        ArrayList<PCProposal> new_pcp = new ArrayList();
        for (PCProposal pc : splittedProposals) {
            if (pc.getUser().getId_user() == user.getId_user()) new_pcp.add(pc);
        }
        return new_pcp;
    }

    private Collection<PCProposal> getProposalUsers(List<PCProposal> splittedProposals, Proposal proposal) {
        ArrayList<PCProposal> new_pcp = new ArrayList();
        for (PCProposal pc : splittedProposals) {
            if (pc.getProposal().getId_proposal() == proposal.getId_proposal()) new_pcp.add(pc);
        }
        return new_pcp;
    }

    public String getNameAuthor(int id) {
        return provider.getAuthorName(id);
    }

    public void UpdatePcProposal(User user, Proposal proposal, int bid, String nota, String review) {
        PCProposal pcProposal = new PCProposal();
        pcProposal.setUser(user);
        pcProposal.setProposal(proposal);
        pcProposal.setBid(bid);
        pcProposal.setNota(nota);
        pcProposal.setReview(review);
        System.out.println(pcProposal.getId());
        provider.UpdatePCProposal(pcProposal);
        setChanged();
        notifyObservers();
    }
    public boolean checkIfReview(User user, Proposal proposal) {
        return provider.checkIfProposalExistReview(proposal, user);
    }

    public boolean checkIfPass(User user, Proposal proposal) {
        return provider.checkIfProposalExistPass(proposal, user);
    }

    public String GetSessionName(int id_session)
    {
        return provider.getSessionName(id_session);
    }
}
