package edu.pe.serviciomjcert.repo;

import edu.pe.serviciomjcert.model.CitaTiposervicio;
import edu.pe.serviciomjcert.model.TipoServicio;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITipoServicioRepo extends IGenericRepo<TipoServicio, Integer>{



}
