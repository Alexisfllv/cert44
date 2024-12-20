package edu.pe.serviciomjcert.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CitaTiposervicioPK implements Serializable {

    //
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "id_cita", nullable = false)
    private Cita cita;


    @ManyToOne
    @JoinColumn(name = "id_tipo_servicio", nullable = false)
    private TipoServicio tiposervicio;

    //hashCode
    @Override
    public int hashCode() {
        return Objects.hash(cita, tiposervicio);
    }

    //equals -> clase
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CitaTiposervicioPK other = (CitaTiposervicioPK) obj;
        return Objects.equals(cita, other.cita) && Objects.equals(tiposervicio, other.tiposervicio);
    }

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

    public CitaTiposervicioPK() {
    }

    public CitaTiposervicioPK(Cita cita, TipoServicio tiposervicio) {
        this.cita = cita;
        this.tiposervicio = tiposervicio;
    }
}
