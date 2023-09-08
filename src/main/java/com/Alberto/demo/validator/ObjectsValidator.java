package com.Alberto.demo.validator;

import com.Alberto.demo.entities.Base;
import com.Alberto.demo.exceptions.ObjectNotValidException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
@Component
public class ObjectsValidator<E extends Base> {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();


    public void validate (E entity){
        Set<ConstraintViolation<E>> violations = validator.validate(entity);
        if(!violations.isEmpty()){
            var errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            throw new ObjectNotValidException(errorMessages);
        }

    }
}
