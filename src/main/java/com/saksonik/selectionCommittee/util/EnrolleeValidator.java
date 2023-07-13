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

        if (enrollee.getEmail().isEmpty())
            errors.rejectValue("email", "", "Некорректный Email");

        if (enrollee.getPassword().isEmpty())
            errors.rejectValue("password", "", "Некорректный пароль");

        if (enrollee.getNameEnrollee().isEmpty())
            errors.rejectValue("nameEnrollee", "", "Некорректное ФИО");
    }

    public void existValidateForRegistration(Object object, Errors errors) {
        Enrollee enrollee = (Enrollee) object;

        if (enrolleeService
                .getEnrolleeByEmail(enrollee.getEmail())
                .isPresent())
            errors.rejectValue("email", "", "Абитуриент с таким Email уже существует");

        if (enrolleeService
                .getEnrolleeByPhoneNumber(enrollee.getPhoneNumber())
                .isPresent())
            errors.rejectValue("phoneNumber", "", "Абитуриент с таким номером телефона уже существует");
    }

    public void existValidate(Object object, Errors errors) {
        Enrollee enrollee = (Enrollee) object;

        if (enrolleeService
                .getEnrolleeByEmailAndPassword(enrollee.getEmail(), enrollee.getPassword())
                .isEmpty()) {
            errors.rejectValue("email", "", "Неверный Email или пароль");
            errors.rejectValue("password", "", "Неверный Email или пароль");
        }
    }
}
