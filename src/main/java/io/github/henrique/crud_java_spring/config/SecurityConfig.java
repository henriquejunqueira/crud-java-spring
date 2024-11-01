package io.github.henrique.crud_java_spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // Marca a classe como uma fonte de configurações do Spring
@EnableWebSecurity // Habilita a segurança da web no projeto
public class SecurityConfig {

    // Define um bean para a cadeia de filtros de segurança, que gerencia a configuração de segurança HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            SenhaMasterAuthenticationProvider senhaMasterAuthenticationProvider,
            CustomFilter customFilter) throws Exception{
        // Configura a segurança HTTP, aplicando regras de autorização
        return http
                // Configura regras de autorização para diferentes requisições HTTP
                .authorizeHttpRequests(customizer -> {
                    // Permite que requisições para "/public" sejam acessadas por qualquer pessoa, sem autenticação
                    customizer.requestMatchers("/public").permitAll();

                    // Exige autenticação para qualquer outra requisição que não seja "/public"
                    customizer.anyRequest().authenticated();
                })
                // Habilita a autenticação HTTP Basic, que é um metodo de autenticação simples
                .httpBasic(Customizer.withDefaults())

                // Habilita a autenticação por formulário com as configurações padrão,
                // fornecendo uma página de login padrão
                .formLogin(Customizer.withDefaults())

                // Adiciona o provedor de autenticação personalizado, permitindo que o Spring use a lógica
                // customizada do SenhaMasterAuthenticationProvider para autenticar usuários.
                .authenticationProvider(senhaMasterAuthenticationProvider)

                // Coloca um filtro de segurança customizado antes de um filtro específico no encadeamento de
                // segurança, para processar as requisições de acordo com a lógica personalizada.
                .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)

                .build(); // Constrói a configuração de segurança com todas as regras definidas
    }

    // Define um bean para o serviço de autenticação de usuários, que carrega e gerencia os detalhes dos usuários
    @Bean
    public UserDetailsService userDetailsService(){

        // Cria um usuário comum com o nome "user", senha "123" (criptografada), e a role "USER"
        UserDetails commonUser = User
                .builder()
                .username("user") // Define o nome de usuário
                .password(passwordEncoder().encode("123")) // Define a senha criptografada
                .roles("USER") // Define o papel (role) como "USER"
                .build(); // Constrói o objeto UserDetails

        // Cria um usuário administrador com o nome "admin", senha "admin" (criptografada),
        // e as roles "USER" e "ADMIN"
        UserDetails adminUser = User
                .builder()
                .username("admin") // Define o nome de usuário
                .password(passwordEncoder().encode("admin")) // Define a senha criptografada
                .roles("USER", "ADMIN") // Define os papéis (roles) como "USER" e "ADMIN"
                .build(); // Constrói o objeto UserDetails

        // Retorna um gerenciador de usuários em memória que usa os detalhes dos usuários criados para autenticação
        return new InMemoryUserDetailsManager(commonUser, adminUser);
    }

    // Define um bean para o codificador de senhas, que é usado para criptografar senhas com o algoritmo BCrypt
    @Bean
    public PasswordEncoder passwordEncoder(){
        // Retorna uma instância de BCryptPasswordEncoder, que aplica uma função de hash forte para
        // proteger as senhas
        return new BCryptPasswordEncoder();
    }

}
