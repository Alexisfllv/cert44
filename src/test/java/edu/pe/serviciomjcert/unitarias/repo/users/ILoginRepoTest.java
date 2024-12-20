package edu.pe.serviciomjcert.unitarias.repo.users;
import edu.pe.serviciomjcert.model.users.Usuario;
import edu.pe.serviciomjcert.repo.users.ILoginRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ILoginRepoTest {

    @Mock
    private ILoginRepo loginRepo;

    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        // Configuración de los objetos de prueba
        usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setUsername("juan");
        usuario.setPassword("oldpassword");
    }

    @Test
    public void testVerificarNombreUsuario() {
        // Configurar el comportamiento simulado del método verificarNombreUsuario
        when(loginRepo.verificarNombreUsuario("juan")).thenReturn(usuario);

        Usuario result = loginRepo.verificarNombreUsuario("juan");

        // Verificar que el resultado no es nulo y tiene el nombre de usuario correcto
        assertNotNull(result);
        assertEquals("juan", result.getUsername());
    }

    @Test
    public void testVerificarNombreUsuarioNoExistente() {
        // Configurar el comportamiento simulado para un usuario no encontrado
        when(loginRepo.verificarNombreUsuario("pedro")).thenReturn(null);

        Usuario result = loginRepo.verificarNombreUsuario("pedro");

        // Verificar que el resultado es nulo, ya que el usuario no existe
        assertNull(result);
    }


}
