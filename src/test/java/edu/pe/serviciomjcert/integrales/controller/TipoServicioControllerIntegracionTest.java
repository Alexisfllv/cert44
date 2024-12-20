package edu.pe.serviciomjcert.integrales.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.pe.serviciomjcert.dto.TipoServicioDTO;
import edu.pe.serviciomjcert.model.TipoServicio;
import edu.pe.serviciomjcert.service.ITipoServicioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;


//allls
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

//alls 2
import static org.hamcrest.Matchers.containsString; // Para verificar cadenas dentro de un header
import static org.mockito.ArgumentMatchers.any; // Para mockear cualquier argumento en Mockito
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; // Para realizar solicitudes POST
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header; // Para verificar headers en la respuesta
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; // Para verificar el código de estado HTTP

//alls 3
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


//alls 4
import static org.mockito.Mockito.when;  // Para mockear el servicio
import static org.mockito.Mockito.verify;  // Para verificar que un método fue llamado
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;



//@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class TipoServicioControllerIntegracionTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITipoServicioService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testListarr() throws Exception {
        // Crear una lista de TipoServicio (modelo) para simular la respuesta
        List<TipoServicio> listado = Arrays.asList(
                new TipoServicio(1, "Servicio 1", "desc1"),
                new TipoServicio(2, "Servicio 2", "desc2")
        );

        // Mockear el servicio para devolver la lista de TipoServicio
        when(service.listar()).thenReturn(listado);

        // Realizar la solicitud GET y verificar la respuesta
        mockMvc.perform(get("/tiposervicios"))  // Ajusta la ruta a tu controlador
                .andExpect(status().isOk())  // Verificar que la respuesta sea OK (200)
                .andExpect(jsonPath("$", hasSize(2)))  // Verificar que la lista tenga 2 elementos
                .andExpect(jsonPath("$[0].idTipoServicio", is(1)))  // Verificar el id del primer servicio
                .andExpect(jsonPath("$[0].nombreTipoServicio", is("Servicio 1")))  // Verificar nombre del primer servicio
                .andExpect(jsonPath("$[0].descripcionTipoServicio", is("desc1")))  // Verificar descripcion del primer servicio
                .andExpect(jsonPath("$[1].idTipoServicio", is(2)))  // Verificar el id del segundo servicio
                .andExpect(jsonPath("$[1].nombreTipoServicio", is("Servicio 2")))  // Verificar nombre del segundo servicio
                .andExpect(jsonPath("$[1].descripcionTipoServicio", is("desc2")));  // Verificar descripcion del segundo servicio
    }

    //listar x id
    @Test
    public void testListarPorId_Encontrado() throws Exception {
        // Crear un objeto TipoServicio (modelo) simulado para el ID 1
        TipoServicio tipoServicio = new TipoServicio(1, "Servicio 1", "Descripcion del servicio 1");

        // Mockear el servicio para devolver un TipoServicio cuando se busca por ID
        when(service.listarPorId(1)).thenReturn(tipoServicio);

        // Realizar la solicitud GET con el ID y verificar la respuesta
        mockMvc.perform(get("/tiposervicios/{id}", 1))  // Ajusta la ruta a tu controlador
                .andExpect(status().isOk())  // Verificar que la respuesta sea OK (200)
                .andExpect(jsonPath("$.idTipoServicio", is(1)))  // Verificar el id del servicio
                .andExpect(jsonPath("$.nombreTipoServicio", is("Servicio 1")))  // Verificar nombre
                .andExpect(jsonPath("$.descripcionTipoServicio", is("Descripcion del servicio 1")));  // Verificar descripcion
    }

    @Test
    public void testListarPorId_NoEncontrado() throws Exception {
        // Mockear el servicio para devolver null cuando se busca por un ID no válido
        when(service.listarPorId(999)).thenReturn(null);

        // Realizar la solicitud GET con el ID y verificar que lance una excepción de tipo 404
        mockMvc.perform(get("/tiposervicios/{id}", 999))  // Ajusta la ruta a tu controlador
                .andExpect(status().isNotFound())  // Verificar que la respuesta sea NOT_FOUND (404)
                .andExpect(jsonPath("$.mensaje", is("ID NO ENCONTRADO : 999")))  // Verificar el mensaje de la excepción
                .andExpect(jsonPath("$.detalles", is("uri=/tiposervicios/999")));  // Verificar el detalle de la excepción
    }


    @Test
    public void testRegistrar() throws Exception {
        // Crear el DTO de entrada simulado
        TipoServicioDTO dtoRequest = new TipoServicioDTO();
        dtoRequest.setIdTipoServicio(null); // ID es generado por la base de datos
        dtoRequest.setNombreTipoServicio("Nuevo Servicio");
        dtoRequest.setDescripcionTipoServicio("Descripción del nuevo servicio");

        // Crear el objeto simulado que devuelve el servicio después de registrar
        TipoServicio tipoServicioRegistrado = new TipoServicio();
        tipoServicioRegistrado.setIdTipoServicio(1); // ID generado
        tipoServicioRegistrado.setNombre("Nuevo Servicio");
        tipoServicioRegistrado.setDescripcion("Descripción del nuevo servicio");

        // Mockear el servicio para devolver el objeto registrado
        when(service.registrar(any(TipoServicio.class))).thenReturn(tipoServicioRegistrado);

        // Realizar la solicitud POST y verificar la respuesta
        mockMvc.perform(post("/tiposervicios")  // Ajusta la ruta a tu controlador
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoRequest)))  // Convertir DTO a JSON
                .andExpect(status().isCreated())  // Verificar que la respuesta sea 201 Created
                .andExpect(header().exists("Location"))  // Verificar que existe la cabecera Location
                .andExpect(header().string("Location", containsString("/tiposervicios/1")));  // Verificar que la cabecera incluye la URI del recurso
    }

    //p1 modificar
    // Test cuando el ID existe
    @Test
    public void testModificarr_Existente() throws Exception {
        // Crear un objeto DTO con datos a modificar
        TipoServicioDTO dtoRequest = new TipoServicioDTO("Nueva Descripción",1 , "Nuevo Servicio");

        // Crear un objeto TipoServicio simulado que debe devolverse al modificarse
        TipoServicio tipoServicioExistente = new TipoServicio(1, "Servicio Original", "Descripción Original");

        // Mockear el servicio para devolver el objeto existente al buscar por ID
        when(service.listarPorId(1)).thenReturn(tipoServicioExistente);
        // Mockear la modificación
        when(service.modificar(any(TipoServicio.class))).thenReturn(tipoServicioExistente);

        // Realizar la solicitud PUT con el DTO y verificar la respuesta
        mockMvc.perform(put("/tiposervicios")  // Ajusta la ruta según tu controlador
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoRequest)))  // Convertir DTO a JSON
                .andExpect(status().isOk())  // Verificar que la respuesta sea OK (200)
                .andExpect(jsonPath("$.idTipoServicio", is(1)))  // Verificar el ID del servicio modificado
                .andExpect(jsonPath("$.nombre", is("Servicio Original")))  // Verificar el nombre (original)
                .andExpect(jsonPath("$.descripcion", is("Descripción Original")));  // Verificar la descripción (original)
    }

    //pt2 modificar
    // Test cuando el ID no existe
    @Test
    public void testModificarr_NoEncontrado() throws Exception {
        // Crear un objeto DTO con datos a modificar
        TipoServicioDTO dtoRequest = new TipoServicioDTO("Descripción Inexistente", 999, "Servicio Inexistente");

        // Mockear el servicio para devolver null cuando se busca un ID no válido
        when(service.listarPorId(999)).thenReturn(null);

        // Realizar la solicitud PUT con el DTO y verificar que lance una excepción de tipo 404
        mockMvc.perform(put("/tiposervicios")  // Ajusta la ruta según tu controlador
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoRequest)))  // Convertir DTO a JSON
                .andExpect(status().isNotFound())  // Verificar que la respuesta sea NOT_FOUND (404)
                .andExpect(jsonPath("$.mensaje", is("ID NO ENCONTRADO : 999")));  // Verificar el mensaje de la excepción
    }


    //
    @Test
    public void testEliminar() throws Exception {
        // Mockear el servicio para que devuelva un objeto cuando se busque por ID
        TipoServicio tipoServicio = new TipoServicio(1, "Servicio 1", "Descripción del servicio");
        when(service.listarPorId(1)).thenReturn(tipoServicio);

        // Realizar la solicitud DELETE y verificar que la respuesta sea NO_CONTENT (204)
        mockMvc.perform(delete("/tiposervicios/{id}", 1))  // Ajusta la ruta de acuerdo al controlador
                .andExpect(status().isNoContent());  // Verificar que la respuesta sea 204 No Content

        // Verificar que el servicio eliminar() se haya llamado correctamente
        verify(service, times(1)).eliminar(1);
    }

    @Test
    public void testEliminar_NoEncontrado() throws Exception {
        // Mockear el servicio para devolver null cuando se busca un ID no existente
        when(service.listarPorId(999)).thenReturn(null);

        // Realizar la solicitud DELETE y verificar que lance una excepción de tipo 404
        mockMvc.perform(delete("/tiposervicios/{id}", 999))  // Ajusta la ruta de acuerdo al controlador
                .andExpect(status().isNotFound())  // Verificar que la respuesta sea NOT_FOUND (404)
                .andExpect(jsonPath("$.mensaje", is("ID NO ENCONTRADO : 999")))  // Verificar el mensaje de la excepción
                .andExpect(jsonPath("$.detalles", containsString("uri=/tiposervicios/999")));  // Verificar que la ruta esté en los detalles
    }



}
