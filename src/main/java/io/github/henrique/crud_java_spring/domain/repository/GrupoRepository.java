package io.github.henrique.crud_java_spring.domain.repository;

import io.github.henrique.crud_java_spring.domain.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo, String> {
}
