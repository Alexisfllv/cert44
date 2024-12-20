package edu.pe.serviciomjcert.dto;


import javax.validation.constraints.NotNull;

public class ClienteDTO {


    private Integer idCliente;

    @NotNull
    private String nombreCliente;

    @NotNull
    private String apellidoCliente;

    @NotNull
    private String correoCliente;

    @NotNull
    private String direccionCliente;

    @NotNull
    private String dniCliente;

    @NotNull
    private String telefonoCliente;


    //gts


    public ClienteDTO() {
    }

    public ClienteDTO(String apellidoCliente, String correoCliente, String direccionCliente, String dniCliente, Integer idCliente, String nombreCliente, String telefonoCliente) {
        this.apellidoCliente = apellidoCliente;
        this.correoCliente = correoCliente;
        this.direccionCliente = direccionCliente;
        this.dniCliente = dniCliente;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
    }

    public @NotNull String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(@NotNull String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public @NotNull String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(@NotNull String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public @NotNull String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(@NotNull String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public @NotNull String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(@NotNull String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public @NotNull String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(@NotNull String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public @NotNull String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(@NotNull String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }
}
