package com.cfrdocarmo.cfrfood.api.controller;

import com.cfrdocarmo.cfrfood.domain.filter.VendaDiariaFilter;
import com.cfrdocarmo.cfrfood.domain.model.dto.VendaDiaria;
import com.cfrdocarmo.cfrfood.domain.service.VendaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {

    @Autowired
    private VendaQueryService vendaQueryService;

    @GetMapping("/vendas-diarias")
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter) {
        return vendaQueryService.consultarVendasDiarias(filter);
    }

}
