package org.dichcorp.validators;

import org.dichcorp.model.rifms.Rifma;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RifmaValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
	return Rifma.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
	if (target == null) {
	    errors.rejectValue("text", "Incorrect");
	}

	Rifma rifma = (Rifma) target;
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "Incorrect");

	if (rifma.getText().length() > 60) {
	    errors.rejectValue("text", "Incorrect");
	}
    }

}
