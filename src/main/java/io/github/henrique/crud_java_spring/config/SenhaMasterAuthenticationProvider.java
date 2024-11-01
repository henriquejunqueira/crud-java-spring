package io.github.henrique.crud_java_spring.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

// Marca esta classe como um componente do Spring, permitindo que o Spring faça a injeção de
// dependências automaticamente
@Component
public class SenhaMasterAuthenticationProvider implements AuthenticationProvider {

    // Sobrescreve o metodo authenticate para realizar a autenticação personalizada
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // Obtém o nome do usuário (login) a partir do objeto Authentication
        var login = authentication.getName();

        // Obtém a senha a partir do objeto Authentication e a converte para String
        var senha = (String) authentication.getCredentials();

        // Define o login e a senha do usuário master
        String loginMaster = "master";
        String senhaMaster = "@321";

        // Verifica se o login e a senha fornecidos correspondem às credenciais do usuário master
        if(loginMaster.equals(login) && senhaMaster.equals(senha)){

            // Se as credenciais forem válidas, retorna um token de autenticação com o nome "Sou Master",
            // sem credenciais adicionais, e uma lista de autoridades contendo "ADMIN"
            return new UsernamePasswordAuthenticationToken(
                    "Sou Master", // Nome do usuário autenticado
                    null, // Credenciais de senha (neste caso, não são necessárias, então são definidas como null)
                    List.of(new SimpleGrantedAuthority("ADMIN")) // Lista de autoridades concedidas
            );
        }

        // Retorna null se as credenciais não forem válidas, indicando falha na autenticação
        return null;
    }

    // Sobrescreve o metodo supports para indicar que este AuthenticationProvider suporta qualquer
    // tipo de autenticação
    @Override
    public boolean supports(Class<?> authentication) {
        // Indica que este provedor de autenticação pode processar qualquer tipo de objeto de autenticação
        return true;
    }
}
