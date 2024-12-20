package edu.pe.serviciomjcert.impl.users;

import edu.pe.serviciomjcert.model.users.ResetToken;
import edu.pe.serviciomjcert.repo.users.IResetTokenRepo;
import edu.pe.serviciomjcert.service.users.IResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResetTokenServiceImpl implements IResetTokenService {

    @Autowired
    private IResetTokenRepo repo;

    @Override
    public ResetToken findByToken(String token) {
        return repo.findByToken(token);
    }

    @Override
    public void guardar(ResetToken token) {
        repo.save(token);
    }

    @Override
    public void eliminar(ResetToken token) {
        repo.delete(token);
    }

}
