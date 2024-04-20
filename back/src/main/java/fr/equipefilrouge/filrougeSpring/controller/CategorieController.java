package fr.equipefilrouge.filrougeSpring.controller;

import fr.equipefilrouge.filrougeSpring.entity.Categorie;
import fr.equipefilrouge.filrougeSpring.services.AllServices;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/categories")
public class CategorieController {
    private final AllServices<Categorie, Long> categorieService;

    public CategorieController(AllServices<Categorie, Long> categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping
    public List<Categorie> getAllCategories() {
        return categorieService.findAll();
    }


    // OK fonctionnel
    @GetMapping("/{id}")
    public Categorie getCategorieById(@PathVariable Long id) {
        return categorieService.findById(id);
    }

    // Ok fonctionnel
    @PostMapping("/create")
    public Categorie createCategorie(@RequestBody Categorie categorie) {
        return categorieService.create(categorie);
    }

    // OK fonctionnel
    @PutMapping("/{id}")
    public Categorie updateCategorie(@PathVariable Long id, @RequestBody Categorie categorie) {
        categorie.setId(id);
        return categorieService.update(categorie);
    }

    // OK fonctionnel
    @DeleteMapping("/{id}")
    public Categorie deleteCategorie(@PathVariable Long id) {
        return categorieService.deleteById(id);
    }

    /**
     * Méthode pour mettre à jour un categorie
     * @param categorie le categorie mis à jour
     */

    // OK fonctionnel
    @PatchMapping("/update")
    public void update(@RequestBody Categorie categorie){
        categorieService.update(categorie);
    }

}
