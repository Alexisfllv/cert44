package edu.pe.serviciomjcert.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;


@Entity
@IdClass(CitaTiposervicioPK.class)
public class CitaTiposervicio {

    //asociaciones

    @Id
    private Cita cita;


    @Id
    private TipoServicio tiposervicio;

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public TipoServicio getTiposervicio() {
        return tiposervicio;
    }

    public void setTiposervicio(TipoServicio tiposervicio) {
        this.tiposervicio = tiposervicio;
    }

    public CitaTiposervicio(Cita cita, TipoServicio tiposervicio) {
        this.cita = cita;
        this.tiposervicio = tiposervicio;
    }

    public CitaTiposervicio() {
    }
}
