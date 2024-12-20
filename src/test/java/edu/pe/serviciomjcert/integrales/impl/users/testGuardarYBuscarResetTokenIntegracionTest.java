package edu.pe.serviciomjcert.integrales.impl.users;
//testGuardarYBuscarResetTokenIntegracionTest

import edu.pe.serviciomjcert.impl.users.ResetTokenServiceImpl;
import edu.pe.serviciomjcert.model.users.ResetToken;
import edu.pe.serviciomjcert.model.users.Usuario;
import edu.pe.serviciomjcert.repo.users.IResetTokenRepo;
import edu.pe.serviciomjcert.repo.users.IUsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
class testGuardarYBuscarResetTokenIntegracionTest {

    @Autowired
    private IResetTokenRepo resetTokenRepo;

    @Autowired
    private ResetTokenServiceImpl resetTokenService;

    @Autowired
    private IUsuarioRepo usuarioRepo;

    @Test
    void testGuardarYBuscarResetToken() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setUsername("juan.perez");
        usuario.setPassword("password123");
        usuario.setEnabled(true);
        usuarioRepo.save(usuario);

        ResetToken token = new ResetToken();
        token.setToken("abc123xyz");
        token.setUser(usuario);
        token.setExpiracion(LocalDateTime.now().plusHours(1));

        // Act
        resetTokenService.guardar(token);
        ResetToken tokenRecuperado = resetTokenService.findByToken("abc123xyz");

        // Assert
        Assertions.assertNotNull(tokenRecuperado);
        Assertions.assertEquals("abc123xyz", tokenRecuperado.getToken());
        Assertions.assertEquals(usuario.getUsername(), tokenRecuperado.getUser().getUsername());
        Assertions.assertTrue(tokenRecuperado.getExpiracion().isAfter(LocalDateTime.now()));
    }

    @Test
    void testEliminarResetToken() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setUsername("ana.gomez");
        usuario.setPassword("securepass");
        usuario.setEnabled(true);
        usuarioRepo.save(usuario);

        ResetToken token = new ResetToken();
        token.setToken("xyz456abc");
        token.setUser(usuario);
        token.setExpiracion(LocalDateTime.now().plusHours(1));
        resetTokenService.guardar(token);

        // Act
        resetTokenService.eliminar(token);
        ResetToken tokenRecuperado = resetTokenService.findByToken("xyz456abc");

        // Assert
        Assertions.assertNull(tokenRecuperado);
    }

    @Test
    void testFindByToken_TokenNoExistente() {
        // Act
        ResetToken tokenRecuperado = resetTokenService.findByToken("inexistente");

        // Assert
        Assertions.assertNull(tokenRecuperado);
    }
}
