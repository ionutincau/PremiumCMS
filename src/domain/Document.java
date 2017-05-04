package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by ASUS on 04.May.2017.
 */
@Entity
public class Document {
    @Id private int id_document;
    @Column private int id_user;
    @Column private String data;
    @Column private String URL;
    @Column private String name;

    public int getId_document() {return id_document;}
    public void setId_document(int id_document) {this.id_document = id_document;}
    public int getId_user() {return id_user;}
    public void setId_user(int id_user) {this.id_user = id_user;}
    public String getData() {return data;}
    public void setData(String data) {this.data = data;}
    public String getURL() {return URL;}
    public void setURL(String URL) {this.URL = URL;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

}
