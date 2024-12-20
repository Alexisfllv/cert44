package edu.pe.serviciomjcert.controller;

//paquetes de hateoas
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


import edu.pe.serviciomjcert.dto.SolicitudDTO;
import edu.pe.serviciomjcert.exception.ModeloNotFoundException;
import edu.pe.serviciomjcert.model.Solicitud;
import edu.pe.serviciomjcert.service.ISolicitudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {

    @Autowired
    private ISolicitudService service;

    @Autowired
    private ModelMapper mapper;

    // LISTAR
    @GetMapping
    public ResponseEntity<List<SolicitudDTO>> listarr() throws Exception {
        List<SolicitudDTO> lista = service.listar().stream().map(p -> mapper.map(p, SolicitudDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // LISTAR X ID
    @GetMapping("/{id}")
    public ResponseEntity<SolicitudDTO> listarPorIdd(@PathVariable("id") Integer id) throws Exception {
        Solicitud obj = service.listarPorId(id);
        //
        if (obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO : " + id);
        }
        SolicitudDTO dto = mapper.map(obj, SolicitudDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // REGISTRAR
    @PostMapping
    public ResponseEntity<Solicitud> registrarr(@Valid @RequestBody SolicitudDTO dtoRequest) throws Exception {
        Solicitud pac = mapper.map(dtoRequest, Solicitud.class);
        Solicitud obj = service.registrar(pac);
        // LocalHost:8080/models/5
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdSolicitud()).toUri();
        return ResponseEntity.created(location).build();
    }

    // MODIFICAR
    @PutMapping
    public ResponseEntity<Solicitud> modificarr(@RequestBody SolicitudDTO dtoRequest) throws Exception {

        Solicitud obj = service.listarPorId(dtoRequest.getIdSolicitud());
        //
        if (obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO : " + dtoRequest.getIdSolicitud());
        }
        Solicitud sol = mapper.map(dtoRequest, Solicitud.class);
        Solicitud solicitud = service.modificar(sol);

        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarr(@PathVariable("id") Integer id) throws Exception {
        Solicitud obj = service.listarPorId(id);
        //
        if (obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO : " + id);
        }
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //hateoas

    @GetMapping("/hateoas/{id}")
    public EntityModel<SolicitudDTO> listarHateoas(@PathVariable ("id") Integer id) throws Exception{
        Solicitud obj = service.listarPorId(id);

        if (obj == null) {
            throw new ModeloNotFoundException("NO se encontro el id " + id );
        }

        SolicitudDTO dto = mapper.map(obj, SolicitudDTO.class);

        //lista
        EntityModel<SolicitudDTO> recurso = EntityModel.of(dto);

        //atributo
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorIdd(id));
        //WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).listarPorIdd(id));
        recurso.add(link1.withRel("solicitud-info"));

        return recurso;
    }
}
