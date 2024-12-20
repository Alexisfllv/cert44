package edu.pe.serviciomjcert.repo;

import edu.pe.serviciomjcert.model.Cita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ICitaRepo extends IGenericRepo<Cita,Integer>{

    //JPQL Java Persistence Query Language
    //Cliente -> Cita
    @Query("FROM Cita c WHERE c.cliente.dni = :dni OR LOWER(c.cliente.nombre) LIKE %:nombreCompleto% OR LOWER(c.cliente.apellido) LIKE %:nombreCompleto%")
    List<Cita> buscar(@Param("dni") String dni, String nombreCompleto);


    // DATE -  DATE
    // >=    -    <
    // >=19-04-2022  <25-04-2022
    // BUSQUEDA ENTRE FECHAS PARA LISTA DE CITAS
    @Query("FROM Cita c WHERE c.fecha BETWEEN :fechaConsulta1  AND  :fechaConsulta2")
    List<Cita> buscarFecha(@Param("fechaConsulta1") LocalDateTime fechaConsulta1, @Param("fechaConsulta2") LocalDateTime fechaConsulta2);

    //Procedimiento almacenado para reportes cantidad/fecha
    //sql
    @Query(value = "select * from fn_listarResumen()", nativeQuery =  true)
    List<Object[]> listarResumen();



}
