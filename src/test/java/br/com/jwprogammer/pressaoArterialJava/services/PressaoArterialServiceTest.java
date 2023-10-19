package br.com.jwprogammer.pressaoArterialJava.services;

import br.com.jwprogammer.pressaoArterialJava.domain.PressaoArterial;
import br.com.jwprogammer.pressaoArterialJava.repository.PressaoArterialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PressaoArterialServiceTest {

    @Mock
    private PressaoArterialRepository repo;

    @InjectMocks
    private PressaoArterialService service;

    private PressaoArterial pressaoTest1;
    @BeforeEach
    void setup() {
        pressaoTest1 = new PressaoArterial("120", "80");
    }

    @Test
    void addPressaoArterialByServiceTest() {
        when(repo.save(ArgumentMatchers.any(PressaoArterial.class))).thenReturn(pressaoTest1);
        var pressao = service.addPressaoArterial(pressaoTest1);

        assertNotNull(pressao);
        assertNotNull(pressao.getDataMedicao());
        assertEquals(LocalDate.now(), pressao.getDataMedicao());
    }

    @Test
    void returnListPressaoArterialByServiceTest() {
        when(repo.findAll()).thenReturn(Arrays.asList(
                new PressaoArterial("120", "80"),
                new PressaoArterial("121", "80"),
                new PressaoArterial("130", "90"),
                new PressaoArterial("120", "90"),
                new PressaoArterial("140", "90")
        ));
        var pressao = service.getAllPressaoArterial();

        assertNotNull(pressao);
        assertFalse(pressao.isEmpty());
        assertEquals(5, pressao.size());
    }
}
