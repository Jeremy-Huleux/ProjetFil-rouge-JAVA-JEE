package fr.equipefilrouge.filrougeSpring.dto;
import fr.equipefilrouge.filrougeSpring.enums.UserRole;
import lombok.Data;

import java.util.List;
@Data
public class StagiaireLoginDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String pseudo;
    private UserRole role;

}
