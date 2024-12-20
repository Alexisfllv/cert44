package edu.pe.serviciomjcert.unitarias.model.users;

import edu.pe.serviciomjcert.model.users.Menu;
import edu.pe.serviciomjcert.model.users.Rol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    private Menu menu;

    @BeforeEach
    public void setUp() {
        // Inicialización de un objeto Menu
        menu = new Menu();
        menu.setIdMenu(1);
        menu.setIcono("home");
        menu.setNombre("Inicio");
        menu.setUrl("/inicio");

        Rol rolAdmin = new Rol();
        rolAdmin.setIdRol(1);
        rolAdmin.setNombre("Administrador");

        menu.setRoles(Collections.singletonList(rolAdmin));
    }

    // Prueba de inicialización de valores
    @Test
    public void testInicializacionValores() {
        assertEquals(1, menu.getIdMenu(), "El ID del menú debería ser 1.");
        assertEquals("home", menu.getIcono(), "El icono debería ser 'home'.");
        assertEquals("Inicio", menu.getNombre(), "El nombre del menú debería ser 'Inicio'.");
        assertEquals("/inicio", menu.getUrl(), "La URL debería ser '/inicio'.");
    }

    // Validación de relaciones ManyToMany
    @Test
    public void testRelacionesRoles() {
        assertNotNull(menu.getRoles(), "La lista de roles no debería ser nula.");
        assertEquals(1, menu.getRoles().size(), "El menú debería estar asignado a un rol.");
        assertEquals("Administrador", menu.getRoles().get(0).getNombre(), "El rol asignado debería ser 'Administrador'.");
    }

    // Validación de getters y setters
    @Test
    public void testGettersSetters() {
        menu.setIdMenu(2);
        menu.setIcono("settings");
        menu.setNombre("Configuración");
        menu.setUrl("/configuracion");

        Rol rolUsuario = new Rol();
        rolUsuario.setIdRol(2);
        rolUsuario.setNombre("Usuario");
        menu.setRoles(Arrays.asList(menu.getRoles().get(0), rolUsuario));

        assertEquals(2, menu.getIdMenu(), "El ID del menú debería ser 2.");
        assertEquals("settings", menu.getIcono(), "El icono debería ser 'settings'.");
        assertEquals("Configuración", menu.getNombre(), "El nombre del menú debería ser 'Configuración'.");
        assertEquals("/configuracion", menu.getUrl(), "La URL debería ser '/configuracion'.");
        assertEquals(2, menu.getRoles().size(), "El menú debería estar asignado a 2 roles.");
    }

    // Validación de campos con restricciones de longitud
    @Test
    public void testRestriccionesDeLongitud() {
        menu.setIcono("icono_muy_largo".repeat(20));
        menu.setNombre("nombre_muy_largo".repeat(20));
        menu.setUrl("url_muy_larga".repeat(50));

        assertFalse(menu.getIcono().length() <= 20, "El icono debería exceder la longitud máxima permitida.");
        assertFalse(menu.getNombre().length() <= 20, "El nombre debería exceder la longitud máxima permitida.");
        assertFalse(menu.getUrl().length() <= 50, "La URL debería exceder la longitud máxima permitida.");
    }

    // Validación de lista de roles vacía
    @Test
    public void testListaRolesVacia() {
        menu.setRoles(Collections.emptyList());
        assertTrue(menu.getRoles().isEmpty(), "La lista de roles debería estar vacía.");
    }

    // Validación de icono nulo
    @Test
    public void testIconoNulo() {
        menu.setIcono(null);
        assertNull(menu.getIcono(), "El icono debería ser nulo.");
    }

    // Validación de URL nula
    @Test
    public void testUrlNula() {
        menu.setUrl(null);
        assertNull(menu.getUrl(), "La URL debería ser nula.");
    }
}
