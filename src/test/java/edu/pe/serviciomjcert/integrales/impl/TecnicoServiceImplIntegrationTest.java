package edu.pe.serviciomjcert.integrales.impl;

import edu.pe.serviciomjcert.impl.TecnicoServiceImpl;
import edu.pe.serviciomjcert.model.Tecnico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.List;


//
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TecnicoServiceImplIntegrationTest {

    @Autowired
    private TecnicoServiceImpl tecnicoService;

    private Tecnico tecnicoPrueba;

    @BeforeEach
    void setUp() {
        // Definir los valores comunes para los tests
        tecnicoPrueba = new Tecnico();
        tecnicoPrueba.setNombre("nombreprueba");
        tecnicoPrueba.setApellido("apellidoprueba");
        tecnicoPrueba.setFoto("fotoprueba");
        tecnicoPrueba.setDni("22222222");
        tecnicoPrueba.setDireccion("Calleprueba");
        tecnicoPrueba.setCorreo("juan.prueba@example.com");
    }

    @Test
    void testRegistrar() throws Exception {
        // Act
        Tecnico registrado = tecnicoService.registrar(tecnicoPrueba);

        // Assert
        Assertions.assertNotNull(registrado.getIdTecnico());
        Assertions.assertEquals("nombreprueba", registrado.getNombre());
        Assertions.assertEquals("apellidoprueba", registrado.getApellido());
        Assertions.assertEquals("fotoprueba", registrado.getFoto());
        Assertions.assertEquals("22222222", registrado.getDni());
        Assertions.assertEquals("Calleprueba", registrado.getDireccion());
        Assertions.assertEquals("juan.prueba@example.com", registrado.getCorreo());
    }

    @Test
    void testModificar() throws Exception {
        // Registrar el técnico
        Tecnico registrado = tecnicoService.registrar(tecnicoPrueba);

        // Modificar el técnico
        registrado.setNombre("Carlos");
        registrado.setApellido("Lopez");
        registrado.setCorreo("carlos.lopez@example.com");

        // Act
        Tecnico modificado = tecnicoService.modificar(registrado);

        // Assert
        Assertions.assertEquals("Carlos", modificado.getNombre());
        Assertions.assertEquals("Lopez", modificado.getApellido());
        Assertions.assertEquals("carlos.lopez@example.com", modificado.getCorreo());
    }

    @Test
    void testListar() throws Exception {
        // Registrar el técnico
        Tecnico registrado = tecnicoService.registrar(tecnicoPrueba);

        // Crear otro técnico para probar listar más de uno
        Tecnico tecnico2 = new Tecnico();
        tecnico2.setNombre("nombreprueba2");
        tecnico2.setApellido("apellidoprueba2");
        tecnico2.setFoto("fotoprueba2");
        tecnico2.setDni("22222223");
        tecnico2.setDireccion("Calleprueba2");
        tecnico2.setCorreo("juan.prueba2@example.com");

        tecnicoService.registrar(tecnico2);

        // Act
        List<Tecnico> tecnicos = tecnicoService.listar();

        // Assert
        Assertions.assertFalse(tecnicos.isEmpty());
        Assertions.assertTrue(tecnicos.size() >= 2);
    }

    @Test
    void testListarPorId() throws Exception {
        // Registrar el técnico
        Tecnico registrado = tecnicoService.registrar(tecnicoPrueba);

        // Act
        Tecnico result = tecnicoService.listarPorId(registrado.getIdTecnico());

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals("nombreprueba", result.getNombre());
        Assertions.assertEquals("apellidoprueba", result.getApellido());
        Assertions.assertEquals("22222222", result.getDni());
        Assertions.assertEquals("Calleprueba", result.getDireccion());
        Assertions.assertEquals("juan.prueba@example.com", result.getCorreo());
    }

    @Test
    void testEliminar() throws Exception {
        // Registrar el técnico
        Tecnico registrado = tecnicoService.registrar(tecnicoPrueba);

        // Act
        tecnicoService.eliminar(registrado.getIdTecnico());
        Tecnico result = tecnicoService.listarPorId(registrado.getIdTecnico());

        // Assert
        Assertions.assertNull(result);
    }
}



