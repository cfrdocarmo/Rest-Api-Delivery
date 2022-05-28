package com.cfrdocarmo.cfrfood.api.openapi.controller;

import com.cfrdocarmo.cfrfood.api.exceptionHandler.Problem;
import com.cfrdocarmo.cfrfood.api.model.CidadeModel;
import com.cfrdocarmo.cfrfood.api.model.FotoProdutoModel;
import com.cfrdocarmo.cfrfood.api.model.input.CidadeInput;
import com.cfrdocarmo.cfrfood.api.model.input.FotoProdutoInput;
import com.cfrdocarmo.cfrfood.domain.exception.EntidadeNaoEncontradaException;
import com.cfrdocarmo.cfrfood.domain.model.FotoProduto;
import com.cfrdocarmo.cfrfood.domain.model.Produto;
import com.cfrdocarmo.cfrfood.domain.service.FotoStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.http.HttpHeaders;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Api(tags = "Produtos")
public interface RestauranteFotoProdutoControllerOpenApi {

    @ApiOperation(value = "Atualiza a foto do produto de um restaurante")
    @ApiResponse(responseCode = "200", description = "Foto do produto atualizada")
    @ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public FotoProdutoModel atualizarFoto(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
                                          @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId,
                                          FotoProdutoInput fotoProdutoInput,
                                          @ApiParam(value = "Arquivo da foto do produto (máximo 500KB, apenas JPG e PNG)", required = true) MultipartFile arquivo) throws IOException;


    @ApiOperation(value = "Exclui a foto do produto de um restaurante")
    @ApiResponse(responseCode = "204", description = "Foto do produto excluída")
    @ApiResponse(responseCode = "400", description = "ID do restaurante ou do produto inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
    @ApiResponse(responseCode = "404", description = "Foto de produto não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    public void excluir(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
                        @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId);


    @ApiOperation(value = "Busca a foto do produto de um restaurante", produces = "image/jpeg, image/png, application/json")

    @ApiResponse(responseCode = "200",description = "OK", content = @Content(schema = @Schema(implementation = FotoProdutoModel.class), mediaType = "application/json"))
    @ApiResponse(responseCode = "200",description = "OK", content = @Content(mediaType = "image/png"))
    @ApiResponse(responseCode = "200",description = "OK", content = @Content(mediaType = "image/jpeg"))
    @ApiResponse(responseCode = "400",description = "ID do restaurante ou produto inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
    @ApiResponse(responseCode = "404",description = "Foto de produto não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))

    ResponseEntity<?> buscar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
                    Long restauranteId,
            @ApiParam(value = "ID do produto", example = "1", required = true)
                    Long produtoId,
            @ApiParam(hidden = true, required = false)
                    String acceptHeader)
            throws HttpMediaTypeNotAcceptableException;


//    @ApiOperation(value = "Busca a foto de um produto de um restaurante", hidden = true)
//    public ResponseEntity<?> servir(Long restauranteId, Long produtoId, String acceptHeader)
//            throws HttpMediaTypeNotAcceptableException;


}