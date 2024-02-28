package br.com.jwprogammer.pressaoArterialJava.domain.dto;

import java.time.LocalDate;

import br.com.jwprogammer.pressaoArterialJava.domain.enuns.Risco;
import lombok.Data;

@Data
public class PressaoArterialQueryFilter {
	private String sistolicaInicial;
	private String sistolicaFinal;
	private String diastolicaInicial;
	private String diastolicaFinal;
	private LocalDate dataMedicaoInicial;
	private LocalDate dataMedicaoFinal;
	private Risco risco;
}
