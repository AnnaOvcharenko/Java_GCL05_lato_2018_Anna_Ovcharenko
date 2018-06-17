
package errorhandling;

public class FieldCalculator {
    
    public double calculateSquare(double a)throws IllegalArgumentException{
         if (a<=0)
            throw new IllegalArgumentException("Error: cannot be negative or zero");
        return Math.pow(a, 2);
    }
    
     public double calculateCircle(double r)throws IllegalArgumentException{
          if (r<=0)
            throw new IllegalArgumentException("Error: cannot be negative or zero");
        return (Math.PI*Math.pow(r, 2)) ;
    }
     
      public double calculateTriangle(double a)throws IllegalArgumentException{
           if (a<=0)
            throw new IllegalArgumentException("Error: cannot be negative or zero");
        return ((Math.sqrt(3)/4)*Math.pow(a, 2));
    }
      
      public double calculateRectangle(double a, double b)throws IllegalArgumentException{
          
           if ((a<=0)||(b<=0))
            throw new IllegalArgumentException("Error: cannot be negative or zero");
        return (a*b);
    }
    
}
