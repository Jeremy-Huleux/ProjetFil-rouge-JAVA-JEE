package fr.equipefilrouge.filrougeSpring.dto;

import lombok.Data;

@Data
public class FormationDTO {
    private Long id;
    private String nom;
    private int prix;
    private String description;
    private String img;
    private Long sousThemeId;
}
