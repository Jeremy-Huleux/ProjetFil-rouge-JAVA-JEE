package fr.equipefilrouge.filrougeSpring.controller;

import fr.equipefilrouge.filrougeSpring.dto.ThemeDTO;
import fr.equipefilrouge.filrougeSpring.entity.Categorie;
import fr.equipefilrouge.filrougeSpring.entity.Theme;
import fr.equipefilrouge.filrougeSpring.services.AllServices;
import fr.equipefilrouge.filrougeSpring.services.impl.CategorieServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/themes")
public class ThemeController {

    private final AllServices<Theme, Long> themeService;
    private final CategorieServiceImpl categorieService;

    public ThemeController(AllServices<Theme, Long> themeService, CategorieServiceImpl categorieService) {
        this.themeService = themeService;
        this.categorieService = categorieService;
    }


    // OK test fonctionnel
    @PostMapping("/create")
    public Theme createTheme(@RequestBody ThemeDTO themeFormat) {
        Long categoryId = themeFormat.getCategoryId();
        Categorie categorie = categorieService.findById(categoryId);
        Theme theme = new Theme(themeFormat.getNom(), themeFormat.getDescription(), categorie);
        return themeService.create(theme);
    }

    // Ok test fonctionnel
    @GetMapping("/all")
    public List<Theme> getAllThemes() {
        return themeService.findAll();
    }

    // Ok test fonctionnel
    @GetMapping("/{id}")
    public Theme getThemeById(@PathVariable Long id) {
        return themeService.findById(id);
    }


    // ok test fonctionnel
    @PutMapping("/{id}")
    public Theme updateTheme(@PathVariable Long id, @RequestBody Theme theme) {
        theme.setId(id);
        // Vérifie si la catégorie existe déjà
        Categorie categorie = theme.getCategorie();
        if (categorie != null && categorie.getId() != null) {
            Categorie existingCategorie = categorieService.findById(categorie.getId());
            if (existingCategorie == null) {
                // Si la catégorie n'existe pas, créez-la
                existingCategorie = new Categorie(categorie.getNom(), categorie.getDescription());
                categorieService.create(existingCategorie);
            }
            // Mettez à jour la catégorie dans le thème avec celle existante ou nouvellement créée
            theme.setCategorie(existingCategorie);
        }
        return themeService.update(theme);
    }
    // OK test fonctionnel
    @DeleteMapping("/{id}")
    public Theme deleteTheme(@PathVariable Long id) {
        return themeService.deleteById(id);
    }
}
