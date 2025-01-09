package com.example.system.validator;

import com.example.system.model.user.User;
import com.example.system.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    UserRepository userRepository;
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user =(User) target;
        if(user.getUsername().isEmpty())
            errors.rejectValue("username","error.username","Must fill this!!");
        else if(user.getUsername().matches("^([a-zA-Z0-9]\\S+)$"))
            errors.rejectValue("username","error.username","Non-whitespace please!!");
        else if(user.getUsername().matches("^[a-zA-Z][a-zA-Z0-9]{4,}+$"))
            errors.rejectValue("username","error.username","Non special chars please!!");
        else if(userRepository.findByName(user.getUsername()) != null)
            errors.rejectValue("username","error.username","Username exist!!");

        if(user.getPassword().isEmpty())
            errors.rejectValue("password","error.password","Must fill this!!");
        if(user.getPassword().isEmpty())
            errors.rejectValue("password","error.password","Must fill this!!");
    }
}
