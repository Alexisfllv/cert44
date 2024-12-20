package edu.pe.serviciomjcert.dto;

public class CitaServicioDTO {

    //cita - tipo servicio
    private CitaDTO cita;

    private TipoServicioDTO tipoServicio;

    public CitaDTO getCita() {
        return cita;
    }

    public void setCita(CitaDTO cita) {
        this.cita = cita;
    }

    public TipoServicioDTO getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicioDTO tipoServicio) {
        this.tipoServicio = tipoServicio;
    }
}
