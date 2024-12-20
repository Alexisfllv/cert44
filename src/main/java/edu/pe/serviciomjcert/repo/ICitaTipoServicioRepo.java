package edu.pe.serviciomjcert.repo;

import edu.pe.serviciomjcert.model.Cita;
import edu.pe.serviciomjcert.model.CitaTiposervicio;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICitaTipoServicioRepo extends IGenericRepo<Cita,Integer> {

    //TABLA TERCIARIA

    @Modifying
    @Query(value = "INSERT INTO cita_tiposervicio(id_cita, id_tipo_servicio) VALUES (:idCita , :idTipoServicio)",
            nativeQuery =true)

    Integer registrar(@Param("idCita")Integer idCita, @Param("idTipoServicio") Integer idTipoServicio);

    //query adicional
    @Query("FROM CitaTiposervicio ct where ct.cita.idCita = :idCita")
    List<CitaTiposervicio> listarTipodeServicioporCita(@Param("idCita") Integer idCita);
}
