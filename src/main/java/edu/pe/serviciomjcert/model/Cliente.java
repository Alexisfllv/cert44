package edu.pe.serviciomjcert.model;

import javax.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @Column(nullable = false, length = 50)  // Limitar la longitud del nombre
    private String nombre;

    @Column(nullable = false, length = 50)  // Limitar la longitud del apellido
    private String apellido;

    @Column(nullable = false, unique = true, length = 100)  // Correo único y con longitud adecuada
    private String correo;

    @Column(nullable = false, length = 150)  // Dirección más extensa, si es necesario
    private String direccion;

    @Column(nullable = false, length = 8, unique = true)  // DNI único y de longitud adecuada
    private String dni;

    @Column(nullable = false, length = 15)  // Teléfono de longitud apropiada
    private String telefono;

    public Cliente() {
    }

    public Cliente(String apellido, String correo, String direccion, String dni, Integer idCliente, String nombre, String telefono) {
        this.apellido = apellido;
        this.correo = correo;
        this.direccion = direccion;
        this.dni = dni;
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    // Getters y Setters

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
