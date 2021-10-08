package br.com.zup.edu.testandoacamadawebot9.carros;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureDataJpa
class CadastrarNovoCarroControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void deveCadastrarUmNovoCarro() throws Exception {
        //ambiente
        NovoCarroRequest golRequest = new NovoCarroRequest("Gol bola", "123455", "Prata", 2003);

        String request = mapper.writeValueAsString(golRequest);


        Map<String, Long> mapResponse = Map.of("id", 1L);

        String response = mapper.writeValueAsString(mapResponse);
        //comportamento
        MockHttpServletRequestBuilder chamada = post("/carros").contentType(APPLICATION_JSON).content(request);

        //verificao
        mockMvc.perform(chamada)
                .andExpect(
                        status().isCreated()
                ).andExpect(
                        redirectedUrlPattern("/carros/*")
                );
    }


}