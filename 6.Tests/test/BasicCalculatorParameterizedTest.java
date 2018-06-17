
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.Before;

import org.junit.runners.Parameterized;
//import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import errorhandling.BasicCalculator;

/**
 *
 * @author Anna
 */
@RunWith(Parameterized.class)
public class BasicCalculatorParameterizedTest {

    private double inputNumber;
    private double expectedResult;
    private BasicCalculator myBasicCalculator;

    @Before
    public void initialize() {
        myBasicCalculator = new BasicCalculator();
    }

    public BasicCalculatorParameterizedTest(double inputNumber, double expectedResult) {
        this.inputNumber = inputNumber;
        this.expectedResult = expectedResult;

    }

    @Parameterized.Parameters
    public static Collection Parameters() {
        return Arrays.asList(new Object[][]{
            {4, 2},
            {9, 3},
            {1, 1}

        });
    }

    @Test
    public void testSqrtParametrized() {
        assertEquals(expectedResult, myBasicCalculator.calculateSqrt(inputNumber), 0);

    }
}

// TODO add test methods here.
// The methods must be annotated with annotation @Test. For example:
//
// @Test
// public void hello() {}

