package edu.pe.serviciomjcert.integrales.impl.users;


import edu.pe.serviciomjcert.impl.users.LoginServiceImpl;
import edu.pe.serviciomjcert.model.users.Usuario;
import edu.pe.serviciomjcert.repo.users.ILoginRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class LoginServiceImplIntegracionTest {

    @Autowired
    private ILoginRepo loginRepo;

    @Autowired
    private LoginServiceImpl loginService;

    @Test
    void testVerificarNombreUsuario_Existe() {
        // Arrange
        // Creamos un usuario ficticio y lo guardamos en el repositorio
        Usuario usuario = new Usuario();
        usuario.setUsername("juan.perez");
        usuario.setPassword("password123");
        usuario.setEnabled(true);
        loginRepo.save(usuario);

        // Act
        // Llamamos al servicio para verificar si el usuario existe
        Usuario usuarioRecuperado = loginService.verificarNombreUsuario("juan.perez");

        // Assert
        // Verificamos que el usuario no sea nulo y que los valores sean correctos
        Assertions.assertNotNull(usuarioRecuperado);
        Assertions.assertEquals("juan.perez", usuarioRecuperado.getUsername());
        Assertions.assertTrue(usuarioRecuperado.isEnabled());
    }

    @Test
    void testVerificarNombreUsuario_NoExiste() {
        // Act
        // Llamamos al servicio con un usuario que no existe
        Usuario usuarioRecuperado = loginService.verificarNombreUsuario("noexiste");

        // Assert
        // Verificamos que el usuario sea nulo (no encontrado)
        Assertions.assertNull(usuarioRecuperado);
    }

    @Test
    void testVerificarNombreUsuario_UsuarioDeshabilitado() {
        // Arrange
        // Creamos un usuario deshabilitado y lo guardamos en el repositorio
        Usuario usuario = new Usuario();
        usuario.setUsername("ana.gomez");
        usuario.setPassword("securepass");
        usuario.setEnabled(false);
        loginRepo.save(usuario);

        // Act
        // Llamamos al servicio para verificar el usuario
        Usuario usuarioRecuperado = loginService.verificarNombreUsuario("ana.gomez");

        // Assert
        // Verificamos que el usuario recuperado no esté habilitado
        Assertions.assertNotNull(usuarioRecuperado);
        Assertions.assertEquals("ana.gomez", usuarioRecuperado.getUsername());
        Assertions.assertFalse(usuarioRecuperado.isEnabled());
    }
}
