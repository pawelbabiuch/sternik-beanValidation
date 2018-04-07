package pl.sternik.pb.bv;

import org.junit.Before;
import org.junit.Test;
import pl.sternik.pb.bv.grupy.Address;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PersonTest {

    private Person SUT;

    @Before
    public void setup() {
        Address address = new Address();
        SUT = new Person();
        SUT.setAddress(address);
        SUT.setName("Kolo");
    }

    @Test
    public void thatOnlyStreet() {
        Set<ConstraintViolation<Person>> violations = validateClass(SUT);
        for (ConstraintViolation<Person> constraintViolation : violations) {
            System.out.println(constraintViolation.getMessage());
        }

        assertEquals(1, violations.size());
    }

    private Set<ConstraintViolation<Person>> validateClass(Person myClass) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Person>> violations = validator.validate(myClass);
        return violations;
    }

    private Set<ConstraintViolation<Person>> validateClass(Person myClass, Class<?> clas) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Person>> violations = validator.validate(myClass, clas);
        return violations;
    }

}