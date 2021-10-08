package br.com.zup.edu.testandoacamadawebot9.carros;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/carros")
public class CadastrarNovoCarroController {
    private final EntityManager manager;

    public CadastrarNovoCarroController(EntityManager manager) {
        this.manager = manager;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid NovoCarroRequest request) {

        Carro novoCarro = request.paraCarro();

        manager.persist(novoCarro);

        URI location = UriComponentsBuilder.fromUriString("/carros/{id}").buildAndExpand(novoCarro.getId()).toUri();

        return ResponseEntity.created(location).build();

    }
}
