package dev.root101.spring_template.rest_api.v1.model.validation;

import java.util.regex.Pattern;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordRegister implements ConstraintValidator<Password, String> {

    @Override
    public void initialize(Password a) {
        ConstraintValidator.super.initialize(a);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext cvc) {
        if (password == null) {
            cvc.disableDefaultConstraintViolation();
            cvc.buildConstraintViolationWithTemplate(String
                    .format("Can't be null."))
                    .addConstraintViolation();
        } else {
            if (password.length() < 8 || password.length() > 64) {
                cvc.disableDefaultConstraintViolation();
                cvc.buildConstraintViolationWithTemplate(String
                        .format("Must be 8 to 64 digits long"))
                        .addConstraintViolation();
            } else {
                boolean pattern = Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\"\\[\\]\\(\\)`~!@#$%^&*-+=,.<>?;:'{}]).{8,64}$", password);
                if (!pattern) {
                    cvc.disableDefaultConstraintViolation();
                    cvc.buildConstraintViolationWithTemplate(String
                            .format("Must containt an Uppercase Letter, a Lowercase Letter, a Number and a Special Character."))
                            .addConstraintViolation();
                }
            }
        }
        return true;
    }

}
