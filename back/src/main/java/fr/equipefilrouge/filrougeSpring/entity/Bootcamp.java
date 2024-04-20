package fr.equipefilrouge.filrougeSpring.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe Bootcamp pour gérer un bootcamp et toutes ses données
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bootcamp")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Bootcamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBootcamp")
    private Long id;

    @Column(name = "date_debut", nullable = false)
    //@Temporal : Pour spécifier si un champ de type "Date" doit être mappé en tant que date
    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Column(name = "date_fin", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateFin;

    @Column(name = "statut", nullable = false)
    private String statut;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "lieu_id")
    private Lieu lieu;

    @ManyToMany(mappedBy = "bootcamps")
    private List<Users> users = new ArrayList<>();

    @ManyToMany(mappedBy = "bootcamps", cascade = CascadeType.MERGE)
    private List<Formation> formations = new ArrayList<>();

    /**
     * Constructeur pour une session de formation
     * @param dateDebut, date de début du bootcamp
     * @param dateFin, date de fin du bootcamp
     * @param statut, le statut
     */
    public Bootcamp(Date dateDebut, Date dateFin, String statut, Lieu lieu) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
        this.lieu = lieu;
    }

    /**
     * Méthode pour afficher un bootcamp
     * @return le bootcamp
     */
    @Override
    public String toString() {
        return "Bootcamp{" +
                "id=" + id +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", statut='" + statut + '\'' +
                '}';
    }
}