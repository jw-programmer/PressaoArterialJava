package br.com.jwprogammer.pressaoArterialJava.repository;

import br.com.jwprogammer.pressaoArterialJava.domain.PressaoArterial;
import br.com.jwprogammer.pressaoArterialJava.integration.AbstractIntegrationTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PressaoArterialRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private PressaoArterialRepository repo;

    @BeforeEach
    void setup() {
        PressaoArterial pressaoTest1 = new PressaoArterial("120", "80");
        PressaoArterial pressaoTest2 = new PressaoArterial("121", "80");
        PressaoArterial pressaoTest3 = new PressaoArterial("130", "90");
        PressaoArterial pressaoTest4 = new PressaoArterial("120", "90");
        PressaoArterial pressaoTest5 = new PressaoArterial("140", "90");
        
        var listaDataRegistarda = Arrays.asList(pressaoTest1,pressaoTest2, pressaoTest3, pressaoTest4, pressaoTest5);
        listaDataRegistarda.stream().forEach(pressao -> pressao.registrarDataMedicao());

        repo.saveAll(listaDataRegistarda);
    }

    @DisplayName("Salvar objetos pressaoArterial")
    @Test
    void testAddPressaoArterial(){
        PressaoArterial pressaoTest = new PressaoArterial("120", "80");
        
        pressaoTest.registrarDataMedicao();
        PressaoArterial pessoaSalva = repo.save(pressaoTest);

        assertNotNull(pessoaSalva);
        assertTrue(pessoaSalva.getId() > 0);
    }

    @DisplayName("Retornar Lista de pressoes")
    @Test
    void testReturnPressaoArterial(){
        List<PressaoArterial> histPressao = repo.findAll();

        assertNotNull(histPressao);
        assertTrue(!histPressao.isEmpty());
    }
}
