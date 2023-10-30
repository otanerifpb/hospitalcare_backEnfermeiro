package br.edu.ifpb.pdist.back.repository;

import br.edu.ifpb.pdist.back.model.Enfermeiro;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnfermeiroRepository extends JpaRepository<Enfermeiro, Integer> {
    Optional<Enfermeiro> findByCoren(String string);

}
