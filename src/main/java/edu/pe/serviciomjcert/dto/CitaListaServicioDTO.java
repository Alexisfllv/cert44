package edu.pe.serviciomjcert.dto;


import javax.validation.constraints.NotNull;
import java.util.List;

public class CitaListaServicioDTO {

    //
    @NotNull
    private CitaDTO cita;

    @NotNull
    private List<TipoServicioDTO> lstTipoServicio;

    //


    public CitaDTO getCita() {
        return cita;
    }

    public void setCita(CitaDTO cita) {
        this.cita = cita;
    }

    public @NotNull List<TipoServicioDTO> getLstTipoServicio() {
        return lstTipoServicio;
    }

    public void setLstTipoServicio(@NotNull List<TipoServicioDTO> lstTipoServicio) {
        this.lstTipoServicio = lstTipoServicio;
    }
}
