package ma.lnet.boncmd.service.dto;

import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the Categorie entity.
 */
public class CategorieDTO implements Serializable {

    private Long id;

    private String referenceCategorie;

    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getReferenceCategorie() {
        return referenceCategorie;
    }

    public void setReferenceCategorie(String referenceCategorie) {
        this.referenceCategorie = referenceCategorie;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CategorieDTO categorieDTO = (CategorieDTO) o;

        if ( ! Objects.equals(id, categorieDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CategorieDTO{" +
            "id=" + id +
            ", referenceCategorie='" + referenceCategorie + "'" +
            ", description='" + description + "'" +
            '}';
    }
}
