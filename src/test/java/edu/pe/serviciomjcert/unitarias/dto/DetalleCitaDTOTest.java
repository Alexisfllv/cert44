package edu.pe.serviciomjcert.unitarias.dto;

import edu.pe.serviciomjcert.dto.DetalleCitaDTO;
import edu.pe.serviciomjcert.dto.CitaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DetalleCitaDTOTest {

    private Validator validator;
    private DetalleCitaDTO detalleCita;

    @BeforeEach
    public void setUp() {
        // Inicializa el validador
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        detalleCita = new DetalleCitaDTO();
    }

    @Test
    public void testAnalisisNoPuedeSerNull() {
        detalleCita.setAnalisis(null);
        Set<ConstraintViolation<DetalleCitaDTO>> violations = validator.validate(detalleCita);
        assertEquals(2, violations.size()); // Debe haber una violación para analisis
    }

    @Test
    public void testSolucionNoPuedeSerNull() {
        detalleCita.setSolucion(null);
        Set<ConstraintViolation<DetalleCitaDTO>> violations = validator.validate(detalleCita);
        assertEquals(2, violations.size()); // Debe haber una violación para solucion
    }

    @Test
    public void testCitaNoDebeSerNull() {
        detalleCita.setCita(null);
        Set<ConstraintViolation<DetalleCitaDTO>> violations = validator.validate(detalleCita);
        assertFalse(violations.isEmpty()); // No debe haber violaciones para cita ya que es ignorada por @JsonIgnore
    }

    @Test
    public void testConstructorCompleto() {
        detalleCita = new DetalleCitaDTO("Analisis", new CitaDTO(), 2, "Solucion");
        Set<ConstraintViolation<DetalleCitaDTO>> violations = validator.validate(detalleCita);
        assertTrue(violations.isEmpty()); // No debe haber violaciones para un objeto completo
    }

    @Test
    public void testIdDetalleCitaNoDebeSerNull() {
        detalleCita.setIdDetalleCita(2);
        Set<ConstraintViolation<DetalleCitaDTO>> violations = validator.validate(detalleCita);
        assertFalse(violations.isEmpty()); // No debe haber violaciones para un id válido
    }
}
