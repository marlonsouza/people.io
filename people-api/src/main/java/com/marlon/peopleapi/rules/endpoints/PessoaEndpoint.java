package com.marlon.peopleapi.rules.endpoints;

import com.marlon.peopleapi.core.rest.BaseEndpoint;
import com.marlon.peopleapi.rules.domain.entity.Pessoa;
import com.marlon.peopleapi.rules.service.PessoaService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pessoa")
public class PessoaEndpoint extends BaseEndpoint<Pessoa> {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiModelProperty("Criação de pessoa")
    public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa) throws Exception {
        Optional<Pessoa> saved = pessoaService.save(pessoa);

        return ResponseEntity.ok(saved.get());
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiModelProperty("Atualização de pessoa")
    @CrossOrigin(origins = {"http://localhost:8888", "http://127.0.0.1:8888"})
    public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa, @PathVariable("id") UUID id) throws Exception {
        if(!id.equals(pessoa.getId())){
            return ResponseEntity.badRequest().build();
        }

        Optional<Pessoa> saved = pessoaService.save(pessoa);

        return ResponseEntity.ok(saved.orElseThrow());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiModelProperty("Listar todas as pessoas")
    public ResponseEntity<List<Pessoa>> findAll(){
        return ResponseEntity.ok(pessoaService.findAll());
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiModelProperty("Buscar pessoa pelo id")
    public ResponseEntity<Pessoa> findById(@PathVariable("id") UUID id){
        Optional<Pessoa> pessoa = pessoaService.findById(id);

        return pessoa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin(origins = {"http://localhost:8888", "http://127.0.0.1:8888"})
    public void deleteById(@PathVariable("id") UUID id){
        pessoaService.deleteById(id);
    }
}
