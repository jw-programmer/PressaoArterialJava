package br.com.jwprogammer.pressaoArterialJava.domain.dto;

import br.com.jwprogammer.pressaoArterialJava.domain.PressaoArterial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PressaoArterialDTO {
    @NonNull
    private String sistolica;

    @NonNull
    private String diastolica;

    public PressaoArterial toEntity(){
        return new PressaoArterial(sistolica, diastolica);
    }
}
