package com.cfrdocarmo.cfrfood.core.openapi;

import com.cfrdocarmo.cfrfood.api.exceptionHandler.Problem;
import com.cfrdocarmo.cfrfood.api.v1.model.*;
import com.cfrdocarmo.cfrfood.api.v1.openapi.model.*;
import com.cfrdocarmo.cfrfood.api.v2.model.CidadeModelV2;
import com.cfrdocarmo.cfrfood.api.v2.model.CozinhaModelV2;
import com.cfrdocarmo.cfrfood.api.v2.openapi.model.CidadesModelV2OpenApi;
import com.cfrdocarmo.cfrfood.api.v2.openapi.model.CozinhasModelV2OpenApi;
import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

    @Bean
    public Docket apiDocketV1() {
        var typeResolver = new TypeResolver();

        return new Docket(DocumentationType.OAS_30)
                .groupName("V1")
                .select()
                    .apis(RequestHandlerSelectors.basePackage("com.cfrdocarmo.cfrfood.api"))
                    .paths(PathSelectors.ant("/v1/**"))
                    .build()
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, globalGetResponseMessages())
                .globalResponses(HttpMethod.POST, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.PUT, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
//                .globalRequestParameters(Collections.singletonList(
//                        new RequestParameterBuilder()
//                                .name("campos")
//                                .description("Nomes das propriedades para filtrar a resposta, separados por v??rgula")
//                                .in(ParameterType.QUERY)
//                                .required(true)
//                                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
//                                .build()))
                .additionalModels(typeResolver.resolve(Problem.class))
                .ignoredParameterTypes(ServletWebRequest.class)
                .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
                .directModelSubstitute(Links.class, LinksModelOpenApi.class)
                .alternateTypeRules(AlternateTypeRules.newRule(
                                                        typeResolver.resolve(PagedModel.class, CozinhaModel.class),
                                                        CozinhasModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                                                        typeResolver.resolve(PagedModel.class, PedidoResumoModel.class),
                                                         PedidosResumoModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                                                        typeResolver.resolve(CollectionModel.class, CidadeModel.class),
                                                        CidadesModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                                                        typeResolver.resolve(CollectionModel.class, EstadoModel.class),
                                                        EstadosModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                                                        typeResolver.resolve(CollectionModel.class, FormaPagamentoModel.class),
                                                        FormasPagamentoModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                                                        typeResolver.resolve(CollectionModel.class, GrupoModel.class),
                                                        GruposModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                                                        typeResolver.resolve(CollectionModel.class, PermissaoModel.class),
                                                        PermissoesModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                                                        typeResolver.resolve(CollectionModel.class, ProdutoModel.class),
                                                        ProdutosModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                                                        typeResolver.resolve(CollectionModel.class, RestauranteBasicoModel.class),
                                                        RestaurantesBasicoModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                                                        typeResolver.resolve(CollectionModel.class, UsuarioModel.class),
                                                        UsuariosModelOpenApi.class))
                .apiInfo(apiInfoV1())
                .tags(new Tag("Cidades", "Gerencia as cidades"),
                      new Tag("Grupos", "Gerencia os grupos de Usu??rios"),
                      new Tag("Cozinhas", "Gerencia as Cozinhas"),
                      new Tag("Formas de Pagamento", "Gerencia as Formas de Pagamento"),
                      new Tag("Pedidos", "Gerencia os Pedidos"),
                      new Tag("Restaurantes", "Gerencia os Restaurantes"),
                      new Tag("Estados", "Gerencia os Restaurantes"),
                      new Tag("Produtos", "Gerencia os Produtos"),
                      new Tag("Usuarios", "Gerencia os Usu??rios"),
                      new Tag("Estatisticas", "Gerencia as Estat??sticas"),
                      new Tag("Permiss??es", "Gerencia as Permiss??es"));
    }

    @Bean
    public Docket apiDocketV2() {
        var typeResolver = new TypeResolver();

        return new Docket(DocumentationType.OAS_30)
                .groupName("V2")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cfrdocarmo.cfrfood.api"))
                .paths(PathSelectors.ant("/v2/**"))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, globalGetResponseMessages())
                .globalResponses(HttpMethod.POST, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.PUT, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
                .additionalModels(typeResolver.resolve(Problem.class))
                .ignoredParameterTypes(ServletWebRequest.class)
                .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
                .directModelSubstitute(Links.class, LinksModelOpenApi.class)
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, CidadeModelV2.class),
                        CidadesModelV2OpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(PagedModel.class, CozinhaModelV2.class),
                        CozinhasModelV2OpenApi.class))
                .apiInfo(apiInfoV2() );
    }

    @Bean
    public JacksonModuleRegistrar springFoxJacksonConfig() {
        return objectMapper -> objectMapper.registerModule(new JavaTimeModule());
    }

    private List<Response> globalDeleteResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("Requisi????o inv??lida (erro do cliente)")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemaModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno no servidor")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemaModelReference())
                        .build()
        );
    }

    private List<Response> globalPostPutResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("Requisi????o inv??lida (erro do cliente) ")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemaModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_FOUND.value()))
                        .description("Recurso n??o encontrado.")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemaModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno no servidor")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemaModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                        .description("Recurso n??o possui representa????o que poderia ser aceita pelo consumidor")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemaModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
                        .description("Requisi????o recusada porque o corpo est?? em um formato n??o suportado")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemaModelReference())
                        .build()
        );
    }

    private List<Response> globalGetResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno do Servidor")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemaModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                        .description("Recurso n??o possui representa????o que pode ser aceita pelo consumidor")
                        .build()
        );
    }

    private ApiInfo apiInfoV1() {
        return new ApiInfoBuilder()
                .title("CFRdoCarmo Food API")
                .description("API aberta para clientes e restaurantes")
                .version("1")
                .contact(new Contact("CFRdoCarmo", "https://github.com/cfrdocarmo", "cfrdocarmo@gmail.com"))
                .build();

    }

    private ApiInfo apiInfoV2() {
        return new ApiInfoBuilder()
                .title("CFRdoCarmo Food API")
                .description("API aberta para clientes e restaurantes")
                .version("2")
                .contact(new Contact("CFRdoCarmo", "https://github.com/cfrdocarmo", "cfrdocarmo@gmail.com"))
                .build();

    }

    private Consumer<RepresentationBuilder> getProblemaModelReference() {
        return r -> r.model(m -> m.name("Problema")
                .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
                        q -> q.name("Problema").namespace("com.cfrdocarmo.cfrfood.api.exceptionHandler")))));
    }

}
