package edu.pe.serviciomjcert.unitarias.dto;

import edu.pe.serviciomjcert.dto.TipoServicioDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TipoServicioDTOTest {

    private Validator validator;
    private TipoServicioDTO tipoServicio;

    @BeforeEach
    public void setUp() {
        // Inicializa el validador
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        tipoServicio = new TipoServicioDTO();
    }

    @Test
    public void testNombreTipoServicioNoPuedeSerNull() {
        tipoServicio.setNombreTipoServicio(null);
        Set<ConstraintViolation<TipoServicioDTO>> violations = validator.validate(tipoServicio);
        assertEquals(2, violations.size()); // Debe haber una violación para nombreTipoServicio
    }

    @Test
    public void testDescripcionTipoServicioNoPuedeSerNull() {
        tipoServicio.setDescripcionTipoServicio(null);
        Set<ConstraintViolation<TipoServicioDTO>> violations = validator.validate(tipoServicio);
        assertEquals(2, violations.size()); // Debe haber una violación para descripcionTipoServicio
    }

    @Test
    public void testConstructorCompleto() {
        tipoServicio = new TipoServicioDTO("Descripción del servicio", 1, "Servicio Básico");
        Set<ConstraintViolation<TipoServicioDTO>> violations = validator.validate(tipoServicio);
        assertTrue(violations.isEmpty()); // No debe haber violaciones para un objeto completo
    }

    @Test
    public void testIdTipoServicioNoDebeSerNull() {
        tipoServicio.setIdTipoServicio(1);
        Set<ConstraintViolation<TipoServicioDTO>> violations = validator.validate(tipoServicio);
        assertFalse(violations.isEmpty()); // No debe haber violaciones cuando el id es válido
    }
}
