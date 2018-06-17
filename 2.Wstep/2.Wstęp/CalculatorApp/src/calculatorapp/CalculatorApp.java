package calculatorapp;
//import javax.swing.JOptionPane;

public class CalculatorApp {
    public static void main(String[] args) {
        double result;
        ConeCalculator cone= new ConeCalculator();
        TetrahedronCalculator tetrahedron = new TetrahedronCalculator();
        
        result=cone.calculateBaseArea();
        System.out.println("Base Area c= "+result);
        
        result=cone.calculateBasePerimeter();
        System.out.println("Base Perimeter c= "+result);
        
        result=tetrahedron.calculateBaseArea();
        System.out.println("Base Area t= "+result);
        
        result=tetrahedron.calculateBasePerimeter();
        System.out.println("Base Perimeter t= "+result);
        
//       String text;
//       text=JOptionPane.showInputDialog("qwertyu");
//       System.out.println(text);
//       JOptionPane.showMessageDialog(null, text);
    }
}


