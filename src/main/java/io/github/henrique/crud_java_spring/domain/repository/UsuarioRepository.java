package io.github.henrique.crud_java_spring.domain.repository;

import io.github.henrique.crud_java_spring.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
