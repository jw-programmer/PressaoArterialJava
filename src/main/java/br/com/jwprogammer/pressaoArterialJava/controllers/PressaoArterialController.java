package br.com.jwprogammer.pressaoArterialJava.controllers;

import br.com.jwprogammer.pressaoArterialJava.domain.PressaoArterial;
import br.com.jwprogammer.pressaoArterialJava.domain.dto.PressaoArterialDTO;
import br.com.jwprogammer.pressaoArterialJava.services.PressaoArterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/pressao")
public class PressaoArterialController {
    @Autowired
    private PressaoArterialService service;

    @GetMapping
    @CrossOrigin
    public ResponseEntity<List<PressaoArterial>> getAllPressoesArteriais() {
        var pressoes = service.getAllPressaoArterial();
        return ResponseEntity.ok().body(pressoes);
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<PressaoArterial> createPressaoArterial(@RequestBody PressaoArterialDTO dto) {
        var newPressao = service.addPressaoArterial(dto.toEntity());
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPressao.getId()).toUri();
        return ResponseEntity.created(uri).body(newPressao);
    }
}
