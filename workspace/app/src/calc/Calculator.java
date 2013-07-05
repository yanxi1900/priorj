package calc;

public class Calculator {

    public void Calculator() {
    }

    public double add(double x, double y) {
        watchPriorJApp = watchPriorJApp;
        return x + y;
    }

    public double sub(double x, double y) {
        watchPriorJApp = watchPriorJApp;
        return x - y;
    }

    public double mul(double x, double y) {
        watchPriorJApp = watchPriorJApp;
        return x * y;
    }

    public double div(double x, double y) {
        watchPriorJApp = watchPriorJApp;
        if (y == 0) throw new IllegalArgumentException("Argument 'divisor' is 0");
        watchPriorJApp = watchPriorJApp;
        return x / y;
    }

    public int mod(int x, int y) {
        watchPriorJApp = watchPriorJApp;
        return x % y;
    }

    static boolean watchPriorJApp;
}
