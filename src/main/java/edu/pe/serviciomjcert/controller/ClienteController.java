package edu.pe.serviciomjcert.controller;

//paquetes de hateoas
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import edu.pe.serviciomjcert.dto.ClienteDTO;
import edu.pe.serviciomjcert.exception.ModeloNotFoundException;
import edu.pe.serviciomjcert.model.Cliente;
import edu.pe.serviciomjcert.service.IClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService service;

    @Autowired
    private ModelMapper mapper;

    // LISTAR
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarr() throws Exception {
        List<ClienteDTO> lista = service.listar().stream().map(p -> mapper.map(p, ClienteDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // LISTAR X ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> listarPorIdd(@PathVariable("id") Integer id) throws Exception {
        Cliente obj = service.listarPorId(id);

        //
        if (obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO : " + id);
        }

        ClienteDTO dto = mapper.map(obj, ClienteDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //
    // REGISTRAR
    @PostMapping
    public ResponseEntity<Cliente> registrarr(@Valid @RequestBody ClienteDTO dtoRequest) throws Exception {
        Cliente cliente = mapper.map(dtoRequest, Cliente.class);
        Cliente obj = service.registrar(cliente);
        // LocalHost:8080/models/5
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdCliente()).toUri();
        return ResponseEntity.created(location).build();
    }

    // MODIFICAR
    @PutMapping
    public ResponseEntity<Cliente> modificarr(@RequestBody ClienteDTO dtoRequest) throws Exception {

        Cliente obj = service.listarPorId(dtoRequest.getIdCliente());

        //
        if (obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO : " + dtoRequest.getIdCliente());
        }

        Cliente cli = mapper.map(dtoRequest, Cliente.class);
        Cliente cliente = service.modificar(cli);

        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarr(@PathVariable("id") Integer id) throws Exception {

        Cliente obj = service.listarPorId(id);

        //
        if (obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO : " + id);
        }

        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //hateoas
    @GetMapping("/hateoas/{id}")
    public EntityModel<ClienteDTO> listarHateoas(@PathVariable ("id") Integer id) throws Exception{
        Cliente obj = service.listarPorId(id);

        if (obj == null) {
            throw new ModeloNotFoundException("NO se encontro el id " + id );
        }

        ClienteDTO dto = mapper.map(obj, ClienteDTO.class);

        //lista
        EntityModel<ClienteDTO> recurso = EntityModel.of(dto);

        //atributo
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorIdd(id));
        //WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).listarPorIdd(id));
        recurso.add(link1.withRel("clientes-info"));

        return recurso;
    }


    //paginadores
    @GetMapping("/pageable")
    public ResponseEntity<Page<ClienteDTO>> listarPageable(Pageable page) throws Exception{
        Page<ClienteDTO> clientes = service.listarPageable(page).map(p -> mapper.map(p, ClienteDTO.class));

        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
}
