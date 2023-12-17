package br.edu.ifpb.pdist.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pdist.back.model.Recepcionista;

public interface RecepcionistaRepository extends JpaRepository<Recepcionista, Integer> {

    Optional<Recepcionista> findByMatricula(String string);
    
}
