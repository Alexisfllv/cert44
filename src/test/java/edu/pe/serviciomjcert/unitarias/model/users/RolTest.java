package edu.pe.serviciomjcert.unitarias.model.users;

import edu.pe.serviciomjcert.model.users.Rol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RolTest {

    private Rol rol;

    @BeforeEach
    public void setUp() {
        // Inicialización de un objeto Rol
        rol = new Rol();
        rol.setIdRol(1);
        rol.setNombre("Administrador");
        rol.setDescripcion("Rol con acceso completo al sistema.");
    }

    // Prueba de inicialización de los valores
    @Test
    public void testInicializacionValores() {
        assertEquals(1, rol.getIdRol(), "El ID del rol debería ser 1.");
        assertEquals("Administrador", rol.getNombre(), "El nombre del rol debería ser 'Administrador'.");
        assertEquals("Rol con acceso completo al sistema.", rol.getDescripcion(), "La descripción del rol debería coincidir.");
    }

    // Validación de que los campos no sean nulos donde corresponda
    @Test
    public void testCamposObligatorios() {
        rol.setNombre(null);
        assertNull(rol.getNombre(), "El nombre puede ser nulo.");

        rol.setDescripcion(null);
        assertNull(rol.getDescripcion(), "La descripción puede ser nula.");
    }

    // Validación de la longitud de los atributos
    @Test
    public void testLongitudAtributos() {
        String nombreLargo = "NombreDeRolMuyLargoQueExcedeLos15Caracteres";
        rol.setNombre(nombreLargo);
        assertTrue(rol.getNombre().length() > 15, "El nombre excede la longitud máxima permitida de 15 caracteres.");

        String descripcionLarga = "Descripción muy larga que debería permitir hasta 150 caracteres, y estamos validando que no se corte.";
        rol.setDescripcion(descripcionLarga);
        assertTrue(rol.getDescripcion().length() <= 150, "La descripción no debería exceder los 150 caracteres.");
    }

    // Validación de getters y setters
    @Test
    public void testGettersSetters() {
        rol.setIdRol(2);
        rol.setNombre("Usuario");
        rol.setDescripcion("Rol con acceso limitado al sistema.");

        assertEquals(2, rol.getIdRol(), "El ID del rol debería ser 2.");
        assertEquals("Usuario", rol.getNombre(), "El nombre del rol debería ser 'Usuario'.");
        assertEquals("Rol con acceso limitado al sistema.", rol.getDescripcion(), "La descripción debería coincidir.");
    }

    // Validación de comportamiento cuando los valores son nulos
    @Test
    public void testValoresNulos() {
        rol.setIdRol(null);
        rol.setNombre(null);
        rol.setDescripcion(null);

        assertNull(rol.getIdRol(), "El ID del rol puede ser nulo.");
        assertNull(rol.getNombre(), "El nombre del rol puede ser nulo.");
        assertNull(rol.getDescripcion(), "La descripción del rol puede ser nula.");
    }

    // Validación de una descripción opcional
    @Test
    public void testDescripcionOpcional() {
        rol.setDescripcion(null);
        assertNull(rol.getDescripcion(), "La descripción del rol es opcional y puede ser nula.");
    }
}
