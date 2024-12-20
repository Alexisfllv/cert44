package edu.pe.serviciomjcert.integrales.impl;

import edu.pe.serviciomjcert.impl.ClienteServiceImpl;
import edu.pe.serviciomjcert.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClienteServiceImplIntegrationTest {

    @Autowired
    private ClienteServiceImpl clienteService;

    @Test
    void testRegistrar() throws Exception {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setNombre("Ana");
        cliente.setApellido("González");
        cliente.setCorreo("ana.gonzalez@example.com");
        cliente.setDireccion("Calle Ficticia 123");
        cliente.setDni("12345678");
        cliente.setTelefono("987654321");

        // Act
        Cliente registrado = clienteService.registrar(cliente);

        // Assert
        Assertions.assertNotNull(registrado.getIdCliente());
        Assertions.assertEquals("Ana", registrado.getNombre());
        Assertions.assertEquals("González", registrado.getApellido());
        Assertions.assertEquals("ana.gonzalez@example.com", registrado.getCorreo());
        Assertions.assertEquals("Calle Ficticia 123", registrado.getDireccion());
        Assertions.assertEquals("12345678", registrado.getDni());
        Assertions.assertEquals("987654321", registrado.getTelefono());
    }

    @Test
    void testModificar() throws Exception {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setNombre("Carlos");
        cliente.setApellido("Méndez");
        cliente.setCorreo("carlos.mendez@example.com");
        cliente.setDireccion("Av. Real 456");
        cliente.setDni("87654321");
        cliente.setTelefono("912345678");
        Cliente registrado = clienteService.registrar(cliente);

        // Act
        registrado.setNombre("Carlos Alberto");
        registrado.setApellido("Méndez Pérez");
        Cliente modificado = clienteService.modificar(registrado);

        // Assert
        Assertions.assertEquals("Carlos Alberto", modificado.getNombre());
        Assertions.assertEquals("Méndez Pérez", modificado.getApellido());
    }

    @Test
    void testListar() throws Exception {
        // Arrange
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Juan");
        cliente1.setApellido("Pérez");
        cliente1.setCorreo("juan.perez@example.com");
        cliente1.setDireccion("Calle 123");
        cliente1.setDni("12312312");
        cliente1.setTelefono("987654321");
        clienteService.registrar(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Laura");
        cliente2.setApellido("Gómez");
        cliente2.setCorreo("laura.gomez@example.com");
        cliente2.setDireccion("Calle Ficticia 789");
        cliente2.setDni("11122333");
        cliente2.setTelefono("911223344");
        clienteService.registrar(cliente2);

        // Act
        List<Cliente> clientes = clienteService.listar();

        // Assert
        Assertions.assertFalse(clientes.isEmpty());
        Assertions.assertTrue(clientes.size() >= 2);
    }

    @Test
    void testListarPorId() throws Exception {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setNombre("Pedro");
        cliente.setApellido("Sánchez");
        cliente.setCorreo("pedro.sanchez@example.com");
        cliente.setDireccion("Av. Central 12");
        cliente.setDni("98765432");
        cliente.setTelefono("999999999");
        Cliente registrado = clienteService.registrar(cliente);

        // Act
        Cliente result = clienteService.listarPorId(registrado.getIdCliente());

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Pedro", result.getNombre());
        Assertions.assertEquals("Sánchez", result.getApellido());
        Assertions.assertEquals("pedro.sanchez@example.com", result.getCorreo());
        Assertions.assertEquals("Av. Central 12", result.getDireccion());
        Assertions.assertEquals("98765432", result.getDni());
        Assertions.assertEquals("999999999", result.getTelefono());
    }

    @Test
    void testEliminar() throws Exception {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setNombre("Eliminar");
        cliente.setApellido("Test");
        cliente.setCorreo("eliminar.test@example.com");
        cliente.setDireccion("Av. Test 123");
        cliente.setDni("00000000");
        cliente.setTelefono("000000000");
        Cliente registrado = clienteService.registrar(cliente);

        // Act
        clienteService.eliminar(registrado.getIdCliente());
        Cliente result = clienteService.listarPorId(registrado.getIdCliente());

        // Assert
        Assertions.assertNull(result);
    }


    //PAGEABLE LISTAR
    @Test
    void testListarPageable() throws Exception {
        // Arrange
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Juan");
        cliente1.setApellido("Pérez");
        cliente1.setCorreo("juan.perez@example.com");
        cliente1.setDireccion("Calle 123");
        cliente1.setDni("12312312");
        cliente1.setTelefono("987654321");
        clienteService.registrar(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Laura");
        cliente2.setApellido("Gómez");
        cliente2.setCorreo("laura.gomez@example.com");
        cliente2.setDireccion("Calle Ficticia 789");
        cliente2.setDni("11122333");
        cliente2.setTelefono("911223344");
        clienteService.registrar(cliente2);

        Pageable pageable = PageRequest.of(0, 1);  // Pagina 0, tamaño 1

        // Act
        Page<Cliente> clientesPage = clienteService.listarPageable(pageable);

        // Assert
        Assertions.assertNotNull(clientesPage);
        Assertions.assertEquals(1, clientesPage.getSize());
        Assertions.assertTrue(clientesPage.getTotalElements() >= 2);  // Debería haber al menos dos clientes
    }
}

