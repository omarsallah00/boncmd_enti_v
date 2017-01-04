package ma.lnet.boncmd.service.impl;

import ma.lnet.boncmd.service.ProduitService;
import ma.lnet.boncmd.domain.Produit;
import ma.lnet.boncmd.repository.ProduitRepository;
import ma.lnet.boncmd.service.dto.ProduitDTO;
import ma.lnet.boncmd.service.mapper.ProduitMapper;
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
 * Service Implementation for managing Produit.
 */
@Service
@Transactional
public class ProduitServiceImpl implements ProduitService{

    private final Logger log = LoggerFactory.getLogger(ProduitServiceImpl.class);
    
    @Inject
    private ProduitRepository produitRepository;

    @Inject
    private ProduitMapper produitMapper;

    /**
     * Save a produit.
     *
     * @param produitDTO the entity to save
     * @return the persisted entity
     */
    public ProduitDTO save(ProduitDTO produitDTO) {
        log.debug("Request to save Produit : {}", produitDTO);
        Produit produit = produitMapper.produitDTOToProduit(produitDTO);
        produit = produitRepository.save(produit);
        ProduitDTO result = produitMapper.produitToProduitDTO(produit);
        return result;
    }

    /**
     *  Get all the produits.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<ProduitDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Produits");
        Page<Produit> result = produitRepository.findAll(pageable);
        return result.map(produit -> produitMapper.produitToProduitDTO(produit));
    }

    /**
     *  Get one produit by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public ProduitDTO findOne(Long id) {
        log.debug("Request to get Produit : {}", id);
        Produit produit = produitRepository.findOne(id);
        ProduitDTO produitDTO = produitMapper.produitToProduitDTO(produit);
        return produitDTO;
    }

    /**
     *  Delete the  produit by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Produit : {}", id);
        produitRepository.delete(id);
    }
}
