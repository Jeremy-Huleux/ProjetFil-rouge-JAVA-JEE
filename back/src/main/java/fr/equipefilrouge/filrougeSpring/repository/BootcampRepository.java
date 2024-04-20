package fr.equipefilrouge.filrougeSpring.repository;

import fr.equipefilrouge.filrougeSpring.entity.Bootcamp;
import fr.equipefilrouge.filrougeSpring.entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BootcampRepository extends JpaRepository<Bootcamp, Long> {

    /**
     * Requête pour compter le nombre de bootcamps
     * @return le nombre de bootcamps trouvés
     */
    @Query("SELECT COUNT(DISTINCT b.id) FROM Bootcamp b")
    Long countBootcamps();

    /**
     * Requête pour trouver les formations liées à un bootcamp
     * @param bootcampId l'identifiant du bootcamp
     * @return la liste des formations d'un bootcamp
     */
    @Query("SELECT b.formations FROM Bootcamp b WHERE b.id = :bootcampId")
    List<Formation> getFormationsInBootcamp(Long bootcampId);

    /**
     * Requête pour trouver le nombre utilisateurs liés à un bootcamp
     * @return le nombre d'utilisateurs d'un bootcamp
     */
    @Query("SELECT b.id, COUNT(u) FROM Bootcamp b LEFT JOIN b.users u GROUP BY b.id")
    List<Object[]> countUsersByBootcamp();

    /**
     * Liste des bootcamps par statut
     * @param statut le statut recherché
     * @return une liste de bootcamps
     */
    List<Bootcamp> findByStatut(String statut);

}
