package pl.sternik.pb.bv.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.base.BaseLocal;

public class PastValidator implements ConstraintValidator<Past, BaseLocal> {

    public void initialize(Past constraintAnnotation) {
    }

    public boolean isValid(BaseLocal value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (value instanceof LocalDate) {
            LocalDate ld = (LocalDate) value;
            if (ld.isBefore(LocalDate.now())) {
                return true;
            }
        } else {
            LocalDateTime ldt = (LocalDateTime) value;
            if (ldt.isBefore(LocalDateTime.now())) {
                return true;
            }
        }
        return false;
    }

}