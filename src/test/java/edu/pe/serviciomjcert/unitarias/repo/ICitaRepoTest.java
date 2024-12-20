package edu.pe.serviciomjcert.unitarias.repo;

import edu.pe.serviciomjcert.model.Cita;
import edu.pe.serviciomjcert.repo.ICitaRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ICitaRepoTest {

    @Mock
    private ICitaRepo citaRepo;

    private Cita cita;
    private LocalDateTime fecha1;
    private LocalDateTime fecha2;

    @BeforeEach
    public void setUp() {
        // Configuración de los objetos de prueba
        cita = new Cita();
        cita.setIdCita(1); // Definir un id de cita de prueba

        // Fechas de ejemplo para las pruebas
        fecha1 = LocalDateTime.of(2022, 4, 19, 10, 0, 0, 0);
        fecha2 = LocalDateTime.of(2022, 4, 25, 10, 0, 0, 0);
    }

    @Test
    public void testBuscar() {
        // Configurar la lista simulada para el método buscar
        List<Cita> mockedList = Arrays.asList(cita);
        when(citaRepo.buscar("12345678", "Juan Pérez")).thenReturn(mockedList);

        List<Cita> result = citaRepo.buscar("12345678", "Juan Pérez");

        // Verificar que el resultado no es nulo y contiene un solo elemento
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testBuscarFecha() {
        // Configurar la lista simulada para el método buscarFecha
        List<Cita> mockedList = Arrays.asList(cita);
        when(citaRepo.buscarFecha(fecha1, fecha2)).thenReturn(mockedList);

        List<Cita> result = citaRepo.buscarFecha(fecha1, fecha2);

        // Verificar que el resultado no es nulo y contiene un solo elemento
        assertNotNull(result);
        assertEquals(1, result.size());
    }


}

