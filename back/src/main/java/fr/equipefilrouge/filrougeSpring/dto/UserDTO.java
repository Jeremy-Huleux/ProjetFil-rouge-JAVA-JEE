package fr.equipefilrouge.filrougeSpring.dto;

import fr.equipefilrouge.filrougeSpring.enums.UserRole;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private UserRole role;
}
