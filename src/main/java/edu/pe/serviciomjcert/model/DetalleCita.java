package edu.pe.serviciomjcert.model;



import javax.persistence.*;


@Entity
@Table
public class DetalleCita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalleCita;

    @ManyToOne
    @JoinColumn(name = "id_cita",nullable = false,foreignKey = @ForeignKey(name = "FK_detalle_cita_cita"))
    private Cita cita;


    @Column(nullable = false,length = 70)
    private String analisis;

    @Column(nullable = false,length = 70)
    private String solucion;

    //


    public DetalleCita() {
    }

    public DetalleCita(String analisis, Cita cita, Integer idDetalleCita, String solucion) {
        this.analisis = analisis;
        this.cita = cita;
        this.idDetalleCita = idDetalleCita;
        this.solucion = solucion;
    }

    public String getAnalisis() {
        return analisis;
    }

    public void setAnalisis(String analisis) {
        this.analisis = analisis;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Integer getIdDetalleCita() {
        return idDetalleCita;
    }

    public void setIdDetalleCita(Integer idDetalleCita) {
        this.idDetalleCita = idDetalleCita;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }
}
