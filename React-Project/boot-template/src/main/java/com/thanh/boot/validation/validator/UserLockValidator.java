package com.thanh.boot.validation.validator;

import com.thanh.boot.controller.request.UserLockRequest;
import com.thanh.boot.validation.constraint.UserLockConstraint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UserLockValidator implements ConstraintValidator<UserLockConstraint, UserLockRequest> {

    private static final Logger logger = LoggerFactory.getLogger(UserLockValidator.class);

    private static final long ADMIN_ID = 1;

    @Override
    public boolean isValid(UserLockRequest cmd, ConstraintValidatorContext context) {
        logger.debug("Validating user lock request ..........");
        if (cmd == null){
            return true;
        }
        List<Long> userIds = cmd.getUserIds();
        boolean containsAdmin = false;
        for (Long id : userIds){
            if (id == ADMIN_ID){
                containsAdmin = true;
                break;
            }
        }

        if (containsAdmin){
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("{canNotDeleteAdmin}")
                    .addConstraintViolation();
            return false;

        }

        return true;
    }

}
