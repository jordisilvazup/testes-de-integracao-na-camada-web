package br.com.zup.edu.testandoacamadawebot9.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = CampoDeValorUnicoValidator.class)
public @interface CampoDeValorUnico {
    String message() default "Valor já cadastrado na base.";
    Class<? extends Payload>[] payload() default {};
    Class<?>[] groups() default {};
    Class<?> classeDeDominio();
    String campo();
}
