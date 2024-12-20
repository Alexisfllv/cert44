package edu.pe.serviciomjcert.controller;

//paquetes de hateoas
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import edu.pe.serviciomjcert.dto.CitaDTO;
import edu.pe.serviciomjcert.dto.CitaListaServicioDTO;
import edu.pe.serviciomjcert.dto.FiltroCitaDTO;
import edu.pe.serviciomjcert.exception.ModeloNotFoundException;
import edu.pe.serviciomjcert.model.Cita;
import edu.pe.serviciomjcert.model.TipoServicio;
import edu.pe.serviciomjcert.service.ICitaService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private ICitaService service;

    //@ Archivo

    @Autowired
    private ModelMapper mapper;

    // LISTAR
    @GetMapping
    public ResponseEntity<List<CitaDTO>> listarr() throws Exception {
        List<CitaDTO> lista = service.listar().stream().map(p -> mapper.map(p, CitaDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    //
    // LISTAR X ID
    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> listarPorIdd(@PathVariable("id") Integer id) throws Exception {
        Cita obj = service.listarPorId(id);
        //
        if (obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO : " + id);
        }
        CitaDTO dto = mapper.map(obj, CitaDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // registrar , transaccion
    @PostMapping
    public ResponseEntity<Void> registrar(@Valid @RequestBody CitaListaServicioDTO dto) throws Exception {
        Cita c = mapper.map(dto.getCita(), Cita.class);

        //Agregar la lista de examenes
        List<TipoServicio> tipoServicios = mapper.map(dto.getLstTipoServicio(), new TypeToken<List<TipoServicio>>() {
        }.getType());

        Cita obj = service.registrarTransaccional(c, tipoServicios);

        ///
        // LocalHost:8080/models/5
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdCita()).toUri();
        return ResponseEntity.created(location).build();
    }

    // MODIFICAR
    @PutMapping
    public ResponseEntity<Cita> modificarr(@RequestBody CitaDTO dtoRequest) throws Exception {

        Cita obj = service.listarPorId(dtoRequest.getIdCita());
        //
        if (obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO : " + dtoRequest.getIdCita());
        }
        Cita cit = mapper.map(dtoRequest, Cita.class);
        Cita citas = service.modificar(cit);

        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarr(@PathVariable("id") Integer id) throws Exception {
        Cita obj = service.listarPorId(id);
        //
        if (obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO : " + id);
        }
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // hateoas
    @GetMapping("/hateoas/{id}")
    public EntityModel<CitaDTO> listarHateoas(@PathVariable("id") Integer id) throws Exception {
        Cita obj = service.listarPorId(id);

        if (obj == null) {
            throw new ModeloNotFoundException("NO se encontro el id " + id);
        }
        CitaDTO dto = mapper.map(obj, CitaDTO.class);
        // lista
        EntityModel<CitaDTO> recurso = EntityModel.of(dto);

        // atributo
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorIdd(id));
        // WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).listarPorIdd(id));
        recurso.add(link1.withRel("cita-info"));
        return recurso;
    }

    //buscar por dni y nombre
    @PostMapping("/buscar/otros")
    public ResponseEntity<List<CitaDTO>> buscarOtro(@RequestBody FiltroCitaDTO filtro) throws Exception {
        List<Cita> consultas = new ArrayList<>();
        consultas = service.buscarCita(filtro.getDni(), filtro.getNombreCompleto());
        List<CitaDTO> citaDTOS = mapper.map(consultas, new TypeToken<List<CitaDTO>>() {}.getType());
        return new ResponseEntity<List<CitaDTO>>(citaDTOS, HttpStatus.OK);
    }

    //buscar por rango fechas
    @GetMapping("/buscar")
    public ResponseEntity<List<CitaDTO>> buscarFecha(@RequestParam(value = "fecha1") String fecha1, @RequestParam(value = "fecha2") String fecha2) throws Exception {
        List<Cita> consultas = new ArrayList<>();

        consultas = service.buscarFecha(LocalDateTime.parse(fecha1), LocalDateTime.parse(fecha2));
        List<CitaDTO> citaDTOS = mapper.map(consultas, new TypeToken<List<CitaDTO>>() {}.getType());

        return new ResponseEntity<>(citaDTOS, HttpStatus.OK);
    }


}
