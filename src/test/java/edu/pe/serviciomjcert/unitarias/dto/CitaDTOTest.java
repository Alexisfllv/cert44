package edu.pe.serviciomjcert.unitarias.dto;

import edu.pe.serviciomjcert.dto.CitaDTO;
import edu.pe.serviciomjcert.dto.ClienteDTO;
import edu.pe.serviciomjcert.dto.DetalleCitaDTO;
import edu.pe.serviciomjcert.dto.SolicitudDTO;
import edu.pe.serviciomjcert.dto.TecnicoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CitaDTOTest {

    private Validator validator;
    private CitaDTO cita;

    @BeforeEach
    public void setUp() {
        // Inicializa el validador
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        cita = new CitaDTO();
    }

    @Test
    public void testClienteNoPuedeSerNull() {
        cita.setCliente(null);
        Set<ConstraintViolation<CitaDTO>> violations = validator.validate(cita);
        assertEquals(6, violations.size()); // Debe haber una violación para cliente
    }

    @Test
    public void testSolicitudNoPuedeSerNull() {
        cita.setSolicitud(null);
        Set<ConstraintViolation<CitaDTO>> violations = validator.validate(cita);
        assertEquals(6, violations.size()); // Debe haber una violación para solicitud
    }

    @Test
    public void testTecnicoNoPuedeSerNull() {
        cita.setTecnico(null);
        Set<ConstraintViolation<CitaDTO>> violations = validator.validate(cita);
        assertEquals(6, violations.size()); // Debe haber una violación para tecnico
    }

    @Test
    public void testNumAlNoPuedeSerNull() {
        cita.setNumAl(null);
        Set<ConstraintViolation<CitaDTO>> violations = validator.validate(cita);
        assertEquals(6, violations.size()); // Debe haber una violación para numAl
    }

    @Test
    public void testFechaNoPuedeSerNull() {
        cita.setFecha(null);
        Set<ConstraintViolation<CitaDTO>> violations = validator.validate(cita);
        assertEquals(6, violations.size()); // Debe haber una violación para fecha
    }

    @Test
    public void testDetalleCitaNoPuedeSerNull() {
        cita.setDetalleCita(null);
        Set<ConstraintViolation<CitaDTO>> violations = validator.validate(cita);
        assertEquals(6, violations.size()); // Debe haber una violación para detalleCita
    }

    @Test
    public void testConstructorCompleto() {
        cita = new CitaDTO(
                new ClienteDTO(),
                Collections.singletonList(new DetalleCitaDTO()),
                LocalDateTime.now(),
                6,
                "62345",
                new SolicitudDTO(),
                new TecnicoDTO()
        );
        Set<ConstraintViolation<CitaDTO>> violations = validator.validate(cita);
        assertTrue(violations.isEmpty()); // No debe haber violaciones para un objeto completo
    }

    @Test
    public void testIdCitaNoDebeSerNull() {
        cita.setIdCita(6);
        Set<ConstraintViolation<CitaDTO>> violations = validator.validate(cita);
        assertFalse(violations.isEmpty()); // No debe haber violaciones para un id válido
    }
}
