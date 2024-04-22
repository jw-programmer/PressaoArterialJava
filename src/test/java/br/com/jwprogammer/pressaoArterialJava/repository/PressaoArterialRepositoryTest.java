package br.com.jwprogammer.pressaoArterialJava.repository;

import br.com.jwprogammer.pressaoArterialJava.domain.PressaoArterial;
import br.com.jwprogammer.pressaoArterialJava.domain.dto.PressaoArterialQueryFilter;
import br.com.jwprogammer.pressaoArterialJava.domain.enuns.Risco;
import br.com.jwprogammer.pressaoArterialJava.integration.AbstractIntegrationTest;
import br.com.jwprogammer.pressaoArterialJava.repository.especifications.PressaoArterialJpaEspecifications;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        PressaoArterial pressaoTest4 = new PressaoArterial("120", "90.5");
        PressaoArterial pressaoTest5 = new PressaoArterial("140", "90");
        
        var listaDataRegistarda = Arrays.asList(pressaoTest1,pressaoTest2, pressaoTest3, pressaoTest4, pressaoTest5);
        listaDataRegistarda.stream().forEach(PressaoArterial::registrarDataMedicao);
        
        PressaoArterial pressaoTest6 = new PressaoArterial("120", "100");
        PressaoArterial pressaoTest7 = new PressaoArterial("121", "80");
        PressaoArterial pressaoTest8 = new PressaoArterial("130", "120");
        PressaoArterial pressaoTest9 = new PressaoArterial("120", "140");
        PressaoArterial pressaoTest10 = new PressaoArterial("140", "90");
        
        pressaoTest6.registrarDataMedicao(LocalDateTime.of(2023, 1, 1, 0, 0));
        pressaoTest7.registrarDataMedicao(LocalDateTime.of(2023, 1, 10, 0, 0));
        pressaoTest8.registrarDataMedicao(LocalDateTime.of(2023, 2, 5, 0, 0));
        pressaoTest9.registrarDataMedicao(LocalDateTime.of(2023, 3, 7, 0, 0));
        pressaoTest10.registrarDataMedicao(LocalDateTime.of(2023, 4, 1, 0, 0));
        
        var listaDataEspecificada = Arrays.asList(pressaoTest6, pressaoTest7, pressaoTest8, pressaoTest9, pressaoTest10);

        repo.saveAll(listaDataRegistarda);
        repo.saveAll(listaDataEspecificada);
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
    
    @DisplayName("Retornar Lista de pressoes com especificação nula.")
    @Test
    void testReturnPressaoArterialWithSpecification(){
        List<PressaoArterial> histPressao = repo.findAll(PressaoArterialJpaEspecifications.searchByFilter(null));

        assertNotNull(histPressao);
        assertTrue(!histPressao.isEmpty());
        assertEquals(10, histPressao.size());
    }
    
    @DisplayName("Retornar Lista de pressoes com determinado risco.")
    @Test
    void testReturnPressaoArterialWithSpecificationRisco(){
    	PressaoArterialQueryFilter queryFilter = new PressaoArterialQueryFilter();
    	queryFilter.setRisco(Risco.NORMAL);
        List<PressaoArterial> histPressao = repo.findAll(PressaoArterialJpaEspecifications.searchByFilter(queryFilter));

        assertNotNull(histPressao);
        assertTrue(!histPressao.isEmpty());
        assertEquals(1, histPressao.size());
    }
    
    @DisplayName("Retornar Lista de pressoes com intervalo de sistolica.")
    @Test
    void testReturnPressaoArterialWithSpecificationIntervaloSistolica(){
    	PressaoArterialQueryFilter queryFilter = new PressaoArterialQueryFilter();
    	queryFilter.setSistolicaInicial("120");
    	queryFilter.setSistolicaFinal("130");
        List<PressaoArterial> histPressao = repo.findAll(PressaoArterialJpaEspecifications.searchByFilter(queryFilter));

        assertNotNull(histPressao);
        assertTrue(!histPressao.isEmpty());
        assertEquals(8, histPressao.size());
    }
    
    @DisplayName("Retornar Lista de pressoes com inicial sistolica.")
    @Test
    void testReturnPressaoArterialWithSpecificationInicialSistolica(){
    	PressaoArterialQueryFilter queryFilter = new PressaoArterialQueryFilter();
    	queryFilter.setSistolicaInicial("130");
        List<PressaoArterial> histPressao = repo.findAll(PressaoArterialJpaEspecifications.searchByFilter(queryFilter));

        assertNotNull(histPressao);
        assertTrue(!histPressao.isEmpty());
        assertEquals(4, histPressao.size());
    }
    
    @DisplayName("Retornar Lista de pressoes com final sistolica.")
    @Test
    void testReturnPressaoArterialWithSpecificationFinalSistolica(){
    	PressaoArterialQueryFilter queryFilter = new PressaoArterialQueryFilter();
    	queryFilter.setSistolicaFinal("121");
        List<PressaoArterial> histPressao = repo.findAll(PressaoArterialJpaEspecifications.searchByFilter(queryFilter));

        assertNotNull(histPressao);
        assertTrue(!histPressao.isEmpty());
        assertEquals(6, histPressao.size());
    }
    
    @DisplayName("Retornar Lista de pressoes com intervalo de diastolica.")
    @Test
    void testReturnPressaoArterialWithSpecificationIntervaloDiastolica(){
    	PressaoArterialQueryFilter queryFilter = new PressaoArterialQueryFilter();
    	queryFilter.setDiastolicaInicial("90");
    	queryFilter.setDiastolicaFinal("100");
        List<PressaoArterial> histPressao = repo.findAll(PressaoArterialJpaEspecifications.searchByFilter(queryFilter));

        assertNotNull(histPressao);
        assertTrue(!histPressao.isEmpty());
        assertEquals(5, histPressao.size());
    }
    
    @DisplayName("Retornar Lista de pressoes com inicial diastolica.")
    @Test
    void testReturnPressaoArterialWithSpecificationInicialDiastolica(){
    	PressaoArterialQueryFilter queryFilter = new PressaoArterialQueryFilter();
    	queryFilter.setDiastolicaInicial("90.5");
        List<PressaoArterial> histPressao = repo.findAll(PressaoArterialJpaEspecifications.searchByFilter(queryFilter));

        assertNotNull(histPressao);
        assertTrue(!histPressao.isEmpty());
        assertEquals(4, histPressao.size());
    }
    
    @DisplayName("Retornar Lista de pressoes com final diastolica.")
    @Test
    void testReturnPressaoArterialWithSpecificationFinalDiastolica(){
    	PressaoArterialQueryFilter queryFilter = new PressaoArterialQueryFilter();
    	queryFilter.setDiastolicaFinal("90.5");
        List<PressaoArterial> histPressao = repo.findAll(PressaoArterialJpaEspecifications.searchByFilter(queryFilter));

        assertNotNull(histPressao);
        assertTrue(!histPressao.isEmpty());
        assertEquals(7, histPressao.size());
    }
    
    @DisplayName("Retornar Lista de pressoes com intervalo de data.")
    @Test
    void testReturnPressaoArterialWithSpecificationIntervaloData(){
    	PressaoArterialQueryFilter queryFilter = new PressaoArterialQueryFilter();
    	queryFilter.setDataHoraMedicaoInicial(LocalDateTime.of(2023, 2, 1, 0, 0));
    	queryFilter.setDataHoraMedicaoFinal(LocalDateTime.of(2023, 3, 30, 0, 0));
        List<PressaoArterial> histPressao = repo.findAll(PressaoArterialJpaEspecifications.searchByFilter(queryFilter));

        assertNotNull(histPressao);
        assertTrue(!histPressao.isEmpty());
        assertEquals(2, histPressao.size());
    }
    
    @DisplayName("Retornar Lista de pressoes com data inicial.")
    @Test
    void testReturnPressaoArterialWithSpecificationInicialData(){
    	PressaoArterialQueryFilter queryFilter = new PressaoArterialQueryFilter();
    	queryFilter.setDataHoraMedicaoInicial(LocalDateTime.of(2023, 2, 28, 0, 0));
        List<PressaoArterial> histPressao = repo.findAll(PressaoArterialJpaEspecifications.searchByFilter(queryFilter));

        assertNotNull(histPressao);
        assertTrue(!histPressao.isEmpty());
        assertEquals(7, histPressao.size());
    }
    
    @DisplayName("Retornar Lista de pressoes com data final.")
    @Test
    void testReturnPressaoArterialWithSpecificationFinalData(){
    	PressaoArterialQueryFilter queryFilter = new PressaoArterialQueryFilter();
    	queryFilter.setDataHoraMedicaoFinal(LocalDateTime.of(2023, 3, 1, 0, 0));
        List<PressaoArterial> histPressao = repo.findAll(PressaoArterialJpaEspecifications.searchByFilter(queryFilter));

        assertNotNull(histPressao);
        assertTrue(!histPressao.isEmpty());
        assertEquals(3, histPressao.size());
    }
    
    @DisplayName("Retornar Lista de pressoes com filtro cheio")
    @Test
    void testReturnPressaoArterialWithSpecificationfiltroCheio(){
    	PressaoArterialQueryFilter queryFilter = new PressaoArterialQueryFilter();
    	queryFilter.setRisco(Risco.LEVE);
    	queryFilter.setSistolicaInicial("120");
    	queryFilter.setSistolicaFinal("130");
    	queryFilter.setDiastolicaInicial("80");
    	queryFilter.setDiastolicaFinal("120");
    	queryFilter.setDataHoraMedicaoInicial(LocalDateTime.of(2023, 1, 1, 0 ,0));
    	queryFilter.setDataHoraMedicaoFinal(LocalDateTime.of(2023, 4, 1, 0, 0));
        List<PressaoArterial> histPressao = repo.findAll(PressaoArterialJpaEspecifications.searchByFilter(queryFilter));

        assertNotNull(histPressao);
        assertTrue(!histPressao.isEmpty());
        assertEquals(1, histPressao.size());
    }
}
