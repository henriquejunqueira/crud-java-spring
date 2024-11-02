package io.github.henrique.crud_java_spring.domain.repository;

import io.github.henrique.crud_java_spring.domain.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GrupoRepository extends JpaRepository<Grupo, String> {

    Optional<Grupo> findByNome(String nome);

}
