package fr.equipefilrouge.filrougeSpring.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import fr.equipefilrouge.filrougeSpring.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Classe Users regroupant les données communes aux utilisateurs, formateurs ou stagiaires
 */
@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "user")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "telephone", nullable = false)
    private String telephone;

    @Email @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "pseudo", nullable = false)
    private String pseudo;

    @Size(min = 8, message = "Le mot de passe doit avoir au minimum 8 caractères")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$",
            message = "Le mot de passe doit contenir 1 majuscule, 1 minuscule, 1 chiffre et 1 caractère spécial")
    @Column(name = "mdp", nullable = false)
    private String mdp;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "noteMoyenne")
    private Double noteMoyenne;

    @ManyToMany
    @JoinTable(name = "participer-bootcamp",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "bootcamp_id"))
    @JsonIdentityReference(alwaysAsId = true)
    private List<Bootcamp> bootcamps = new ArrayList<>();

    /**
     * Constructeur d'un utilisateur
     * @param nom, som nom
     * @param prenom, son prénom
     * @param telephone, son téléphone
     * @param email, son email,
     * @param pseudo, son pseudo
     * @param mdp, son mot de passe
     * @param role, son rôle
     * @param noteMoyenne, sa note moyenne si c'est un formateur
     */
    public Users(String nom, String prenom, String telephone, String email, String pseudo, String mdp,
                 UserRole role, Double noteMoyenne) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.role = role;
        this.noteMoyenne = noteMoyenne;
    }

    /**
     * Méthode pour afficher un utilisateur
     * @return l'utilisateur
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", Users.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("nom='" + nom + "'")
                .add("prenom='" + prenom + "'")
                .add("telephone='" + telephone + "'")
                .add("email='" + email + "'")
                .add("pseudo='" + pseudo + "'")
                .add("mdp='" + mdp + "'")
                .add("role=" + role)
                .toString();
    }
}