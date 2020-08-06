package DesignPatterns.Structural;

interface Payment{
    void pay(int money);
    int getCurrentBalance();
}

class Cash implements Payment{
    private int currentBalance;

    public Cash(int currentBalance){
        this.currentBalance = currentBalance;
    }
    @Override
    public void pay(int money) {
        currentBalance -= money;
        System.out.println("-" + money + " from your balance");
        System.out.println(currentBalance + " left on your balance");
    }

    public void curBal(){
        System.out.println(currentBalance + " your current balance");
    }

    public int getCurrentBalance() {
        return currentBalance;
    }
}

class CreditCard implements  Payment{
    private Cash cash;

    public CreditCard(int balance){
        cash = new Cash(balance);
    }

    @Override
    public void pay(int money) {
        if(check(money))
            cash.pay(money);
        else{
            System.out.println("Can`t be payed! Too less money!");
            return;
        }
    }
    @Override
    public int getCurrentBalance(){
        return cash.getCurrentBalance();
    }

    public void curBal(){
        System.out.println(cash.getCurrentBalance() + " your current balance");
    }

    public boolean check(int money){
        return cash.getCurrentBalance() - money >= 0;
    }
}

public class ProxyDemo {
    public static void main(String[] args) {
        CreditCard card = new CreditCard(1500);

        card.curBal();
        card.pay(1000);

        card.pay(200);
        card.pay(500);
    }

}
