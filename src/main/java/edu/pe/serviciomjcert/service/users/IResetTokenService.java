package edu.pe.serviciomjcert.service.users;

import edu.pe.serviciomjcert.model.users.ResetToken;

public interface IResetTokenService {

    ResetToken findByToken(String token);

    void guardar(ResetToken token);

    void eliminar(ResetToken token);
}
