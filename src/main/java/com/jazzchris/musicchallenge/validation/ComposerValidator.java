package com.jazzchris.musicchallenge.validation;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jazzchris.musicchallenge.entity.Composer;

@Component
public class ComposerValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Composer.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Composer theComposer = (Composer) target;
		LocalDate birth = theComposer.getDateBirth();
		LocalDate death = theComposer.getDateDeath();
			
		if (birth != null && death != null && theComposer.getDateDeath().isBefore(theComposer.getDateBirth())) {
			errors.rejectValue("dateDeath", "dead.before.born");
		}
		if (death != null && theComposer.getDateDeath().isAfter(LocalDate.now())) {
			errors.rejectValue("dateDeath", "dead.future");
		}
		if (birth != null && theComposer.getDateBirth().isAfter(LocalDate.now())) {
			errors.rejectValue("dateBirth", "born.future");
		}

	}

}
