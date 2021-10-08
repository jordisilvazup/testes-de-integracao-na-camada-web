package br.com.zup.edu.testandoacamadawebot9.carros;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CarroResponse {

    @JsonProperty
    @NotBlank
    private String modelo;

    @JsonProperty
    @NotBlank
    private String placa;

    @JsonProperty
    @NotBlank
    private String cor;

    @JsonProperty
    @Positive
    private Integer anoLancamento;

    public CarroResponse(Carro carro) {
        this.anoLancamento= carro.getAnoLancamento();
        this.cor=carro.getCor();
        this.modelo=carro.getModelo();
        this.placa= carro.getPlaca();
    }
}
