package com.cfrdocarmo.cfrfood.infrastructure.service;

import com.cfrdocarmo.cfrfood.domain.filter.VendaDiariaFilter;
import com.cfrdocarmo.cfrfood.domain.model.dto.VendaDiaria;
import com.cfrdocarmo.cfrfood.domain.service.VendaQueryService;

import java.util.List;

public class VendaQueryServiceImpl implements VendaQueryService {

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter) {
        return null;
    }
}
