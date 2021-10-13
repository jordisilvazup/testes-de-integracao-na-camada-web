package br.com.zup.edu.testandoacamadawebot9.carros;

import br.com.zup.edu.testandoacamadawebot9.carros.Carro;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
public class CarroDao {
    private final EntityManager manager;

    public CarroDao(EntityManager manager) {
        this.manager = manager;
    }

    @Transactional
    public void salvar(Carro carro){
        manager.persist(carro);
    }

    @Transactional
    public Carro consularPelaPlaca(String placa){
        String jpql="SELECT r FROM  Carro r where r.placa=:placa";
        Carro carro = manager.createQuery(jpql, Carro.class)
                .setParameter("placa", placa)
                .getSingleResult();

        return carro;
    }
}
