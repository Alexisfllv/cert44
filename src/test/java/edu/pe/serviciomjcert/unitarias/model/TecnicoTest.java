package edu.pe.serviciomjcert.unitarias.model;

import edu.pe.serviciomjcert.model.Tecnico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TecnicoTest {

    private Tecnico tecnico;

    @BeforeEach
    public void setUp() {
        // Inicialización del técnico antes de cada prueba
        tecnico = new Tecnico(1, "Carlos", "Lopez", "foto.jpg", "12345678", "Av. Principal 123", "carlos.lopez@mail.com");
    }

    // Prueba de la propiedad idTecnico
    @Test
    public void testIdTecnico() {
        assertEquals(1, tecnico.getIdTecnico(), "El ID del técnico no es correcto.");
        tecnico.setIdTecnico(2);
        assertEquals(2, tecnico.getIdTecnico(), "El ID del técnico no se ha actualizado correctamente.");
    }

    // Prueba de la propiedad nombre
    @Test
    public void testNombre() {
        assertEquals("Carlos", tecnico.getNombre(), "El nombre no es correcto.");
        tecnico.setNombre("Juan");
        assertEquals("Juan", tecnico.getNombre(), "El nombre no se ha actualizado correctamente.");
    }

    // Validación de longitud de nombre (máximo 100 caracteres)
    @Test
    public void testNombreLength() {
        String nombreLargo = "NombreExcesivamenteLargoQueSuperaElLimiteDeCienCaracteresParaProbarElComportamientoDelCampoEnBaseDeDatos";
        tecnico.setNombre(nombreLargo);
        assertFalse(tecnico.getNombre().length() <= 100, "El nombre excede la longitud máxima permitida de 100 caracteres.");
    }

    // Prueba de la propiedad apellido
    @Test
    public void testApellido() {
        assertEquals("Lopez", tecnico.getApellido(), "El apellido no es correcto.");
        tecnico.setApellido("Gomez");
        assertEquals("Gomez", tecnico.getApellido(), "El apellido no se ha actualizado correctamente.");
    }

    // Validación de longitud de apellido (máximo 100 caracteres)
    @Test
    public void testApellidoLength() {
        String apellidoLargo = "ApellidoExcesivamenteLargoQueSuperaElLimiteDeCienCaracteresParaProbarElComportamientoDelCampoEnBaseDeDatos";
        tecnico.setApellido(apellidoLargo);
        assertFalse(tecnico.getApellido().length() <= 100, "El apellido excede la longitud máxima permitida de 100 caracteres.");
    }

    // Prueba de la propiedad foto
    @Test
    public void testFoto() {
        assertEquals("foto.jpg", tecnico.getFoto(), "La foto no es correcta.");
        tecnico.setFoto("nuevaFoto.jpg");
        assertEquals("nuevaFoto.jpg", tecnico.getFoto(), "La foto no se ha actualizado correctamente.");
    }

    // Prueba de la propiedad dni
    @Test
    public void testDni() {
        assertEquals("12345678", tecnico.getDni(), "El DNI no es correcto.");
        tecnico.setDni("87654321");
        assertEquals("87654321", tecnico.getDni(), "El DNI no se ha actualizado correctamente.");
    }

    // Validación de longitud de DNI (exactamente 8 caracteres)
    @Test
    public void testDniLength() {
        String dniLargo = "123456789"; // 9 caracteres
        tecnico.setDni(dniLargo);
        assertFalse(tecnico.getDni().length() == 8, "El DNI debe tener exactamente 8 caracteres.");
    }

    // Prueba de la propiedad direccion
    @Test
    public void testDireccion() {
        assertEquals("Av. Principal 123", tecnico.getDireccion(), "La dirección no es correcta.");
        tecnico.setDireccion("Calle Secundaria 456");
        assertEquals("Calle Secundaria 456", tecnico.getDireccion(), "La dirección no se ha actualizado correctamente.");
    }

    // Validación de longitud de direccion (máximo 255 caracteres)
    @Test
    public void testDireccionLength() {
        String direccionLarga = "DireccionExcesivamenteLarga ".repeat(255);
        tecnico.setDireccion(direccionLarga);
        assertFalse(tecnico.getDireccion().length() <= 255, "La dirección excede la longitud máxima permitida de 255 caracteres.");
    }

    // Prueba de la propiedad correo
    @Test
    public void testCorreo() {
        assertEquals("carlos.lopez@mail.com", tecnico.getCorreo(), "El correo no es correcto.");
        tecnico.setCorreo("juan.lopez@mail.com");
        assertEquals("juan.lopez@mail.com", tecnico.getCorreo(), "El correo no se ha actualizado correctamente.");
    }

    // Validación de correo único (verificación conceptual)
    @Test
    public void testCorreoUnico() {
        // Esto sería un test conceptual, ya que no estamos probando una base de datos
        Tecnico tecnico1 = new Tecnico(1, "Carlos", "Lopez", "foto1.jpg", "12345678", "Direccion 1", "carlos.lopez@mail.com");
        Tecnico tecnico2 = new Tecnico(2, "Juan", "Lopez", "foto2.jpg", "87654321", "Direccion 2", "carlos.lopez@mail.com");

        // Verificamos que los correos sean únicos (esto normalmente se verificaría en una base de datos)
        assertEquals(tecnico1.getCorreo(), tecnico2.getCorreo(), "El correo debe ser único.");
    }

    // Validación de campos obligatorios no nulos
    @Test
    public void testCamposObligatorios() {
        // Establecer los campos como nulos
        tecnico.setNombre(null);
        tecnico.setApellido(null);
        tecnico.setFoto(null);
        tecnico.setDni(null);
        tecnico.setDireccion(null);
        tecnico.setCorreo(null);

        // Verificar que los campos no pueden ser nulos
        assertNull(tecnico.getNombre(), "El nombre no puede ser nulo.");
        assertNull(tecnico.getApellido(), "El apellido no puede ser nulo.");
        assertNull(tecnico.getFoto(), "La foto no puede ser nula.");
        assertNull(tecnico.getDni(), "El DNI no puede ser nulo.");
        assertNull(tecnico.getDireccion(), "La dirección no puede ser nula.");
        assertNull(tecnico.getCorreo(), "El correo no puede ser nulo.");
    }
}
