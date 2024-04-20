package fr.equipefilrouge.filrougeSpring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "theme")
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomTheme", nullable = false)
    private String nom;

    @Column(name = "description", nullable = false)
    @Size(min = 3, max = 2000)
    private String description;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Categorie categorie;

    public Theme(String nom, String description, Categorie categorie) {
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", categorie=" + categorie +
                '}';
    }
}


