package fr.equipefilrouge.filrougeSpring.dto;

import lombok.Data;

@Data
public class UsersDTO {
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String pseudo;
}
