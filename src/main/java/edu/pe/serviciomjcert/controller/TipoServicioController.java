package edu.pe.serviciomjcert.controller;

//paquetes de hateoas
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import edu.pe.serviciomjcert.dto.TipoServicioDTO;
import edu.pe.serviciomjcert.exception.ModeloNotFoundException;
import edu.pe.serviciomjcert.model.TipoServicio;
import edu.pe.serviciomjcert.service.ITipoServicioService;
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
@RequestMapping("/tiposervicios")
public class TipoServicioController {

    @Autowired
    private ITipoServicioService service;

    @Autowired
    private ModelMapper mapper;

    // LISTAR
    @GetMapping
    public ResponseEntity<List<TipoServicioDTO>> listarr() throws Exception {
        List<TipoServicioDTO> lista = service.listar().stream().map(p -> mapper.map(p, TipoServicioDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // LISTAR X ID
    @GetMapping("/{id}")
    public ResponseEntity<TipoServicioDTO> listarPorIdd(@PathVariable("id") Integer id) throws Exception {
        TipoServicio obj = service.listarPorId(id);
        //
        if (obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO : " + id);
        }
        TipoServicioDTO dto = mapper.map(obj, TipoServicioDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // REGISTRAR
    @PostMapping
    public ResponseEntity<TipoServicio> registrarr(@Valid @RequestBody TipoServicioDTO dtoRequest) throws Exception {
        TipoServicio tip = mapper.map(dtoRequest, TipoServicio.class);
        TipoServicio obj = service.registrar(tip);
        // LocalHost:8080/models/5
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdTipoServicio()).toUri();
        return ResponseEntity.created(location).build();
    }

    // MODIFICAR
    @PutMapping
    public ResponseEntity<TipoServicio> modificarr(@RequestBody TipoServicioDTO dtoRequest) throws Exception {
        TipoServicio obj = service.listarPorId(dtoRequest.getIdTipoServicio());
        //
        if (obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO : " + dtoRequest.getIdTipoServicio());
        }
        TipoServicio tip = mapper.map(dtoRequest, TipoServicio.class);
        TipoServicio tipoServicio = service.modificar(tip);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarr(@PathVariable("id") Integer id) throws Exception {
        TipoServicio obj = service.listarPorId(id);
        //
        if (obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO : " + id);
        }
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<TipoServicioDTO> listarHateoas(@PathVariable ("id") Integer id) throws Exception{
        TipoServicio obj = service.listarPorId(id);

        if (obj == null) {
            throw new ModeloNotFoundException("NO se encontro el id " + id );
        }

        TipoServicioDTO dto = mapper.map(obj, TipoServicioDTO.class);

        //lista
        EntityModel<TipoServicioDTO> recurso = EntityModel.of(dto);

        //atributo
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorIdd(id));
        //WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).listarPorIdd(id));
        recurso.add(link1.withRel("tiposervicios-info"));

        return recurso;
    }
}
