package ma.lnet.boncmd.service.impl;

import ma.lnet.boncmd.service.CategorieService;
import ma.lnet.boncmd.domain.Categorie;
import ma.lnet.boncmd.repository.CategorieRepository;
import ma.lnet.boncmd.service.dto.CategorieDTO;
import ma.lnet.boncmd.service.mapper.CategorieMapper;
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
 * Service Implementation for managing Categorie.
 */
@Service
@Transactional
public class CategorieServiceImpl implements CategorieService{

    private final Logger log = LoggerFactory.getLogger(CategorieServiceImpl.class);
    
    @Inject
    private CategorieRepository categorieRepository;

    @Inject
    private CategorieMapper categorieMapper;

    /**
     * Save a categorie.
     *
     * @param categorieDTO the entity to save
     * @return the persisted entity
     */
    public CategorieDTO save(CategorieDTO categorieDTO) {
        log.debug("Request to save Categorie : {}", categorieDTO);
        Categorie categorie = categorieMapper.categorieDTOToCategorie(categorieDTO);
        categorie = categorieRepository.save(categorie);
        CategorieDTO result = categorieMapper.categorieToCategorieDTO(categorie);
        return result;
    }

    /**
     *  Get all the categories.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<CategorieDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Categories");
        Page<Categorie> result = categorieRepository.findAll(pageable);
        return result.map(categorie -> categorieMapper.categorieToCategorieDTO(categorie));
    }

    /**
     *  Get one categorie by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public CategorieDTO findOne(Long id) {
        log.debug("Request to get Categorie : {}", id);
        Categorie categorie = categorieRepository.findOne(id);
        CategorieDTO categorieDTO = categorieMapper.categorieToCategorieDTO(categorie);
        return categorieDTO;
    }

    /**
     *  Delete the  categorie by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Categorie : {}", id);
        categorieRepository.delete(id);
    }
}
