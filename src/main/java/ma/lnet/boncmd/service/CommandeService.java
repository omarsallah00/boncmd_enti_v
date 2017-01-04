package ma.lnet.boncmd.service;

import ma.lnet.boncmd.service.dto.CommandeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Commande.
 */
public interface CommandeService {

    /**
     * Save a commande.
     *
     * @param commandeDTO the entity to save
     * @return the persisted entity
     */
    CommandeDTO save(CommandeDTO commandeDTO);

    /**
     *  Get all the commandes.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<CommandeDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" commande.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CommandeDTO findOne(Long id);

    /**
     *  Delete the "id" commande.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
