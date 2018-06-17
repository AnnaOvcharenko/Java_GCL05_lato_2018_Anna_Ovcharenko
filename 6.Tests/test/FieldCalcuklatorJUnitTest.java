
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import errorhandling.FieldCalculator;

public class FieldCalcuklatorJUnitTest {
    public FieldCalculator myFieldCalculator = new FieldCalculator();
    
    public FieldCalcuklatorJUnitTest() {
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
    public void testCalculateSquare(){
        assertEquals(4, myFieldCalculator.calculateSquare(2), 0);
        assertEquals(1, myFieldCalculator.calculateSquare(1), 0);
        assertEquals(16, myFieldCalculator.calculateSquare(4), 0);
        
    }
    
    @Test
    public void testCalculateRectangle(){
        assertEquals(2, myFieldCalculator.calculateRectangle(1, 2), 0);
         assertEquals(6, myFieldCalculator.calculateRectangle(3, 2), 0);
          assertEquals(18, myFieldCalculator.calculateRectangle(9, 2), 0); 
    }
    
     @Test
    public void testCalculateCircle(){
        assertEquals(Math.PI, myFieldCalculator.calculateCircle(1), 0.0);
        assertEquals(4*Math.PI, myFieldCalculator.calculateCircle(2), 0.0);
        assertEquals(9*Math.PI, myFieldCalculator.calculateCircle(3), 0.0);
        
    }
    
     @Test
    public void testCalculateTriangle(){
        assertEquals((Math.sqrt(3)/4), myFieldCalculator.calculateTriangle(1), 0);
        assertEquals(Math.sqrt(3), myFieldCalculator.calculateTriangle(2), 0);
        assertEquals((Math.sqrt(3)/4)*9, myFieldCalculator.calculateTriangle(3), 0);  
    }
    
    
     //TEST EXCEPTIONS:
    
    @Test (expected = IllegalArgumentException.class)
    public void testSquareEx(){
        myFieldCalculator.calculateSquare(-1);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testRectangleEx(){
        myFieldCalculator.calculateRectangle(-1,0);
    }
    
     @Test (expected = IllegalArgumentException.class)
    public void testCircleEx(){
        myFieldCalculator.calculateCircle(0);
    }
    
     @Test (expected = IllegalArgumentException.class)
    public void testTriangleEx(){
        myFieldCalculator.calculateTriangle(-10);
    }
    
    
    
}
