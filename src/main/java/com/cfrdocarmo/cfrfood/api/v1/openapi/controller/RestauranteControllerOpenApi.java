package com.cfrdocarmo.cfrfood.api.v1.openapi.controller;

import com.cfrdocarmo.cfrfood.api.exceptionHandler.Problem;
import com.cfrdocarmo.cfrfood.api.v1.model.RestauranteApenasNomeModel;
import com.cfrdocarmo.cfrfood.api.v1.model.RestauranteBasicoModel;
import com.cfrdocarmo.cfrfood.api.v1.model.RestauranteModel;
import com.cfrdocarmo.cfrfood.api.v1.model.input.RestauranteInput;
import com.cfrdocarmo.cfrfood.api.v1.openapi.model.RestauranteBasicoModelOpenApi;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

    @ApiOperation(value = "Lista restaurantes", response = RestauranteBasicoModelOpenApi.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nome da projeção de pedidos",
                    allowableValues = "apenas-nome",
                    name = "projecao",
                    paramType = "query",
                    type = "string"
            )
    })
//    @JsonView(RestauranteView.Resumo.class)
    public CollectionModel<RestauranteBasicoModel> listar();

    @ApiIgnore
    @ApiOperation(value = "Lista restaurantes", hidden = true)
    public CollectionModel<RestauranteApenasNomeModel> listarApenasNomes();

    @ApiOperation("Busca um restaurante por ID")
    @ApiResponse(responseCode = "400", description = "Id do restaurante inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
    @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public RestauranteModel buscar(@ApiParam(name = "RestauranteID", value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation("Cadastra um restaurante")
    @ApiResponse(responseCode = "201", description = "Restaurante cadastrado")
    public RestauranteModel adicionar(@ApiParam(name = "Corpo", value = "Representação de um restaurante") RestauranteInput restauranteInput);

    @ApiOperation("Atualiza um restaurante")
    @ApiResponse(responseCode = "200", description = "Restaurante atualizado")
    @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema( implementation = Problem.class)))
    public RestauranteModel atualizar(@ApiParam(name = "RestauranteID", value = "ID de um restaurante", example = "1") Long restauranteId,
                                      @ApiParam(name = "Corpo", value = "Representação de um restaurante") RestauranteInput restauranteInput);

    @ApiOperation("Ativa um restaurante por ID")
    @ApiResponse(responseCode = "204", description = "Restaurante ativado com sucesso")
    @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema( implementation = Problem.class)))
    public ResponseEntity<Void> ativar(@ApiParam(name = "RestauranteID", value = "ID de um restaurante", required = true, example = "1") Long restauranteId);

    @ApiOperation("Inativa um restaurante por ID")
    @ApiResponse(responseCode = "204", description = "Restaurante inativado com sucesso")
    @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema( implementation = Problem.class)))
    public ResponseEntity<Void> inativar(@ApiParam(name = "RestauranteID", value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation("Ativa múltiplos restaurantes")
    @ApiResponse(responseCode = "204", description = "Restaurantes ativados com sucesso")
    public void ativarMultiplos( @ApiParam(name = "Corpo", value = "IDs de restaurantes") List<Long> restaurantesIds);

    @ApiOperation("Inativa múltiplos restaurantes")
    @ApiResponse(responseCode = "204", description = "Restaurantes inativados com sucesso")
    public void inativarMultiplos( @ApiParam(name = "Corpo", value = "IDs de restaurantes") List<Long> restaurantesIds);

    @ApiOperation("Abre um restaurante por Id")
    @ApiResponse(responseCode = "204", description = "Restaurante aberto com sucesso")
    @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema( implementation = Problem.class)))
    public ResponseEntity<Void> abrir(@ApiParam(name = "RestauranteID", value = "ID de um restaurante", example = "1") Long restauranteId);

    @ApiOperation("Fecha um restaurante por Id")
    @ApiResponse(responseCode = "204", description = "Restaurante fechado com sucesso")
    @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema( implementation = Problem.class)))
    public ResponseEntity<Void> fechar(@ApiParam(name = "RestauranteID", value = "ID de um restaurante", example = "1") Long restauranteId);

}
