package fr.equipefilrouge.filrougeSpring.controller;

import fr.equipefilrouge.filrougeSpring.dto.*;
import fr.equipefilrouge.filrougeSpring.entity.Users;
import fr.equipefilrouge.filrougeSpring.enums.UserRole;
import fr.equipefilrouge.filrougeSpring.services.impl.UsersServiceImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    /**
     * Appelle le service users
     */
    public final UsersServiceImpl usersService;
    /**
     * Constructeur
     * @param usersService le service users
     */
    public UserController(UsersServiceImpl usersService) {
        this.usersService = usersService;
    }

    /**
     * Créer un stagiaire
     * @param user l'utilisateur à créer
     * @return l'utilisateur nouvellement créé
     */
    @PostMapping("/create")
    public Users createUser(@Valid @RequestBody Users user) {
        return usersService.create(user);
    }

    /**
     * Création d'une liste de formations
     * @param users la liste des utilisateurs à créer
     * @return la liste des utilisateurs nouvellement créée
     */
    @PostMapping("/createUsers")
    public List<Users> listUsers(@RequestBody List<Users> users) {
        return usersService.createListe(users);
    }

    /**
     * Méthode de connection au site
     * @param identifiants les identifiants de l'utilisateur
     * @return l'utilisateur connecté
     */
    @PostMapping("/login")
    public UserLoginDTO login(@RequestBody Map<String, String> identifiants) {
        // Extraire l'e-mail et le mot de passe à partir du corps de la requête
        String email = identifiants.get("email");
        String mdp = identifiants.get("mdp");

        // Vérifier les informations d'identification
        if (usersService.areIdsValid(email, mdp)) {
            System.out.println("connexion réussie !");
            // Si les informations d'identification sont valides, renvoyer une réponse 200 OK avec un message de succès
            return usersService.userLoginByMail(email);
        } else {
            System.out.println("connexion impossible !");
            // Si les informations d'identification ne sont pas valides, renvoyer une réponse 401 Unauthorized avec un message d'erreur
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants incorrects !");
            return null;
        }
    }

    /**
     * Mettre à jour un stagiaire
     * @param stagiaire le stagiaire à mettre à jour
     * @return le stagiaire mis à jour
     */
    @PatchMapping("/update")
    public Users updateUser(@RequestBody Users stagiaire) {
        return usersService.update(stagiaire);
    }

    /**
     * Afficher tous les stagiaires
     * @return la liste de tous les stagiaires
     */
    @GetMapping("/all")
    public List<UsersDTO> getAllUsers() {
        return usersService.findAllUsers();
    }

    /**
     * Méthode pour retrouver les candidats
     * @return les candidats recherchés filtrés avec le DTO
     */
    @GetMapping("/all/candidats")
    public List<UserCandidatDTO> findCandidats() {
        return usersService.findAllCandidats();
    }

    /**
     * Méthode pour retrouver les formateurs
     * @return les formateurs recherchés filtrés avec le DTO
     */
    @GetMapping("/all/formateurs")
    public List<UserFormateurDTO> findFormateurs() {
        return usersService.findAllFormateurs();
    }

    /**
     * Méthode pour retrouver les stagiaires
     * @return les stagiaires recherchés filtré avec le DTO
     */
    @GetMapping("/all/stagiaires")
    public List<UserStagiaireDTO> findStagiaires() {
        return usersService.findAllStagiaires();
    }

    /**
     * Afficher un stagiaire selon son id
     * @param id l'identifiant du stagiaire
     * @return le stagiaire recherché
     */
    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return usersService.findById(id);
    }

    /**
     * Renvoyer le nombre de formateurs en BDD
     * @return le nombre de formateurs
     */
    @GetMapping("/nbFormateurs")
    public Long nbFormateurs() {
        return usersService.countUsersByRole(UserRole.FORMATEUR);
    }

    /**
     * Renvoyer le nombre de stagiaires en BDD
     * @return le nombre de stagiaire
     */
    @GetMapping("/nbStagiaires")
    public Long nbStagiaires() {
        return usersService.countUsersByRole(UserRole.STAGIAIRE);
    }

    /**
     * Renvoyer le nombre de candidats en BDD
     * @return le nombre de candidats
     */
    @GetMapping("/nbCandidats")
    public Long nbCandidats() {
        return usersService.countUsersByRole(UserRole.CANDIDAT);
    }

    /**
     * Supprimer un stagiaire selon son id
     * @param id l'identifiant du stagiaire
     * @return le stagiaire supprimé
     */
    @DeleteMapping("/delete/{id}")
    public Users deleteUserById(@PathVariable Long id) {
        return usersService.deleteById(id);
    }

    /**
     * Supprimer tous les stagiaires
     */
    @DeleteMapping("/delete/all")
    public void deleteAllUsers() {
        usersService.deleteAll();
    }
}
