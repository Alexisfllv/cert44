package edu.pe.serviciomjcert.controller.users;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import edu.pe.serviciomjcert.model.users.ResetToken;
import edu.pe.serviciomjcert.model.users.Usuario;
import edu.pe.serviciomjcert.service.users.ILoginService;
import edu.pe.serviciomjcert.service.users.IResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private ILoginService service;

    @Autowired
    private IResetTokenService tokenService;



    @GetMapping(value = "/restablecer/verificar/{token}")
    public ResponseEntity<Integer> verificarToken(@PathVariable("token") String token) {
        int rpta = 0;
        try {
            if (token != null && !token.isEmpty()) {
                ResetToken rt = tokenService.findByToken(token);
                if (rt != null && rt.getId() > 0) {
                    if (!rt.estaExpirado()) {
                        rpta = 1;
                    }
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
    }



}
