package br.com.zup.edu.testandoacamadawebot9.validators;

import br.com.zup.edu.testandoacamadawebot9.carros.Carro;
import org.hibernate.annotations.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CampoDeValorUnicoValidatorTest {
    @PersistenceContext
    private EntityManager manager;

    @Mock
    private ConstraintValidatorContext context;

    private CampoDeValorUnicoValidator validator;

    private CampoDeValorUnicoValidatorCasoDeTeste casoDeTeste;

    @BeforeEach
    void setUp() {
        //preparo
        this.validator = new CampoDeValorUnicoValidator(manager);
        this.casoDeTeste = new CampoDeValorUnicoValidatorCasoDeTeste();
        this.validator.initialize(casoDeTeste);
    }

    @Test
    void naoDeveExistirUmValor() {
        //acao

        boolean valid = this.validator.isValid("12345", context);

        //validacao

        assertTrue(valid);

    }

    @Test
    @Transactional
    void deveExistirOCadastroDeUmValor() {
        //preparo
        Carro gol = new Carro("Prata", "Gol Bola", "12345", 2021);

        manager.persist(gol);

        //acao
        boolean notValid = this.validator.isValid("12345", context);

        //verificao
        assertFalse(notValid);

    }


    static class CampoDeValorUnicoValidatorCasoDeTeste implements CampoDeValorUnico {

        @Override
        public String message() {
            return "Valor já cadastrado na base";
        }

        @Override
        public Class<? extends Payload>[] payload() {
            return new Class[0];
        }

        @Override
        public Class<?>[] groups() {
            return new Class[0];
        }

        @Override
        public Class<?> classeDeDominio() {
            return Carro.class;
        }

        @Override
        public String campo() {
            return "placa";
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return CampoDeValorUnico.class;
        }
    }

}