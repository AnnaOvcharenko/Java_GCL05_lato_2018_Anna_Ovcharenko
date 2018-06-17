
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
 import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.Before;

import org.junit.runners.Parameterized;
//import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;
//import static org.junit.Assert.assertEquals;
import errorhandling.BasicCalculator;


@RunWith(Parameterized.class)
public class BasicCalculatorParameterizedExTest {
    private double inputNumber;
    private BasicCalculator myBasicCalculator;
    
@Before
    public void initialize() {
        myBasicCalculator = new BasicCalculator();
    }

    public BasicCalculatorParameterizedExTest(double inputNumber) {
        this.inputNumber = inputNumber;

    }

    @Parameterized.Parameters
    public static Collection Parameters() {
        return Arrays.asList(new Object[][]{
            {-1},
            {-4},
            {-10}

        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSqrtParametrized() {
        myBasicCalculator.calculateSqrt(inputNumber);

    }
    

}