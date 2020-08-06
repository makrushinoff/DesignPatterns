package DesignPatterns.Structural;

import java.util.ArrayList;
import java.util.LinkedList;

interface Component{
    int countCost();
}

class Product implements Component{
    private int price;

    public Product(int price){
        this.price = price;
    }

    @Override
    public int countCost() {
       return price;
    }
}

class Box implements Component{
    private LinkedList<Component> components = new LinkedList<>();
    private int boxPrice;
    private int i = 0;
    public void put(Component component){
        components.add(i, component);
        i++;
    }

    public void delete(Component component){
        components.remove(component);
    }

    @Override
    public int countCost() {
        for(Component component : components) {
            boxPrice += component.countCost();
        }
        return boxPrice;
    }
}
public class CompositeDemo {
    public static void main(String[] args){
        Box main_box = new Box();
        Product prod1 = new Product(15);
        Product prod2 = new Product(10);
        Product prod3 = new Product(13);
        Product prod4 = new Product(20);
        Product prod5 = new Product(100);
        Product prod6 = new Product(4);
        Product prod_left = new Product(77);
        Box box1 = new Box();
        Box box2 = new Box();
        box1.put(prod1);
        box1.put(prod2);
        box1.put(prod3);
        box2.put(prod4);
        box2.put(prod5);
        box2.put(prod6);
        box1.put(box2);
        main_box.put(prod_left);
        main_box.put(box1);
        System.out.println(main_box.countCost());
    }
}
