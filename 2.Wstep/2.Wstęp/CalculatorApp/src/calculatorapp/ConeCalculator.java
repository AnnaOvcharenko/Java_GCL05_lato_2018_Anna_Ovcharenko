package calculatorapp;
import java.util.*;

public class ConeCalculator implements Calculator {
    double r;
    ConeCalculator()
    {
        r=1;
    }
    
     ConeCalculator(double r)
    {
        this.r=r;
    }
     
     private void WhatAboutR()
     {
         Scanner in= new Scanner(System.in);
         System.out.println("Please, enter R");
         this.r=in.nextDouble();
     }

    @Override
    public double calculateBaseArea() {
        this.WhatAboutR();
        return (3.14*Math.pow(r, 2));
        
    }

    @Override
    public double calculateBasePerimeter() {
        this.WhatAboutR();
        return(2*3.14*this.r);
        
    }
    
}
