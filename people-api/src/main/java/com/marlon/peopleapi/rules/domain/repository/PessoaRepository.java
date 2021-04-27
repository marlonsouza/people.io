package com.marlon.peopleapi.rules.domain.repository;

import com.marlon.peopleapi.core.database.BaseRepository;
import com.marlon.peopleapi.rules.domain.entity.Pessoa;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends BaseRepository<Pessoa> {

    Optional<Pessoa> findByCpf(String cpf);

}
