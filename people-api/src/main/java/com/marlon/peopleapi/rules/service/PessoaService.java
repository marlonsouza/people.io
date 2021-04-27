package com.marlon.peopleapi.rules.service;

import com.marlon.peopleapi.core.service.BaseEntityService;
import com.marlon.peopleapi.rules.domain.entity.Pessoa;
import com.marlon.peopleapi.rules.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
public class PessoaService extends BaseEntityService<PessoaRepository, Pessoa> {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    protected boolean beforeSave(Pessoa toSave) throws Exception {
        if(pessoaRepository.findByCpf(toSave.getCpf()).isPresent() && toSave.getId() == null){
            throw new EntityExistsException("CPF existente na base de dados");
        }

        return super.beforeSave(toSave);
    }
}
