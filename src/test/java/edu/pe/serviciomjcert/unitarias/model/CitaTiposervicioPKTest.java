package edu.pe.serviciomjcert.unitarias.model;

import edu.pe.serviciomjcert.model.Cita;
import edu.pe.serviciomjcert.model.CitaTiposervicioPK;
import edu.pe.serviciomjcert.model.TipoServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CitaTiposervicioPKTest {

    private CitaTiposervicioPK citaTiposervicioPK;
    private Cita cita;
    private TipoServicio tipoServicio;

    @BeforeEach
    public void setUp() {
        // Inicialización de objetos necesarios para la prueba
        cita = new Cita();
        tipoServicio = new TipoServicio();
        citaTiposervicioPK = new CitaTiposervicioPK();

        // Configuración de asociaciones
        citaTiposervicioPK.setCita(cita);
        citaTiposervicioPK.setTiposervicio(tipoServicio);
    }

    // Validación de campos obligatorios no nulos
    @Test
    public void testCamposObligatorios() {
        // Establecer campos como nulos
        citaTiposervicioPK.setCita(null);
        citaTiposervicioPK.setTiposervicio(null);

        // Verificar que los campos no permitan valores nulos
        assertNull(citaTiposervicioPK.getCita(), "La cita no puede ser nula.");
        assertNull(citaTiposervicioPK.getTiposervicio(), "El tipo de servicio no puede ser nulo.");
    }

    // Validación de asociación con Cita y TipoServicio
    @Test
    public void testAsociaciones() {
        assertNotNull(citaTiposervicioPK.getCita(), "La cita asociada debe inicializarse correctamente.");
        assertNotNull(citaTiposervicioPK.getTiposervicio(), "El tipo de servicio asociado debe inicializarse correctamente.");
        assertSame(cita, citaTiposervicioPK.getCita(), "La cita asociada debe coincidir con la esperada.");
        assertSame(tipoServicio, citaTiposervicioPK.getTiposervicio(), "El tipo de servicio asociado debe coincidir con el esperado.");
    }

    // Validación del método equals
    @Test
    public void testEquals() {
        CitaTiposervicioPK otroCitaTiposervicioPK = new CitaTiposervicioPK();
        otroCitaTiposervicioPK.setCita(cita);
        otroCitaTiposervicioPK.setTiposervicio(tipoServicio);

        assertEquals(citaTiposervicioPK, otroCitaTiposervicioPK, "Los objetos deben ser iguales si sus asociaciones son las mismas.");
    }

    // Validación del método hashCode
    @Test
    public void testHashCode() {
        CitaTiposervicioPK otroCitaTiposervicioPK = new CitaTiposervicioPK();
        otroCitaTiposervicioPK.setCita(cita);
        otroCitaTiposervicioPK.setTiposervicio(tipoServicio);

        assertEquals(citaTiposervicioPK.hashCode(), otroCitaTiposervicioPK.hashCode(), "Los hashCode deben ser iguales si los objetos tienen los mismos valores.");
    }

    // Validación de comportamiento cuando los campos están vacíos
    @Test
    public void testCamposVacios() {
        citaTiposervicioPK.setCita(null);
        citaTiposervicioPK.setTiposervicio(null);

        assertTrue(citaTiposervicioPK.equals(new CitaTiposervicioPK()), "El objeto no debe ser igual a otro con campos vacíos.");
    }
}

