package fr.equipefilrouge.filrougeSpring.controller;

import fr.equipefilrouge.filrougeSpring.dto.BootcampDTO;
import fr.equipefilrouge.filrougeSpring.entity.*;
import fr.equipefilrouge.filrougeSpring.services.impl.BootcampServiceImpl;
import fr.equipefilrouge.filrougeSpring.services.impl.LieuServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Controller des bootcamps
 */
@RestController
@RequestMapping("/bootcamps")
@CrossOrigin
public class BootcampController {

    /**
     * Le service des bootcamps
     */
    private final BootcampServiceImpl bootcampService;


    /**
     * Le service des lieux
     */
    private final LieuServiceImpl lieuService;

    /**
     * Le constructeur
     * @param bootcampService le service bootcamp
     * @param lieuService le service lieu
     */
    public BootcampController(BootcampServiceImpl bootcampService, LieuServiceImpl lieuService) {
        this.bootcampService = bootcampService;
        this.lieuService = lieuService;
    }

    /**
     * Méthode pour récupérer tous les bootcamps
     * @return la liste de tous les bootcamps
     */
    @GetMapping("/all")
    public List<Bootcamp> findAll(){
        return bootcampService.findAll();
    }

    /**
     * Méthode pour trouver un bootcamp avec son id
     * @param id l'id recherché
     * @return le bootcamp recherché
     */
    @GetMapping("/find/{id}")
    public Bootcamp findById(@PathVariable long id){
        return bootcampService.findById(id);
    }

    /**
     * Renvoyer le nombre de bootcamps en BDD
     * @return le nombre de bootcamps
     */
    @GetMapping("/nbBootcamps")
    public Long nbBootcamps() {
        return bootcampService.countBootcamps();
    }

    /**
     * Méthode pour récupérer toutes les formations liées à un bootcamp
     * @param bootcampId l'identifiant du bootcamp
     * @return la liste des formations associées
     */
    @GetMapping("/{bootcampId}/formations")
    public ResponseEntity<List<Formation>> getFormationsInBootcamp(@PathVariable Long bootcampId) {
        List<Formation> formations = bootcampService.getFormationsInBootcamp(bootcampId);
        return new ResponseEntity<>(formations, HttpStatus.OK);
    }

    /**
     * Méthode pour récupérer le nombre d'utilisateurs inscrits à un bootcamp
     * @return le nombre d'utilisateurs
     */
    @GetMapping("/nbUsers")
    public ResponseEntity<Map<Long, Long>> countUsersInBootcamps() {
        Map<Long, Long> userCounts = bootcampService.countUsersInBootcamps();
        return new ResponseEntity<>(userCounts, HttpStatus.OK);
    }

    /** Recherche les bootcamps par nom
     * @param statut Le statut à rechercher dans les bootcamps
     * @return Liste des bootcamps correspondants au statut recherché
     */
    @GetMapping("/search")
    public List<Bootcamp> searchByStatut(@RequestParam String statut) {
        return bootcampService.findByStatut(statut);
    }

    /**
     * Méthode pour créer un bootcamp
     * @param bootcampDTO le bootcamp à créer
     * @return le bootcamp nouvellement créé
     */
    @PostMapping("/createBootcamp")
    public Bootcamp createBootcamp(@RequestBody BootcampDTO bootcampDTO) {
        // Récupère le thème correspondant à l'ID
        Lieu lieu = lieuService.findById(bootcampDTO.getLieuId());
        // Crée un nouveau sous-thème
        Bootcamp bootcamp = new Bootcamp(bootcampDTO.getDateDebut(), bootcampDTO.getDateFin(),
                bootcampDTO.getStatut(), lieu);
        // Enregistre le sous-thème
        return bootcampService.create(bootcamp);
    }

    /**
     * Crée une liste de bootcamps avec les informations fournies.
     *
     * @param bootcamps  Liste des bootcamps à créer.
     * @param idLieux    Liste des identifiants des lieux associés aux bootcamps.
     * @return Liste des bootcamps créés sous forme de DTO réduit.
     */
    @PostMapping("/createBootcamps")
    public List<BootcampDTO> createBootcamps(
            @Validated @RequestBody List<Bootcamp> bootcamps,
            @RequestParam List<Long> idLieux) {

        // Liste pour stocker les bootcamps créés sous forme de DTO réduit
        List<BootcampDTO> createdBootcamps = new ArrayList<>();

        // Parcours de la liste des bootcamps à créer
        for (int i = 0; i < bootcamps.size(); i++) {
            // Récupération du bootcamp et de l'identifiant du lieu associé
            Bootcamp bootcamp = bootcamps.get(i);
            Long idLieu = idLieux.get(i);

            // Récupération du lieu associé à partir de l'identifiant
            Lieu lieu = lieuService.findById(idLieu);

            // Création du bootcamp à l'aide du service
            Bootcamp createdBootcamp = bootcampService.create(
                    new Bootcamp(bootcamp.getDateDebut(), bootcamp.getDateFin(),
                            bootcamp.getStatut(), lieu));

            // Création du DTO réduit pour le bootcamp créé
            BootcampDTO bootcampDTO = new BootcampDTO();
            bootcampDTO.setDateDebut(createdBootcamp.getDateDebut());
            bootcampDTO.setDateFin(createdBootcamp.getDateFin());
            bootcampDTO.setStatut(createdBootcamp.getStatut());

            // Ajout du DTO réduit à la liste des bootcamps créés
            createdBootcamps.add(bootcampDTO);
        }
        // Retourne la liste des bootcamps créés sous forme de DTO réduit
        return createdBootcamps;
    }

    /**
     * Ajouter un user à un bootcamp
     * @param bootcampId l'id du bootcamp
     * @param requestData la donnée recherchée
     * @return Réponse ok si l'utilisateur a été ajouté
     */
    @PostMapping("/{bootcampId}/addUser")
    public ResponseEntity<String> addUserToBootcamp(@PathVariable Long bootcampId, @RequestBody Map<String, Long> requestData) {
        Long userId = requestData.get("userId");
        BootcampDTO bootcampDTO = new BootcampDTO();
        bootcampDTO.setId(bootcampId);

        bootcampService.addUserBootcamp(bootcampDTO, userId);

        return new ResponseEntity<>("User ajouté au bootcamp " + bootcampId, HttpStatus.OK);
    }

    @PostMapping("/bootcamps/{bootcampId}/addFormations")
    public ResponseEntity<String> addFormationsToBootcamp(@PathVariable Long bootcampId, @RequestBody List<Formation> formations) {
        BootcampDTO bootcampDTO = new BootcampDTO();
        bootcampDTO.setId(bootcampId);

        bootcampService.addFormationsToBootcamp(bootcampDTO, formations);
        return new ResponseEntity<>("Formations ajoutées au bootcamp " + bootcampId, HttpStatus.OK);
    }

    /**
     * Méthode pour mettre à jour un bootcamp
     * @param bootcamp le bootcamp mis à jour
     */
    @PatchMapping("/update")
    public void update(@RequestBody Bootcamp bootcamp){
        bootcampService.update(bootcamp);
    }

    /**
     * Méthode pour supprimer un bootcamp selon son id
     * @param id l'id recherché
     */
    @DeleteMapping("/delete/{id}")
    public Bootcamp deleteById(@PathVariable long id){
        return bootcampService.deleteById(id);
    }

    /**
     * Méthode pour supprimer tous les bootcamps
     */
    @DeleteMapping("/delete")
    public void deleteAll(){
        bootcampService.deleteAll();
    }

}