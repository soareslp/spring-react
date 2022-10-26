package br.com.api.professores.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.api.professores.modelo.ProfessorModelo;

@Repository
public interface ProfessorRepositorio extends CrudRepository<ProfessorModelo, Long> {

}
