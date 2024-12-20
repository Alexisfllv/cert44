package edu.pe.serviciomjcert.dto;


import javax.validation.constraints.NotNull;

public class TipoServicioDTO {

    private Integer idTipoServicio;

    @NotNull
    private String nombreTipoServicio;

    @NotNull
    private String descripcionTipoServicio;

    public String getDescripcionTipoServicio() {
        return descripcionTipoServicio;
    }

    public void setDescripcionTipoServicio(String descripcionTipoServicio) {
        this.descripcionTipoServicio = descripcionTipoServicio;
    }

    public Integer getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(Integer idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public String getNombreTipoServicio() {
        return nombreTipoServicio;
    }

    public void setNombreTipoServicio(String nombreTipoServicio) {
        this.nombreTipoServicio = nombreTipoServicio;
    }

    public TipoServicioDTO(String descripcionTipoServicio, Integer idTipoServicio, String nombreTipoServicio) {
        this.descripcionTipoServicio = descripcionTipoServicio;
        this.idTipoServicio = idTipoServicio;
        this.nombreTipoServicio = nombreTipoServicio;
    }

    public TipoServicioDTO() {
    }
}
