package fr.equipefilrouge.filrougeSpring.controller;

import fr.equipefilrouge.filrougeSpring.dto.FormationDTO;
import fr.equipefilrouge.filrougeSpring.entity.Bootcamp;
import fr.equipefilrouge.filrougeSpring.entity.Formation;
import fr.equipefilrouge.filrougeSpring.entity.SousTheme;
import fr.equipefilrouge.filrougeSpring.services.impl.FormationServiceImpl;
import fr.equipefilrouge.filrougeSpring.services.impl.SousThemeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller pour une formation
 */
@RestController
@RequestMapping("/formations")
@CrossOrigin
public class FormationController {

    /**
     * Déclaration du service Formation
     */
    private final FormationServiceImpl formationServiceImpl;

    /**
     * Déclaration du service SousTheme
     */
    private final SousThemeServiceImpl sousThemeService;

    /**
     * Constructeur du controller Formation
     * @param formationServiceImpl le service de la formation
     */
    public FormationController(FormationServiceImpl formationServiceImpl, SousThemeServiceImpl sousThemeService) {
        this.formationServiceImpl = formationServiceImpl;
        this.sousThemeService = sousThemeService;
    }

    /**
     * Affiche toutes les formations
     * @return la liste des formations
     */
    @GetMapping("/all")
    public List<Formation> getAllFormations() {
        return formationServiceImpl.findAll();
    }

    /**
     * Méthode pour rechercher une session avec son identifiant
     * @param id l'identifiant de la session
     * @return la session recherchée
     */
    @GetMapping("/{id}")
    public Formation getFormationById(@PathVariable Long id) {
        return formationServiceImpl.findById(id);
    }

    /**
     * Méthode pour retrouver une liste de bootcamps dans les formations
     * @param formationId l'identifiant de la formation liée au bootcamp
     * @return la liste des bootcamps liés à la formation
     */
    @GetMapping("/{formationId}/bootcamps")
    public ResponseEntity<List<Bootcamp>> getBootcampsInFormation(@PathVariable Long formationId) {
        List<Bootcamp> bootcamps = formationServiceImpl.getBootcampsInFormations(formationId);
        return new ResponseEntity<>(bootcamps, HttpStatus.OK);
    }

    /**
     * Méthode pour créer une nouvelle formation
     * @param formationDTO La formation à créer
     * @return la formation nouvellement créée
     */
    @PostMapping("/create")
    public Formation createFormation(@RequestBody FormationDTO formationDTO) {
        // Récupère le sous-thème correspondant à l'ID
        SousTheme sousTheme = sousThemeService.findById(formationDTO.getSousThemeId());
        // Crée une nouvelle formation
        Formation formation = new Formation(formationDTO.getNom(), formationDTO.getPrix(),
                formationDTO.getDescription(), formationDTO.getImg(), sousTheme);
        // Enregistre la formation
        return formationServiceImpl.create(formation);
    }

    /**
     * Création d'une liste de formations
     * @param formations la liste des formations à créer
     * @return la liste des formations nouvellement créée
     */
    @PostMapping("/createFormations")
    public List<Formation> listFormations(@RequestBody List<Formation> formations) {
        return formationServiceImpl.createListe(formations);
    }

    /**
     * Méthode pour mettre à jour une formation
     * @param formation la formation à mettre à jour
     * @return la formation mis à jour
     */
    @PatchMapping("/update")
    public Formation updateFormation(@RequestBody Formation formation) {
        return formationServiceImpl.update(formation);
    }

    /**
     * Renvoyer le nombre de formations en BDD
     * @return le nombre de formations
     */
    @GetMapping("/nbFormations")
    public Long nbFormations() {
        return formationServiceImpl.countFormations();
    }

    /**Recherche les formations par nom
    * @param nom Le nom à rechercher dans les formations
    * @return Liste des formations correspondantes au nom recherché*/
    @GetMapping("/search")
    public List<Formation> searchFormationsByName(@RequestParam String nom) {
        return formationServiceImpl.findByNomContaining(nom);
    }

    /**
     * Supprimer une formation selon son id
     * @param id l'identifiant de la formation
     * @return la formation supprimée
     */
    @DeleteMapping("/delete/{id}")
    public Formation deleteFormationById(@PathVariable Long id) {
        return formationServiceImpl.deleteById(id);
    }

    /**
     * Supprimer toutes les formations
     */
    @DeleteMapping("/delete/all")
    public void deleteAllFormation() {
        formationServiceImpl.deleteAll();
    }

}
