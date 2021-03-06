package com.cfrdocarmo.cfrfood.core.security;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface CheckSecurity {

    public @interface Cozinhas {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_COZINHAS')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeEditar {
        }

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar {
        }
    }

    public @interface Restaurantes {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_RESTAURANTES') ")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeGerenciarCadastro {
        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_RESTAURANTES') or @CFRdoCarmoSecurity.gerenciaRestaurante(#restauranteId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeGerenciarFuncionamento {
        }

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar {
        }
    }

    public @interface Pedidos {

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @PostAuthorize("hasAuthority('CONSULTAR_PEDIDOS') or "
                + "@CFRdoCarmoSecurity.getUsuarioId() == returnObject.cliente.id or "
                + "@CFRdoCarmoSecurity.gerenciaRestaurante(returnObject.restaurante.id)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeBuscar { }

        @PreAuthorize("hasAuthority('SCOPE_READ') and (hasAuthority('CONSULTAR_PEDIDOS') or "
                + "@CFRdoCarmoSecurity.getUsuarioId() == #filtro.clienteId or "
                + "@CFRdoCarmoSecurity.gerenciaRestaurante(#filtro.restauranteId))")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodePesquisar { }

    }

}



