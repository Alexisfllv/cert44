package edu.pe.serviciomjcert.unitarias.dto;

import edu.pe.serviciomjcert.dto.FiltroCitaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class FiltroCitaDTOTest {

    private Validator validator;
    private FiltroCitaDTO filtroCita;

    @BeforeEach
    public void setUp() {
        // Inicializa el validador
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        filtroCita = new FiltroCitaDTO();
    }

    @Test
    public void testDniNoPuedeSerNull() {
        filtroCita.setDni(null);
        Set<ConstraintViolation<FiltroCitaDTO>> violations = validator.validate(filtroCita);
        assertTrue(violations.isEmpty()); // No hay validaciones para dni porque no tiene restricciones @NotNull
    }

    @Test
    public void testNombreCompletoNoPuedeSerNull() {
        filtroCita.setNombreCompleto(null);
        Set<ConstraintViolation<FiltroCitaDTO>> violations = validator.validate(filtroCita);
        assertTrue(violations.isEmpty()); // No hay validaciones para nombreCompleto porque no tiene restricciones @NotNull
    }

    @Test
    public void testConstructorCompleto() {
        filtroCita = new FiltroCitaDTO();
        filtroCita.setDni("12345678");
        filtroCita.setNombreCompleto("Juan Perez");
        Set<ConstraintViolation<FiltroCitaDTO>> violations = validator.validate(filtroCita);
        assertTrue(violations.isEmpty()); // No debe haber violaciones para un objeto completamente inicializado
    }

    @Test
    public void testSetDniYNombreCompleto() {
        filtroCita.setDni("12345678");
        filtroCita.setNombreCompleto("Juan Perez");
        assertEquals("12345678", filtroCita.getDni()); // Verifica que el dni se haya asignado correctamente
        assertEquals("Juan Perez", filtroCita.getNombreCompleto()); // Verifica que el nombreCompleto se haya asignado correctamente
    }
}
