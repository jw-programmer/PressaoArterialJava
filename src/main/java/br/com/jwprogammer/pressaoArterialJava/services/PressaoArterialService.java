package br.com.jwprogammer.pressaoArterialJava.services;

import br.com.jwprogammer.pressaoArterialJava.domain.PressaoArterial;
import br.com.jwprogammer.pressaoArterialJava.repository.PressaoArterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PressaoArterialService {

    @Autowired
    private PressaoArterialRepository repo;

    public PressaoArterial addPressaoArterial(PressaoArterial newPressao){
        newPressao.registrarDataMedicao();
        return repo.save(newPressao);
    }

    public List<PressaoArterial> getAllPressaoArterial() {
        return repo.findAll();
    }
    
    public Page<PressaoArterial> getAllPressaoArterialPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
    	PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }
}
