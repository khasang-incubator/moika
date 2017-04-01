package io.khasang.moika.validator;

import io.khasang.moika.entity.Car;
import io.khasang.moika.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class ClientValidator implements Validator {

    private final CarValidator carValidator;

    @Autowired
    public ClientValidator(CarValidator carValidator) {
        this.carValidator = carValidator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Client.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client client = (Client) target;
        if (StringUtils.isEmpty(client.getFirstName())) {
            errors.rejectValue("name", "name_empty");
        }
        if (!client.getPhone().matches("^\\d{9}+")) {
            errors.rejectValue("phone", "phone_invalid");
        }
        if (!client.getLastname().matches("^[A-Za-z]*|^[А-Яа-я]*")) {
            errors.rejectValue("lastname", "lastname_invalid");
        }

        if (client.getCars() != null) {
            errors.pushNestedPath("car");
            for (Car car :  client.getCars()) {
                ValidationUtils.invokeValidator(carValidator, car, errors);
            }
        }
        errors.popNestedPath();
    }
}
