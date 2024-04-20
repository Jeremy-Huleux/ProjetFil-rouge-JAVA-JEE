package fr.equipefilrouge.filrougeSpring.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ThemeDTO {
    private String nom;
    private String description;
    private Long categoryId;
}
