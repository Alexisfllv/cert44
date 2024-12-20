package edu.pe.serviciomjcert.controller;
//paquetes de hateoas
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;


import edu.pe.serviciomjcert.dto.TecnicoDTO;
import edu.pe.serviciomjcert.exception.ModeloNotFoundException;
import edu.pe.serviciomjcert.model.Tecnico;
import edu.pe.serviciomjcert.service.ITecnicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {

    @Autowired
    private ITecnicoService service;

    @Autowired
    private ModelMapper mapper;


    // LISTAR
    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> listarr() throws Exception {
        List<TecnicoDTO> lista = service.listar().stream().map(p -> mapper.map(p, TecnicoDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // LISTAR X ID
    @GetMapping("/{id}")
    public ResponseEntity<TecnicoDTO> listarPorIdd(@PathVariable("id") Integer id) throws Exception {
        Tecnico obj = service.listarPorId(id);
        //
        if (obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO : " + id);
        }
        TecnicoDTO dto = mapper.map(obj, TecnicoDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // REGISTRAR
    @PostMapping
    public ResponseEntity<Tecnico> registrarr(@Valid @RequestBody TecnicoDTO dtoRequest) throws Exception {
        Tecnico pac = mapper.map(dtoRequest, Tecnico.class);
        Tecnico obj = service.registrar(pac);
        // LocalHost:8080/models/5
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdTecnico()).toUri();
        return ResponseEntity.created(location).build();
    }

    // MODIFICAR
    @PutMapping
    public ResponseEntity<Tecnico> modificarr(@RequestBody TecnicoDTO dtoRequest) throws Exception {
        Tecnico obj = service.listarPorId(dtoRequest.getIdTecnico());
        //
        if (obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO : " + dtoRequest.getIdTecnico());
        }
        Tecnico tec = mapper.map(dtoRequest, Tecnico.class);
        Tecnico tecnico = service.modificar(tec);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarr(@PathVariable("id") Integer id) throws Exception {

        Tecnico obj = service.listarPorId(id);
        //
        if (obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO : " + id);
        }
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<TecnicoDTO> listarHateoas(@PathVariable ("id") Integer id) throws Exception{
        Tecnico obj = service.listarPorId(id);

        if (obj == null) {
            throw new ModeloNotFoundException("NO se encontro el id " + id );
        }
        TecnicoDTO dto = mapper.map(obj, TecnicoDTO.class);
        //lista
        EntityModel<TecnicoDTO> recurso = EntityModel.of(dto);
        //atributo
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorIdd(id));
        //WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).listarPorIdd(id));
        recurso.add(link1.withRel("tecnico-info"));
        return recurso;
    }
}
