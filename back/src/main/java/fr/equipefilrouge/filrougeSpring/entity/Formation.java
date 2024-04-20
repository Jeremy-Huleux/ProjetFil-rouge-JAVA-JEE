package fr.equipefilrouge.filrougeSpring.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Formations pour gérer toutes les formations et leurs données
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "formations")
// Annotation utilisée pour gérer les références cycliques lors de la sérialisation JSON.
// Le générateur d'identifiant extrait la propriété "id" de l'objet pour éviter les boucles infinies.
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "idFormation")
    private Long id;

    @Column(name = "nomFormation", nullable = false)
    private String nom;

    @Column(name = "prix", nullable = false)
    private int prix;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "img")
    private String img;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sous_theme_id")
    @JsonIdentityReference(alwaysAsId = true)
    private SousTheme sousThemeId;

    @ManyToMany
    @JoinTable(
            name = "lier",
            joinColumns = @JoinColumn(name = "formation_id"),
            inverseJoinColumns = @JoinColumn(name = "bootcamp_id")
    )
    // Annotation pour indiquer que, lors de la désérialisation JSON,
    // la formation doit être représentée uniquement par son identifiant.
    @JsonIdentityReference(alwaysAsId = true)
    private List<Bootcamp> bootcamps = new ArrayList<>();

    /**
     *
     * @param nom, le nom de la formation
     * @param prix, le prix
     * @param description, sa description
     * @param img l'image à ajouter
     * @param sousThemeId, le sous-thème lié
     */
    public Formation(String nom, int prix, String description, String img, SousTheme sousThemeId) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.img = img;
        this.sousThemeId = sousThemeId;
    }

    /**
     * Méthode pour afficher une formation
     * @return la formation
     */
    @Override
    public String toString() {
        return "Formation{" +
                "nom='" + nom + '\'' +
                ", prix=" + prix +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                ", sousThemeId=" + sousThemeId +
                ", bootcamps=" + bootcamps +
                '}';
    }
}