package edu.pe.serviciomjcert.service;

import edu.pe.serviciomjcert.model.Cita;
import edu.pe.serviciomjcert.model.TipoServicio;

import java.time.LocalDateTime;
import java.util.List;

public interface ICitaService extends ICRUD<Cita,Integer>{

    //vista desde Controlador
    Cita registrarTransaccional(Cita cita, List<TipoServicio> tipoServicios) throws Exception;

    //buscar citas x nombre y fecha
    List<Cita> buscarCita (String dni , String nombreCompleto) throws Exception;

    //buscar por rango de fecha
    List<Cita> buscarFecha(LocalDateTime fecha1 , LocalDateTime fecha2) throws Exception;

    //listar Resumen , citaResumenDTO;

    // reportes cantidad fecha



}
