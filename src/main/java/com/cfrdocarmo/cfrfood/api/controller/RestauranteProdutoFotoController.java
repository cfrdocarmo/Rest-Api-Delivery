package com.cfrdocarmo.cfrfood.api.controller;

import com.cfrdocarmo.cfrfood.api.assembler.FotoProdutoModelAssembler;
import com.cfrdocarmo.cfrfood.api.model.FotoProdutoModel;
import com.cfrdocarmo.cfrfood.api.model.input.FotoProdutoInput;
import com.cfrdocarmo.cfrfood.api.openapi.controller.RestauranteFotoProdutoControllerOpenApi;
import com.cfrdocarmo.cfrfood.domain.exception.EntidadeNaoEncontradaException;
import com.cfrdocarmo.cfrfood.domain.model.FotoProduto;
import com.cfrdocarmo.cfrfood.domain.model.Produto;
import com.cfrdocarmo.cfrfood.domain.service.CadastroProdutoService;
import com.cfrdocarmo.cfrfood.domain.service.CatalogoFotoProdutoService;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.cfrdocarmo.cfrfood.domain.service.FotoStorageService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/produtos/{produtoId}/foto", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoFotoController implements RestauranteFotoProdutoControllerOpenApi {

    @Autowired
    private CadastroProdutoService cadastroProduto;

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProduto;

    @Autowired
    private FotoStorageService fotoStorage;

    @Autowired
    private FotoProdutoModelAssembler fotoProdutoModelAssembler;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId,
                                          @PathVariable Long produtoId, @Valid FotoProdutoInput fotoProdutoInput,
                                          @RequestPart(required = true) MultipartFile arquivo) throws IOException {
        Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);

//        MultipartFile arquivo = fotoProdutoInput.getArquivo();

        FotoProduto foto = new FotoProduto();
        foto.setProduto(produto);
        foto.setDescricao(fotoProdutoInput.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getOriginalFilename());

        FotoProduto fotoSalva = catalogoFotoProduto.salvar(foto, arquivo.getInputStream());

        return fotoProdutoModelAssembler.toModel(fotoSalva);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long restauranteId,
                        @PathVariable Long produtoId) {
        catalogoFotoProduto.excluir(restauranteId, produtoId);
    }

    @GetMapping(produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                    @RequestHeader(name="accept") String acceptHeader)
            throws HttpMediaTypeNotAcceptableException  {

        if (acceptHeader.equals(MediaType.APPLICATION_JSON_VALUE)) {
            return recuperarFoto(restauranteId, produtoId);
        }

        try {
            FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(restauranteId, produtoId);
            MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());

            List<MediaType> mediaTypeAceitas = MediaType.parseMediaTypes(acceptHeader);
            verificarCompatibilidadeMediaType(mediaTypeFoto,mediaTypeAceitas);
            var fotoRecuperada =  fotoStorage.recuperar(fotoProduto.getNomeArquivo());
            if(fotoRecuperada.temUrl()) {

                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
                        .build();
            } else {
                return ResponseEntity.ok()
                        .contentType(mediaTypeFoto)
                        .body(new InputStreamResource(fotoRecuperada.getInputStream()));
            }
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> recuperarFoto(@PathVariable Long restauranteId,@PathVariable Long produtoId)  {
        FotoProdutoModel fotoProdutoModel = fotoProdutoModelAssembler.toModel(catalogoFotoProduto.buscarOuFalhar(restauranteId, produtoId));
        return ResponseEntity.ok(fotoProdutoModel);
    }

    private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto,
                                                   List<MediaType> mediaTypesAceitas) throws HttpMediaTypeNotAcceptableException {

        boolean compativel = mediaTypesAceitas.stream()
                .anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));

        if (!compativel) {
            throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
        }
    }

//    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.IMAGE_PNG_VALUE })
//    public FotoProdutoModel buscar(@PathVariable Long restauranteId,
//                                   @PathVariable Long produtoId) {
//        FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(restauranteId, produtoId);
//
//        return fotoProdutoModelAssembler.toModel(fotoProduto);
//    }
//
//    @GetMapping(produces = MediaType.ALL_VALUE)
//    public ResponseEntity<?> servir(@PathVariable Long restauranteId,
//                                    @PathVariable Long produtoId, @RequestHeader(name = "accept") String acceptHeader)
//            throws HttpMediaTypeNotAcceptableException {
//        try {
//            FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(restauranteId, produtoId);
//
//            MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
//            List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);
//
//            verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);
//
//            FotoStorageService.FotoRecuperada fotoRecuperada = fotoStorage.recuperar(fotoProduto.getNomeArquivo());
//
//            if (fotoRecuperada.temUrl()) {
//                return ResponseEntity
//                        .status(HttpStatus.FOUND)
//                        .header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
//                        .build();
//            } else {
//                return ResponseEntity.ok()
//                        .contentType(mediaTypeFoto)
//                        .body(new InputStreamResource(fotoRecuperada.getInputStream()));
//            }
//
//        } catch (EntidadeNaoEncontradaException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }

}