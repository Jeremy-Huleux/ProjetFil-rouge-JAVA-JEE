package fr.equipefilrouge.filrougeSpring.services.impl;

import fr.equipefilrouge.filrougeSpring.entity.Categorie;
import fr.equipefilrouge.filrougeSpring.repository.CategorieRepository;
import fr.equipefilrouge.filrougeSpring.services.AllServices;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service pour implémenter les méthodes du CRUD pour une session de Categorie
 */
@Service
@Transactional
public class CategorieServiceImpl implements AllServices<Categorie, Long> {

    private final CategorieRepository categorieRepository;

    public CategorieServiceImpl(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
    public List<Categorie> findAll() {return categorieRepository.findAll();}

    @Override
    public Categorie findById(Long id) {
        return categorieRepository.findById(id).orElse(null);
    }

    @Override
    public Categorie create(Categorie newObj) {
        return categorieRepository.save(newObj);
    }

    @Override
    public Categorie update(Categorie categorie) {
        if (categorieRepository.existsById(categorie.getId())) {
            return categorieRepository.save(categorie);
        }
        return null;
    }

    @Override
    public Categorie deleteById(Long id) {
        Categorie categorieToDelete = findById(id);
        if (categorieToDelete != null) {
            categorieRepository.deleteById(id);
            return categorieToDelete;
        }
        return categorieToDelete;
    }

    @Override
    public void deleteAll() {
        categorieRepository.deleteAll();
    }
}
