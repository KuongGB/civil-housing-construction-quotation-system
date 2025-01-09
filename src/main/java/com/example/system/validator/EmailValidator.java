package com.example.system.validator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {


    @Override
    public boolean test(String s) {
        //TO DO regex to validate email
        return true;
    }
}
