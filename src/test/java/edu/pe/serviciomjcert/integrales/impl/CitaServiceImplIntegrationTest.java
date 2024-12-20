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
        cliente.setCorreo("juan.perez@example.com");
        cliente.setDireccion("Calle 123");
        cliente.setDni("12312312");
        cliente.setTelefono("987654321");
        clienteRepo.save(cliente);

        Solicitud solicitud = new Solicitud();
        solicitud.setNombre("Servicio Test");
        solicitud.setApellido("Test");
        solicitud.setCorreo("servicio.test@example.com");
        solicitud.setTelefono("987654321");
        solicitud.setTipoServicio("Instalación");
        solicitud.setDescripcion("Instalación de equipo");
        solicitud.setEstado("Pendiente");
        solicitudRepo.save(solicitud);

        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Carlos");
        tecnico.setApellido("Méndez");
        tecnico.setCorreo("carlos.mendez@example.com");
        tecnico.setDni("87654321");
        tecnico.setDireccion("Av. Real 456");
        tecnico.setFoto("foto.jpg");
        tecnico.setCorreo("juan.perez@example.com");
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
        cliente.setNombre("Pedro");
        cliente.setApellido("Sánchez");
        cliente.setCorreo("pedro.sanchez@example.com");
        cliente.setDireccion("Av. Central 12");
        cliente.setDni("98765432");
        cliente.setTelefono("999999999");
        clienteRepo.save(cliente);

        Solicitud solicitud = new Solicitud();
        solicitud.setNombre("Reparación Test");
        solicitud.setApellido("Test");
        solicitud.setCorreo("reparacion.test@example.com");
        solicitud.setTelefono("999999999");
        solicitud.setTipoServicio("Reparación");
        solicitud.setDescripcion("Reparación de equipo");
        solicitud.setEstado("Pendiente");
        solicitudRepo.save(solicitud);

        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Luis");
        tecnico.setApellido("Gómez");
        tecnico.setCorreo("luis.gomez@example.com");
        tecnico.setDni("45678901");
        tecnico.setDireccion("Calle Test 123");
        tecnico.setFoto("foto.jpg");
        tecnico.setCorreo("juan.perez@example.com");
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
        tecnico.setCorreo("juan.perez@example.com");
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
        Cliente cliente = new Cliente();
        cliente.setNombre("Ricardo");
        cliente.setApellido("Hernández");
        cliente.setCorreo("ricardo.hernandez@example.com");
        cliente.setDireccion("Av. Ejemplo 99");
        cliente.setDni("55667788");
        cliente.setTelefono("924567890");
        clienteRepo.save(cliente);

        Solicitud solicitud = new Solicitud();
        solicitud.setNombre("Inspección Test");
        solicitud.setApellido("Test");
        solicitud.setCorreo("inspeccion.test@example.com");
        solicitud.setTelefono("924567890");
        solicitud.setTipoServicio("Inspección");
        solicitud.setDescripcion("Inspección de equipos");
        solicitud.setEstado("Pendiente");
        solicitudRepo.save(solicitud);

        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Rosa");
        tecnico.setApellido("Martínez");
        tecnico.setCorreo("rosa.martinez@example.com");
        tecnico.setDni("11223345");
        tecnico.setDireccion("Calle Ejemplo 10");
        tecnico.setFoto("foto.jpg");
        tecnico.setCorreo("juan.perez@example.com");
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
        Cliente cliente = new Cliente();
        cliente.setNombre("Esteban");
        cliente.setApellido("Lopez");
        cliente.setCorreo("esteban.lopez@example.com");
        cliente.setDireccion("Calle Final 100");
        cliente.setDni("99887766");
        cliente.setTelefono("934567890");
        clienteRepo.save(cliente);

        Solicitud solicitud = new Solicitud();
        solicitud.setNombre("Revisión Test");
        solicitud.setApellido("Test");
        solicitud.setCorreo("revision.test@example.com");
        solicitud.setTelefono("934567890");
        solicitud.setTipoServicio("Revisión");
        solicitud.setDescripcion("Revisión de equipo");
        solicitud.setEstado("Pendiente");
        solicitudRepo.save(solicitud);

        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Julia");
        tecnico.setApellido("González");
        tecnico.setCorreo("julia.gonzalez@example.com");
        tecnico.setDni("22334455");
        tecnico.setDireccion("Calle 123");
        tecnico.setFoto("foto.jpg");
        tecnico.setCorreo("juan.perez@example.com");
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
        Cliente cliente = new Cliente();
        cliente.setNombre("Mario");
        cliente.setApellido("Ruiz");
        cliente.setCorreo("mario.ruiz@example.com");
        cliente.setDireccion("Av. Principal 100");
        cliente.setDni("12345678");
        cliente.setTelefono("944556677");
        clienteRepo.save(cliente);

        Solicitud solicitud = new Solicitud();
        solicitud.setNombre("Instalación Servicio");
        solicitud.setApellido("Servicio");
        solicitud.setCorreo("instalacion.servicio@example.com");
        solicitud.setTelefono("944556677");
        solicitud.setTipoServicio("Instalación");
        solicitud.setDescripcion("Instalación de nuevo equipo");
        solicitud.setEstado("Pendiente");
        solicitudRepo.save(solicitud);

        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Luis");
        tecnico.setApellido("Mora");
        tecnico.setCorreo("luis.mora@example.com");
        tecnico.setDni("87654321");
        tecnico.setDireccion("Calle Técnica 456");
        tecnico.setFoto("foto.jpg");
        tecnico.setCorreo("juan.perez@example.com");
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
        Cliente cliente = new Cliente();
        cliente.setNombre("Ana");
        cliente.setApellido("Pérez");
        cliente.setCorreo("ana.perez@example.com");
        cliente.setDireccion("Av. Libertad 100");
        cliente.setDni("98765432");
        cliente.setTelefono("933322111");
        clienteRepo.save(cliente);

        Solicitud solicitud = new Solicitud();
        solicitud.setNombre("Reparación Servicio");
        solicitud.setApellido("Servicio");
        solicitud.setCorreo("reparacion.servicio@example.com");
        solicitud.setTelefono("933322111");
        solicitud.setTipoServicio("Reparación");
        solicitud.setDescripcion("Reparación de equipo");
        solicitud.setEstado("Pendiente");
        solicitudRepo.save(solicitud);

        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Roberto");
        tecnico.setApellido("García");
        tecnico.setCorreo("roberto.garcia@example.com");
        tecnico.setDni("55554433");
        tecnico.setDireccion("Calle Taller 78");
        tecnico.setFoto("foto.jpg");
        tecnico.setCorreo("juan.perez@example.com");
        tecnicoRepo.save(tecnico);

        Cita cita = new Cita();
        cita.setCliente(cliente);
        cita.setSolicitud(solicitud);
        cita.setTecnico(tecnico);
        cita.setNumAl("H888");
        cita.setFecha(LocalDateTime.now());
        citaService.registrar(cita);

        // Act
        List<Cita> citasEncontradas = citaService.buscarCita("98765432", "Ana Pérez");

        // Assert
        Assertions.assertFalse(citasEncontradas.isEmpty());
        Assertions.assertEquals(1, citasEncontradas.size());
        Assertions.assertEquals("98765432", citasEncontradas.get(0).getCliente().getDni());
        Assertions.assertEquals("Ana Pérez", citasEncontradas.get(0).getCliente().getNombre() + " " + citasEncontradas.get(0).getCliente().getApellido());
    }


    //buscar fecha

    @Test
    void testBuscarFecha() throws Exception {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setNombre("Sofía");
        cliente.setApellido("Martínez");
        cliente.setCorreo("sofia.martinez@example.com");
        cliente.setDireccion("Calle Ejemplo 90");
        cliente.setDni("33322211");
        cliente.setTelefono("920011223");
        clienteRepo.save(cliente);

        Solicitud solicitud = new Solicitud();
        solicitud.setNombre("Inspección Servicio");
        solicitud.setApellido("Servicio");
        solicitud.setCorreo("inspeccion.servicio@example.com");
        solicitud.setTelefono("920011223");
        solicitud.setTipoServicio("Inspección");
        solicitud.setDescripcion("Inspección de equipos");
        solicitud.setEstado("Pendiente");
        solicitudRepo.save(solicitud);

        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Martín");
        tecnico.setApellido("Serrano");
        tecnico.setCorreo("martin.serrano@example.com");
        tecnico.setDni("66778899");
        tecnico.setDireccion("Av. Ejemplo 200");
        tecnico.setFoto("foto.jpg");
        tecnico.setCorreo("juan.perez@example.com");
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