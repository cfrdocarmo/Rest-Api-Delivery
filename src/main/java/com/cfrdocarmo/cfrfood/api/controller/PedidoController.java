package com.cfrdocarmo.cfrfood.api.controller;

import com.cfrdocarmo.cfrfood.api.assembler.PedidoInputDisassembler;
import com.cfrdocarmo.cfrfood.api.assembler.PedidoModelAssembler;
import com.cfrdocarmo.cfrfood.api.assembler.PedidoResumoModelAssembler;
import com.cfrdocarmo.cfrfood.api.model.PedidoModel;
import com.cfrdocarmo.cfrfood.api.model.PedidoResumoModel;
import com.cfrdocarmo.cfrfood.api.model.input.PedidoInput;
import com.cfrdocarmo.cfrfood.domain.exception.EntidadeNaoEncontradaException;
import com.cfrdocarmo.cfrfood.domain.exception.NegocioException;
import com.cfrdocarmo.cfrfood.domain.model.Pedido;
import com.cfrdocarmo.cfrfood.domain.model.Usuario;
import com.cfrdocarmo.cfrfood.domain.repository.PedidoRepository;
import com.cfrdocarmo.cfrfood.domain.repository.filter.PedidoFilter;
import com.cfrdocarmo.cfrfood.domain.service.EmissaoPedidoService;
import com.cfrdocarmo.cfrfood.infrastructure.repository.spec.PedidoSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private EmissaoPedidoService emissaoPedido;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;


//    @GetMapping
//    public MappingJacksonValue listar(@RequestParam(required = false) String campos) {
//        List<Pedido> pedidos = pedidoRepository.findAll();
//        List<PedidoResumoModel> pedidosModel = pedidoResumoModelAssembler.toCollectionModel(pedidos);
//
//        MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosModel);
//
//        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
//
//        pedidosWrapper.setFilters(filterProvider);
//        filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());
//
//        if(StringUtils.isNotBlank(campos)) {
//            filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
//        }
//
//        return pedidosWrapper;
//    }

    @GetMapping
    public List<PedidoResumoModel> pesquisar(PedidoFilter filtro) {
        List<Pedido> todosPedidos = pedidoRepository.findAll(PedidoSpec.usandoFiltro(filtro));

        return pedidoResumoModelAssembler.toCollectionModel(todosPedidos);
    }

    @GetMapping("/{codigoPedido}")
    public PedidoModel buscar(@PathVariable String codigoPedido) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);

        return pedidoModelAssembler.toModel(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@RequestBody @Valid PedidoInput pedidoInput) {
        try{
            Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

            //TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedido.emitir(novoPedido);

            return pedidoModelAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }



    }




}
