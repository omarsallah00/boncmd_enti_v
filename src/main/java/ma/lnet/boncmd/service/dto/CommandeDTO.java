package ma.lnet.boncmd.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Commande entity.
 */
public class CommandeDTO implements Serializable {

    private Long id;

    private String referenceCommande;

    private ZonedDateTime dateCommande;

    private String emailContactAdministratif;

    private String telContactAdministratif;

    private String emailContactTechnique;

    private String telContactTechnique;


    private Long produitId;
    

    private String produitReferenceProduit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getReferenceCommande() {
        return referenceCommande;
    }

    public void setReferenceCommande(String referenceCommande) {
        this.referenceCommande = referenceCommande;
    }
    public ZonedDateTime getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(ZonedDateTime dateCommande) {
        this.dateCommande = dateCommande;
    }
    public String getEmailContactAdministratif() {
        return emailContactAdministratif;
    }

    public void setEmailContactAdministratif(String emailContactAdministratif) {
        this.emailContactAdministratif = emailContactAdministratif;
    }
    public String getTelContactAdministratif() {
        return telContactAdministratif;
    }

    public void setTelContactAdministratif(String telContactAdministratif) {
        this.telContactAdministratif = telContactAdministratif;
    }
    public String getEmailContactTechnique() {
        return emailContactTechnique;
    }

    public void setEmailContactTechnique(String emailContactTechnique) {
        this.emailContactTechnique = emailContactTechnique;
    }
    public String getTelContactTechnique() {
        return telContactTechnique;
    }

    public void setTelContactTechnique(String telContactTechnique) {
        this.telContactTechnique = telContactTechnique;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }


    public String getProduitReferenceProduit() {
        return produitReferenceProduit;
    }

    public void setProduitReferenceProduit(String produitReferenceProduit) {
        this.produitReferenceProduit = produitReferenceProduit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CommandeDTO commandeDTO = (CommandeDTO) o;

        if ( ! Objects.equals(id, commandeDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CommandeDTO{" +
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
