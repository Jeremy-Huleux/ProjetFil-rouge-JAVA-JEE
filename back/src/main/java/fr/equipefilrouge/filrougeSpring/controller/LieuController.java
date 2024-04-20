package fr.equipefilrouge.filrougeSpring.controller;

import fr.equipefilrouge.filrougeSpring.entity.Lieu;
import fr.equipefilrouge.filrougeSpring.services.impl.LieuServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller pour un centre de formation
 */
@RestController
@RequestMapping("/lieux")
@CrossOrigin
public class LieuController {

    /**
     * Appelle le service du centre de formation
     */
    private final LieuServiceImpl lieuService;

    /**
     * Constructeur du controller centre de formation
     * @param lieuService, le service centre de formation
     */
    public LieuController(LieuServiceImpl lieuService) {
        this.lieuService = lieuService;
    }

    /**
     * Méthode pour créer un nouveau centre de formation
     * @param lieu, le centre de formation à créer
     * @return le centre de formation nouvellement créé
     */
    @PostMapping("/create")
    public Lieu createLieu(@RequestBody Lieu lieu) {
        return lieuService.create(lieu);
    }

    @PostMapping("/createLieux")
    public List<Lieu> createLieux(@RequestBody List<Lieu> lieux) {
        return lieuService.createListe(lieux);
    }

    /**
     * Méthode pour mettre à jour un lieu
     * @param lieu la session de formation à mettre à jour
     * @return la session de formation mis à jour
     */
    @PatchMapping("/update")
    public Lieu updateLieu(@RequestBody Lieu lieu) {
        return lieuService.update(lieu);
    }

    /**
     * Affiche tous les lieux
     * @return la liste des lieux
     */
    @GetMapping("/all")
    public List<Lieu> getAllLieux() {
        return lieuService.findAll();
    }

    /**
     * Méthode pour rechercher un lieu avec son identifiant
     * @param id l'identifiant du lieu
     * @return le lieu recherchée
     */
    @GetMapping("/{id}")
    public Lieu getLieuById(@PathVariable Long id) {
        return lieuService.findById(id);
    }

    /**
     * Supprimer un lieu selon son id
     * @param id l'identifiant de la session
     * @return la session supprimée
     */
    @DeleteMapping("/delete/{id}")
    public Lieu deleteLieuById(@PathVariable Long id) {
        return lieuService.deleteById(id);
    }

    /**
     * Supprimer tous les lieus
     */
    @DeleteMapping("/delete/all")
    public void deleteAllLieus() {
        lieuService.deleteAll();
    }


}
