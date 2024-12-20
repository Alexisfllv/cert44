package edu.pe.serviciomjcert.repo.users;

import edu.pe.serviciomjcert.model.users.ResetToken;
import edu.pe.serviciomjcert.repo.IGenericRepo;

public interface IResetTokenRepo extends IGenericRepo<ResetToken,Integer> {

    //qry
    //from resetToken rt where rt.token = : ?
    ResetToken findByToken(String token);
}
