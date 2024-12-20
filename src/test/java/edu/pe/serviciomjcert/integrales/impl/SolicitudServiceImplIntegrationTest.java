package edu.pe.serviciomjcert.integrales.impl;

import edu.pe.serviciomjcert.impl.SolicitudServiceImpl;
import edu.pe.serviciomjcert.model.Solicitud;
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
class SolicitudServiceImplIntegrationTest {

    @Autowired
    private SolicitudServiceImpl solicitudService;

    @Test
    void testRegistrar() throws Exception {
        // Arrange
        Solicitud solicitud = new Solicitud();
        solicitud.setNombre("Juan");
        solicitud.setApellido("Pérez");
        solicitud.setCorreo("juan.perez@example.com");
        solicitud.setTelefono("987654321");
        solicitud.setTipoServicio("Servicio A");
        solicitud.setDescripcion("Descripción del servicio A");

        // Act
        Solicitud registrada = solicitudService.registrar(solicitud);

        // Assert
        Assertions.assertNotNull(registrada.getIdSolicitud());
        Assertions.assertEquals("Juan", registrada.getNombre());
        Assertions.assertEquals("Pérez", registrada.getApellido());
        Assertions.assertEquals("juan.perez@example.com", registrada.getCorreo());
        Assertions.assertEquals("987654321", registrada.getTelefono());
        Assertions.assertEquals("Servicio A", registrada.getTipoServicio());
        Assertions.assertEquals("Descripción del servicio A", registrada.getDescripcion());
    }

    @Test
    void testModificar() throws Exception {
        // Arrange
        Solicitud solicitud = new Solicitud();
        solicitud.setNombre("Juan");
        solicitud.setApellido("Pérez");
        solicitud.setCorreo("juan.perez@example.com");
        solicitud.setTelefono("987654321");
        solicitud.setTipoServicio("Servicio A");
        solicitud.setDescripcion("Descripción del servicio A");
        Solicitud registrada = solicitudService.registrar(solicitud);

        // Act
        registrada.setNombre("Carlos");
        registrada.setApellido("Gómez");
        Solicitud modificada = solicitudService.modificar(registrada);

        // Assert
        Assertions.assertEquals("Carlos", modificada.getNombre());
        Assertions.assertEquals("Gómez", modificada.getApellido());
    }

    @Test
    void testListar() throws Exception {
        // Arrange
        Solicitud solicitud1 = new Solicitud();
        solicitud1.setNombre("Juan");
        solicitud1.setApellido("Pérez");
        solicitud1.setCorreo("juan.perez@example.com");
        solicitud1.setTelefono("987654321");
        solicitud1.setTipoServicio("Servicio A");
        solicitud1.setDescripcion("Descripción del servicio A");
        solicitudService.registrar(solicitud1);

        Solicitud solicitud2 = new Solicitud();
        solicitud2.setNombre("Carlos");
        solicitud2.setApellido("Gómez");
        solicitud2.setCorreo("carlos.gomez@example.com");
        solicitud2.setTelefono("912345678");
        solicitud2.setTipoServicio("Servicio B");
        solicitud2.setDescripcion("Descripción del servicio B");
        solicitudService.registrar(solicitud2);

        // Act
        List<Solicitud> solicitudes = solicitudService.listar();

        // Assert
        Assertions.assertFalse(solicitudes.isEmpty());
        Assertions.assertTrue(solicitudes.size() >= 2);
    }

    @Test
    void testListarPorId() throws Exception {
        // Arrange
        Solicitud solicitud = new Solicitud();
        solicitud.setNombre("Juan");
        solicitud.setApellido("Pérez");
        solicitud.setCorreo("juan.perez@example.com");
        solicitud.setTelefono("987654321");
        solicitud.setTipoServicio("Servicio A");
        solicitud.setDescripcion("Descripción del servicio A");
        Solicitud registrada = solicitudService.registrar(solicitud);

        // Act
        Solicitud result = solicitudService.listarPorId(registrada.getIdSolicitud());

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Juan", result.getNombre());
        Assertions.assertEquals("Pérez", result.getApellido());
        Assertions.assertEquals("juan.perez@example.com", result.getCorreo());
        Assertions.assertEquals("987654321", result.getTelefono());
        Assertions.assertEquals("Servicio A", result.getTipoServicio());
        Assertions.assertEquals("Descripción del servicio A", result.getDescripcion());
    }

    @Test
    void testEliminar() throws Exception {
        // Arrange
        Solicitud solicitud = new Solicitud();
        solicitud.setNombre("Eliminar");
        solicitud.setApellido("Test");
        solicitud.setCorreo("eliminar.test@example.com");
        solicitud.setTelefono("000000000");
        solicitud.setTipoServicio("Servicio A");
        solicitud.setDescripcion("Descripción del servicio para eliminar");
        Solicitud registrada = solicitudService.registrar(solicitud);

        // Act
        solicitudService.eliminar(registrada.getIdSolicitud());
        Solicitud result = solicitudService.listarPorId(registrada.getIdSolicitud());

        // Assert
        Assertions.assertNull(result);
    }
}

