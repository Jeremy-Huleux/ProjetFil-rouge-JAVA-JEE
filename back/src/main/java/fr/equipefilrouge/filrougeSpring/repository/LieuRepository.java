package fr.equipefilrouge.filrougeSpring.repository;

import fr.equipefilrouge.filrougeSpring.entity.Lieu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Le repository qui répertorie toutes les méthodes du CRUD pour un centre de formation
 */
public interface LieuRepository extends JpaRepository<Lieu, Long> {
}
