package br.com.zup.edu.testandoacamadawebot9.carros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carros")
public class ConsultarCarroController {

    private final CarroRepository repository;

    public ConsultarCarroController(CarroRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<?> consultar(@RequestParam(required = false) String placa){

        if(Objects.nonNull(placa)){
            Optional<Carro> possivelCarro = repository.findByPlaca(placa);
            if (possivelCarro.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(new CarroResponse(possivelCarro.get()));
        }

        List<Carro> carros = repository.findAll();

        List<CarroResponse> carroResponses = carros.stream().map(CarroResponse::new).collect(Collectors.toList());

        return ResponseEntity.ok(carroResponses);
    }
}
