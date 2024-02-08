package hu.progmasters.backend.domain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class SizeValidator implements ConstraintValidator<ValidSize, String> {

    private static final List<String> validSizes = Arrays.asList("1024x1024", "512x512", "256x256");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return validSizes.contains(value);
    }

}