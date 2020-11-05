package com.thanh.boot.validation.constraint;

import com.thanh.boot.validation.validator.UserLockValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = UserLockValidator.class)
@Documented
public @interface UserLockConstraint {
    String message() default "Could not lock user";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
