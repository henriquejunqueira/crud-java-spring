package io.github.henrique.crud_java_spring.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Marca a classe como um controlador REST, o que significa que ela lida com requisições HTTP e retorna dados
@RestController
public class FooController {

    // Mapeia a rota "/public" para o metodo publicRoute(), que responde a requisições HTTP GET
    @GetMapping("/public")
    public ResponseEntity<String> publicRoute(){
        // Retorna uma resposta HTTP 200 OK com o corpo "Public route ok!"
        return ResponseEntity.ok("Public route ok!");
    }

    // Mapeia a rota "/private" para o metodo privateRoute(), que também responde a requisições HTTP GET
    @GetMapping("/private")
    public ResponseEntity<String> privateRoute(Authentication authentication){
        // Retorna uma resposta HTTP 200 OK com o corpo "Private route ok!"
        // e inclui o nome do usuário autenticado obtido através do objeto Authentication
        return ResponseEntity.ok("Private route ok! Usuário conectado: " + authentication.getName());
    }

}
