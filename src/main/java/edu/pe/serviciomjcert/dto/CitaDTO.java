package edu.pe.serviciomjcert.dto;


import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class CitaDTO {

    private Integer idCita;

    @NotNull
    private ClienteDTO cliente;

    @NotNull
    private SolicitudDTO solicitud;

    @NotNull
    private TecnicoDTO tecnico;

    @NotNull
    private String numAl;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDateTime fecha;

    //relacion

    @NotNull
    private List<DetalleCitaDTO> detalleCita;


    //GTS


    public @NotNull ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(@NotNull ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public @NotNull List<DetalleCitaDTO> getDetalleCita() {
        return detalleCita;
    }

    public void setDetalleCita(@NotNull List<DetalleCitaDTO> detalleCita) {
        this.detalleCita = detalleCita;
    }

    public @NotNull LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(@NotNull LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Integer getIdCita() {
        return idCita;
    }

    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }

    public @NotNull String getNumAl() {
        return numAl;
    }

    public void setNumAl(@NotNull String numAl) {
        this.numAl = numAl;
    }

    public @NotNull SolicitudDTO getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(@NotNull SolicitudDTO solicitud) {
        this.solicitud = solicitud;
    }

    public @NotNull TecnicoDTO getTecnico() {
        return tecnico;
    }

    public void setTecnico(@NotNull TecnicoDTO tecnico) {
        this.tecnico = tecnico;
    }

    public CitaDTO(ClienteDTO cliente, List<DetalleCitaDTO> detalleCita, LocalDateTime fecha, Integer idCita, String numAl, SolicitudDTO solicitud, TecnicoDTO tecnico) {
        this.cliente = cliente;
        this.detalleCita = detalleCita;
        this.fecha = fecha;
        this.idCita = idCita;
        this.numAl = numAl;
        this.solicitud = solicitud;
        this.tecnico = tecnico;
    }

    public CitaDTO() {
    }
}
