package br.com.zup.edu.testandoacamadawebot9.carros;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
class ConsultarCarroControllerTest {
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarroRepository repository;


    @BeforeEach
    void setUp() {
        repository.saveAll(getCarros());
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void deveRetornaUmCarroCasoAPlacaExista() throws Exception {
        MockHttpServletRequestBuilder chamada = get("/carros")
                .param("placa", "123")
                .contentType(APPLICATION_JSON);

        CarroResponse carroResponse = new CarroResponse(getCarros().get(0));

        String response = mapper.writeValueAsString(carroResponse);

        mockMvc.perform(chamada)
                .andExpect(
                        status().isOk()
                ).andExpect(
                        content().json(response)
                );
    }
    @Test
    void naoDeveRetornaUmCarroCasoAPlacaNaoExista() throws Exception {
        MockHttpServletRequestBuilder chamada = get("/carros")
                .param("placa", "1236758")
                .contentType(APPLICATION_JSON);


        mockMvc.perform(chamada)
                .andExpect(
                        status().isNotFound()
                );
    }
    @Test
    void deveRetornaUmaListaDeCarrosQuandoAPlacaNaoEhInformada() throws Exception {
        MockHttpServletRequestBuilder chamada = get("/carros")
                .contentType(APPLICATION_JSON);

        List<CarroResponse> carroResponses = getCarros().stream().map(CarroResponse::new).collect(Collectors.toList());

        String response = mapper.writeValueAsString(carroResponses);

        mockMvc.perform(chamada)
                .andExpect(
                        status().isOk()
                ).andExpect(
                        content().json(response)
                );
    }

    public List<Carro> getCarros() {
        return List.of(
                new Carro("rosa", "gol", "123", 2001),
                new Carro("rosa", "uno", "1234", 2021),
                new Carro("rosa", "palio", "12345", 2011)
        );
    }
}