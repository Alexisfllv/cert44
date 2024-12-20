package edu.pe.serviciomjcert.integrales.impl;


import edu.pe.serviciomjcert.impl.CitaServiceImpl;
import edu.pe.serviciomjcert.impl.TipoServicioServiceImpl;
import edu.pe.serviciomjcert.model.*;
import edu.pe.serviciomjcert.repo.*;
import edu.pe.serviciomjcert.repo.users.IUsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CitaServiceImplIntegrationTest {

    @Autowired
    private CitaServiceImpl citaService;

    @Autowired
    private ICitaRepo citaRepo;

    @Autowired
    private IClienteRepo clienteRepo;

    @Autowired
    private ISolicitudRepo solicitudRepo;

    @Autowired
    private ITecnicoRepo tecnicoRepo;

    @Autowired
    private ITipoServicioRepo tipoServicioRepo;

    @Autowired
    private TipoServicioServiceImpl tipoServicioService;

    //
    //@Autowired
    //private ICitaTipoServicioRepo ceRepo;

    @Autowired
    private CitaServiceImpl ceRepo;

    @Test
    void testRegistrar() throws Exception {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setCorreo("juan.perezz@example.com");
        cliente.setDireccion("Calle 123");
        cliente.setDni("11111111");
        cliente.setTelefono("222222222");
        clienteRepo.save(cliente);

        Solicitud solicitud = new Solicitud();
        solicitud.setNombre("Servicio Test");
        solicitud.setApellido("Test");
        solicitud.setCorreo("servicio.tests@example.com");
        solicitud.setTelefono("999999999");
        solicitud.setTipoServicio("Instalación");
        solicitud.setDescripcion("Instalación de equipo");
        solicitud.setEstado("Pendiente");
        solicitudRepo.save(solicitud);

        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Carlos");
        tecnico.setApellido("Méndez");
        tecnico.setCorreo("carlos.mendezz@example.com");
        tecnico.setDni("88888888");
        tecnico.setDireccion("Av. Real 456");
        tecnico.setFoto("foto.jpg");
        tecnico.setCorreo("juan.cars@example.com");
        tecnicoRepo.save(tecnico);

        Cita cita = new Cita();
        cita.setCliente(cliente);
        cita.setSolicitud(solicitud);
        cita.setTecnico(tecnico);
        cita.setNumAl("A123");
        cita.setFecha(LocalDateTime.now());

        // Act
        Cita registrada = citaService.registrar(cita);

        // Assert
        Assertions.assertNotNull(registrada.getIdCita());
        Assertions.assertEquals("Juan", registrada.getCliente().getNombre());
        Assertions.assertEquals("Carlos", registrada.getTecnico().getNombre());
    }

    @Test
    void testModificar() throws Exception {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setCorreo("juan.perezz@example.com");
        cliente.setDireccion("Calle 123");
        cliente.setDni("11111111");
        cliente.setTelefono("222222222");
        clienteRepo.save(cliente);

        Solicitud solicitud = new Solicitud();
        solicitud.setNombre("Servicio Test");
        solicitud.setApellido("Test");
        solicitud.setCorreo("servicio.tests@example.com");
        solicitud.setTelefono("999999999");
        solicitud.setTipoServicio("Instalación");
        solicitud.setDescripcion("Instalación de equipo");
        solicitud.setEstado("Pendiente");
        solicitudRepo.save(solicitud);

        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Carlos");
        tecnico.setApellido("Méndez");
        tecnico.setCorreo("carlos.mendezz@example.com");
        tecnico.setDni("88888888");
        tecnico.setDireccion("Av. Real 456");
        tecnico.setFoto("foto.jpg");
        tecnico.setCorreo("juan.cars@example.com");
        tecnicoRepo.save(tecnico);

        Cita cita = new Cita();
        cita.setCliente(cliente);
        cita.setSolicitud(solicitud);
        cita.setTecnico(tecnico);
        cita.setNumAl("B456");
        cita.setFecha(LocalDateTime.now());
        Cita registrada = citaService.registrar(cita);

        // Act
        registrada.setNumAl("B457");
        Cita modificada = citaService.modificar(registrada);

        // Assert
        Assertions.assertEquals("B457", modificada.getNumAl());
    }

    @Test
    void testListar() throws Exception {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setCorreo("juan.perezz@example.com");
        cliente.setDireccion("Calle 123");
        cliente.setDni("11111111");
        cliente.setTelefono("222222222");
        clienteRepo.save(cliente);

        Solicitud solicitud = new Solicitud();
        solicitud.setNombre("Servicio Test");
        solicitud.setApellido("Test");
        solicitud.setCorreo("servicio.tests@example.com");
        solicitud.setTelefono("999999999");
        solicitud.setTipoServicio("Instalación");
        solicitud.setDescripcion("Instalación de equipo");
        solicitud.setEstado("Pendiente");
        solicitudRepo.save(solicitud);

        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Carlos");
        tecnico.setApellido("Méndez");
        tecnico.setCorreo("carlos.mendezz@example.com");
        tecnico.setDni("88888888");
        tecnico.setDireccion("Av. Real 456");
        tecnico.setFoto("foto.jpg");
        tecnico.setCorreo("juan.cars@example.com");
        tecnicoRepo.save(tecnico);

        Cita cita1 = new Cita();
        cita1.setCliente(cliente);
        cita1.setSolicitud(solicitud);
        cita1.setTecnico(tecnico);
        cita1.setNumAl("C123");
        cita1.setFecha(LocalDateTime.now());
        citaService.registrar(cita1);

        Cita cita2 = new Cita();
        cita2.setCliente(cliente);
        cita2.setSolicitud(solicitud);
        cita2.setTecnico(tecnico);
        cita2.setNumAl("C124");
        cita2.setFecha(LocalDateTime.now());
        citaService.registrar(cita2);

        // Act
        List<Cita> citas = citaService.listar();

        // Assert
        Assertions.assertFalse(citas.isEmpty());
        Assertions.assertTrue(citas.size() >= 2);
    }

    @Test
    void testListarPorId() throws Exception {
        // Arrange
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setCorreo("juan.perezz@example.com");
        cliente.setDireccion("Calle 123");
        cliente.setDni("11111111");
        cliente.setTelefono("222222222");
        clienteRepo.save(cliente);

        Solicitud solicitud = new Solicitud();
        solicitud.setNombre("Servicio Test");
        solicitud.setApellido("Test");
        solicitud.setCorreo("servicio.tests@example.com");
        solicitud.setTelefono("999999999");
        solicitud.setTipoServicio("Instalación");
        solicitud.setDescripcion("Instalación de equipo");
        solicitud.setEstado("Pendiente");
        solicitudRepo.save(solicitud);

        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Carlos");
        tecnico.setApellido("Méndez");
        tecnico.setCorreo("carlos.mendezz@example.com");
        tecnico.setDni("88888888");
        tecnico.setDireccion("Av. Real 456");
        tecnico.setFoto("foto.jpg");
        tecnico.setCorreo("juan.cars@example.com");
        tecnicoRepo.save(tecnico);

        Cita cita = new Cita();
        cita.setCliente(cliente);
        cita.setSolicitud(solicitud);
        cita.setTecnico(tecnico);
        cita.setNumAl("D789");
        cita.setFecha(LocalDateTime.now());
        Cita registrada = citaService.registrar(cita);

        // Act
        Cita result = citaService.listarPorId(registrada.getIdCita());

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals("D789", result.getNumAl());
    }

    @Test
    void testEliminar() throws Exception {
        // Arrange
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setCorreo("juan.perezz@example.com");
        cliente.setDireccion("Calle 123");
        cliente.setDni("11111111");
        cliente.setTelefono("222222222");
        clienteRepo.save(cliente);

        Solicitud solicitud = new Solicitud();
        solicitud.setNombre("Servicio Test");
        solicitud.setApellido("Test");
        solicitud.setCorreo("servicio.tests@example.com");
        solicitud.setTelefono("999999999");
        solicitud.setTipoServicio("Instalación");
        solicitud.setDescripcion("Instalación de equipo");
        solicitud.setEstado("Pendiente");
        solicitudRepo.save(solicitud);

        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Carlos");
        tecnico.setApellido("Méndez");
        tecnico.setCorreo("carlos.mendezz@example.com");
        tecnico.setDni("88888888");
        tecnico.setDireccion("Av. Real 456");
        tecnico.setFoto("foto.jpg");
        tecnico.setCorreo("juan.cars@example.com");
        tecnicoRepo.save(tecnico);

        Cita cita = new Cita();
        cita.setCliente(cliente);
        cita.setSolicitud(solicitud);
        cita.setTecnico(tecnico);
        cita.setNumAl("E987");
        cita.setFecha(LocalDateTime.now());
        Cita registrada = citaService.registrar(cita);

        // Act
        citaService.eliminar(registrada.getIdCita());

        // Assert
        Assertions.assertFalse(citaRepo.findById(registrada.getIdCita()).isPresent());
    }

    //registro de la transaacional

    @Test
    void testRegistrarTransaccional() throws Exception {
        // Arrange
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setCorreo("juan.perezz@example.com");
        cliente.setDireccion("Calle 123");
        cliente.setDni("11111111");
        cliente.setTelefono("222222222");
        clienteRepo.save(cliente);

        Solicitud solicitud = new Solicitud();
        solicitud.setNombre("Servicio Test");
        solicitud.setApellido("Test");
        solicitud.setCorreo("servicio.tests@example.com");
        solicitud.setTelefono("999999999");
        solicitud.setTipoServicio("Instalación");
        solicitud.setDescripcion("Instalación de equipo");
        solicitud.setEstado("Pendiente");
        solicitudRepo.save(solicitud);

        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Carlos");
        tecnico.setApellido("Méndez");
        tecnico.setCorreo("carlos.mendezz@example.com");
        tecnico.setDni("88888888");
        tecnico.setDireccion("Av. Real 456");
        tecnico.setFoto("foto.jpg");
        tecnico.setCorreo("juan.cars@example.com");
        tecnicoRepo.save(tecnico);

        TipoServicio tipoServicio = new TipoServicio();
        tipoServicio.setNombre("Mantenimiento");
        tipoServicio.setDescripcion("Mantenimiento preventivo de equipos");
        tipoServicioRepo.save(tipoServicio);

        Cita cita = new Cita();
        cita.setCliente(cliente);
        cita.setSolicitud(solicitud);
        cita.setTecnico(tecnico);
        cita.setNumAl("G999");
        cita.setFecha(LocalDateTime.now());

        // DetalleCita
        DetalleCita detalleCita = new DetalleCita();
        detalleCita.setAnalisis("dato relevante");
        detalleCita.setSolucion("Mantenimiento preventivo");
        detalleCita.setCita(cita);
        cita.setDetalleCita(List.of(detalleCita));

        // Act
        Cita registrada = citaService.registrarTransaccional(cita, List.of(tipoServicio));

        // Assert
        Assertions.assertNotNull(registrada.getIdCita());
        Assertions.assertFalse(registrada.getDetalleCita().isEmpty());
        Assertions.assertTrue(registrada.getDetalleCita().get(0).getCita().getIdCita().equals(registrada.getIdCita()));
        Assertions.assertEquals(1, citaService.listarPorId(registrada.getIdCita()).getDetalleCita().size()); // Verificar que el tipo de servicio fue registrado correctamente
    }

    //buscar cita
    @Test
    void testBuscarCita() throws Exception {
        // Arrange
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setCorreo("juan.perezz@example.com");
        cliente.setDireccion("Calle 123");
        cliente.setDni("11111111");
        cliente.setTelefono("222222222");
        clienteRepo.save(cliente);

        Solicitud solicitud = new Solicitud();
        solicitud.setNombre("Servicio Test");
        solicitud.setApellido("Test");
        solicitud.setCorreo("servicio.tests@example.com");
        solicitud.setTelefono("999999999");
        solicitud.setTipoServicio("Instalación");
        solicitud.setDescripcion("Instalación de equipo");
        solicitud.setEstado("Pendiente");
        solicitudRepo.save(solicitud);

        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Carlos");
        tecnico.setApellido("Méndez");
        tecnico.setCorreo("carlos.mendezz@example.com");
        tecnico.setDni("88888888");
        tecnico.setDireccion("Av. Real 456");
        tecnico.setFoto("foto.jpg");
        tecnico.setCorreo("juan.cars@example.com");
        tecnicoRepo.save(tecnico);

        Cita cita = new Cita();
        cita.setCliente(cliente);
        cita.setSolicitud(solicitud);
        cita.setTecnico(tecnico);
        cita.setNumAl("H888");
        cita.setFecha(LocalDateTime.now());
        citaService.registrar(cita);

        // Act
        List<Cita> citasEncontradas = citaService.buscarCita("11111111", "Juan Pérez");

        // Assert
        Assertions.assertFalse(citasEncontradas.isEmpty());
        Assertions.assertEquals(1, citasEncontradas.size());
        Assertions.assertEquals("11111111", citasEncontradas.get(0).getCliente().getDni());
        Assertions.assertEquals("Juan Pérez", citasEncontradas.get(0).getCliente().getNombre() + " " + citasEncontradas.get(0).getCliente().getApellido());
    }


    //buscar fecha

    @Test
    void testBuscarFecha() throws Exception {
        // Arrange
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setCorreo("juan.perezz@example.com");
        cliente.setDireccion("Calle 123");
        cliente.setDni("11111111");
        cliente.setTelefono("222222222");
        clienteRepo.save(cliente);

        Solicitud solicitud = new Solicitud();
        solicitud.setNombre("Servicio Test");
        solicitud.setApellido("Test");
        solicitud.setCorreo("servicio.tests@example.com");
        solicitud.setTelefono("999999999");
        solicitud.setTipoServicio("Instalación");
        solicitud.setDescripcion("Instalación de equipo");
        solicitud.setEstado("Pendiente");
        solicitudRepo.save(solicitud);

        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Carlos");
        tecnico.setApellido("Méndez");
        tecnico.setCorreo("carlos.mendezz@example.com");
        tecnico.setDni("88888888");
        tecnico.setDireccion("Av. Real 456");
        tecnico.setFoto("foto.jpg");
        tecnico.setCorreo("juan.cars@example.com");
        tecnicoRepo.save(tecnico);


        //cita 1

        Cita cita1 = new Cita();
        cita1.setCliente(cliente);
        cita1.setSolicitud(solicitud);
        cita1.setTecnico(tecnico);
        cita1.setNumAl("I777");
        cita1.setFecha(LocalDateTime.now().minusDays(5));
        citaService.registrar(cita1);

        //cita 2
        Cita cita2 = new Cita();
        cita2.setCliente(cliente);
        cita2.setSolicitud(solicitud);
        cita2.setTecnico(tecnico);
        cita2.setNumAl("I778");
        cita2.setFecha(LocalDateTime.now().minusDays(3));
        citaService.registrar(cita2);


        // Act
        List<Cita> citasEncontradas = citaService.buscarFecha(LocalDateTime.now().minusDays(6), LocalDateTime.now().minusDays(4));

        // Assert
        Assertions.assertFalse(citasEncontradas.isEmpty());
        Assertions.assertEquals(2, citasEncontradas.size());
        Assertions.assertEquals("I777", citasEncontradas.get(0).getNumAl());
    }


}