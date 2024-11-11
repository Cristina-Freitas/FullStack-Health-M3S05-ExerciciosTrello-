package br.com.exemplo.aula.repositories;

import br.com.exemplo.aula.entities.Nutricionista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NutricionistaRepository extends JpaRepository<Nutricionista,Long> {

    Optional<Nutricionista> findByNome(String nome);

}
