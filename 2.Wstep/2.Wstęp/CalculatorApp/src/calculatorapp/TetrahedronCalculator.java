package calculatorapp;
import java.util.*;

public class TetrahedronCalculator implements Calculator {
    double a; 
    
    TetrahedronCalculator()
    {
        a=1;
    }
    TetrahedronCalculator(double a)
    {
        this.a=a;
    }

    private void WhatAboutA()
    {
        Scanner in= new Scanner(System.in);
        System.out.println("Please, enter A");
        this.a=in.nextDouble();
    }
    @Override
    public double calculateBaseArea() {
        this.WhatAboutA();
        return(0.433*Math.pow(this.a, 2));
    }

    @Override
    public double calculateBasePerimeter() {
        this.WhatAboutA();
        return(a*3);
        
    }
    
}
