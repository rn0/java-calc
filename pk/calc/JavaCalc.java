package pk.calc;

import java.io.*;

// sin(PI/2+2*PI)+cos(2*2*PI)+tg(2*PI)+ctg(PI/2+2*PI)+2^2/2-2+2*2-4+((2+2)*2-(4/2*2)*2)
// 1 + 1 + 0 + 0

class JavaCalc {
  public static void main(String args[]) {
    Util.showLogs = true;
    
    System.out.println("---------------------------------------");
    System.out.println("Java Calculator - rn0");
    System.out.println("---------------------------------------");
    System.out.println("Operatory:\t\t+, -, *, /, ^");
    System.out.println("Funkcje:\t\tsin, cos, tg, ctg");
    System.out.println("Sta³e:\t\t\tPI");
    System.out.println("---------------------------------------");
    System.out.println("Przyk³ad:\t\t((2.0+7)/3+(14-3)*4)/2");
    System.out.println("Przyk³ad:\t\tsin(PI/2+2*PI)");
    System.out.println("---------------------------------------");
    System.out.println("Wcisnij Ctrl+C by wyjœæ");
    System.out.println("---------------------------------------");
     
    String expr = null;
    
    while(true) {
      System.out.print(":> ");
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
  
      try { 
        expr = br.readLine(); 
      } catch (IOException ioe) { 
        System.out.println("IO error trying to read math expression!"); 
        System.exit(1); 
      }
  
      try {
        Calc calc = new Calc(expr);
        System.out.print(" = " + calc.evaluate() + "\n");
      }
      catch(Exception e) {
         e.printStackTrace();
      }
    }
  }
}