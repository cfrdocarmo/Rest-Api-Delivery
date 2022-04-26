package com.cfrdocarmo.cfrfood.api.assembler;

import com.cfrdocarmo.cfrfood.api.model.CozinhaModel;
import com.cfrdocarmo.cfrfood.api.model.RestauranteModel;
import com.cfrdocarmo.cfrfood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestauranteModel toModel(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteModel.class);
    }

    public List<RestauranteModel> toCollectionsModel(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map( restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }

}
