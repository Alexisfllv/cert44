package edu.pe.serviciomjcert.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;

public class DetalleCitaDTO {

    private Integer idDetalleCita;

    @JsonIgnore
    private CitaDTO cita;

    @NotNull
    private String analisis;

    @NotNull
    private String solucion;

    //

    public String getAnalisis() {
        return analisis;
    }

    public void setAnalisis(String analisis) {
        this.analisis = analisis;
    }

    public CitaDTO getCita() {
        return cita;
    }

    public void setCita(CitaDTO cita) {
        this.cita = cita;
    }

    public Integer getIdDetalleCita() {
        return idDetalleCita;
    }

    public void setIdDetalleCita(Integer idDetalleCita) {
        this.idDetalleCita = idDetalleCita;
    }

    public @NotNull String getSolucion() {
        return solucion;
    }

    public void setSolucion(@NotNull String solucion) {
        this.solucion = solucion;
    }

    public DetalleCitaDTO(String analisis, CitaDTO cita, Integer idDetalleCita, String solucion) {
        this.analisis = analisis;
        this.cita = cita;
        this.idDetalleCita = idDetalleCita;
        this.solucion = solucion;
    }

    public DetalleCitaDTO() {
    }
}
