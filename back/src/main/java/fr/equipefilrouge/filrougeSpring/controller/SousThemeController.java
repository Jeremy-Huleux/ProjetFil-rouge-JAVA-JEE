package fr.equipefilrouge.filrougeSpring.controller;

import fr.equipefilrouge.filrougeSpring.dto.SousThemeDTO;
import fr.equipefilrouge.filrougeSpring.entity.SousTheme;
import fr.equipefilrouge.filrougeSpring.entity.Theme;
import fr.equipefilrouge.filrougeSpring.services.impl.SousThemeServiceImpl;
import fr.equipefilrouge.filrougeSpring.services.impl.ThemeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sousThemes")
@CrossOrigin
public class SousThemeController {

    private final SousThemeServiceImpl sousThemeService;
    private final ThemeServiceImpl themeService;

    public SousThemeController(SousThemeServiceImpl sousThemeService, ThemeServiceImpl themeService) {
        this.sousThemeService = sousThemeService;
        this.themeService = themeService;
    }

    // OK testé
    @PostMapping("/create")
    public SousTheme createSousTheme(@RequestBody SousThemeDTO sousThemeDTO) {
        // Récupère le thème correspondant à l'ID
        Theme theme = themeService.findById(sousThemeDTO.getThemeId());
        // Crée un nouveau sous-thème
        SousTheme sousTheme = new SousTheme(sousThemeDTO.getNomSousTheme(), theme);
        // Enregistre le sous-thème
        return sousThemeService.create(sousTheme);
    }

    // PAS TESTE / Pas utile
    @PatchMapping("/update")
    public SousTheme updateSousTheme(@RequestBody SousTheme sousTheme) {
        return sousThemeService.update(sousTheme);
    }

    // OK fonctionnel
    @GetMapping("/all")
    public List<SousTheme> getAllSousThemes() {
        return sousThemeService.findAll();
    }

    // ok fonctionnel
    @GetMapping("/{id}")
    public SousTheme getSousThemeById(@PathVariable Long id) {
        return sousThemeService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public SousTheme deleteSousThemeById(@PathVariable Long id) {
        return sousThemeService.deleteById(id);
    }

    @DeleteMapping("/{id}")
    public Theme deleteTheme(@PathVariable Long id) {
        return themeService.deleteById(id);
    }
}
