package ma.lnet.boncmd.web.rest;

import ma.lnet.boncmd.Boncmd3App;

import ma.lnet.boncmd.domain.Commande;
import ma.lnet.boncmd.repository.CommandeRepository;
import ma.lnet.boncmd.service.CommandeService;
import ma.lnet.boncmd.service.dto.CommandeDTO;
import ma.lnet.boncmd.service.mapper.CommandeMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static ma.lnet.boncmd.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CommandeResource REST controller.
 *
 * @see CommandeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Boncmd3App.class)
public class CommandeResourceIntTest {

    private static final String DEFAULT_REFERENCE_COMMANDE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE_COMMANDE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_COMMANDE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_COMMANDE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_EMAIL_CONTACT_ADMINISTRATIF = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_CONTACT_ADMINISTRATIF = "BBBBBBBBBB";

    private static final String DEFAULT_TEL_CONTACT_ADMINISTRATIF = "AAAAAAAAAA";
    private static final String UPDATED_TEL_CONTACT_ADMINISTRATIF = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_CONTACT_TECHNIQUE = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_CONTACT_TECHNIQUE = "BBBBBBBBBB";

    private static final String DEFAULT_TEL_CONTACT_TECHNIQUE = "AAAAAAAAAA";
    private static final String UPDATED_TEL_CONTACT_TECHNIQUE = "BBBBBBBBBB";

    @Inject
    private CommandeRepository commandeRepository;

    @Inject
    private CommandeMapper commandeMapper;

    @Inject
    private CommandeService commandeService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restCommandeMockMvc;

