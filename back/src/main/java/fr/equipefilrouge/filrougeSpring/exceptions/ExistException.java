package fr.equipefilrouge.filrougeSpring.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Gestion de l'exception IM USED
 */
@Getter
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ExistException extends RuntimeException{
    /*
       Variables à gérer lors de l'exception
    */
    private final String nomRessource;
    private final String nomChamp;
    private final Object valeurChamp;

    /**
     * Constructeur de l'exception
     * @param nomRessource, la ressource
     * @param nomChamp, le champ
     * @param valeurChamp, la valeur du champ
     */
    public ExistException(String nomRessource, String nomChamp, Object valeurChamp) {
        // "%s" sera remplacé par les valeurs réelles lors de l'exécution
        super(String.format("%s already exists with %s : '%s", nomRessource, nomChamp, valeurChamp));
        this.nomRessource = nomRessource;
        this.nomChamp = nomChamp;
        this.valeurChamp = valeurChamp;
    }

}
