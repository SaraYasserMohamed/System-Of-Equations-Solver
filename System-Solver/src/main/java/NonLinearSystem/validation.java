package NonLinearSystem;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import javax.swing.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Locale;

public class validation {
    boolean check_brackets(String expr){
        Deque<Character> stack = new ArrayDeque<Character>();
        for (int i = 0; i < expr.length(); i++)
        {
            char x = expr.charAt(i);
            if (x == '(')
            {
                stack.push(x);
                continue;
            }
            else if(x == ')'){
                stack.pop();
            }
//            if (stack.isEmpty())
//                return false;
//            char check;
//            switch (x) {
//                case ')':
//                    System.out.println("tota");
//                    check = stack.pop();
//                    if (check == '{' || check == '[')
//                        return false;
//                    break;
//            }
            }
        return (stack.isEmpty());
    }

    public boolean validate(String eqn){
        eqn = eqn.toLowerCase(Locale.ROOT);
        boolean state=true;
        if(!(eqn.contains("sin")||eqn.contains("cos")||eqn.contains("^")||eqn.contains("e"))){
            JOptionPane.showMessageDialog(null, "Sorry, the equation does not include poly, sin or cos");
            return false;
        }else if(eqn.contains("<") || eqn.contains(">")){
            JOptionPane.showMessageDialog(null, "Sorry, Incorrect format");

            System.out.println("Sorry, Incorrect format");
            return false;
        }
        else if(!check_brackets(eqn)){
            JOptionPane.showMessageDialog(null, "Sorry, Incorrect format");
            System.out.println("Sorry, Incorrect format");
            return false;
        }
        else if(eqn.contains("tan")){
            JOptionPane.showMessageDialog(null, "Sorry, Incorrect format");
            System.out.println("Sorry, Incorrect format");
            return false;
        }else{
            int x = 1;
                if(eqn.contains("=")){
                    String[] equation = eqn.split("=");
                    if (equation.length != 2){
                        JOptionPane.showMessageDialog(null, "Invalid equation");
                        return false;
//				System.exit(1);
                    }
                    double result= 0;
                    try {

                        Expression expression = new ExpressionBuilder(equation[0])
                                .variables("x")
                                .build()
                                .setVariable("x", x);
                        result = expression.evaluate();
                    }
                    catch (Exception e){
                        JOptionPane.showMessageDialog(null, "Invalid equation");
                        return false;
//				return new String[]{"null","null","null"};
                    }
                    try {


                        Expression expression2 = new ExpressionBuilder(equation[1])
                                .variables("x")
                                .build()
                                .setVariable("x", x);

                        result -= expression2.evaluate();
                    }
                    catch (Exception e){
                        System.out.println("Invvalid equation");
                        return false;
                    }
                }else { double result = 0;
                    try {
                        Expression expression = new ExpressionBuilder(eqn)
                                .variables("x")
                                .build()
                                .setVariable("x", x);

                        result = expression.evaluate();}
                    catch (Exception e){
                        System.out.println("Invvalid equation");
                        return false;
                    }

                }

        }


        return state;
    }
}
