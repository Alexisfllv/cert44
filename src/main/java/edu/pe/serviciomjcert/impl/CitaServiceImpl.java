package edu.pe.serviciomjcert.impl;

import edu.pe.serviciomjcert.model.Cita;
import edu.pe.serviciomjcert.model.TipoServicio;
import edu.pe.serviciomjcert.repo.ICitaRepo;
import edu.pe.serviciomjcert.repo.ICitaTipoServicioRepo;
import edu.pe.serviciomjcert.service.ICitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CitaServiceImpl extends ICRUDServiceImpl <Cita,Integer> implements ICitaService {


    @Autowired
    private ICitaRepo repo;

    @Autowired
    private ICitaTipoServicioRepo ceRepo;

    @Override
    protected JpaRepository<Cita, Integer> getRepo() {
        return repo;
    }

    //Vista para controlador
    //Ejecutar el sql desde la forma global

    @Transactional
    @Override
    public Cita registrarTransaccional(Cita cita, List<TipoServicio> tipoServicios) throws Exception {
        //cd
        cita.getDetalleCita().forEach(det -> det.setCita(cita));

        repo.save(cita);

        tipoServicios.forEach(e -> ceRepo.registrar(cita.getIdCita(),e.getIdTipoServicio()));
        return cita;
    }

    //buscar dni y nombre
    @Override
    public List<Cita> buscarCita(String dni, String nombreCompleto) throws Exception {
        return repo.buscar(dni,nombreCompleto);
    }

    //buscar rango de fechas >  <
    @Override
    public List<Cita> buscarFecha(LocalDateTime fecha1, LocalDateTime fecha2) throws Exception {
        return repo.buscarFecha(fecha1,fecha2.plusDays(1));
    }




}
