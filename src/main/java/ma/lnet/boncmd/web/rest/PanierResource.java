package ma.lnet.boncmd.web.rest;

import com.codahale.metrics.annotation.Timed;
import ma.lnet.boncmd.service.PanierService;
import ma.lnet.boncmd.web.rest.util.HeaderUtil;
import ma.lnet.boncmd.web.rest.util.PaginationUtil;
import ma.lnet.boncmd.service.dto.PanierDTO;

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
 * REST controller for managing Panier.
 */
@RestController
@RequestMapping("/api")
public class PanierResource {

    private final Logger log = LoggerFactory.getLogger(PanierResource.class);
        
    @Inject
    private PanierService panierService;

    /**
     * POST  /paniers : Create a new panier.
     *
     * @param panierDTO the panierDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new panierDTO, or with status 400 (Bad Request) if the panier has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/paniers")
    @Timed
    public ResponseEntity<PanierDTO> createPanier(@RequestBody PanierDTO panierDTO) throws URISyntaxException {
        log.debug("REST request to save Panier : {}", panierDTO);
        if (panierDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("panier", "idexists", "A new panier cannot already have an ID")).body(null);
        }
        PanierDTO result = panierService.save(panierDTO);
        return ResponseEntity.created(new URI("/api/paniers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("panier", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /paniers : Updates an existing panier.
     *
     * @param panierDTO the panierDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated panierDTO,
     * or with status 400 (Bad Request) if the panierDTO is not valid,
     * or with status 500 (Internal Server Error) if the panierDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/paniers")
    @Timed
    public ResponseEntity<PanierDTO> updatePanier(@RequestBody PanierDTO panierDTO) throws URISyntaxException {
        log.debug("REST request to update Panier : {}", panierDTO);
        if (panierDTO.getId() == null) {
            return createPanier(panierDTO);
        }
        PanierDTO result = panierService.save(panierDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("panier", panierDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /paniers : get all the paniers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of paniers in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/paniers")
    @Timed
    public ResponseEntity<List<PanierDTO>> getAllPaniers(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Paniers");
        Page<PanierDTO> page = panierService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/paniers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /paniers/:id : get the "id" panier.
     *
     * @param id the id of the panierDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the panierDTO, or with status 404 (Not Found)
     */
    @GetMapping("/paniers/{id}")
    @Timed
    public ResponseEntity<PanierDTO> getPanier(@PathVariable Long id) {
        log.debug("REST request to get Panier : {}", id);
        PanierDTO panierDTO = panierService.findOne(id);
        return Optional.ofNullable(panierDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /paniers/:id : delete the "id" panier.
     *
     * @param id the id of the panierDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/paniers/{id}")
    @Timed
    public ResponseEntity<Void> deletePanier(@PathVariable Long id) {
        log.debug("REST request to delete Panier : {}", id);
        panierService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("panier", id.toString())).build();
    }

}
