package com.cfrdocarmo.cfrfood.api.assembler;

import com.cfrdocarmo.cfrfood.api.model.EstadoModel;
import com.cfrdocarmo.cfrfood.api.model.FotoProdutoModel;
import com.cfrdocarmo.cfrfood.domain.model.Estado;
import com.cfrdocarmo.cfrfood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FotoProdutoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FotoProdutoModel toModel(FotoProduto foto) {
        return modelMapper.map(foto, FotoProdutoModel.class);
    }

}
