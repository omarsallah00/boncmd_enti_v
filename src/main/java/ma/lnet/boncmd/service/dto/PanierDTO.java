package ma.lnet.boncmd.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Panier entity.
 */
public class PanierDTO implements Serializable {

    private Long id;

    private Long quantite;


    private Long commandeId;
    

    private String commandeReferenceCommande;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getQuantite() {
        return quantite;
    }

    public void setQuantite(Long quantite) {
        this.quantite = quantite;
    }

    public Long getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Long commandeId) {
        this.commandeId = commandeId;
    }


    public String getCommandeReferenceCommande() {
        return commandeReferenceCommande;
    }

    public void setCommandeReferenceCommande(String commandeReferenceCommande) {
        this.commandeReferenceCommande = commandeReferenceCommande;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PanierDTO panierDTO = (PanierDTO) o;

        if ( ! Objects.equals(id, panierDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PanierDTO{" +
            "id=" + id +
            ", quantite='" + quantite + "'" +
            '}';
    }
}
