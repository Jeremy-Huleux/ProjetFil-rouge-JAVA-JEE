package fr.equipefilrouge.filrougeSpring.repository;

import fr.equipefilrouge.filrougeSpring.entity.Users;
import fr.equipefilrouge.filrougeSpring.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {

    /**
     * Requête pour vérifier l'existence de l'email en BDD
     * @param email l'email à vérifier
     * @return la vérification de l'email
     */
    @Query("SELECT u FROM Users u WHERE u.email = :email")
    Users findByEmail(@Param("email") String email);

    /**
     * Requête pour compter le nombre de user par rôle
     * @param role le role de 'utilisateur
     * @return le nombre d'utilisateurs trouvés
     */
    @Query("SELECT COUNT(DISTINCT u) FROM Users u WHERE :role IN (u.role)")
    Long countByRole(@Param("role") UserRole role);

    /**
     * Requête pour trouver une liste d'utilisateurs par le rôle
     */
    List<Users> findByRole(UserRole role);

}
