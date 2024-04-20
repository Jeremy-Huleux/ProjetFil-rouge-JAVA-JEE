package fr.equipefilrouge.filrougeSpring.dto;

import fr.equipefilrouge.filrougeSpring.entity.Lieu;
import lombok.Data;

import java.util.Date;
@Data
public class BootcampDTO {
    private Long id;
    private Date dateDebut;
    private Date dateFin;
    private String statut;
    private Long lieuId;
}
