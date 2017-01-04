package ma.lnet.boncmd.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Produit entity.
 */
public class ProduitDTO implements Serializable {

    private Long id;

    private String referenceProduit;

    private String designation;


    private Long categorieId;
    

    private String categorieReferenceCategorie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getReferenceProduit() {
        return referenceProduit;
    }

    public void setReferenceProduit(String referenceProduit) {
        this.referenceProduit = referenceProduit;
    }
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Long categorieId) {
        this.categorieId = categorieId;
    }


    public String getCategorieReferenceCategorie() {
        return categorieReferenceCategorie;
    }

    public void setCategorieReferenceCategorie(String categorieReferenceCategorie) {
        this.categorieReferenceCategorie = categorieReferenceCategorie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProduitDTO produitDTO = (ProduitDTO) o;

        if ( ! Objects.equals(id, produitDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProduitDTO{" +
            "id=" + id +
            ", referenceProduit='" + referenceProduit + "'" +
            ", designation='" + designation + "'" +
            '}';
    }
}
