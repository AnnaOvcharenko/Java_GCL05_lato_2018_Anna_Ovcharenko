
package errorhandling;



public class BasicCalculator {
    
    public double calculateSum(double a, double b) throws IllegalArgumentException {
        return (a+b);   
    }
    
    public double calulateDifference(double a, double b)throws IllegalArgumentException{
        return (a-b);   
    }
    
     public double calculateMultiplication(double a, double b)throws IllegalArgumentException{
        return (a*b);   
    }
    
    public double calculateDivision(double a, double b)throws IllegalArgumentException{
        if (b==0)
            throw new IllegalArgumentException("division by zero");
        return (a/b);   
    }
    
     public double calculatePow(double base, double exponenet)throws IllegalArgumentException{
        return Math.pow(base, exponenet);   
    }
    
    public double calculateSqrt(double a)throws IllegalArgumentException{
        if (a<0)
            throw new IllegalArgumentException("Error: cannot be negative");
        return Math.sqrt(a);   
    }
    
            
}
