package com.cfrdocarmo.cfrfood.core.modelmapper;

import com.cfrdocarmo.cfrfood.api.model.EnderecoModel;
import com.cfrdocarmo.cfrfood.domain.model.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        var modelMapper = new ModelMapper();

        /*
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
                .addMapping((Restaurante::getTaxaFrete), RestauranteModel::setTaxaFrete);
        */

        var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(
                Endereco.class, EnderecoModel.class);

        enderecoToEnderecoModelTypeMap.<String>addMapping(
                enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
                (enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));

        return  modelMapper;
    }
}