package NonLinearSystem;

import com.example.phase2.Phase2Application;

public class fixedPoint {
    public static double Round(double value,int digit) {
        double scale =  Math.pow(10, digit);
        return Math.round(value * scale) / scale;
    }

    public String[] Fixedpt(String eqn, double x0, double es, int imax, int sign){
        validation eq = new validation();
        if (!eq.validate(eqn)){
            return new String[]{"null","null","null"};
        }

        double start = System.currentTimeMillis();
        double end = System.currentTimeMillis();
        if(sign == -1){
        Phase2Application evaluator = new Phase2Application();
        double xr = x0;
        double xr_old;
        double ea = 0; /////???????
        String gx = eqn + "+x";
        String steps = gx + "&";
        int i = 0;
        do {
            steps += "iteration" + i + "&";
            xr_old = xr;
            steps += "Xi = " + xr_old + "&";
            xr = evaluator.evaluateFunction(gx,xr_old);
            steps += "Xi+1 = " + xr + "&";
            if (xr != 0){
                ea = Math.abs((xr-xr_old)/xr);
                steps += "ea = |(" + xr +" - " + xr_old + "/)" + xr + "|&";
                i++;
            }
        }while (ea > es && i < imax);
        String res = "X = " + xr;
        String[] result = new String[3];
        result[0]  =res;
        result[1] = steps;
            end = System.currentTimeMillis();
            double elapsedTime = end - start;
        result[2] = "R.T = " + elapsedTime+ "ms";
        return result;
    }
    else {
            Phase2Application evaluator = new Phase2Application();
            double xr = x0;
            double xr_old;
            double ea = 0; /////???????
            String gx = eqn + "+x";
            String steps = gx + "&";
            int i = 0;
            do {
                steps += "iteration" + i + "&";
                xr_old = xr;
                steps += "Xi = " + xr_old + "&";
                xr = evaluator.evaluateFunction(gx,xr_old);
                xr = Round(xr,sign);
                steps += "Xi+1 = " + xr + "&";
                if (xr != 0){
                    ea = Math.abs((xr-xr_old)/xr);
                    steps += "ea = |(" + xr +" - " + xr_old + "/)" + xr + "|&";
                    i++;
                }
            }while (ea > es && i < imax);
            String res = "X = " + xr;
            String[] result = new String[3];
            result[0]  =res;
            result[1] = steps;
            end = System.currentTimeMillis();
            double elapsedTime = end - start;
            result[2] = "R.T = " + elapsedTime+ "ms";
            return result;
        }
    }
}
