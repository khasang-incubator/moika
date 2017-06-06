package io.khasang.moika.validator;

import io.khasang.moika.entity.Car;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CarValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Car.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Car car = (Car) target;
        if(StringUtils.isEmpty(car.getCarTypeEntity().getTypeCode())){
            errors.rejectValue("carType","cartype_empty");
        }
        if(!car.getCarNumber().matches("^[A-Z]\\d{3}[A-Z]{2}\\d{3}")){
            errors.rejectValue("carNumber","carnumber_invalid");
        }
        if(StringUtils.isEmpty(car.getCarModel())){
            errors.rejectValue("carModel","carmodel_empty");
        }
    }
}
