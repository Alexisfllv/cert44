package edu.pe.serviciomjcert.unitarias.dto;

import edu.pe.serviciomjcert.dto.SolicitudDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SolicitudDTOTest {

    private Validator validator;
    private SolicitudDTO solicitud;

    @BeforeEach
    public void setUp() {
        // Inicializa el validador
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        solicitud = new SolicitudDTO();
    }

    @Test
    public void testNombreSolicitudNoPuedeSerNull() {
        solicitud.setNombreSolicitud(null);
        Set<ConstraintViolation<SolicitudDTO>> violations = validator.validate(solicitud);
        assertEquals(6, violations.size()); // Debe haber una violación
    }

    @Test
    public void testApellidoSolicitudNoPuedeSerNull() {
        solicitud.setApellidoSolicitud(null);
        Set<ConstraintViolation<SolicitudDTO>> violations = validator.validate(solicitud);
        assertEquals(6, violations.size()); // Debe haber una violación
    }

    @Test
    public void testCorreoSolicitudNoPuedeSerNull() {
        solicitud.setCorreoSolicitud(null);
        Set<ConstraintViolation<SolicitudDTO>> violations = validator.validate(solicitud);
        assertEquals(6, violations.size()); // Debe haber una violación
    }

    @Test
    public void testTelefonoSolicitudNoPuedeSerNull() {
        solicitud.setTelefonoSolicitud(null);
        Set<ConstraintViolation<SolicitudDTO>> violations = validator.validate(solicitud);
        assertEquals(6, violations.size()); // Debe haber una violación
    }

    @Test
    public void testTipoServicioSolicitudNoPuedeSerNull() {
        solicitud.setTipoServicioSolicitud(null);
        Set<ConstraintViolation<SolicitudDTO>> violations = validator.validate(solicitud);
        assertEquals(6, violations.size()); // Debe haber una violación
    }

    @Test
    public void testDescripcionSolicitudNoPuedeSerNull() {
        solicitud.setDescripcionSolicitud(null);
        Set<ConstraintViolation<SolicitudDTO>> violations = validator.validate(solicitud);
        assertEquals(6, violations.size()); // Debe haber una violación
    }

    @Test
    public void testConstructorCompleto() {
        solicitud = new SolicitudDTO("Apellido", "correo@dominio.com", "Descripción", "Pendiente", 6, "Nombre", "623456789", "Servicio");
        Set<ConstraintViolation<SolicitudDTO>> violations = validator.validate(solicitud);
        assertTrue(violations.isEmpty()); // No debe haber violaciones
    }

    @Test
    public void testIdSolicitudNoPuedeSerNull() {
        solicitud.setIdSolicitud(600);
        Set<ConstraintViolation<SolicitudDTO>> violations = validator.validate(solicitud);
        assertFalse(violations.isEmpty()); // No debe haber violaciones para un id válido
    }
}
