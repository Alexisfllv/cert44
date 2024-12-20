package edu.pe.serviciomjcert.model;




import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Entity
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCita;

    @ManyToOne
    @JoinColumn(name = "id_cliente",nullable = false,foreignKey = @ForeignKey(name = "FK_cita_cliente"))
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_solicitud ",nullable = false,foreignKey = @ForeignKey(name = "FK_cita_solicitud"))
    private Solicitud solicitud;

    @ManyToOne
    @JoinColumn(name = "id_tecnico",nullable = false,foreignKey = @ForeignKey(name = "FK_cita_tecnico"))
    private Tecnico tecnico;

    @Column
    private String numAl;

    @Column (nullable = false)
    private LocalDateTime fecha;

    //Visualizar detalleCita de cada cita
    //@OneToMany
    @OneToMany(mappedBy = "cita",cascade = {CascadeType.ALL},orphanRemoval = true)
    private List<DetalleCita> detalleCita;


    //


    public Cita() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleCita> getDetalleCita() {
        return detalleCita;
    }

    public void setDetalleCita(List<DetalleCita> detalleCita) {
        this.detalleCita = detalleCita;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Integer getIdCita() {
        return idCita;
    }

    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }

    public String getNumAl() {
        return numAl;
    }

    public void setNumAl(String numAl) {
        this.numAl = numAl;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    //creacion de la tercera
    @Override
    public int hashCode() {
        return Objects.hash(idCita);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cita other = (Cita) obj;
        return Objects.equals(idCita, other.idCita);
    }

}
