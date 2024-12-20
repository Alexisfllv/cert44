package edu.pe.serviciomjcert.unitarias.dto;

import edu.pe.serviciomjcert.dto.ClienteDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteDTOTest {

    private Validator validator;
    private ClienteDTO cliente;

    @BeforeEach
    public void setUp() {
        // Inicializa el validador
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        cliente = new ClienteDTO();
    }

    @Test
    public void testNombreClienteNoPuedeSerNull() {
        cliente.setNombreCliente(null);
        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(cliente);
        assertEquals(6, violations.size()); // Debe haber una violación
    }

    @Test
    public void testApellidoClienteNoPuedeSerNull() {
        cliente.setApellidoCliente(null);
        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(cliente);
        assertEquals(6, violations.size()); // Debe haber una violación
    }

    @Test
    public void testCorreoClienteNoPuedeSerNull() {
        cliente.setCorreoCliente(null);
        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(cliente);
        assertEquals(6, violations.size()); // Debe haber una violación
    }

    @Test
    public void testDireccionClienteNoPuedeSerNull() {
        cliente.setDireccionCliente(null);
        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(cliente);
        assertEquals(6, violations.size()); // Debe haber una violación
    }

    @Test
    public void testDniClienteNoPuedeSerNull() {
        cliente.setDniCliente(null);
        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(cliente);
        assertEquals(6, violations.size()); // Debe haber una violación
    }

    @Test
    public void testTelefonoClienteNoPuedeSerNull() {
        cliente.setTelefonoCliente(null);
        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(cliente);
        assertEquals(6, violations.size()); // Debe haber una violación
    }

    @Test
    public void testConstructorCompleto() {
        cliente = new ClienteDTO("Apellido", "correo@dominio.com", "Dirección", "12345678", 1, "Nombre", "987654321");
        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(cliente);
        assertTrue(violations.isEmpty()); // No debe haber violaciones
    }

    @Test
    public void testIdClienteNoPuedeSerNull() {
        cliente.setIdCliente(100);
        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(cliente);
        assertFalse(violations.isEmpty()); // No debe haber violaciones para un id válido
    }
}
