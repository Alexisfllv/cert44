package edu.pe.serviciomjcert.unitarias.repo;

import edu.pe.serviciomjcert.model.Cita;
import edu.pe.serviciomjcert.model.CitaTiposervicio;
import edu.pe.serviciomjcert.repo.ICitaTipoServicioRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ICitaTipoServicioRepoTest {

    @Mock
    private ICitaTipoServicioRepo citaTipoServicioRepo;

    private Cita cita;
    private CitaTiposervicio citaTiposervicio;

    @BeforeEach
    public void setUp() {
        // Configuración de los objetos de prueba
        cita = new Cita();
        cita.setIdCita(1); // Definir un id de cita de prueba

        citaTiposervicio = new CitaTiposervicio();
        citaTiposervicio.setCita(cita); // Asociar la cita con CitaTiposervicio
    }

    @Test
    public void testRegistrar() {
        // Configurar el comportamiento simulado del repositorio
        when(citaTipoServicioRepo.registrar(1, 1)).thenReturn(1); // Simular que el registro retorna 1

        Integer result = citaTipoServicioRepo.registrar(1, 1);

        // Verificar que el resultado sea 1
        assertEquals(1, result);
    }

    @Test
    public void testListarTipodeServicioporCita() {
        // Configurar la lista simulada para el método listarTipodeServicioporCita
        List<CitaTiposervicio> mockedList = Arrays.asList(citaTiposervicio);
        when(citaTipoServicioRepo.listarTipodeServicioporCita(1)).thenReturn(mockedList);

        List<CitaTiposervicio> result = citaTipoServicioRepo.listarTipodeServicioporCita(1);

        // Verificar que el resultado no es nulo y contiene un solo elemento
        assertNotNull(result);
        assertEquals(1, result.size());
    }
}
