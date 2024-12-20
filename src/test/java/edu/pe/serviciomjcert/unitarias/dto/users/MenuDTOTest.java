package edu.pe.serviciomjcert.unitarias.dto.users;

import edu.pe.serviciomjcert.dto.users.MenuDTO;
import edu.pe.serviciomjcert.dto.users.RolDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MenuDTOTest {

    private Validator validator;
    private MenuDTO menu;

    @BeforeEach
    public void setUp() {
        // Inicializa el validador
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        menu = new MenuDTO();
    }

    @Test
    public void testMenuDTOConstructor() {
        List<RolDTO> roles = Arrays.asList(new RolDTO("detalle",1, "Admin"), new RolDTO("detalle",2, "User"));
        menu.setIdMenu(1);
        menu.setNombre("Dashboard");
        menu.setUrl("/dashboard");
        menu.setIcono("dashboard-icon");
        menu.setRoles(roles);

        assertEquals(1, menu.getIdMenu());
        assertEquals("Dashboard", menu.getNombre());
        assertEquals("/dashboard", menu.getUrl());
        assertEquals("dashboard-icon", menu.getIcono());
        assertEquals(roles, menu.getRoles());
    }

    @Test
    public void testSetGetIdMenu() {
        menu.setIdMenu(1);
        assertEquals(1, menu.getIdMenu());
    }

    @Test
    public void testSetGetNombre() {
        menu.setNombre("Dashboard");
        assertEquals("Dashboard", menu.getNombre());
    }

    @Test
    public void testSetGetUrl() {
        menu.setUrl("/dashboard");
        assertEquals("/dashboard", menu.getUrl());
    }

    @Test
    public void testSetGetIcono() {
        menu.setIcono("dashboard-icon");
        assertEquals("dashboard-icon", menu.getIcono());
    }

    @Test
    public void testSetGetRoles() {
        List<RolDTO> roles = Arrays.asList(new RolDTO("detalle",1, "Admin"));
        menu.setRoles(roles);
        assertEquals(roles, menu.getRoles());
    }

    @Test
    public void testValidMenuDTO() {
        List<RolDTO> roles = Arrays.asList(new RolDTO("detalle",1, "Admin"));
        menu.setIdMenu(1);
        menu.setNombre("Dashboard");
        menu.setUrl("/dashboard");
        menu.setIcono("dashboard-icon");
        menu.setRoles(roles);

        Set<ConstraintViolation<MenuDTO>> violations = validator.validate(menu);
        assertTrue(violations.isEmpty(), "No deben existir violaciones de validación");
    }

}
