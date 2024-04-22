package br.com.jwprogammer.pressaoArterialJava.domain.dto;

import java.time.LocalDateTime;

import br.com.jwprogammer.pressaoArterialJava.domain.enuns.Risco;
import lombok.Data;

@Data
public class PressaoArterialQueryFilter {
	private String sistolicaInicial;
	private String sistolicaFinal;
	private String diastolicaInicial;
	private String diastolicaFinal;
	private LocalDateTime dataHoraMedicaoInicial;
	private LocalDateTime dataHoraMedicaoFinal;
	private Risco risco;
}
