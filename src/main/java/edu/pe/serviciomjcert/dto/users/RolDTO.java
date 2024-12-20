package edu.pe.serviciomjcert.dto.users;

public class RolDTO {

    private Integer idRol;
    private String nombre;
    private String descripcion;

    //

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public RolDTO(String descripcion, Integer idRol, String nombre) {
        this.descripcion = descripcion;
        this.idRol = idRol;
        this.nombre = nombre;
    }

    public RolDTO() {
    }
}
