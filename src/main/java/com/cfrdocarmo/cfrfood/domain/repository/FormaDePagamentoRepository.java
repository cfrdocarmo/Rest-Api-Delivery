package com.cfrdocarmo.cfrfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cfrdocarmo.cfrfood.domain.model.FormaPagamento;

@Repository
public interface FormaDePagamentoRepository extends JpaRepository<FormaPagamento, Long> {

}
