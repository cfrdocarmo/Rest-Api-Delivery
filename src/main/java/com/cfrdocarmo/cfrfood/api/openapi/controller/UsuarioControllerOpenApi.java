package com.cfrdocarmo.cfrfood.api.openapi.controller;

import com.cfrdocarmo.cfrfood.api.exceptionHandler.Problem;
import com.cfrdocarmo.cfrfood.api.model.CidadeModel;
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
public interface UsuarioControllerOpenApi {

    @ApiOperation("Lista os usuários")
    public List<UsuarioModel> listar();

    @ApiOperation("Busca um usuário por ID")
    @ApiResponse(responseCode = "400", description = "ID do usuário inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public UsuarioModel buscar(@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId);

    @ApiOperation("Cadastra um usuário")
    @ApiResponse(responseCode = "201", description = "Usuário cadastrado")
    public UsuarioModel adicionar(@ApiParam(name = "corpo", value = "Representação de um novo usuário", required = true) UsuarioComSenhaInput usuarioInput);

    @ApiOperation("Atualiza um usuário por ID")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public UsuarioModel atualizar(@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId,
                                  @ApiParam(name = "corpo", value = "Representação de um usuário com os novos dados", required = true) UsuarioInput usuarioInput);

    @ApiOperation("Atualiza a senha de um usuário")
    @ApiResponse(responseCode = "204", description = "Senha alterada com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public void alterarSenha(@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId,
                             @ApiParam(name = "corpo", value = "Representação de uma nova senha", required = true) SenhaInput senha);

}