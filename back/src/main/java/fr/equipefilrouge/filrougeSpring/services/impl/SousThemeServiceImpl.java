package fr.equipefilrouge.filrougeSpring.services.impl;

import fr.equipefilrouge.filrougeSpring.dto.SousThemeDTO;
import fr.equipefilrouge.filrougeSpring.dto.ThemeDTO;
import fr.equipefilrouge.filrougeSpring.entity.*;
import fr.equipefilrouge.filrougeSpring.exceptions.CustomException;
import fr.equipefilrouge.filrougeSpring.repository.SousThemeRepository;
import fr.equipefilrouge.filrougeSpring.services.AllServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SousThemeServiceImpl implements AllServices<SousTheme, Long> {

    private final SousThemeRepository sousThemeRepository;

    private ThemeServiceImpl themeService;

    public SousThemeServiceImpl(SousThemeRepository sousThemeRepository, ThemeServiceImpl themeService) {
        this.sousThemeRepository = sousThemeRepository;
        this.themeService = themeService;
    }

    @Override
    public List<SousTheme> findAll() {
        return sousThemeRepository.findAll();
    }

    @Override
    public SousTheme findById(Long id) {
        return sousThemeRepository.findById(id)
                .orElseThrow(() -> new CustomException("Sous-thème", "id", id));
    }

    @Override
    public SousTheme create(SousTheme newObj) {
        return sousThemeRepository.save(newObj);
    }

    public SousTheme createSousTheme(SousTheme sousTheme, Long themeId) {
        Theme theme = themeService.findById(themeId);
        create(new SousTheme(sousTheme.getNomSousTheme(), theme));
        return sousTheme;
    }

    public List<SousTheme> createList(List<SousTheme> sousThemes) {
        return sousThemeRepository.saveAll(sousThemes);
    }

    @Override
    public SousTheme update(SousTheme sousTheme) {
        Long id = sousTheme.getId();
        Optional<SousTheme> existingSousTheme = sousThemeRepository.findById(id);
        if (existingSousTheme.isPresent()) {
            return sousThemeRepository.save(sousTheme);
        } else {
            throw new CustomException("Sous-thème", "id", id);
        }
    }

    @Override
    public SousTheme deleteById(Long id) {
        SousTheme sousTheme = findById(id);
        if (sousTheme != null) {
            sousThemeRepository.deleteById(id);
            return sousTheme;
        } else {
            throw new CustomException("Sous-thème", "id", id);
        }
    }

    @Override
    public void deleteAll() {
        sousThemeRepository.deleteAll();
    }
}
