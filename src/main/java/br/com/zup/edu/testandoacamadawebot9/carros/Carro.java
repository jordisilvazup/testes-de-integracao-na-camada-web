package br.com.zup.edu.testandoacamadawebot9.carros;

import javax.persistence.*;

@Entity
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cor;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false, unique = true)
    private String placa;

    @Column(nullable = false)
    private Integer anoLancamento;

    public Carro(String cor, String modelo, String placa, Integer anoLancamento) {
        this.cor = cor;
        this.modelo = modelo;
        this.placa = placa;
        this.anoLancamento = anoLancamento;
    }

    @Deprecated
    public Carro(){}

    public Long getId() {
        return id;
    }

    public String getCor() {
        return cor;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }
}
