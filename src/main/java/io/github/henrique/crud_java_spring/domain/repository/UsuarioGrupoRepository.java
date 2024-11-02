package io.github.henrique.crud_java_spring.domain.repository;

import io.github.henrique.crud_java_spring.domain.entity.UsuarioGrupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioGrupoRepository extends JpaRepository<UsuarioGrupo, String> {
}
