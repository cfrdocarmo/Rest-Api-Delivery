package com.cfrdocarmo.cfrfood.core.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Value("${cfrdocarmo.oauth}")
    private String secret;

    @Value("${cfrdocarmo.oauth.algothm}")
    private String algothm;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                    .anyRequest().authenticated()
                .and()
                    .cors().and()
                    .oauth2ResourceServer().jwt();

    }

//    @Bean
//    public JwtDecoder jwtDecoder() {
//        var secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), algothm);
//
//        return NimbusJwtDecoder.withSecretKey(secretKey).build();
//    }
}
