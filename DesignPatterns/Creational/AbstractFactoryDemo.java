package DesignPatterns.Creational;

import java.util.Scanner;

interface Shirt{
    void paint();
}

interface Pants{
    void paint();
}

class ModernShirt implements Shirt{

    @Override
    public void paint() {
        System.out.println("This is MODERN SHIRT.");
    }
}

class ClassicShirt implements Shirt{

    @Override
    public void paint() {
        System.out.println("This is CLASSIC SHIRT.");
    }
}

class ModernPants implements Pants{
    @Override
    public void paint(){
        System.out.println("This is MODERN PANTS.");
    }
}

class ClassicPants implements Pants{
    @Override
    public void paint() {
        System.out.println("This is CLASSIC PANTS.");
    }
}

interface ClothesFactory{
    Pants createPants();
    Shirt createShirt();
}

class ModernFactory implements ClothesFactory{

    @Override
    public ModernPants createPants(){
        return new ModernPants();
    }

    @Override
    public ModernShirt createShirt() {
        return new ModernShirt();
    }
}

class ClassicFactory implements ClothesFactory{

    @Override
    public ClassicPants createPants(){
        return new ClassicPants();
    }

    @Override
    public ClassicShirt createShirt(){
        return new ClassicShirt();
    }
}

class Application{
    private ClothesFactory factory;
    private Pants pants;
    private Shirt shirt;
    public Application(ClothesFactory factory){
        this.factory = factory;
        create_attributes();
        pants.paint();
        shirt.paint();
    }
    private void create_attributes(){
        pants = factory.createPants();
        shirt = factory.createShirt();
    }

}
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        System.out.println("Please, input a style:");
        Scanner input = new Scanner(System.in);
        String style = input.nextLine();
        ClothesFactory factory = null;
        if(style.equals("Classic"))
            factory = new ClassicFactory();
        else if(style.equals("Modern"))
            factory = new ModernFactory();
        Application app = new Application(factory);
    }
}
