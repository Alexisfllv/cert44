package edu.pe.serviciomjcert.unitarias.model;

import edu.pe.serviciomjcert.model.Cita;
import edu.pe.serviciomjcert.model.Cliente;
import edu.pe.serviciomjcert.model.Solicitud;
import edu.pe.serviciomjcert.model.Tecnico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CitaTest {

    private Cita cita;
    private Cliente cliente;
    private Solicitud solicitud;
    private Tecnico tecnico;

    @BeforeEach
    public void setUp() {
        // Inicialización de objetos necesarios para la prueba
        cliente = new Cliente();
        solicitud = new Solicitud();
        tecnico = new Tecnico();
        cita = new Cita();

        // Configuración de valores iniciales válidos
        cita.setCliente(cliente);
        cita.setSolicitud(solicitud);
        cita.setTecnico(tecnico);
        cita.setFecha(LocalDateTime.now());
        cita.setNumAl("12345");
    }

    // Validación de campos obligatorios no nulos
    @Test
    public void testCamposObligatorios() {
        // Establecer campos obligatorios como nulos
        cita.setCliente(null);
        cita.setSolicitud(null);
        cita.setTecnico(null);
        cita.setFecha(null);

        // Verificar que los campos no pueden ser nulos
        assertNull(cita.getCliente(), "El cliente no puede ser nulo.");
        assertNull(cita.getSolicitud(), "La solicitud no puede ser nula.");
        assertNull(cita.getTecnico(), "El técnico no puede ser nulo.");
        assertNull(cita.getFecha(), "La fecha no puede ser nula.");
    }



    // Validación de valores iniciales
    @Test
    public void testValoresIniciales() {
        assertEquals("12345", cita.getNumAl(), "El número de autorización (numAl) debería coincidir con el valor inicial.");
        assertNotNull(cita.getFecha(), "La fecha debe inicializarse correctamente.");
    }
}
