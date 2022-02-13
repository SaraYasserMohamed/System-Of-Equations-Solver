package NonLinearSystem;

import java.util.ResourceBundle;
import java.util.Scanner;

public class NewtonRaphson extends Derivative
{
  // function for rounding results
  public static double Round(double value,int digit) {
    double scale =  Math.pow(10, digit);
    return Math.round(value * scale) / scale;
  }

  public static String[] newtonRaphson(String temp,double y, int maxIteration, int digit, double es)
  {
    validation eq = new validation();
    if (!eq.validate(temp)){
      return new String[]{"null","null","null"};
    }

    String steps = "";
    double start = System.currentTimeMillis();
    temp = temp.replaceAll("x","*\\x").replaceAll("\\+\\*","+")
            .replaceAll("\\-\\*","-").replaceAll("\\*\\*","*");
    if(temp.charAt(0)=='*'){temp= temp.substring(1, temp.length());}

    function f,f_,x;
    x = new function("x");
    f = new function(temp);
    //derivative the input
    temp = der(temp);
    //replace some symbols to simplify the output
    temp  = temp .replaceAll(("x"),("*\\x")).replaceAll("x\\^0","1").replaceAll(("x\\^1"),("x"));
    if(temp.charAt(0)=='+' || temp.charAt(0)=='-'){ temp = temp.substring(1,temp.length());}
    f_ = new function(temp);
    //
    variable_table table = f.get_variables_table();
    table.add_variable("x",Double.toString(y));

    int iteration = 0;
    double ea;
    do
    { // print steps value of (x, f, f')
      steps += "Iteration : " + iteration + "&";
      System.out.println("\n-----------------------------------\nIteration : "+iteration+"\n");
      double f_x,f__x,x_x;
      f_x = Round(f.get_value(table),digit);
      f__x =Round(f_.get_value(table),digit);
      x_x = Round(x.get_value(table), digit);
      steps += "x"+iteration+" = "+x_x + "&";
      System.out.println("x"+iteration+" = "+x_x);
      steps += "f"+iteration+" = "+f_x + "&";
      System.out.println("f"+iteration+" = "+f_x);
      steps += "f'"+iteration+" = "+f__x + "&";
      System.out.println("f'"+iteration+" = "+f__x);
      // ans of current iteration and x of the next iteration
      double ans =Round(x_x - Round((f_x /f__x ),digit), digit);
      System.out.println("x"+Integer.toString(iteration+1)+" = "+ans);
      steps += "x"+Integer.toString(iteration+1)+" = "+ans + "&";
      table.add_variable("x",Double.toString(ans));
      ea =Math.abs((ans - y)/ans);
      y = ans;
      iteration++;
    }while(ea>es&& iteration<maxIteration);
    y= Round(y,digit);
    double F= f.get_value(table);
    F = Round(F,digit);
    System.out.printf("\nf( %f ) = %f\n",y,F);

    String[] result = new String[3];
    result[0] = "x = " + y;
    result[1] = steps;

    double end = System.currentTimeMillis();
    double elapsedTime = end - start;
    System.out.println(elapsedTime+" ms");
    result[2] = "R.T = " + elapsedTime+" ms";
return result;
  }
  public static void main(String []args){
    String temp;
    //function should be 3*x not 3x
    System.out.println("Enter the function: ");
    Scanner input = new Scanner(System.in);
    temp = input.nextLine();
    System.out.println("Enter initial value for x : ");
    double y = input.nextFloat();

    newtonRaphson(temp,y,50,5,  0.00001);
  }
}

