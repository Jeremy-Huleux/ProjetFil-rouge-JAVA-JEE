package fr.equipefilrouge.filrougeSpring.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategorieDTO {

    private Long id;
    @Size(min = 2, max = 20)
    private String nom;
    @Size(min = 10, max = 1000)
    private String description;

}
