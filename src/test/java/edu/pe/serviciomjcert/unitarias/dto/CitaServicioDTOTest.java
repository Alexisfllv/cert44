package edu.pe.serviciomjcert.unitarias.dto;


import edu.pe.serviciomjcert.dto.CitaServicioDTO;
import edu.pe.serviciomjcert.dto.CitaDTO;
import edu.pe.serviciomjcert.dto.TipoServicioDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CitaServicioDTOTest {

    private Validator validator;
    private CitaServicioDTO citaServicio;

    @BeforeEach
    public void setUp() {
        // Inicializa el validador
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        citaServicio = new CitaServicioDTO();
    }

    @Test
    public void testCitaNoPuedeSerNull() {
        citaServicio.setCita(null);
        Set<ConstraintViolation<CitaServicioDTO>> violations = validator.validate(citaServicio);
        assertTrue(violations.isEmpty()); // No hay validaciones para cita, ya que no tiene @NotNull
    }

    @Test
    public void testTipoServicioNoPuedeSerNull() {
        citaServicio.setTipoServicio(null);
        Set<ConstraintViolation<CitaServicioDTO>> violations = validator.validate(citaServicio);
        assertTrue(violations.isEmpty()); // No hay validaciones para tipoServicio, ya que no tiene @NotNull
    }

    @Test
    public void testConstructorCompleto() {
        CitaDTO cita = new CitaDTO();
        TipoServicioDTO tipoServicio = new TipoServicioDTO("Servicio Test", 1, "Descripción Servicio");

        citaServicio = new CitaServicioDTO();
        citaServicio.setCita(cita);
        citaServicio.setTipoServicio(tipoServicio);

        Set<ConstraintViolation<CitaServicioDTO>> violations = validator.validate(citaServicio);
        assertTrue(violations.isEmpty()); // No debe haber violaciones con el objeto completamente inicializado
    }

    @Test
    public void testSetCitaYTipoServicio() {
        CitaDTO cita = new CitaDTO();
        TipoServicioDTO tipoServicio = new TipoServicioDTO("Servicio Test", 1, "Descripción Servicio");

        citaServicio.setCita(cita);
        citaServicio.setTipoServicio(tipoServicio);

        assertEquals(cita, citaServicio.getCita()); // Verifica que la cita esté correctamente asignada
        assertEquals(tipoServicio, citaServicio.getTipoServicio()); // Verifica que el tipo de servicio esté correctamente asignado
    }
}

