package fr.equipefilrouge.filrougeSpring.dto;

import fr.equipefilrouge.filrougeSpring.enums.UserRole;
import lombok.Data;

@Data
public class UserLoginDTO {
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String pseudo;
    private UserRole role;
}
/*
@param nom, som nom
     * @param prenom, son prénom
     * @param telephone, son téléphone
     * @param email, son email,
     * @param pseudo, son pseudo
     * @param mdp, son mot de passe
     * @param role, son rôle
     * @param noteMoyenne, sa note moyenne si c'est un formateur
 */
