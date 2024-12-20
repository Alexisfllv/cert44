package edu.pe.serviciomjcert.integrales.impl;

import edu.pe.serviciomjcert.impl.TecnicoServiceImpl;
import edu.pe.serviciomjcert.model.Tecnico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TecnicoServiceImplIntegrationTest {

    @Autowired
    private TecnicoServiceImpl tecnicoService;

    @Test
    void testRegistrar() throws Exception {
        // Arrange
        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Juan");
        tecnico.setApellido("Pérez");
        tecnico.setFoto("foto.jpg");
        tecnico.setDni("12345678");
        tecnico.setDireccion("Av. Siempre Viva 123");
        tecnico.setCorreo("juan.perez@example.com");

        // Act
        Tecnico registrado = tecnicoService.registrar(tecnico);

        // Assert
        Assertions.assertNotNull(registrado.getIdTecnico());
        Assertions.assertEquals("Juan", registrado.getNombre());
        Assertions.assertEquals("Pérez", registrado.getApellido());
        Assertions.assertEquals("12345678", registrado.getDni());
        Assertions.assertEquals("juan.perez@example.com", registrado.getCorreo());
    }

    @Test
    void testModificar() throws Exception {
        // Arrange
        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Juan");
        tecnico.setApellido("Pérez");
        tecnico.setFoto("foto.jpg");
        tecnico.setDni("12345678");
        tecnico.setDireccion("Av. Siempre Viva 123");
        tecnico.setCorreo("juan.perez@example.com");
        Tecnico registrado = tecnicoService.registrar(tecnico);

        // Act
        registrado.setNombre("Carlos");
        registrado.setApellido("Gómez");
        Tecnico modificado = tecnicoService.modificar(registrado);

        // Assert
        Assertions.assertEquals("Carlos", modificado.getNombre());
        Assertions.assertEquals("Gómez", modificado.getApellido());
    }

    @Test
    void testListar() throws Exception {
        // Arrange
        Tecnico tecnico1 = new Tecnico();
        tecnico1.setNombre("Juan");
        tecnico1.setApellido("Pérez");
        tecnico1.setFoto("foto.jpg");
        tecnico1.setDni("12345678");
        tecnico1.setDireccion("Av. Siempre Viva 123");
        tecnico1.setCorreo("juan.perez@example.com");
        tecnicoService.registrar(tecnico1);

        Tecnico tecnico2 = new Tecnico();
        tecnico2.setNombre("Carlos");
        tecnico2.setApellido("Gómez");
        tecnico2.setFoto("foto.jpg");
        tecnico2.setDni("87654321");
        tecnico2.setDireccion("Av. Falsa 456");
        tecnico2.setCorreo("carlos.gomez@example.com");
        tecnicoService.registrar(tecnico2);

        // Act
        List<Tecnico> tecnicos = tecnicoService.listar();

        // Assert
        Assertions.assertFalse(tecnicos.isEmpty());
        Assertions.assertTrue(tecnicos.size() >= 2);
    }

    @Test
    void testListarPorId() throws Exception {
        // Arrange
        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Juan");
        tecnico.setApellido("Pérez");
        tecnico.setFoto("foto.jpg");
        tecnico.setDni("12345678");
        tecnico.setDireccion("Av. Siempre Viva 123");
        tecnico.setCorreo("juan.perez@example.com");
        Tecnico registrado = tecnicoService.registrar(tecnico);

        // Act
        Tecnico result = tecnicoService.listarPorId(registrado.getIdTecnico());

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Juan", result.getNombre());
        Assertions.assertEquals("Pérez", result.getApellido());
        Assertions.assertEquals("12345678", result.getDni());
        Assertions.assertEquals("juan.perez@example.com", result.getCorreo());
    }

    @Test
    void testEliminar() throws Exception {
        // Arrange
        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Eliminar");
        tecnico.setApellido("Test");
        tecnico.setFoto("foto.jpg");
        tecnico.setDni("00000000");
        tecnico.setDireccion("Dirección eliminada");
        tecnico.setCorreo("eliminar.test@example.com");
        Tecnico registrado = tecnicoService.registrar(tecnico);

        // Act
        tecnicoService.eliminar(registrado.getIdTecnico());
        Tecnico result = tecnicoService.listarPorId(registrado.getIdTecnico());

        // Assert
        Assertions.assertNull(result);
    }
}

