package fr.equipefilrouge.filrougeSpring.dto;

import fr.equipefilrouge.filrougeSpring.entity.Bootcamp;
import lombok.Data;

@Data
public class ParticipationReduitDTO {
    private Long id;
    private Bootcamp bootcamp;
}
