package pl.sternik.pb.bv.grupy;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import pl.sternik.pb.bv.grupy.Address.Complete;
import pl.sternik.pb.bv.grupy.Address.OnlyGroups;
import pl.sternik.pb.bv.grupy.Address.OnlyStreet;
import pl.sternik.pb.bv.grupy.Address.OnlyZip;


public class AdressTest {

    private Address SUT;

    @Before
    public void setup() {
        SUT = new Address();
    }

    @Test
    public void thatOnlyStreet() {
        Set<ConstraintViolation<Address>> violations = validateClass(SUT,OnlyStreet.class);
        System.out.println("OnlyStreet");
        for (ConstraintViolation<Address> constraintViolation : violations) {
            System.out.println(constraintViolation.getMessage());
        }

        assertEquals(1, violations.size());
    }

    @Test
    public void thatOnlyZip() {
        Set<ConstraintViolation<Address>> violations = validateClass(SUT,OnlyZip.class);
        System.out.println("OnlyZip");
        for (ConstraintViolation<Address> constraintViolation : violations) {
            System.out.println(constraintViolation.getMessage());
        }

        assertEquals(1, violations.size());
    }

    @Test
    public void thatComplete() {
        Set<ConstraintViolation<Address>> violations = validateClass(SUT,Complete.class);
        System.out.println("Complete");
        for (ConstraintViolation<Address> constraintViolation : violations) {
            System.out.println(constraintViolation.getMessage());
        }


        assertEquals(1, violations.size());
    }
    @Test
    public void thatOnlyGroups() {
        Set<ConstraintViolation<Address>> violations = validateClass(SUT,OnlyGroups.class);
        System.out.println("OnlyGroups");
        for (ConstraintViolation<Address> constraintViolation : violations) {
            System.out.println(constraintViolation.getMessage());
        }


        assertEquals(1, violations.size());
    }

    @Test
    public void thatDefault() {
        Set<ConstraintViolation<Address>> violations = validateClass(SUT);
        System.out.println("Default");
        for (ConstraintViolation<Address> constraintViolation : violations) {
            System.out.println(constraintViolation.getMessage());
        }


        assertEquals(1, violations.size());
    }
    @Test
    public void thatDefaultWithSomeData() {
        SUT.setCity("1234567890123456789012345678901");
        Set<ConstraintViolation<Address>> violations = validateClass(SUT);
        System.out.println("Default");
        for (ConstraintViolation<Address> constraintViolation : violations) {
            System.out.println(constraintViolation.getMessage());
        }


        assertEquals(1, violations.size());
    }
    private Set<ConstraintViolation<Address>> validateClass(Address myClass) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Address>> violations = validator.validate(myClass);
        return violations;
    }
    private Set<ConstraintViolation<Address>> validateClass(Address myClass, Class<?> clas) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Address>> violations = validator.validate(myClass, clas);
        return violations;
    }

}