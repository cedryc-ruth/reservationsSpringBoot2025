package be.iccbxl.pid.reservations_springboot.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {
    String message() default "Les mots de passe ne correspondent pas";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
