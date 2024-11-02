package io.github.henrique.crud_java_spring.api.dto;

import io.github.henrique.crud_java_spring.domain.entity.Usuario;
import lombok.Data;

import java.util.List;

@Data
public class CadastroUsuarioDTO {
    private Usuario usuario;
    private List<String> permissoes; // nome de cada grupo que o usuário faz parte
}
