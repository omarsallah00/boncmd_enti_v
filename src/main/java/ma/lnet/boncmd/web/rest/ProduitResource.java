package ma.lnet.boncmd.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.lnet.boncmd.service.ProduitService;
import ma.lnet.boncmd.web.rest.util.HeaderUtil;
import ma.lnet.boncmd.web.rest.util.PaginationUtil;
import ma.lnet.boncmd.service.dto.ProduitDTO;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Produit.
 */
@RestController
@RequestMapping("/api")
public class ProduitResource {

    private final Logger log = LoggerFactory.getLogger(ProduitResource.class);
        
    @Inject
    private ProduitService produitService;

    /**
     * POST  /produits : Create a new produit.
     *
     * @param produitDTO the produitDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new produitDTO, or with status 400 (Bad Request) if the produit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/produits")
    @Timed
    public ResponseEntity<ProduitDTO> createProduit(@RequestBody ProduitDTO produitDTO) throws URISyntaxException {
        log.debug("REST request to save Produit : {}", produitDTO);
        if (produitDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("produit", "idexists", "A new produit cannot already have an ID")).body(null);
        }
        ProduitDTO result = produitService.save(produitDTO);
        return ResponseEntity.created(new URI("/api/produits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("produit", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /produits : Updates an existing produit.
     *
     * @param produitDTO the produitDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated produitDTO,
     * or with status 400 (Bad Request) if the produitDTO is not valid,
     * or with status 500 (Internal Server Error) if the produitDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/produits")
    @Timed
    public ResponseEntity<ProduitDTO> updateProduit(@RequestBody ProduitDTO produitDTO) throws URISyntaxException {
        log.debug("REST request to update Produit : {}", produitDTO);
        if (produitDTO.getId() == null) {
            return createProduit(produitDTO);
        }
        ProduitDTO result = produitService.save(produitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("produit", produitDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /produits : get all the produits.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of produits in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/produits")
    @Timed
    public ResponseEntity<List<ProduitDTO>> getAllProduits(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Produits");
        Page<ProduitDTO> page = produitService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/produits");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /produits/:id : get the "id" produit.
     *
     * @param id the id of the produitDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the produitDTO, or with status 404 (Not Found)
     */
    @GetMapping("/produits/{id}")
    @Timed
    public ResponseEntity<ProduitDTO> getProduit(@PathVariable Long id) {
        log.debug("REST request to get Produit : {}", id);
        ProduitDTO produitDTO = produitService.findOne(id);
        return Optional.ofNullable(produitDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /produits/:id : delete the "id" produit.
     *
     * @param id the id of the produitDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/produits/{id}")
    @Timed
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        log.debug("REST request to delete Produit : {}", id);
        produitService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("produit", id.toString())).build();
    }

}
