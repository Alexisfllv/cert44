package edu.pe.serviciomjcert.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ModeloNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ModeloNotFoundException(String mensaje) {
        super(mensaje);
    }
}
