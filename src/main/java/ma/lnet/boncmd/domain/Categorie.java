package ma.lnet.boncmd.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Categorie.
 */
@Entity
@Table(name = "categorie")
public class Categorie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "reference_categorie")
    private String referenceCategorie;

    @Column(name = "description")
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

    public Categorie referenceCategorie(String referenceCategorie) {
        this.referenceCategorie = referenceCategorie;
        return this;
    }

    public void setReferenceCategorie(String referenceCategorie) {
        this.referenceCategorie = referenceCategorie;
    }

    public String getDescription() {
        return description;
    }

    public Categorie description(String description) {
        this.description = description;
        return this;
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
        Categorie categorie = (Categorie) o;
        if (categorie.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, categorie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Categorie{" +
            "id=" + id +
            ", referenceCategorie='" + referenceCategorie + "'" +
            ", description='" + description + "'" +
            '}';
    }
}
