package ma.lnet.boncmd.service.impl;

import ma.lnet.boncmd.service.PanierService;
import ma.lnet.boncmd.domain.Panier;
import ma.lnet.boncmd.repository.PanierRepository;
import ma.lnet.boncmd.service.dto.PanierDTO;
import ma.lnet.boncmd.service.mapper.PanierMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Panier.
 */
@Service
@Transactional
public class PanierServiceImpl implements PanierService{

    private final Logger log = LoggerFactory.getLogger(PanierServiceImpl.class);
    
    @Inject
    private PanierRepository panierRepository;

    @Inject
    private PanierMapper panierMapper;

    /**
     * Save a panier.
     *
     * @param panierDTO the entity to save
     * @return the persisted entity
     */
    public PanierDTO save(PanierDTO panierDTO) {
        log.debug("Request to save Panier : {}", panierDTO);
        Panier panier = panierMapper.panierDTOToPanier(panierDTO);
        panier = panierRepository.save(panier);
        PanierDTO result = panierMapper.panierToPanierDTO(panier);
        return result;
    }

    /**
     *  Get all the paniers.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<PanierDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Paniers");
        Page<Panier> result = panierRepository.findAll(pageable);
        return result.map(panier -> panierMapper.panierToPanierDTO(panier));
    }

    /**
     *  Get one panier by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public PanierDTO findOne(Long id) {
        log.debug("Request to get Panier : {}", id);
        Panier panier = panierRepository.findOne(id);
        PanierDTO panierDTO = panierMapper.panierToPanierDTO(panier);
        return panierDTO;
    }

    /**
     *  Delete the  panier by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Panier : {}", id);
        panierRepository.delete(id);
    }
}
