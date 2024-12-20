package edu.pe.serviciomjcert.unitarias.dto;

import edu.pe.serviciomjcert.dto.TecnicoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TecnicoDTOTest {

    private Validator validator;
    private TecnicoDTO tecnico;

    @BeforeEach
    public void setUp() {
        // Inicializa el validador
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        tecnico = new TecnicoDTO();
    }

    @Test
    public void testNombreTecnicoNoPuedeSerNull() {
        tecnico.setNombreTecnico(null);
        Set<ConstraintViolation<TecnicoDTO>> violations = validator.validate(tecnico);
        assertEquals(6, violations.size()); // Debe haber una violación
    }

    @Test
    public void testApellidoTecnicoNoPuedeSerNull() {
        tecnico.setApellidoTecnico(null);
        Set<ConstraintViolation<TecnicoDTO>> violations = validator.validate(tecnico);
        assertEquals(6, violations.size()); // Debe haber una violación
    }

    @Test
    public void testCorreoTecnicoNoPuedeSerNull() {
        tecnico.setCorreoTecnico(null);
        Set<ConstraintViolation<TecnicoDTO>> violations = validator.validate(tecnico);
        assertEquals(6, violations.size()); // Debe haber una violación
    }

    @Test
    public void testFotoTecnicoNoPuedeSerNull() {
        tecnico.setFotoTecnico(null);
        Set<ConstraintViolation<TecnicoDTO>> violations = validator.validate(tecnico);
        assertEquals(6, violations.size()); // Debe haber una violación
    }

    @Test
    public void testDniTecnicoNoPuedeSerNull() {
        tecnico.setDniTecnico(null);
        Set<ConstraintViolation<TecnicoDTO>> violations = validator.validate(tecnico);
        assertEquals(6, violations.size()); // Debe haber una violación
    }

    @Test
    public void testDireccionTecnicoNoPuedeSerNull() {
        tecnico.setDireccionTecnico(null);
        Set<ConstraintViolation<TecnicoDTO>> violations = validator.validate(tecnico);
        assertEquals(6, violations.size()); // Debe haber una violación
    }

    @Test
    public void testConstructorCompleto() {
        tecnico = new TecnicoDTO("Apellido", "correo@dominio.com", "Dirección", "62345678", "foto.jpg", 6, "Nombre");
        Set<ConstraintViolation<TecnicoDTO>> violations = validator.validate(tecnico);
        assertTrue(violations.isEmpty()); // No debe haber violaciones
    }

    @Test
    public void testIdTecnicoNoPuedeSerNull() {
        tecnico.setIdTecnico(600);
        Set<ConstraintViolation<TecnicoDTO>> violations = validator.validate(tecnico);
        assertFalse(violations.isEmpty()); // No debe haber violaciones para un id válido
    }
}
