package io.github.henrique.crud_java_spring.api;

import io.github.henrique.crud_java_spring.api.dto.CadastroUsuarioDTO;
import io.github.henrique.crud_java_spring.domain.entity.Usuario;
import io.github.henrique.crud_java_spring.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> salvar(@RequestBody CadastroUsuarioDTO cadastroUsuarioDTO){

        Usuario usuario = usuarioService.salvar(cadastroUsuarioDTO.getUsuario(), cadastroUsuarioDTO.getPermissoes());

        return ResponseEntity.ok(usuario);

    }

}
