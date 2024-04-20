package fr.equipefilrouge.filrougeSpring.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.equipefilrouge.filrougeSpring.dto.FormationDTO;
import fr.equipefilrouge.filrougeSpring.entity.Bootcamp;
import fr.equipefilrouge.filrougeSpring.entity.Formation;
import fr.equipefilrouge.filrougeSpring.exceptions.CustomException;
import fr.equipefilrouge.filrougeSpring.repository.FormationRepository;
import fr.equipefilrouge.filrougeSpring.services.AllServices;
import jakarta.transaction.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service pour implémenter les méthodes du CRUD pour une session de formation
 */
@Service
@Transactional
public class FormationServiceImpl implements AllServices<Formation, Long> {

    /**
     * Le repository Formation
     */
    private final FormationRepository formationRepository;

    /**
     * API pour gérer les opérations sur la BDD
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Sérialisation d'objet Java au format Json
     */
    private final ObjectMapper objectMapper;

    /**
     * Le constructeur du service
     * @param formationRepository etc... les repository injectés
     */
    public FormationServiceImpl(FormationRepository formationRepository, JdbcTemplate jdbcTemplate, ObjectMapper objectMapper) {
        this.formationRepository = formationRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * @return la liste des formations
     */
    @Override
    public List<Formation> findAll() {
        return formationRepository.findAll();
    }

    /**
     * Méthode pour retourner toutes les formations avec les informations choisies
     * @return la liste des formations
     */
    public List<FormationDTO> findAllFormationReduit() {
        List<Formation> formations = formationRepository.findAll();
        return formations.stream()
                .map(formation -> objectMapper.convertValue(formation, FormationDTO.class))
                .toList();
    }

    /**
     * Méthode pour trouver une formation par son id
     * @param id l'identifiant recherché
     * @return la formation trouvé
     */
    @Override
    public Formation findById(Long id) {
        return formationRepository.findById(id)
                .orElseThrow(() -> new CustomException("Formation", "id", id));
    }

    /**
     * Récupérer tous les bootcamps liés à une formation donnée
     * @param formationId l'identifiant du bootcamp
     * @return la liste des bootcamps associés
     */
    public List<Bootcamp> getBootcampsInFormations(Long formationId) {
        return formationRepository.getBootcampsInFormations(formationId);
    }

    /**
     * Méthode pour trouver une formation par son nom
     * @param nom le nom de la formation
     * @return la formation recherchée
     */
    public List<Formation> findByNomContaining(String nom) {
        return formationRepository.findByNomContaining(nom);
    }

    /**
     * @param newObj le nouvel objet formation
     * @return la formation nouvellement créée
     */
    @Override
    public Formation create(Formation newObj) {
        return formationRepository.save(newObj);
    }

    /**
     * Méthode pour ajouter une liste de formations
     * @param formations la liste des formations à ajouter
     * @return la liste des formations
     */
    public List<Formation> createListe(List<Formation> formations) {
        return formationRepository.saveAll(formations);
    }

    /**
     * Méthode pour mettre à jou une formation
     * @param formation l'objet à mettre à jour
     * @return la formation mise à jour
     */
    @Override
    public Formation update(Formation formation) {
        if (formation.getId() != null) {
            jdbcTemplate.update("UPDATE formation SET nom_formation = ?, prix = ?, description = ? WHERE id = ?",
                    formation.getNom(), formation.getPrix(), formation.getDescription(), formation.getId());
            formationRepository.save(formation);
        } else {
            throw new CustomException("Formation", "id", "Identifiant inconnu !");
        }
        return formation;
    }

    /**
     * @param id l'identifiant recherché
     * @return la formation à supprimer
     */
    @Override
    public Formation deleteById(Long id) {
        Formation formation = findById(id);
        formationRepository.deleteById(id);
        return formation;
    }

    /**
     * Méthode pour supprimer toutes les formations
     */
    @Override
    public void deleteAll() {
        formationRepository.deleteAll();
    }

    /**
     * Compteur de formations dans la BDD
     * @return number
     */
    public Long countFormations() {
        return formationRepository.countFormation();
    }

}
