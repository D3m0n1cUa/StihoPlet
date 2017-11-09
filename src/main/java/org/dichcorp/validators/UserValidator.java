package org.dichcorp.validators;

import java.util.regex.Pattern;

import org.dichcorp.model.users.User;
import org.dichcorp.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern
	    .compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
	return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
	User user = (User) target;

	checkLogin(user, errors);
	checkFirstName(user, errors);
	checkLastName(user, errors);
	checkEmail(user, errors);
	checkPasswords(user, errors);
    }

    private void checkLogin(User user, Errors errors) {
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "NotEmpty");

	if (!user.getLogin().matches("^[a-zA-Z0-9]+$")) {
	    errors.rejectValue("login", "Incorrect");
	    return;
	}

	if (user.getLogin().length() < 3) {
	    errors.rejectValue("login", "SizeTooSmall");
	} else if (user.getLogin().length() > 30) {
	    errors.rejectValue("login", "SizeTooBig");
	}

	if (userService.isUserWithLogin(user.getLogin())) {
	    errors.rejectValue("login", "Duplicate");
	}
    }

    private void checkFirstName(User user, Errors errors) {
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");

	if (user.getFirstName().length() < 3) {
	    errors.rejectValue("firstName", "SizeTooSmall");
	} else if (user.getFirstName().length() > 45) {
	    errors.rejectValue("firstName", "SizeTooBig");
	}
    }

    private void checkLastName(User user, Errors errors) {
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");

	if (user.getLastName().length() < 3) {
	    errors.rejectValue("lastName", "SizeTooSmall");
	} else if (user.getLastName().length() > 45) {
	    errors.rejectValue("lastName", "SizeTooBig");
	}
    }

    private void checkEmail(User user, Errors errors) {
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");

	if (!VALID_EMAIL_ADDRESS_REGEX.matcher(user.getEmail()).matches()) {
	    errors.rejectValue("email", "Incorrect");
	    return;
	}

	if (userService.isUserWithEmail(user.getEmail())) {
	    errors.rejectValue("email", "Duplicate");
	}
    }

    private void checkPasswords(User user, Errors errors) {
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");

	if (user.getPassword().length() < 8) {
	    errors.rejectValue("password", "Size");
	}

	if (!user.getPassword().equals(user.getConfirmPassword())) {
	    errors.rejectValue("confirmPassword", "Different");
	}
    }

}
