package edu.pe.serviciomjcert.integrales.impl;

import edu.pe.serviciomjcert.impl.TipoServicioServiceImpl;
import edu.pe.serviciomjcert.model.TipoServicio;
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
class TipoServicioServiceImplIntegrationTest {

    @Autowired
    private TipoServicioServiceImpl tipoServicioService;

    @Test
    void testRegistrar() throws Exception {
        // Arrange
        TipoServicio tipoServicio = new TipoServicio();
        tipoServicio.setNombre("Mantenimiento");
        tipoServicio.setDescripcion("Servicio de mantenimiento preventivo");

        // Act
        TipoServicio registrado = tipoServicioService.registrar(tipoServicio);

        // Assert
        Assertions.assertNotNull(registrado.getIdTipoServicio());
        Assertions.assertEquals("Mantenimiento", registrado.getNombre());
        Assertions.assertEquals("Servicio de mantenimiento preventivo", registrado.getDescripcion());
    }

    @Test
    void testModificar() throws Exception {
        // Arrange
        TipoServicio tipoServicio = new TipoServicio();
        tipoServicio.setNombre("Mantenimiento");
        tipoServicio.setDescripcion("Servicio de mantenimiento preventivo");
        TipoServicio registrado = tipoServicioService.registrar(tipoServicio);

        // Act
        registrado.setNombre("Reparación");
        registrado.setDescripcion("Servicio de reparación de equipos");
        TipoServicio modificado = tipoServicioService.modificar(registrado);

        // Assert
        Assertions.assertEquals("Reparación", modificado.getNombre());
        Assertions.assertEquals("Servicio de reparación de equipos", modificado.getDescripcion());
    }

    @Test
    void testListar() throws Exception {
        // Arrange
        TipoServicio tipo1 = new TipoServicio();
        tipo1.setNombre("Mantenimiento");
        tipo1.setDescripcion("Servicio de mantenimiento preventivo");
        tipoServicioService.registrar(tipo1);

        TipoServicio tipo2 = new TipoServicio();
        tipo2.setNombre("Reparación");
        tipo2.setDescripcion("Servicio de reparación de equipos");
        tipoServicioService.registrar(tipo2);

        // Act
        List<TipoServicio> tipos = tipoServicioService.listar();

        // Assert
        Assertions.assertFalse(tipos.isEmpty());
        Assertions.assertTrue(tipos.size() >= 2);
    }

    @Test
    void testListarPorId() throws Exception {
        // Arrange
        TipoServicio tipoServicio = new TipoServicio();
        tipoServicio.setNombre("Mantenimiento");
        tipoServicio.setDescripcion("Servicio de mantenimiento preventivo");
        TipoServicio registrado = tipoServicioService.registrar(tipoServicio);

        // Act
        TipoServicio result = tipoServicioService.listarPorId(registrado.getIdTipoServicio());

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Mantenimiento", result.getNombre());
        Assertions.assertEquals("Servicio de mantenimiento preventivo", result.getDescripcion());
    }

    @Test
    void testEliminar() throws Exception {
        // Arrange
        TipoServicio tipoServicio = new TipoServicio();
        tipoServicio.setNombre("Eliminar");
        tipoServicio.setDescripcion("Este servicio será eliminado");
        TipoServicio registrado = tipoServicioService.registrar(tipoServicio);

        // Act
        tipoServicioService.eliminar(registrado.getIdTipoServicio());
        TipoServicio result = tipoServicioService.listarPorId(registrado.getIdTipoServicio());

        // Assert
        Assertions.assertNull(result);
    }
}

