package edu.pe.serviciomjcert.integrales.impl.users;

import edu.pe.serviciomjcert.impl.users.UsuarioServiceImpl;
import edu.pe.serviciomjcert.model.users.Rol;
import edu.pe.serviciomjcert.model.users.Usuario;
import edu.pe.serviciomjcert.repo.users.IUsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
class UsuarioServiceImplTest {

    @Autowired
    private IUsuarioRepo usuarioRepo;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Test
    void testLoadUserByUsername_UsuarioExistente() {
        // Arrange
        Rol rolAdmin = new Rol();
        rolAdmin.setIdRol(1);
        rolAdmin.setNombre("ROLE_ADMIN");
        rolAdmin.setDescripcion("Administrador del sistema");

        Rol rolUser = new Rol();
        rolUser.setIdRol(2);
        rolUser.setNombre("ROLE_USER");
        rolUser.setDescripcion("Usuario estándar");

        Usuario usuario = new Usuario();
        usuario.setUsername("juan.perez");
        usuario.setPassword("password123");
        usuario.setEnabled(true);
        usuario.setRoles(List.of(rolAdmin, rolUser));

        usuarioRepo.save(usuario);

        // Act
        UserDetails userDetails = usuarioService.loadUserByUsername("juan.perez");

        // Assert
        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals("juan.perez", userDetails.getUsername());
        Assertions.assertEquals("password123", userDetails.getPassword());
        Assertions.assertTrue(userDetails.isEnabled());
        Assertions.assertEquals(2, userDetails.getAuthorities().size());
        Assertions.assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN")));
        Assertions.assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER")));
    }

    @Test
    void testLoadUserByUsername_UsuarioNoExistente() {
        // Act & Assert
        UsernameNotFoundException exception = Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> usuarioService.loadUserByUsername("usuario.inexistente")
        );

        Assertions.assertEquals("Usuario no existe", exception.getMessage());
    }
}
