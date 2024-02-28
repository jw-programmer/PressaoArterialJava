package br.com.jwprogammer.pressaoArterialJava.repository;

import br.com.jwprogammer.pressaoArterialJava.domain.PressaoArterial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PressaoArterialRepository extends JpaRepository<PressaoArterial, Integer>, JpaSpecificationExecutor<PressaoArterial> {

}
