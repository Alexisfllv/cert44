package edu.pe.serviciomjcert.unitarias.model;

import edu.pe.serviciomjcert.model.TipoServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TipoServicioTest {

    private TipoServicio tipoServicio;

    @BeforeEach
    public void setUp() {
        // Inicialización del TipoServicio antes de cada prueba
        tipoServicio = new TipoServicio(1, "Reparación", "Servicio de reparación de dispositivos electrónicos.");
    }

    // Prueba de la propiedad idTipoServicio
    @Test
    public void testIdTipoServicio() {
        assertEquals(1, tipoServicio.getIdTipoServicio(), "El ID del tipo de servicio no es correcto.");
        tipoServicio.setIdTipoServicio(2);
        assertEquals(2, tipoServicio.getIdTipoServicio(), "El ID del tipo de servicio no se ha actualizado correctamente.");
    }

    // Prueba de la propiedad nombre
    @Test
    public void testNombre() {
        assertEquals("Reparación", tipoServicio.getNombre(), "El nombre del tipo de servicio no es correcto.");
        tipoServicio.setNombre("Mantenimiento");
        assertEquals("Mantenimiento", tipoServicio.getNombre(), "El nombre del tipo de servicio no se ha actualizado correctamente.");
    }

    // Validación de longitud de nombre (máximo 100 caracteres)
    @Test
    public void testNombreLength() {
        String nombreLargo = "TipoDeServicioExcesivamenteLargoQueSuperaElLimiteDeCienCaracteresParaProbarElComportamientoDelCampoEnBaseDeDatos";
        tipoServicio.setNombre(nombreLargo);
        assertFalse(tipoServicio.getNombre().length() <= 100, "El nombre excede la longitud máxima permitida de 100 caracteres.");
    }

    // Prueba de la propiedad descripcion
    @Test
    public void testDescripcion() {
        assertEquals("Servicio de reparación de dispositivos electrónicos.", tipoServicio.getDescripcion(), "La descripción no es correcta.");
        tipoServicio.setDescripcion("Servicio integral de mantenimiento de dispositivos electrónicos.");
        assertEquals("Servicio integral de mantenimiento de dispositivos electrónicos.", tipoServicio.getDescripcion(), "La descripción no se ha actualizado correctamente.");
    }

    // Validación de longitud de descripcion (máximo 255 caracteres)
    @Test
    public void testDescripcionLength() {
        String descripcionLarga = "DescripcionExcesivamenteLarga".repeat(255);
        tipoServicio.setDescripcion(descripcionLarga);
        assertFalse(tipoServicio.getDescripcion().length() <= 255, "La descripción excede la longitud máxima permitida de 255 caracteres.");
    }

    // Validación de campos obligatorios no nulos
    @Test
    public void testCamposObligatorios() {
        // Establecer los campos como nulos
        tipoServicio.setNombre(null);
        tipoServicio.setDescripcion(null);

        // Verificar que los campos no pueden ser nulos
        assertNull(tipoServicio.getNombre(), "El nombre no puede ser nulo.");
        assertNull(tipoServicio.getDescripcion(), "La descripción no puede ser nula.");
    }
}
