package edu.pe.serviciomjcert.unitarias.repo.users;

import edu.pe.serviciomjcert.model.users.Menu;
import edu.pe.serviciomjcert.repo.users.IMenuRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class IMenuRepoTest {

    @Mock
    private IMenuRepo menuRepo;

    private Menu menu1;
    private Menu menu2;

    @BeforeEach
    public void setUp() {
        // Configuración de los objetos de prueba
        menu1 = new Menu();
        menu1.setIdMenu(1);
        menu1.setNombre("Menu 1");

        menu2 = new Menu();
        menu2.setIdMenu(2);
        menu2.setNombre("Menu 2");
    }

    @Test
    public void testListarMenuPorUsuario() {
        // Simular el comportamiento del método listarMenuPorUsuario
        when(menuRepo.listarMenuPorUsuario("juan")).thenReturn(Arrays.asList(menu1, menu2));

        List<Menu> result = menuRepo.listarMenuPorUsuario("juan");

        // Verificar que el resultado no es nulo y contiene los menús esperados
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(menu1));
        assertTrue(result.contains(menu2));
    }

    @Test
    public void testListarMenuPorUsuarioNoEncontrado() {
        // Simular el comportamiento del método listarMenuPorUsuario cuando no se encuentran menús
        when(menuRepo.listarMenuPorUsuario("pedro")).thenReturn(Arrays.asList());

        List<Menu> result = menuRepo.listarMenuPorUsuario("pedro");

        // Verificar que el resultado es una lista vacía
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}

