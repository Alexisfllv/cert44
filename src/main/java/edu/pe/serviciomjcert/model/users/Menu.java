package edu.pe.serviciomjcert.model.users;

import javax.persistence.*;
import java.util.List;

@Entity
public class Menu {

    @Id
    private Integer idMenu;

    @Column(length = 20)
    private String icono;

    @Column(length = 20)
    private String nombre;

    @Column(length = 50)
    private String url;

    //
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "menu_rol",
            joinColumns = @JoinColumn(name = "id_menu",referencedColumnName = "idMenu"),
            inverseJoinColumns = @JoinColumn (name = "id_rol", referencedColumnName = "idRol"))
    private List<Rol> roles;


    //
    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //


    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }
}