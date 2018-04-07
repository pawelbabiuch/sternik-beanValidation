package pl.sternik.pb.bv.validator;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

public class PastTest {

    private BeanWithPast SUT;

    @Before
    public void setup() {
        SUT = new BeanWithPast();
    }

    @Test
    public void thatNullIsValid() {
        Set<ConstraintViolation<BeanWithPast>> violations = validateClass(SUT);

        assertEquals(0, violations.size());
    }

    @Test
    public void thatYesterdayIsValid() throws Exception {
        SUT.setDate(LocalDate.now().minusDays(1));
        SUT.setDateTime(LocalDateTime.now().minusDays(1));

        Set<ConstraintViolation<BeanWithPast>> violations = validateClass(SUT);

        assertEquals(0, violations.size());
    }

    @Test
    public void thatTodayMidnightIsInvalid() throws Exception {
        SUT.setDate(LocalDate.now());

        Set<ConstraintViolation<BeanWithPast>> violations = validateClass(SUT);

        assertEquals(1, violations.size());
    }
    //Przykad niestabilnego testu
    @Test
    public void thatTodayNowIsValid() throws Exception {
        Set<ConstraintViolation<BeanWithPast>> violations;

        SUT.setDateTime(LocalDateTime.now());
        //System.out.println("Depends on speed....!");
//        Thread.sleep(10);
        violations = validateClass(SUT);
        assertEquals(1, violations.size());
        System.out.println("Now!");
        for (ConstraintViolation<BeanWithPast> constraintViolation : violations) {
            System.out.println(constraintViolation.getMessage());
        }

        SUT.setDateTime(LocalDateTime.now());
        violations = validateClass(SUT);
        assertEquals(1, violations.size());
    }

    @Test
    public void thatTomorrowIsInvalid() throws Exception {
        SUT.setDate(LocalDate.now().plusDays(1));
        SUT.setDateTime(LocalDateTime.now().plusDays(1));
        Set<ConstraintViolation<BeanWithPast>> violations = validateClass(SUT);
        System.out.println("Tommorow");
        for (ConstraintViolation<BeanWithPast> constraintViolation : violations) {
            System.out.println(constraintViolation.getMessage());
        }


        assertEquals(2, violations.size());
    }

    private Set<ConstraintViolation<BeanWithPast>> validateClass(BeanWithPast myClass) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<BeanWithPast>> violations = validator.validate(myClass);
        return violations;
    }

}