package fr.equipefilrouge.filrougeSpring.dto;

import lombok.Data;

@Data
public class UserFormateurDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private double noteMoyenne;
}
