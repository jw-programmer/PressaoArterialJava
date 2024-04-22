package br.com.jwprogammer.pressaoArterialJava.repository.especifications;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import br.com.jwprogammer.pressaoArterialJava.domain.PressaoArterial;
import br.com.jwprogammer.pressaoArterialJava.domain.dto.PressaoArterialQueryFilter;
import jakarta.persistence.criteria.Predicate;

public class PressaoArterialJpaEspecifications {
	public static Specification<PressaoArterial> searchByFilter(PressaoArterialQueryFilter filter) {
		return (root, query, criteriaBuilder) -> {
			if(Objects.isNull(filter)) {
				return null;
			}
			List<Predicate> predicates = new LinkedList<>();
			
			if(!Objects.isNull(filter.getSistolicaInicial()) && !Objects.isNull(filter.getSistolicaFinal())) {
				var pressaoIntervaloPredicate = criteriaBuilder.between(root.get("sistolica")
						, new BigDecimal(filter.getSistolicaInicial())
						, new BigDecimal(filter.getSistolicaFinal()));
				
				predicates.add(pressaoIntervaloPredicate);
			}else {
				if(!Objects.isNull(filter.getSistolicaInicial())) {
					var pressaoInicalPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("sistolica")
							, new BigDecimal(filter.getSistolicaInicial()));
					predicates.add(pressaoInicalPredicate);
				}
				if(!Objects.isNull(filter.getSistolicaFinal())) {
					var pressaoFinalPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("sistolica"),
							new BigDecimal(filter.getSistolicaFinal()));
					predicates.add(pressaoFinalPredicate);
				}
			}
			
			if(!Objects.isNull(filter.getDiastolicaInicial()) && !Objects.isNull(filter.getDiastolicaFinal())) {
				var pressaoIntervaloPredicate = criteriaBuilder.between(root.get("diastolica")
						, new BigDecimal(filter.getDiastolicaInicial())
						, new BigDecimal(filter.getDiastolicaFinal()));
				
				predicates.add(pressaoIntervaloPredicate);
			}else {
				if(!Objects.isNull(filter.getDiastolicaInicial())) {
					var pressaoInicalPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("diastolica")
							, new BigDecimal(filter.getDiastolicaInicial()));
					predicates.add(pressaoInicalPredicate);
				}
				if(!Objects.isNull(filter.getDiastolicaFinal())) {
					var pressaoFinalPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("diastolica")
							, new BigDecimal(filter.getDiastolicaFinal()));
					predicates.add(pressaoFinalPredicate);
				}
			}
			
			if(!Objects.isNull(filter.getDataHoraMedicaoInicial()) && !Objects.isNull(filter.getDataHoraMedicaoFinal())) {
				var pressaoIntervaloPredicate = criteriaBuilder.between(root.get("dataHoraMedicao")
						, filter.getDataHoraMedicaoInicial()
						, filter.getDataHoraMedicaoFinal());
				
				predicates.add(pressaoIntervaloPredicate);
			}else {
				if(!Objects.isNull(filter.getDataHoraMedicaoInicial())) {
					var pressaoInicalPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("dataHoraMedicao"), filter.getDataHoraMedicaoInicial());
					predicates.add(pressaoInicalPredicate);
				}
				if(!Objects.isNull(filter.getDataHoraMedicaoFinal())) {
					var pressaoFinalPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("dataHoraMedicao"), filter.getDataHoraMedicaoFinal());
					predicates.add(pressaoFinalPredicate);
				}
			}
			
			if(!Objects.isNull(filter.getRisco())) {
				var riscoPredicate = criteriaBuilder.equal(root.get("risco"), filter.getRisco());
				predicates.add(riscoPredicate);
			}
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
}
