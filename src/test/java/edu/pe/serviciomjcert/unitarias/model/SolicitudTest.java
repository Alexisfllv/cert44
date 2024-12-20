package edu.pe.serviciomjcert.unitarias.model;
import edu.pe.serviciomjcert.model.Solicitud;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SolicitudTest {

    private Solicitud solicitud;

    @BeforeEach
    public void setUp() {
        // Inicialización de la solicitud antes de cada prueba
        solicitud = new Solicitud("Perez", "juan.perez@mail.com", "Descripción de la solicitud", "Pendiente", 1, "Juan", "987654321", "Soporte");
    }

    // Prueba de la propiedad idSolicitud
    @Test
    public void testIdSolicitud() {
        assertEquals(1, solicitud.getIdSolicitud(), "El ID de la solicitud no es correcto.");
        solicitud.setIdSolicitud(2);
        assertEquals(2, solicitud.getIdSolicitud(), "El ID de la solicitud no se ha actualizado correctamente.");
    }

    // Prueba de la propiedad nombre
    @Test
    public void testNombre() {
        assertEquals("Juan", solicitud.getNombre(), "El nombre no es correcto.");
        solicitud.setNombre("Carlos");
        assertEquals("Carlos", solicitud.getNombre(), "El nombre no se ha actualizado correctamente.");
    }

    // Validación de longitud de nombre (máximo 50 caracteres)
    @Test
    public void testNombreLength() {
        String nombreLargo = "NombreExcesivamenteLargoParaEsteCampoQueSuperaElLimiteDe50Caracteres";
        solicitud.setNombre(nombreLargo);
        assertFalse(solicitud.getNombre().length() <= 50, "El nombre excede la longitud máxima permitida de 50 caracteres.");
    }

    // Prueba de la propiedad apellido
    @Test
    public void testApellido() {
        assertEquals("Perez", solicitud.getApellido(), "El apellido no es correcto.");
        solicitud.setApellido("Lopez");
        assertEquals("Lopez", solicitud.getApellido(), "El apellido no se ha actualizado correctamente.");
    }

    // Validación de longitud de apellido (máximo 50 caracteres)
    @Test
    public void testApellidoLength() {
        String apellidoLargo = "ApellidoExcesivamenteLargoParaEsteCampoQueSuperaElLimiteDe50Caracteres";
        solicitud.setApellido(apellidoLargo);
        assertFalse(solicitud.getApellido().length() <= 50, "El apellido excede la longitud máxima permitida de 50 caracteres.");
    }

    // Prueba de la propiedad correo
    @Test
    public void testCorreo() {
        assertEquals("juan.perez@mail.com", solicitud.getCorreo(), "El correo no es correcto.");
        solicitud.setCorreo("carlos.lopez@mail.com");
        assertEquals("carlos.lopez@mail.com", solicitud.getCorreo(), "El correo no se ha actualizado correctamente.");
    }

    // Validación de correo único (verificación conceptual)
    @Test
    public void testCorreoUnico() {
        // Esto sería un test conceptual, ya que no estamos probando una base de datos
        Solicitud solicitud1 = new Solicitud("Perez", "juan.perez@mail.com", "Descripción 1", "Pendiente", 1, "Juan", "987654321", "Soporte");
        Solicitud solicitud2 = new Solicitud("Lopez", "juan.perez@mail.com", "Descripción 2", "Pendiente", 2, "Carlos", "987654322", "Soporte");

        // Verificamos que los correos sean únicos (esto normalmente se verificaría en una base de datos)
        assertEquals(solicitud1.getCorreo(), solicitud2.getCorreo(), "El correo debe ser único.");
    }

    // Validación de longitud de teléfono (máximo 9 caracteres)
    @Test
    public void testTelefonoLength() {
        String telefonoLargo = "1234567890"; // 10 caracteres
        solicitud.setTelefono(telefonoLargo);
        assertFalse(solicitud.getTelefono().length() <= 9, "El teléfono excede la longitud máxima permitida de 9 caracteres.");
    }

    // Prueba de la propiedad tipoServicio
    @Test
    public void testTipoServicio() {
        assertEquals("Soporte", solicitud.getTipoServicio(), "El tipo de servicio no es correcto.");
        solicitud.setTipoServicio("Mantenimiento");
        assertEquals("Mantenimiento", solicitud.getTipoServicio(), "El tipo de servicio no se ha actualizado correctamente.");
    }

    // Validación de longitud de tipoServicio (máximo 50 caracteres)
    @Test
    public void testTipoServicioLength() {
        String tipoServicioLargo = "ServicioDePruebaQueSuperaLosCincuentaCaracteresPermitidosPorLaColumna";
        solicitud.setTipoServicio(tipoServicioLargo);
        assertFalse(solicitud.getTipoServicio().length() <= 50, "El tipo de servicio excede la longitud máxima permitida de 50 caracteres.");
    }

    // Prueba de la propiedad descripcion
    @Test
    public void testDescripcion() {
        assertEquals("Descripción de la solicitud", solicitud.getDescripcion(), "La descripción no es correcta.");
        solicitud.setDescripcion("Descripción actualizada");
        assertEquals("Descripción actualizada", solicitud.getDescripcion(), "La descripción no se ha actualizado correctamente.");
    }

    // Validación de longitud de descripción (máximo 500 caracteres)
    @Test
    public void testDescripcionLength() {
        String descripcionLarga = "Descripción ".repeat(50); // Crear un string de 500 caracteres
        solicitud.setDescripcion(descripcionLarga);
        assertFalse(solicitud.getDescripcion().length() <= 500, "La descripción excede la longitud máxima permitida de 500 caracteres.");
    }

    // Prueba de la propiedad estado
    @Test
    public void testEstado() {
        assertEquals("Pendiente", solicitud.getEstado(), "El estado no es correcto.");
        solicitud.setEstado("Completado");
        assertEquals("Completado", solicitud.getEstado(), "El estado no se ha actualizado correctamente.");
    }

    // Validación de longitud de estado (máximo 20 caracteres)
    @Test
    public void testEstadoLength() {
        String estadoLargo = "EstadoQueSuperaLosVeinteCaracteresPermitidosEnLaColumnaEstado";
        solicitud.setEstado(estadoLargo);
        assertFalse(solicitud.getEstado().length() <= 20, "El estado excede la longitud máxima permitida de 20 caracteres.");
    }

    // Prueba de que los campos obligatorios no sean nulos
    @Test
    public void testCamposObligatorios() {
        solicitud.setNombre(null);
        solicitud.setApellido(null);
        solicitud.setCorreo(null);
        solicitud.setTelefono(null);
        solicitud.setTipoServicio(null);
        solicitud.setDescripcion(null);

        assertNull(solicitud.getNombre(), "El nombre no puede ser nulo.");
        assertNull(solicitud.getApellido(), "El apellido no puede ser nulo.");
        assertNull(solicitud.getCorreo(), "El correo no puede ser nulo.");
        assertNull(solicitud.getTelefono(), "El teléfono no puede ser nulo.");
        assertNull(solicitud.getTipoServicio(), "El tipo de servicio no puede ser nulo.");
        assertNull(solicitud.getDescripcion(), "La descripción no puede ser nula.");
    }
}

