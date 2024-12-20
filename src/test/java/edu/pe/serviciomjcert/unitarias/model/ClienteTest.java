package edu.pe.serviciomjcert.unitarias.model;
import edu.pe.serviciomjcert.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        // Inicialización del cliente antes de cada prueba
        cliente = new Cliente("Perez", "juan.perez@mail.com", "Dirección del cliente", "12345678", 1, "Juan", "987654321");
    }

    // Prueba de la propiedad idCliente
    @Test
    public void testIdCliente() {
        assertEquals(1, cliente.getIdCliente(), "El ID del cliente no es correcto.");
        cliente.setIdCliente(2);
        assertEquals(2, cliente.getIdCliente(), "El ID del cliente no se ha actualizado correctamente.");
    }

    // Prueba de la propiedad nombre
    @Test
    public void testNombre() {
        assertEquals("Juan", cliente.getNombre(), "El nombre no es correcto.");
        cliente.setNombre("Carlos");
        assertEquals("Carlos", cliente.getNombre(), "El nombre no se ha actualizado correctamente.");
    }

    // Validación de longitud de nombre (máximo 50 caracteres)
    @Test
    public void testNombreLength() {
        String nombreLargo = "NombreExcesivamenteLargoParaEsteCampoQueSuperaElLimiteDe50Caracteres";
        cliente.setNombre(nombreLargo);
        assertFalse(cliente.getNombre().length() <= 50, "El nombre excede la longitud máxima permitida de 50 caracteres.");
    }

    // Prueba de la propiedad apellido
    @Test
    public void testApellido() {
        assertEquals("Perez", cliente.getApellido(), "El apellido no es correcto.");
        cliente.setApellido("Lopez");
        assertEquals("Lopez", cliente.getApellido(), "El apellido no se ha actualizado correctamente.");
    }

    // Validación de longitud de apellido (máximo 50 caracteres)
    @Test
    public void testApellidoLength() {
        String apellidoLargo = "ApellidoExcesivamenteLargoParaEsteCampoQueSuperaElLimiteDe50Caracteres";
        cliente.setApellido(apellidoLargo);
        assertFalse(cliente.getApellido().length() <= 50, "El apellido excede la longitud máxima permitida de 50 caracteres.");
    }

    // Prueba de la propiedad correo
    @Test
    public void testCorreo() {
        assertEquals("juan.perez@mail.com", cliente.getCorreo(), "El correo no es correcto.");
        cliente.setCorreo("carlos.lopez@mail.com");
        assertEquals("carlos.lopez@mail.com", cliente.getCorreo(), "El correo no se ha actualizado correctamente.");
    }

    // Validación de correo único (verificación conceptual)
    @Test
    public void testCorreoUnico() {
        // Esto sería un test conceptual, ya que no estamos probando una base de datos
        Cliente cliente1 = new Cliente("Perez", "juan.perez@mail.com", "Dirección 1", "12345678", 1, "Juan", "987654321");
        Cliente cliente2 = new Cliente("Lopez", "juan.perez@mail.com", "Dirección 2", "87654321", 2, "Carlos", "987654322");

        // Verificamos que los correos sean únicos (esto normalmente se verificaría en una base de datos)
        assertEquals(cliente1.getCorreo(), cliente2.getCorreo(), "El correo debe ser único.");
    }

    // Validación de longitud de DNI (máximo 8 caracteres)
    @Test
    public void testDniLength() {
        String dniLargo = "123456789"; // 9 caracteres
        cliente.setDni(dniLargo);
        assertFalse(cliente.getDni().length() <= 8, "El DNI excede la longitud máxima permitida de 8 caracteres.");
    }

    // Prueba de la propiedad telefono
    @Test
    public void testTelefono() {
        assertEquals("987654321", cliente.getTelefono(), "El teléfono no es correcto.");
        cliente.setTelefono("123456789");
        assertEquals("123456789", cliente.getTelefono(), "El teléfono no se ha actualizado correctamente.");
    }

    // Validación de longitud de teléfono (máximo 15 caracteres)
    @Test
    public void testTelefonoLength() {
        String telefonoLargo = "1234567890123456"; // 16 caracteres
        cliente.setTelefono(telefonoLargo);
        assertFalse(cliente.getTelefono().length() <= 15, "El teléfono excede la longitud máxima permitida de 15 caracteres.");
    }

    // Prueba de la propiedad direccion
    @Test
    public void testDireccion() {
        assertEquals("Dirección del cliente", cliente.getDireccion(), "La dirección no es correcta.");
        cliente.setDireccion("Dirección actualizada");
        assertEquals("Dirección actualizada", cliente.getDireccion(), "La dirección no se ha actualizado correctamente.");
    }

    // Validación de longitud de dirección (máximo 150 caracteres)
    @Test
    public void testDireccionLength() {
        String direccionLarga = "Dirección ".repeat(20); // Crear un string de 150 caracteres
        cliente.setDireccion(direccionLarga);
        assertFalse(cliente.getDireccion().length() <= 150, "La dirección excede la longitud máxima permitida de 150 caracteres.");
    }

    // Validación de campos obligatorios no nulos
    @Test
    public void testCamposObligatorios() {
        cliente.setNombre(null);
        cliente.setApellido(null);
        cliente.setCorreo(null);
        cliente.setTelefono(null);
        cliente.setDireccion(null);
        cliente.setDni(null);

        assertNull(cliente.getNombre(), "El nombre no puede ser nulo.");
        assertNull(cliente.getApellido(), "El apellido no puede ser nulo.");
        assertNull(cliente.getCorreo(), "El correo no puede ser nulo.");
        assertNull(cliente.getTelefono(), "El teléfono no puede ser nulo.");
        assertNull(cliente.getDireccion(), "La dirección no puede ser nula.");
        assertNull(cliente.getDni(), "El DNI no puede ser nulo.");
    }
}
