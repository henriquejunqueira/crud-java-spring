package io.github.henrique.crud_java_spring.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UsuarioGrupo { // essa classe faz o relacionamento entre as classes Usuário e Grupo

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_grupo")
    private Grupo grupo;

    public UsuarioGrupo(Usuario usuario, Grupo grupo) {
        this.usuario = usuario;
        this.grupo = grupo;
    }
}
