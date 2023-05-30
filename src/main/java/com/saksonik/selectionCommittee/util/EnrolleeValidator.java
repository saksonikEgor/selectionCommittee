package com.saksonik.selectionCommittee.util;

import com.saksonik.selectionCommittee.models.Enrollee;
import com.saksonik.selectionCommittee.services.EnrolleeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EnrolleeValidator implements Validator {
    private final EnrolleeService enrolleeService;

    @Autowired
    public EnrolleeValidator(EnrolleeService enrolleeService) {
        this.enrolleeService = enrolleeService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Enrollee.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Enrollee enrollee = (Enrollee) target;

        if (enrolleeService
                .getEnrolleeByEmailAndPassword(enrollee.getEmail(), enrollee.getPassword())
                .isEmpty()) {
            errors.rejectValue("email", "", "неверный Email или пароль");
            errors.rejectValue("password", "", "неверный Email или пароль");
        }
    }
}
