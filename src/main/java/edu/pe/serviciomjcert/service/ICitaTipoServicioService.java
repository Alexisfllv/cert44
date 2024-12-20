package edu.pe.serviciomjcert.service;

import edu.pe.serviciomjcert.model.CitaTiposervicio;

import java.util.List;

public interface ICitaTipoServicioService {

    //listar tipo de servicio por id cita
    List<CitaTiposervicio> listarTipoServicioPorCita(Integer idcita);
}
