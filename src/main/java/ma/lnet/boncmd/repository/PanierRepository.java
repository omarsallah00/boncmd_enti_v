package ma.lnet.boncmd.repository;

import ma.lnet.boncmd.domain.Panier;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Panier entity.
 */
@SuppressWarnings("unused")
public interface PanierRepository extends JpaRepository<Panier,Long> {

}
