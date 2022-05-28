package com.cfrdocarmo.cfrfood.api.openapi.controller;

import com.cfrdocarmo.cfrfood.api.exceptionHandler.Problem;
import com.cfrdocarmo.cfrfood.api.model.CidadeModel;
import com.cfrdocarmo.cfrfood.api.model.GrupoModel;
import com.cfrdocarmo.cfrfood.api.model.UsuarioModel;
import com.cfrdocarmo.cfrfood.api.model.input.CidadeInput;
import com.cfrdocarmo.cfrfood.api.model.input.SenhaInput;
import com.cfrdocarmo.cfrfood.api.model.input.UsuarioComSenhaInput;
import com.cfrdocarmo.cfrfood.api.model.input.UsuarioInput;
import com.cfrdocarmo.cfrfood.domain.model.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Usuarios")
public interface UsuarioGrupoControllerOpenApi {

    @ApiOperation("Lista os grupos associados a um usuário")
    @ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public List<GrupoModel> listar(@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId);

    @ApiOperation("Desassociação de grupo com usuário")
    @ApiResponse(responseCode = "204", description = "Associação realizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public void adicionarGrupo(@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId,
                               @ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId);

    @ApiOperation("Associação de grupo com usuário")
    @ApiResponse(responseCode = "204", description = "Desassociação realizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public void removerGrupo(@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId,
                             @ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId);

}