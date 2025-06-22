import java.util.Scanner;

public class LiveFinancialForecaster {
    private double sumGrowth;
    private int dataCount;
    private double lastValue;
    private boolean firstValue = true;
    
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Live Financial Forecaster");
        System.out.println("Enter 'quit' to exit");
        System.out.println("-----------------------------");
        
        while (true) {
            System.out.print("Enter next value (or 'predict'): ");
            String input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("quit")) {
                break;
            }
            
            if (input.equalsIgnoreCase("predict")) {
                if (dataCount < 2) {
                    System.out.println("Need at least 2 values to predict");
                    continue;
                }
                System.out.print("Enter periods to predict: ");
                int periods = Integer.parseInt(scanner.nextLine());
                double forecast = predictFutureValue(lastValue, periods);
                System.out.printf("Forecast after %d periods: %.2f\n", periods, forecast);
                continue;
            }
            
            try {
                double value = Double.parseDouble(input);
                addDataPoint(value);
                System.out.printf("Added %.2f | Current growth rate: %.2f%%\n", value, getCurrentGrowthRate());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number or command");
            }
        }
        
        scanner.close();
        System.out.println("Forecasting ended");
    }
    
    private void addDataPoint(double newValue) {
        if (!firstValue) {
            double growth = (newValue - lastValue) / lastValue;
            sumGrowth += growth;
            dataCount++;
        } else {
            firstValue = false;
        }
        lastValue = newValue;
    }
    
    private double getCurrentGrowthRate() {
        return (sumGrowth / dataCount) * 100;
    }
    
    private double predictFutureValue(double currentValue, int periods) {
        if (periods == 0) return currentValue;
        double growthRate = getCurrentGrowthRate();
        double nextValue = currentValue * (1 + growthRate / 100);
        sumGrowth += (nextValue - currentValue) / currentValue;
        dataCount++;
        return predictFutureValue(nextValue, periods - 1);
    }

    public static void main(String[] args) {
        new LiveFinancialForecaster().run();
    }
}
