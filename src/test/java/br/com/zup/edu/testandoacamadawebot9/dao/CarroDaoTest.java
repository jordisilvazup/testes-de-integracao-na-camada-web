package br.com.zup.edu.testandoacamadawebot9.dao;

import br.com.zup.edu.testandoacamadawebot9.carros.Carro;
import br.com.zup.edu.testandoacamadawebot9.carros.CarroDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@SpringBootTest
class CarroDaoTest {
    @Autowired
    private CarroDao repositorio;
    @PersistenceContext
    private EntityManager manager;

    @Test
    void naoDeveExistirCarroParaPlacaNaoCadastrada(){
        assertThrows(NoResultException.class,()-> repositorio.consularPelaPlaca("12345"));
    }

    @Test
    void deveRetornarUmCarroCasoEleExista(){
        Carro carro= new Carro("Prata","gol","12345",1998);
        repositorio.salvar(carro);
        Carro response = repositorio.consularPelaPlaca("12345");

        assertEquals("Prata",response.getCor());
        assertEquals("gol",response.getModelo());
        assertEquals("12345",response.getPlaca());
        assertEquals(1998,response.getAnoLancamento());
    }

    @Test
    void deveCadastrarCarro(){
        Carro carro= new Carro("Prata","gol","12345",1998);
        repositorio.salvar(carro);
        Carro response = manager.find(Carro.class, carro.getId());
        assertEquals(carro.getCor(),response.getCor());
        assertEquals(carro.getModelo(),response.getModelo());
        assertEquals(carro.getPlaca(),response.getPlaca());
        assertEquals(carro.getAnoLancamento(),response.getAnoLancamento());

    }



}