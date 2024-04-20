package fr.equipefilrouge.filrougeSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "lieu")
public class Lieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "raisonSociale")
    private String raisonSociale;

    @Column(name = "codePostal", nullable = false)
    private String codePostal;

    @Column(name = "rue", nullable = false)
    private String rue;

    @Column(name = "ville", nullable = false)
    private String ville;

    @Column(name = "pays", nullable = false)
    private String pays;

    public Lieu(String raisonSociale, String codePostal, String rue, String ville, String pays) {
        this.raisonSociale = raisonSociale;
        this.codePostal = codePostal;
        this.rue = rue;
        this.ville = ville;
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "Lieu{" +
                "id=" + id +
                ", RaisonSociale='" + raisonSociale + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", rue='" + rue + '\'' +
                ", ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                '}';
    }
}
