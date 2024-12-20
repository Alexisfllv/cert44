package edu.pe.serviciomjcert.integrales.impl;
//

import edu.pe.serviciomjcert.model.*;
import edu.pe.serviciomjcert.repo.*;
import edu.pe.serviciomjcert.service.ICitaService;
import edu.pe.serviciomjcert.service.ICitaTipoServicioService;
import edu.pe.serviciomjcert.service.IClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
@Transactional
public class CitatipoServicioImplIntegracionTest {

    @Autowired
    private ICitaTipoServicioService citaTipoServicioService;

    @Autowired
    private ICitaTipoServicioRepo citaTipoServicioRepo;

    @Autowired
    private ICitaRepo citaRepo;

    @Autowired
    private ICitaService citaService;

    @Autowired
    private ITipoServicioRepo tipoServicioRepo;

    //cliente
    @Autowired
    IClienteRepo clienteRepo;

    //solicitud
    @Autowired
    ISolicitudRepo solicitudRepo;


    @Autowired
    IClienteService clienteService;

    @Autowired
    ITecnicoRepo tecnicoRepo;

    @Test
    void testListarTiposDeServicioPorCita() throws Exception {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setNombre("Laura");
        cliente.setApellido("Gómez");
        cliente.setCorreo("laura.gomez@example.com");
        cliente.setDireccion("Calle 789");
        cliente.setDni("12345678");
        cliente.setTelefono("911223344");
        clienteRepo.save(cliente);

        Solicitud solicitud = new Solicitud();
        solicitud.setNombre("Mantenimiento Test");
        solicitud.setApellido("Test");
        solicitud.setCorreo("mantenimiento.test@example.com");
        solicitud.setTelefono("911223344");
        solicitud.setTipoServicio("Mantenimiento");
        solicitud.setDescripcion("Mantenimiento de equipo");
        solicitud.setEstado("Pendiente");
        solicitudRepo.save(solicitud);

        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Andrés");
        tecnico.setApellido("Ramírez");
        tecnico.setCorreo("andres.ramirez@example.com");
        tecnico.setDni("11223344");
        tecnico.setDireccion("Av. Ejemplo 321");
        tecnico.setFoto("foto.jpg");
        tecnicoRepo.save(tecnico);

        Cita cita = new Cita();
        cita.setCliente(cliente);
        cita.setSolicitud(solicitud);
        cita.setTecnico(tecnico);
        cita.setNumAl("C125");
        cita.setFecha(LocalDateTime.now());
        citaService.registrar(cita);

        TipoServicio tipoServicio1 = new TipoServicio();
        tipoServicio1.setNombre("Servicio 1");
        tipoServicio1.setDescripcion("Reparación 1");
        tipoServicioRepo.save(tipoServicio1);

        TipoServicio tipoServicio2 = new TipoServicio();
        tipoServicio2.setNombre("Servicio 2");
        tipoServicio2.setDescripcion("Reparación 2");
        tipoServicioRepo.save(tipoServicio2);

        citaTipoServicioRepo.registrar(cita.getIdCita(), tipoServicio1.getIdTipoServicio());
        citaTipoServicioRepo.registrar(cita.getIdCita(), tipoServicio2.getIdTipoServicio());

        // Act
        List<CitaTiposervicio> tiposDeServicio = citaTipoServicioService.listarTipoServicioPorCita(cita.getIdCita());

        // Assert
        Assertions.assertFalse(tiposDeServicio.isEmpty());
        Assertions.assertEquals(2, tiposDeServicio.size());
        Assertions.assertEquals("Servicio 1", tiposDeServicio.get(0).getTiposervicio().getNombre());
        Assertions.assertEquals("Servicio 2", tiposDeServicio.get(1).getTiposervicio().getNombre());
    }


}
