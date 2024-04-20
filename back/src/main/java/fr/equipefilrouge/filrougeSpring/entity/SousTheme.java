package fr.equipefilrouge.filrougeSpring.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Classe SousThemes pour gérer les sous-thèmes des différentes formations
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sous_themes")
public class SousTheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSousTheme")
    private Long id;

    @Column(name = "nomSousTheme")
    private String nomSousTheme;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name ="theme_id")
    private Theme theme;

    /**
     * Constructeur du sous-thème
     * @param sousTheme le sous-thème
     * @param theme le thème lié
     */
    public SousTheme(String sousTheme, Theme theme) {
        this.nomSousTheme = sousTheme;
        this.theme = theme;
    }

    /**
     * Constructeur utilisé par Jackson pour désérialiser un SousTheme à partir de JSON.
     * Le paramètre 'id' est de type Number pour gérer la flexibilité des types numériques dans le JSON.
     * Il est converti en long pour assurer la cohérence du stockage.
     */
    @JsonCreator
    public SousTheme(@JsonProperty("id") Number id) {
        this.id = id.longValue();
    }

    /**
     * Méthode pour afficher un sous-thème
     * @return le sous-thème
     */
    @Override
    public String toString() {
        return "SousTheme{" +
                "id=" + id +
                ", sousTheme=" + nomSousTheme +
                ", theme=" + theme +
                '}';
    }
}
