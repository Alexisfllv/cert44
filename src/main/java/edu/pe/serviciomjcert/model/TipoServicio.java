package edu.pe.serviciomjcert.model;

import javax.persistence.*;

@Entity
public class TipoServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoServicio;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 255)
    private String descripcion;

    // Constructor sin parámetros
    public TipoServicio() {
    }

    // Constructor con todos los parámetros
    public TipoServicio(Integer idTipoServicio, String nombre, String descripcion) {
        this.idTipoServicio = idTipoServicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y setters
    public Integer getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(Integer idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
