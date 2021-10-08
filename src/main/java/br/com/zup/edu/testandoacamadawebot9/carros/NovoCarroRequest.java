package br.com.zup.edu.testandoacamadawebot9.carros;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class NovoCarroRequest {

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

    public NovoCarroRequest(String modelo, String placa, String cor, Integer anoLancamento) {
        this.modelo = modelo;
        this.placa = placa;
        this.cor = cor;
        this.anoLancamento = anoLancamento;
    }

    public Carro paraCarro() {
        return new Carro(cor,modelo,placa,anoLancamento);
    }
}
