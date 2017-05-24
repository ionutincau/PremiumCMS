package domain;

import java.sql.Date;

/**
 * Created by MariusDK on 23.05.2017.
 */
public class ProposalEvalPC extends Proposal {
    private String review;
    private String nota;
    private int id_PCproposal;

    public ProposalEvalPC() {
    }

    public ProposalEvalPC(int id_author, String other_authors, String name, String keywords, String topics, String type, Date send_date, Date accept_date, String status, String abs, String document, int id_session, String review, String nota) {
        super(id_author, other_authors, name, keywords, topics, type, send_date, accept_date, status, abs, document, id_session);
        this.review = review;
        this.nota = nota;
    }

    public int getId_PCproposal() {
        return id_PCproposal;
    }

    public void setId_PCproposal(int id_PCproposal) {
        this.id_PCproposal = id_PCproposal;
    }

    public ProposalEvalPC(Proposal proposal, String review, int id_PCproposal, String nota) {
        super(proposal.getId_proposal(),proposal.getId_author(),proposal.getOther_authors(),proposal.getName(),proposal.getKeywords(),proposal.getTopics(),proposal.getType(),proposal.getSend_date(),proposal.getAccept_date(),proposal.getStatus(),proposal.getAbs(),proposal.getDocument(),proposal.getId_session());
        this.review = review;
        this.nota = nota;

        this.id_PCproposal=id_PCproposal;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
