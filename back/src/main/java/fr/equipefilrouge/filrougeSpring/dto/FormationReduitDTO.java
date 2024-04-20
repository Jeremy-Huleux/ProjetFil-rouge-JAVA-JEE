package fr.equipefilrouge.filrougeSpring.dto;

import lombok.Data;

import java.util.List;

@Data
public class FormationReduitDTO {
    private Long id;
    private String nom;
    private int prix;
    private String description;
    private String img;
}
