package ma.lnet.boncmd.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Panier.
 */
@Entity
@Table(name = "panier")
public class Panier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "quantite")
    private Long quantite;

    @ManyToOne
    private Commande commande;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantite() {
        return quantite;
    }

    public Panier quantite(Long quantite) {
        this.quantite = quantite;
        return this;
    }

    public void setQuantite(Long quantite) {
        this.quantite = quantite;
    }

    public Commande getCommande() {
        return commande;
    }

    public Panier commande(Commande commande) {
        this.commande = commande;
        return this;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Panier panier = (Panier) o;
        if (panier.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, panier.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Panier{" +
            "id=" + id +
            ", quantite='" + quantite + "'" +
            '}';
    }
}
