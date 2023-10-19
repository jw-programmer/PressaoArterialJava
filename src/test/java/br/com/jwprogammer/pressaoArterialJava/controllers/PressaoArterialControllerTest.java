package br.com.jwprogammer.pressaoArterialJava.controllers;

import br.com.jwprogammer.pressaoArterialJava.domain.PressaoArterial;
import br.com.jwprogammer.pressaoArterialJava.domain.dto.PressaoArterialDTO;
import br.com.jwprogammer.pressaoArterialJava.services.PressaoArterialService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class PressaoArterialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PressaoArterialService service;

    @Autowired
    private ObjectMapper objectMapper;

    private PressaoArterial pressaoTest1;
    private List<PressaoArterial> listaPressao;

    @BeforeEach
    void setup () {
        pressaoTest1 = new PressaoArterial("120", "80");

        PressaoArterial pressaoTest1 = new PressaoArterial("120", "80");
        PressaoArterial pressaoTest2 = new PressaoArterial("121", "80");
        PressaoArterial pressaoTest3 = new PressaoArterial("130", "90");
        PressaoArterial pressaoTest4 = new PressaoArterial("120", "90");
        PressaoArterial pressaoTest5 = new PressaoArterial("140", "90");

        listaPressao = Arrays.asList(pressaoTest1,pressaoTest2, pressaoTest3, pressaoTest4, pressaoTest5);
    }

    @DisplayName("Testar requisição de criação")
    @Test
    void testCreateRequest () throws Exception {
        given(service.addPressaoArterial(any(PressaoArterial.class)))
                .willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/pressao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new PressaoArterialDTO("120", "80"))));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.sistolica", is(pressaoTest1.getSistolica().doubleValue())))
                .andExpect(jsonPath("$.diastolica", is(pressaoTest1.getDiastolica().doubleValue())))
                .andExpect(jsonPath("$.risco", is(pressaoTest1.getRisco().toString())));
    }

    @DisplayName("Testar requisição de getAll")
    @Test
    void testFindAllRequest () throws Exception {

        when(service.getAllPressaoArterial()).thenReturn(listaPressao);

        ResultActions response = mockMvc.perform(get("/pressao"));

        response
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(5)));
    }

}
