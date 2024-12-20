package edu.pe.serviciomjcert.unitarias.dto;

import edu.pe.serviciomjcert.dto.CitaListaServicioDTO;
import edu.pe.serviciomjcert.dto.CitaDTO;
import edu.pe.serviciomjcert.dto.TipoServicioDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CitaListaServicioDTOTest {

    private Validator validator;
    private CitaListaServicioDTO citaListaServicio;

    @BeforeEach
    public void setUp() {
        // Inicializa el validador
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        citaListaServicio = new CitaListaServicioDTO();
    }

    @Test
    public void testCitaNoPuedeSerNull() {
        citaListaServicio.setCita(null);
        List<TipoServicioDTO> tipoServicios = Arrays.asList(new TipoServicioDTO("Servicio 1", 1, "Descripción"));
        citaListaServicio.setLstTipoServicio(tipoServicios);
        Set<ConstraintViolation<CitaListaServicioDTO>> violations = validator.validate(citaListaServicio);
        assertFalse(violations.isEmpty(), "La cita no puede ser nula");
    }

    @Test
    public void testLstTipoServicioNoPuedeSerNull() {
        citaListaServicio.setCita(new CitaDTO());
        citaListaServicio.setLstTipoServicio(null);
        Set<ConstraintViolation<CitaListaServicioDTO>> violations = validator.validate(citaListaServicio);
        assertFalse(violations.isEmpty(), "La lista de servicios no puede ser nula");
    }


    @Test
    public void testConstructorCompleto() {
        CitaDTO cita = new CitaDTO();
        TipoServicioDTO tipoServicio1 = new TipoServicioDTO("Servicio 1", 1, "Descripción 1");
        TipoServicioDTO tipoServicio2 = new TipoServicioDTO("Servicio 2", 2, "Descripción 2");
        List<TipoServicioDTO> tipoServicios = Arrays.asList(tipoServicio1, tipoServicio2);

        citaListaServicio.setCita(cita);
        citaListaServicio.setLstTipoServicio(tipoServicios);

        Set<ConstraintViolation<CitaListaServicioDTO>> violations = validator.validate(citaListaServicio);
        assertTrue(violations.isEmpty(), "No deben existir violaciones de validación");
    }

    @Test
    public void testSetCitaYLstTipoServicio() {
        CitaDTO cita = new CitaDTO();
        List<TipoServicioDTO> tipoServicios = Arrays.asList(new TipoServicioDTO("Servicio 1", 1, "Descripción"));

        citaListaServicio.setCita(cita);
        citaListaServicio.setLstTipoServicio(tipoServicios);

        assertEquals(cita, citaListaServicio.getCita()); // Verifica que la cita esté correctamente asignada
        assertEquals(tipoServicios, citaListaServicio.getLstTipoServicio()); // Verifica que la lista de servicios esté correctamente asignada
    }
}

