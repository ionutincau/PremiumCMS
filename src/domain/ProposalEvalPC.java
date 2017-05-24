package domain;

import java.sql.Date;

/**
 * Created by MariusDK on 23.05.2017.
 */
public class ProposalEvalPC extends Proposal {
    private String review;
    private String nota;

    public ProposalEvalPC() {
    }

    public ProposalEvalPC(int id_author, String other_authors, String name, String keywords, String topics, String type, Date send_date, Date accept_date, String status, String abs, String document, int id_session, String review, String nota) {
        super(id_author, other_authors, name, keywords, topics, type, send_date, accept_date, status, abs, document, id_session);
        this.review = review;
        this.nota = nota;
    }

    public ProposalEvalPC(Proposal proposal, String review, String nota) {
        super(proposal.getId_author(),proposal.getOther_authors(),proposal.getName(),proposal.getKeywords(),proposal.getTopics(),proposal.getType(),proposal.getSend_date(),proposal.getAccept_date(),proposal.getStatus(),proposal.getAbs(),proposal.getDocument(),proposal.getId_session());
        this.review = review;
        this.nota = nota;
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
