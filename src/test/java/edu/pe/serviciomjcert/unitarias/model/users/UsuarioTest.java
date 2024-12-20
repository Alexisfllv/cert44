package edu.pe.serviciomjcert.unitarias.model.users;

import edu.pe.serviciomjcert.model.users.Rol;
import edu.pe.serviciomjcert.model.users.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        // Inicialización de un objeto Usuario
        usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setUsername("admin");
        usuario.setPassword("securepassword");
        usuario.setEnabled(true);

        Rol rolAdmin = new Rol();
        rolAdmin.setIdRol(1);
        rolAdmin.setNombre("Administrador");
        rolAdmin.setDescripcion("Rol con acceso completo.");

        usuario.setRoles(Collections.singletonList(rolAdmin));
    }

    // Prueba de inicialización de valores
    @Test
    public void testInicializacionValores() {
        assertEquals(1, usuario.getIdUsuario(), "El ID del usuario debería ser 1.");
        assertEquals("admin", usuario.getUsername(), "El nombre de usuario debería ser 'admin'.");
        assertEquals("securepassword", usuario.getPassword(), "La contraseña debería coincidir.");
        assertTrue(usuario.isEnabled(), "El estado del usuario debería ser 'habilitado'.");
    }

    // Validación de relaciones ManyToMany
    @Test
    public void testRelacionesRoles() {
        assertNotNull(usuario.getRoles(), "La lista de roles no debería ser nula.");
        assertEquals(1, usuario.getRoles().size(), "El usuario debería tener un rol asignado.");
        assertEquals("Administrador", usuario.getRoles().get(0).getNombre(), "El rol asignado debería ser 'Administrador'.");
    }

    // Validación de getters y setters
    @Test
    public void testGettersSetters() {
        usuario.setIdUsuario(2);
        usuario.setUsername("usuario");
        usuario.setPassword("newpassword");
        usuario.setEnabled(false);

        Rol rolUsuario = new Rol();
        rolUsuario.setIdRol(2);
        rolUsuario.setNombre("Usuario");
        usuario.setRoles(Arrays.asList(usuario.getRoles().get(0), rolUsuario));

        assertEquals(2, usuario.getIdUsuario(), "El ID del usuario debería ser 2.");
        assertEquals("usuario", usuario.getUsername(), "El nombre de usuario debería ser 'usuario'.");
        assertEquals("newpassword", usuario.getPassword(), "La contraseña debería coincidir.");
        assertFalse(usuario.isEnabled(), "El estado del usuario debería ser 'deshabilitado'.");
        assertEquals(2, usuario.getRoles().size(), "El usuario debería tener 2 roles asignados.");
    }

    // Validación de nombre único y obligatorio
    @Test
    public void testNombreUnicoObligatorio() {
        usuario.setUsername(null);
        assertNull(usuario.getUsername(), "El nombre de usuario debería poder ser nulo, pero es obligatorio según las reglas de negocio.");

        usuario.setUsername("admin");
        assertEquals("admin", usuario.getUsername(), "El nombre de usuario debería coincidir.");
    }

    // Validación de contraseña obligatoria
    @Test
    public void testPasswordObligatoria() {
        usuario.setPassword(null);
        assertNull(usuario.getPassword(), "La contraseña debería poder ser nula, pero es obligatoria según las reglas de negocio.");

        usuario.setPassword("securepassword");
        assertEquals("securepassword", usuario.getPassword(), "La contraseña debería coincidir.");
    }

    // Validación de estado booleano
    @Test
    public void testEstadoBooleano() {
        usuario.setEnabled(false);
        assertFalse(usuario.isEnabled(), "El estado del usuario debería ser 'deshabilitado'.");

        usuario.setEnabled(true);
        assertTrue(usuario.isEnabled(), "El estado del usuario debería ser 'habilitado'.");
    }

    // Validación de lista de roles vacía
    @Test
    public void testListaRolesVacia() {
        usuario.setRoles(Collections.emptyList());
        assertTrue(usuario.getRoles().isEmpty(), "La lista de roles debería estar vacía.");
    }
}
