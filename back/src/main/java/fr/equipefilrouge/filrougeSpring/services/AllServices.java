package fr.equipefilrouge.filrougeSpring.services;

import java.util.List;

/**
 * Interface Service général pour gérer tous les services
 * @param <TypeObj> le type d'objet géré
 * @param <PK> l'identifiant s'il y a lieu
 */
public interface AllServices<TypeObj, PK> {
    /**
     * @return tous les objets existants
     */
    List<TypeObj> findAll();

    /**
     * Méthode pour trouver un objet par son identifiant
     *
     * @param id l'identifiant recherché
     * @return l'objet recherché
     */
    TypeObj findById(PK id);

    /**
     * Méthode pour créer un nouvel objet
     *
     * @param newObj le nouvel objet
     * @return l'objet nouvellement créé
     */
    TypeObj create(TypeObj newObj);

    /**
     * @param obj l'objet à mettre à jour
     */
    TypeObj update(TypeObj obj);

    /**
     * @param id l'identifiant recherché
     * @return l'objet à supprimer
     */
    TypeObj deleteById(PK id);

    /**
     * Méthode pour supprimer tous les objets
     */
    void deleteAll();
}