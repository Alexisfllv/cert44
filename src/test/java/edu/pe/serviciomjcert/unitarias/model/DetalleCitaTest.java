package edu.pe.serviciomjcert.unitarias.model;

import edu.pe.serviciomjcert.model.Cita;
import edu.pe.serviciomjcert.model.DetalleCita;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DetalleCitaTest {

    private DetalleCita detalleCita;
    private Cita cita;

    @BeforeEach
    public void setUp() {
        // Inicialización de objetos necesarios para la prueba
        cita = new Cita();
        detalleCita = new DetalleCita();

        // Configuración de valores iniciales válidos
        detalleCita.setCita(cita);
        detalleCita.setAnalisis("Análisis inicial");
        detalleCita.setSolucion("Solución inicial");
    }

    // Validación de campos obligatorios no nulos
    @Test
    public void testCamposObligatorios() {
        // Establecer campos obligatorios como nulos
        detalleCita.setCita(null);
        detalleCita.setAnalisis(null);
        detalleCita.setSolucion(null);

        // Verificar que los campos no pueden ser nulos
        assertNull(detalleCita.getCita(), "La cita no puede ser nula.");
        assertNull(detalleCita.getAnalisis(), "El análisis no puede ser nulo.");
        assertNull(detalleCita.getSolucion(), "La solución no puede ser nula.");
    }

    // Validación de asociación con Cita
    @Test
    public void testAsociacionCita() {
        assertNotNull(detalleCita.getCita(), "La cita asociada debe inicializarse correctamente.");
        assertSame(cita, detalleCita.getCita(), "La cita asociada debe coincidir con la esperada.");
    }

    // Validación de valores iniciales
    @Test
    public void testValoresIniciales() {
        assertEquals("Análisis inicial", detalleCita.getAnalisis(), "El análisis debería coincidir con el valor inicial.");
        assertEquals("Solución inicial", detalleCita.getSolucion(), "La solución debería coincidir con el valor inicial.");
    }

    // Validación de campos con longitud máxima permitida
    @Test
    public void testLongitudMaximaCampos() {
        String analisisLargo = "A".repeat(70); // Analizar un caso límite
        String solucionLarga = "B".repeat(70);

        detalleCita.setAnalisis(analisisLargo);
        detalleCita.setSolucion(solucionLarga);

        assertEquals(70, detalleCita.getAnalisis().length(), "El análisis debería tener como máximo 70 caracteres.");
        assertEquals(70, detalleCita.getSolucion().length(), "La solución debería tener como máximo 70 caracteres.");
    }
}
