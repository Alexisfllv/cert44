package edu.pe.serviciomjcert.controller;
import org.modelmapper.TypeToken;
import edu.pe.serviciomjcert.dto.CitaServicioDTO;
import edu.pe.serviciomjcert.model.CitaTiposervicio;
import edu.pe.serviciomjcert.service.ICitaTipoServicioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/citatiposervicios")
public class CitaTipoServicioController {

    //
    @Autowired
    private ICitaTipoServicioService service;

    @Autowired
    private ModelMapper mapper;

    //
    @GetMapping(value =  "/{idCita}")
    public ResponseEntity<List<CitaServicioDTO>> listarex(@PathVariable("idCita") Integer idcita){
        List<CitaTiposervicio> ce = new ArrayList<>();

        ce = service.listarTipoServicioPorCita(idcita);
        List<CitaServicioDTO> ceDTO = mapper.map(ce, new TypeToken<List<CitaServicioDTO>> () {}.getType());
        return new ResponseEntity<List<CitaServicioDTO>>(ceDTO, HttpStatus.OK);
    }
}
