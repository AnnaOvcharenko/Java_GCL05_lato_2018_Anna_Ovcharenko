

import errorhandling.BasicCalculator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class BasicCalculatorJUnitTest {
    public BasicCalculator myBasicCalculator= new BasicCalculator();
    
    public BasicCalculatorJUnitTest() {
        //myBasicCalculator = new BasicCalculator();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testCalculateSum(){
        assertEquals(30.0, myBasicCalculator.calculateSum(10.0, 20.0),0);
        assertEquals(20.0, myBasicCalculator.calculateSum(20.0, 0.0),0);
        assertEquals(150.0, myBasicCalculator.calculateSum(-50.0, 200.0),0);
    }
    
    @Test
     public void testCalculateDifference(){
       assertEquals(-10.0, myBasicCalculator.calulateDifference(10.0, 20.0),0);
       assertEquals(10.0, myBasicCalculator.calulateDifference(30.0, 20.0),0);
       assertEquals(9.0, myBasicCalculator.calulateDifference(10.0, 1.0),0);
       
    }
     @Test
      public void testCalculateMultiplication(){
        assertEquals(200.0, myBasicCalculator.calculateMultiplication(10.0, 20.0),0);
        assertEquals(0.0, myBasicCalculator.calculateMultiplication(10.0, 0.0),0);
        assertEquals(20.0, myBasicCalculator.calculateMultiplication(10.0, 2.0),0);
    }
     @Test 
    public void testCalculateDivision(){
        assertEquals(5.0, myBasicCalculator.calculateDivision(10.0, 2.0),0);
          assertEquals(10.0, myBasicCalculator.calculateDivision(10.0, 1.0),0);
            assertEquals(2.0, myBasicCalculator.calculateDivision(10.0, 5.0),0);
    }
     @Test 
    public void testCalculatePow(){
        assertEquals(100.0, myBasicCalculator.calculatePow(10.0, 2.0),0);
        assertEquals(1.0, myBasicCalculator.calculatePow(10.0, 0.0),0);
        assertEquals(4.0, myBasicCalculator.calculatePow(2.0, 2.0),0);
    }
    @Test
    public void testCalculateSqrt(){
        assertEquals(3.0, myBasicCalculator.calculateSqrt(9.0),0);
        assertEquals(2.0, myBasicCalculator.calculateSqrt(4.0),0);
        assertEquals(1.0, myBasicCalculator.calculateSqrt(1.0),0);
    }
    
    
    //TEST EXCEPTIONS:
    @Test (expected = IllegalArgumentException.class)
    public void testDivisionEx(){
        myBasicCalculator.calculateDivision(2, 0);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testSqrtEx(){
        myBasicCalculator.calculateSqrt(-1);
    }
    

    
    
}
