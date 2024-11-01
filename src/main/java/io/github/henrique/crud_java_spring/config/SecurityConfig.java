package io.github.henrique.crud_java_spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Anota a classe como uma classe de configuração do Spring
@EnableWebSecurity // Habilita a segurança da web para o aplicativo
public class SecurityConfig {

    // Define um bean que configura a cadeia de filtros de segurança
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        // Configura a segurança HTTP
        return http
                // Define as regras de autorização para requisições HTTP
                .authorizeHttpRequests(customizer -> {
                    // Permite acesso público à URL "/public" sem autenticação
                    customizer.requestMatchers("/public").permitAll();
                    // Requer autenticação para todas as outras requisições
                    customizer.anyRequest().authenticated();
                })
                // Habilita a autenticação HTTP Basic com as configurações padrão
                .httpBasic(Customizer.withDefaults())
                // Habilita o formulário de login com as configurações padrão
                .formLogin(Customizer.withDefaults())
                .build(); // Constrói a configuração de segurança
    }

}
