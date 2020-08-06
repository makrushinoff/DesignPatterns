package DesignPatterns.Behavioral;

import java.io.IOException;
import java.util.Scanner;

interface Strategy{
    int execute(int a, int b);
}

class AddStrategy implements Strategy{
    @Override
    public int execute(int a, int b) {
        return a + b;
    }
}

class SubtractStrategy implements Strategy{
    @Override
    public int execute(int a, int b) {
        return a - b;
    }
}

class MultiplyStrategy implements Strategy{
    @Override
    public int execute(int a, int b) {
        return a * b;
    }
}

class DivideStrategy implements Strategy{
    @Override
    public int execute(int a, int b) {
        return a / b;
    }
}

class Context {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int a, int b){
        return strategy.execute(a, b);
    }
}

public class StrategyDemo {
    public static void main(String[] args)  {
        Context context = new Context();
        System.out.println("Please, input first number: ");
        Scanner input = new Scanner(System.in);
        int num1 = input.nextInt();
        System.out.println("Please, input second number: ");
        int num2 = input.nextInt();

        while(true) {
        System.out.println("Choose operation:");
        System.out.println("1. Add");
        System.out.println("2. Subtract");
        System.out.println("3. Multiply");
        System.out.println("4. Divide");
        Scanner choiceInput = new Scanner(System.in);
        int choice = choiceInput.nextInt();

            switch (choice) {
                case 1 -> context.setStrategy(new AddStrategy());
                case 2 -> context.setStrategy(new SubtractStrategy());
                case 3 -> context.setStrategy(new MultiplyStrategy());
                case 4 -> context.setStrategy(new DivideStrategy());
                default -> {
                    return;
                }
            }
            int res = context.executeStrategy(num1, num2);
            System.out.println("Result: " + res);
        }
    }
}
