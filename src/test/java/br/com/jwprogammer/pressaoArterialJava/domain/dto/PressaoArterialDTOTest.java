package br.com.jwprogammer.pressaoArterialJava.domain.dto;

import br.com.jwprogammer.pressaoArterialJava.domain.enuns.Risco;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PressaoArterialDTOTest {

    @DisplayName("Verificar se dto consegue retonar objeto completo")
    @Test
    void testePressaoDTO(){
        var pressaoDTO = new PressaoArterialDTO("120", "80");

        var pressao = pressaoDTO.toEntity();

        assertNotNull(pressao);
        assertEquals(Risco.NORMAL, pressao.getRisco());
    }
}
