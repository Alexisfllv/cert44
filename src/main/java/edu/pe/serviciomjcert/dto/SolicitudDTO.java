package edu.pe.serviciomjcert.dto;


import javax.validation.constraints.NotNull;

public class SolicitudDTO {

    private Integer idSolicitud;

    @NotNull
    private String nombreSolicitud;

    @NotNull
    private String apellidoSolicitud;

    @NotNull
    private String correoSolicitud;

    @NotNull
    private String telefonoSolicitud;

    @NotNull
    private String tipoServicioSolicitud;

    @NotNull
    private String descripcionSolicitud;

    
    private String estadoSolicitud;

    public String getApellidoSolicitud() {
        return apellidoSolicitud;
    }

    public void setApellidoSolicitud(String apellidoSolicitud) {
        this.apellidoSolicitud = apellidoSolicitud;
    }

    public String getCorreoSolicitud() {
        return correoSolicitud;
    }

    public void setCorreoSolicitud(String correoSolicitud) {
        this.correoSolicitud = correoSolicitud;
    }

    public String getDescripcionSolicitud() {
        return descripcionSolicitud;
    }

    public void setDescripcionSolicitud(String descripcionSolicitud) {
        this.descripcionSolicitud = descripcionSolicitud;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getNombreSolicitud() {
        return nombreSolicitud;
    }

    public void setNombreSolicitud(String nombreSolicitud) {
        this.nombreSolicitud = nombreSolicitud;
    }

    public String getTelefonoSolicitud() {
        return telefonoSolicitud;
    }

    public void setTelefonoSolicitud(String telefonoSolicitud) {
        this.telefonoSolicitud = telefonoSolicitud;
    }

    public String getTipoServicioSolicitud() {
        return tipoServicioSolicitud;
    }

    public void setTipoServicioSolicitud(String tipoServicioSolicitud) {
        this.tipoServicioSolicitud = tipoServicioSolicitud;
    }

    public SolicitudDTO(String apellidoSolicitud, String correoSolicitud, String descripcionSolicitud, String estadoSolicitud, Integer idSolicitud, String nombreSolicitud, String telefonoSolicitud, String tipoServicioSolicitud) {
        this.apellidoSolicitud = apellidoSolicitud;
        this.correoSolicitud = correoSolicitud;
        this.descripcionSolicitud = descripcionSolicitud;
        this.estadoSolicitud = estadoSolicitud;
        this.idSolicitud = idSolicitud;
        this.nombreSolicitud = nombreSolicitud;
        this.telefonoSolicitud = telefonoSolicitud;
        this.tipoServicioSolicitud = tipoServicioSolicitud;
    }

    public SolicitudDTO() {
    }
}
