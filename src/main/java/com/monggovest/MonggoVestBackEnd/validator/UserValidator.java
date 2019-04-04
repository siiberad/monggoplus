package com.monggovest.MonggoVestBackEnd.validator;

import com.monggovest.MonggoVestBackEnd.model.UserModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserModel.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        UserModel user = (UserModel) object;

        if (user.getUserPassword().length() < 6){
            errors.rejectValue("password","length", "password must be at least 6 characters");
        }
    }
}
