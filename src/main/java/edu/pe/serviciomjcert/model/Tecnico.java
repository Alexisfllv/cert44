package edu.pe.serviciomjcert.model;

import javax.persistence.*;

@Entity
public class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTecnico;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(nullable = false)
    private String foto;

    @Column(nullable = false, unique = true, length = 8)
    private String dni;

    @Column(nullable = false, length = 255)
    private String direccion;

    @Column(nullable = false, unique = true, length = 150)
    private String correo;

    // Constructor sin parámetros
    public Tecnico() {
    }

    // Constructor con todos los parámetros
    public Tecnico(Integer idTecnico, String nombre, String apellido, String foto, String dni, String direccion, String correo) {
        this.idTecnico = idTecnico;
        this.nombre = nombre;
        this.apellido = apellido;
        this.foto = foto;
        this.dni = dni;
        this.direccion = direccion;
        this.correo = correo;
    }

    // Getters y setters
    public Integer getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(Integer idTecnico) {
        this.idTecnico = idTecnico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
