package ma.lnet.boncmd.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Commande.
 */
@Entity
@Table(name = "commande")
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "reference_commande")
    private String referenceCommande;

    @Column(name = "date_commande")
    private ZonedDateTime dateCommande;

    @Column(name = "email_contact_administratif")
    private String emailContactAdministratif;

    @Column(name = "tel_contact_administratif")
    private String telContactAdministratif;

    @Column(name = "email_contact_technique")
    private String emailContactTechnique;

    @Column(name = "tel_contact_technique")
    private String telContactTechnique;

    @ManyToOne
    private Produit produit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceCommande() {
        return referenceCommande;
    }

    public Commande referenceCommande(String referenceCommande) {
        this.referenceCommande = referenceCommande;
        return this;
    }

    public void setReferenceCommande(String referenceCommande) {
        this.referenceCommande = referenceCommande;
    }

    public ZonedDateTime getDateCommande() {
        return dateCommande;
    }

    public Commande dateCommande(ZonedDateTime dateCommande) {
        this.dateCommande = dateCommande;
        return this;
    }

    public void setDateCommande(ZonedDateTime dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getEmailContactAdministratif() {
        return emailContactAdministratif;
    }

    public Commande emailContactAdministratif(String emailContactAdministratif) {
        this.emailContactAdministratif = emailContactAdministratif;
        return this;
    }

    public void setEmailContactAdministratif(String emailContactAdministratif) {
        this.emailContactAdministratif = emailContactAdministratif;
    }

    public String getTelContactAdministratif() {
        return telContactAdministratif;
    }

    public Commande telContactAdministratif(String telContactAdministratif) {
        this.telContactAdministratif = telContactAdministratif;
        return this;
    }

    public void setTelContactAdministratif(String telContactAdministratif) {
        this.telContactAdministratif = telContactAdministratif;
    }

    public String getEmailContactTechnique() {
        return emailContactTechnique;
    }

    public Commande emailContactTechnique(String emailContactTechnique) {
        this.emailContactTechnique = emailContactTechnique;
        return this;
    }

    public void setEmailContactTechnique(String emailContactTechnique) {
        this.emailContactTechnique = emailContactTechnique;
    }

    public String getTelContactTechnique() {
        return telContactTechnique;
    }

    public Commande telContactTechnique(String telContactTechnique) {
        this.telContactTechnique = telContactTechnique;
        return this;
    }

    public void setTelContactTechnique(String telContactTechnique) {
        this.telContactTechnique = telContactTechnique;
    }

    public Produit getProduit() {
        return produit;
    }

    public Commande produit(Produit produit) {
        this.produit = produit;
        return this;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Commande commande = (Commande) o;
        if (commande.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, commande.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Commande{" +
            "id=" + id +
            ", referenceCommande='" + referenceCommande + "'" +
            ", dateCommande='" + dateCommande + "'" +
            ", emailContactAdministratif='" + emailContactAdministratif + "'" +
            ", telContactAdministratif='" + telContactAdministratif + "'" +
            ", emailContactTechnique='" + emailContactTechnique + "'" +
            ", telContactTechnique='" + telContactTechnique + "'" +
            '}';
    }
}
