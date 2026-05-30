package com.amvsolar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // -------------------------------------------------------
                //  Autorização de rotas
                // -------------------------------------------------------
                .authorizeHttpRequests(auth -> auth
                        // Recursos estáticos — público
                        .requestMatchers(
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/favicon.ico",
                                "/robots.txt"
                        ).permitAll()

                        // Console H2 — apenas desenvolvimento
                        .requestMatchers("/h2-console/**").permitAll()

                        // Páginas públicas do site
                        .requestMatchers(
                                "/",
                                "/index",
                                "/sobre",
                                "/servicos",
                                "/projetos",
                                "/contato",
                                "/faq",
                                "/simulacao",
                                "/api/leads",      // endpoint do formulário de simulação
                                "/api/contato"     // endpoint do formulário de contato
                        ).permitAll()

                        // Qualquer outra rota exige autenticação (admin futuro)
                        .anyRequest().authenticated()
                )

                // -------------------------------------------------------
                //  Proteção CSRF — mantida para formulários HTML
                //  Ignorada para /api/** (chamadas AJAX do front)
                // -------------------------------------------------------
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/**", "/h2-console/**")
                )

                // -------------------------------------------------------
                //  Headers de segurança
                // -------------------------------------------------------
                .headers(headers -> headers
                        // Permite que o console H2 use iframes (dev)
                        .frameOptions(frame -> frame.sameOrigin())

                        // Content Security Policy
                        .contentSecurityPolicy(csp -> csp
                                .policyDirectives(
                                        "default-src 'self'; " +
                                                "script-src 'self' 'unsafe-inline'; " +
                                                "style-src 'self' 'unsafe-inline' https://fonts.googleapis.com; " +
                                                "font-src 'self' https://fonts.gstatic.com; " +
                                                "img-src 'self' data: https:; " +
                                                "connect-src 'self' https://wa.me https://api.whatsapp.com"
                                )
                        )

                        // Referrer Policy
                        .referrerPolicy(ref -> ref
                                .policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
                        )
                );

        return http.build();
    }
}