    private Commande commande;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CommandeResource commandeResource = new CommandeResource();
        ReflectionTestUtils.setField(commandeResource, "commandeService", commandeService);
        this.restCommandeMockMvc = MockMvcBuilders.standaloneSetup(commandeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Commande createEntity(EntityManager em) {
        Commande commande = new Commande()
                .referenceCommande(DEFAULT_REFERENCE_COMMANDE)
                .dateCommande(DEFAULT_DATE_COMMANDE)
                .emailContactAdministratif(DEFAULT_EMAIL_CONTACT_ADMINISTRATIF)
                .telContactAdministratif(DEFAULT_TEL_CONTACT_ADMINISTRATIF)
                .emailContactTechnique(DEFAULT_EMAIL_CONTACT_TECHNIQUE)
                .telContactTechnique(DEFAULT_TEL_CONTACT_TECHNIQUE);
        return commande;
    }

    @Before
    public void initTest() {
        commande = createEntity(em);
    }

    @Test
    @Transactional
    public void createCommande() throws Exception {
        int databaseSizeBeforeCreate = commandeRepository.findAll().size();

        // Create the Commande
        CommandeDTO commandeDTO = commandeMapper.commandeToCommandeDTO(commande);

        restCommandeMockMvc.perform(post("/api/commandes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commandeDTO)))
            .andExpect(status().isCreated());

        // Validate the Commande in the database
        List<Commande> commandeList = commandeRepository.findAll();
        assertThat(commandeList).hasSize(databaseSizeBeforeCreate + 1);
        Commande testCommande = commandeList.get(commandeList.size() - 1);
        assertThat(testCommande.getReferenceCommande()).isEqualTo(DEFAULT_REFERENCE_COMMANDE);
        assertThat(testCommande.getDateCommande()).isEqualTo(DEFAULT_DATE_COMMANDE);
        assertThat(testCommande.getEmailContactAdministratif()).isEqualTo(DEFAULT_EMAIL_CONTACT_ADMINISTRATIF);
        assertThat(testCommande.getTelContactAdministratif()).isEqualTo(DEFAULT_TEL_CONTACT_ADMINISTRATIF);
        assertThat(testCommande.getEmailContactTechnique()).isEqualTo(DEFAULT_EMAIL_CONTACT_TECHNIQUE);
        assertThat(testCommande.getTelContactTechnique()).isEqualTo(DEFAULT_TEL_CONTACT_TECHNIQUE);
    }

    @Test
    @Transactional
    public void createCommandeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = commandeRepository.findAll().size();

        // Create the Commande with an existing ID
        Commande existingCommande = new Commande();
        existingCommande.setId(1L);
        CommandeDTO existingCommandeDTO = commandeMapper.commandeToCommandeDTO(existingCommande);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommandeMockMvc.perform(post("/api/commandes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingCommandeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Commande> commandeList = commandeRepository.findAll();
        assertThat(commandeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCommandes() throws Exception {
        // Initialize the database
        commandeRepository.saveAndFlush(commande);

        // Get all the commandeList
        restCommandeMockMvc.perform(get("/api/commandes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commande.getId().intValue())))
            .andExpect(jsonPath("$.[*].referenceCommande").value(hasItem(DEFAULT_REFERENCE_COMMANDE.toString())))
            .andExpect(jsonPath("$.[*].dateCommande").value(hasItem(sameInstant(DEFAULT_DATE_COMMANDE))))
            .andExpect(jsonPath("$.[*].emailContactAdministratif").value(hasItem(DEFAULT_EMAIL_CONTACT_ADMINISTRATIF.toString())))
            .andExpect(jsonPath("$.[*].telContactAdministratif").value(hasItem(DEFAULT_TEL_CONTACT_ADMINISTRATIF.toString())))
            .andExpect(jsonPath("$.[*].emailContactTechnique").value(hasItem(DEFAULT_EMAIL_CONTACT_TECHNIQUE.toString())))
            .andExpect(jsonPath("$.[*].telContactTechnique").value(hasItem(DEFAULT_TEL_CONTACT_TECHNIQUE.toString())));
    }

    @Test
    @Transactional
    public void getCommande() throws Exception {
        // Initialize the database
        commandeRepository.saveAndFlush(commande);

        // Get the commande
        restCommandeMockMvc.perform(get("/api/commandes/{id}", commande.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(commande.getId().intValue()))
            .andExpect(jsonPath("$.referenceCommande").value(DEFAULT_REFERENCE_COMMANDE.toString()))
            .andExpect(jsonPath("$.dateCommande").value(sameInstant(DEFAULT_DATE_COMMANDE)))
            .andExpect(jsonPath("$.emailContactAdministratif").value(DEFAULT_EMAIL_CONTACT_ADMINISTRATIF.toString()))
            .andExpect(jsonPath("$.telContactAdministratif").value(DEFAULT_TEL_CONTACT_ADMINISTRATIF.toString()))
            .andExpect(jsonPath("$.emailContactTechnique").value(DEFAULT_EMAIL_CONTACT_TECHNIQUE.toString()))
            .andExpect(jsonPath("$.telContactTechnique").value(DEFAULT_TEL_CONTACT_TECHNIQUE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCommande() throws Exception {
        // Get the commande
        restCommandeMockMvc.perform(get("/api/commandes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCommande() throws Exception {
        // Initialize the database
        commandeRepository.saveAndFlush(commande);
        int databaseSizeBeforeUpdate = commandeRepository.findAll().size();

        // Update the commande
        Commande updatedCommande = commandeRepository.findOne(commande.getId());
        updatedCommande
                .referenceCommande(UPDATED_REFERENCE_COMMANDE)
                .dateCommande(UPDATED_DATE_COMMANDE)
                .emailContactAdministratif(UPDATED_EMAIL_CONTACT_ADMINISTRATIF)
                .telContactAdministratif(UPDATED_TEL_CONTACT_ADMINISTRATIF)
                .emailContactTechnique(UPDATED_EMAIL_CONTACT_TECHNIQUE)
                .telContactTechnique(UPDATED_TEL_CONTACT_TECHNIQUE);
        CommandeDTO commandeDTO = commandeMapper.commandeToCommandeDTO(updatedCommande);

        restCommandeMockMvc.perform(put("/api/commandes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commandeDTO)))
            .andExpect(status().isOk());

        // Validate the Commande in the database
        List<Commande> commandeList = commandeRepository.findAll();
        assertThat(commandeList).hasSize(databaseSizeBeforeUpdate);
        Commande testCommande = commandeList.get(commandeList.size() - 1);
        assertThat(testCommande.getReferenceCommande()).isEqualTo(UPDATED_REFERENCE_COMMANDE);
        assertThat(testCommande.getDateCommande()).isEqualTo(UPDATED_DATE_COMMANDE);
        assertThat(testCommande.getEmailContactAdministratif()).isEqualTo(UPDATED_EMAIL_CONTACT_ADMINISTRATIF);
        assertThat(testCommande.getTelContactAdministratif()).isEqualTo(UPDATED_TEL_CONTACT_ADMINISTRATIF);
        assertThat(testCommande.getEmailContactTechnique()).isEqualTo(UPDATED_EMAIL_CONTACT_TECHNIQUE);
        assertThat(testCommande.getTelContactTechnique()).isEqualTo(UPDATED_TEL_CONTACT_TECHNIQUE);
    }

    @Test
    @Transactional
    public void updateNonExistingCommande() throws Exception {
        int databaseSizeBeforeUpdate = commandeRepository.findAll().size();

        // Create the Commande
        CommandeDTO commandeDTO = commandeMapper.commandeToCommandeDTO(commande);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCommandeMockMvc.perform(put("/api/commandes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commandeDTO)))
            .andExpect(status().isCreated());

        // Validate the Commande in the database
        List<Commande> commandeList = commandeRepository.findAll();
        assertThat(commandeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCommande() throws Exception {
        // Initialize the database
        commandeRepository.saveAndFlush(commande);
        int databaseSizeBeforeDelete = commandeRepository.findAll().size();

        // Get the commande
        restCommandeMockMvc.perform(delete("/api/commandes/{id}", commande.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Commande> commandeList = commandeRepository.findAll();
        assertThat(commandeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
