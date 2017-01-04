package ma.lnet.boncmd.service;

import ma.lnet.boncmd.service.dto.ProduitDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Produit.
 */
public interface ProduitService {

    /**
     * Save a produit.
     *
     * @param produitDTO the entity to save
     * @return the persisted entity
     */
    ProduitDTO save(ProduitDTO produitDTO);

    /**
     *  Get all the produits.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ProduitDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" produit.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProduitDTO findOne(Long id);

    /**
     *  Delete the "id" produit.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
