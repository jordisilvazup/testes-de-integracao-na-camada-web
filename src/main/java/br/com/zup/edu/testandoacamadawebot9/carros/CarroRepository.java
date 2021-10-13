package br.com.zup.edu.testandoacamadawebot9.carros;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarroRepository  extends JpaRepository<Carro,Long> {
    Optional<Carro> findByPlaca(String placa);
    Carro findByNome(String nome);
}
