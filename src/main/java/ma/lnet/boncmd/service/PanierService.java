package ma.lnet.boncmd.service;

import ma.lnet.boncmd.service.dto.PanierDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Panier.
 */
public interface PanierService {

    /**
     * Save a panier.
     *
     * @param panierDTO the entity to save
     * @return the persisted entity
     */
    PanierDTO save(PanierDTO panierDTO);

    /**
     *  Get all the paniers.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<PanierDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" panier.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    PanierDTO findOne(Long id);

    /**
     *  Delete the "id" panier.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
