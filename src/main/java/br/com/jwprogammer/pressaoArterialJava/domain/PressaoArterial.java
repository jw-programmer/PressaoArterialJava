package br.com.jwprogammer.pressaoArterialJava.domain;

import br.com.jwprogammer.pressaoArterialJava.domain.enuns.Risco;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
@Getter
public class PressaoArterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal sistolica;
    private BigDecimal diastolica;
    private Risco risco;
    private LocalDate dataMedicao;
    
    private PressaoArterial() {
    	
    };

    public PressaoArterial(String sistolica, String diastolica) {
        this.diastolica = new BigDecimal(diastolica).setScale(2, RoundingMode.UP);
        this.sistolica = new BigDecimal(sistolica).setScale(2, RoundingMode.UP);

        this.risco = calcularRisco();
    }

    private Risco calcularRisco() {
        var risco = Risco.NORMAL;
        if(sistolica.compareTo(new BigDecimal("120")) <= 0 && diastolica.compareTo(new BigDecimal("80")) <= 0){
            return risco;
        }if((sistolica.compareTo(new BigDecimal("120")) > 0 && sistolica.compareTo(new BigDecimal("139.9")) <= 0)
                || (diastolica.compareTo(new BigDecimal("80")) > 0 && diastolica.compareTo(new BigDecimal("89.9")) <= 0)){
            risco =  Risco.LEVE;
        }if((sistolica.compareTo(new BigDecimal("140")) >= 0 && sistolica.compareTo(new BigDecimal("159.9")) <= 0)
                || (diastolica.compareTo(new BigDecimal("90")) >= 0 && diastolica.compareTo(new BigDecimal("100")) <= 0)){
            risco = Risco.MODERADA;
        }if(sistolica.compareTo(new BigDecimal("160")) >= 0 || diastolica.compareTo(new BigDecimal("100")) > 0) {
            risco = Risco.GRAVE;
        }

        return risco;
    }

    public void registrarDataMedicao() {
        dataMedicao = LocalDate.now();
    }
}
