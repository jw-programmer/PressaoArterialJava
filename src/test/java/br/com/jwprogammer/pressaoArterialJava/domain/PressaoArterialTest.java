package br.com.jwprogammer.pressaoArterialJava.domain;

import br.com.jwprogammer.pressaoArterialJava.domain.enuns.Risco;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class PressaoArterialTest {

    @DisplayName("Verificar instancia de PressaoArterial")
    @Test
    void testPressaoInstance() {
        //Given
        PressaoArterial pressaoArterial = new PressaoArterial("120", "80");

        assertNotNull(pressaoArterial);
        assertNotNull(pressaoArterial.getDiastolica(), () -> "Medida diastólica não deveria ter sido nulo");
        assertNotNull(pressaoArterial.getSistolica(), () -> "Medida sistolica não ter sido nulo");
    }

    @DisplayName("Verificar o risco de pressao arterial na instacia do objeto")
    @ParameterizedTest
    @CsvFileSource(resources = "/test_csv/pressao.csv")
    void testPressoRisco(String sistolica, String diastolica, String riscoEsperado) {
        //Given
        PressaoArterial pressaoArterial = new PressaoArterial(sistolica, diastolica);
        //Then, pressaoArterial é instanciada
        assertNotNull(pressaoArterial.getRisco(), () -> "Risco deveria ser calculado automaticamente, apenas por instanciar um objeto");
        assertEquals(Risco.toEnun(Integer.parseInt(riscoEsperado)), pressaoArterial.getRisco());
    }

    @DisplayName("Verificar se comando de gerar a data da medição é chamado.")
    @Test
    void testTakeDate() {
        //Given
        PressaoArterial pressaoArterial = new PressaoArterial("120", "80");
        //When
        pressaoArterial.registrarDataMedicao();
        //Then
        assertNotNull(pressaoArterial.getDataHoraMedicao(), () -> "A data deveria ter sido instanciada quando o método do registro é chamado.");
        assertEquals(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), pressaoArterial.getDataHoraMedicao().truncatedTo(ChronoUnit.SECONDS), () -> "A medição deveria ser hoje");

    }
}