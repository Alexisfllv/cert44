package edu.pe.serviciomjcert.unitarias.dto.users;

import edu.pe.serviciomjcert.dto.users.RolDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class RolDTOTest {

    private Validator validator;
    private RolDTO rol;

    @BeforeEach
    public void setUp() {
        // Inicializa el validador
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        rol = new RolDTO();
    }

    @Test
    public void testRolDTOConstructor() {
        rol.setIdRol(1);
        rol.setNombre("Admin");
        rol.setDescripcion("Administrador del sistema");

        assertEquals(1, rol.getIdRol());
        assertEquals("Admin", rol.getNombre());
        assertEquals("Administrador del sistema", rol.getDescripcion());
    }

    @Test
    public void testSetGetIdRol() {
        rol.setIdRol(1);
        assertEquals(1, rol.getIdRol());
    }

    @Test
    public void testSetGetNombre() {
        rol.setNombre("Admin");
        assertEquals("Admin", rol.getNombre());
    }

    @Test
    public void testSetGetDescripcion() {
        rol.setDescripcion("Administrador del sistema");
        assertEquals("Administrador del sistema", rol.getDescripcion());
    }

    @Test
    public void testValidRolDTO() {
        rol.setIdRol(1);
        rol.setNombre("Admin");
        rol.setDescripcion("Administrador del sistema");

        Set<ConstraintViolation<RolDTO>> violations = validator.validate(rol);
        assertTrue(violations.isEmpty(), "No deben existir violaciones de validación");
    }

}
