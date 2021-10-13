package br.com.zup.edu.testandoacamadawebot9.validators;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class CampoDeValorUnicoValidator implements ConstraintValidator<CampoDeValorUnico, Object> {
   private final EntityManager manager;
   private String classe;
   private String campo;

    public CampoDeValorUnicoValidator(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void initialize(CampoDeValorUnico constraintAnnotation) {
            this.classe=constraintAnnotation.classeDeDominio().getSimpleName();
            this.campo=constraintAnnotation.campo();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        String jpql="SELECT r FROM "+this.classe+" r WHERE r."+campo+" =:valor";

        List resultados = manager.createQuery(jpql)
                .setParameter("valor",o)
                .getResultList();

        return resultados.isEmpty();
    }
}
