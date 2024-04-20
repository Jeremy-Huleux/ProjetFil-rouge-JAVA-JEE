package fr.equipefilrouge.filrougeSpring.services.impl;

import fr.equipefilrouge.filrougeSpring.entity.Categorie;
import fr.equipefilrouge.filrougeSpring.entity.Theme;
import fr.equipefilrouge.filrougeSpring.repository.ThemeRepository;
import fr.equipefilrouge.filrougeSpring.services.AllServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ThemeServiceImpl implements AllServices<Theme, Long> {

    private final ThemeRepository themeRepository;
    private final CategorieServiceImpl categorieService;

    public ThemeServiceImpl(ThemeRepository themeRepository, CategorieServiceImpl categorieService) {
        this.themeRepository = themeRepository;
        this.categorieService = categorieService;
    }

    @Override
    public List<Theme> findAll() {return themeRepository.findAll();}

    @Override
    public Theme findById(Long id) {
        return themeRepository.findById(id).orElse(null);
    }

    @Override
    public Theme create(Theme newObj) {
        return themeRepository.save(newObj);
    }

    public Theme createWithCategorie(Theme theme, Long idCategorie) {
        Categorie categorie = categorieService.findById(idCategorie);
        create(new Theme(theme.getNom(),theme.getDescription(), categorie));
        return themeRepository.save(theme);
    }

    @Override
    public Theme update(Theme theme) {
        if (themeRepository.existsById(theme.getId())) {
            return themeRepository.save(theme);
        }
        return null;
    }

    @Override
    public Theme deleteById(Long id) {
        Theme themeToDelete = findById(id);
        if (themeToDelete != null) {
            themeRepository.deleteById(id);
            return themeToDelete;
        }
        return null;
    }

    @Override
    public void deleteAll() {
        themeRepository.deleteAll();
    }
}
