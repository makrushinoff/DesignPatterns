package DesignPatterns.Creational;

abstract class FigurePrototype {
    protected  double square;
    protected String color;

    public FigurePrototype(double square, String color){
        this.square = square;
        this.color = color;
    }

    FigurePrototype(FigurePrototype prototype){
        if(prototype != null){
            this.square = prototype.square;
            this.color = prototype.color;
        }
    }
    public abstract FigurePrototype clone();

    public boolean equals(Object object){
        if(!(object instanceof FigurePrototype)) return false;
        FigurePrototype prototype = (FigurePrototype) object;
        return prototype.square == square && prototype.color.equals(color);
    }
}

class Circle extends FigurePrototype{
    public double radius;
    public static final double pi = 3.1415;
    public Circle(double square, String color, double radius){
        super(square, color);
        this.radius = radius;
    }

    public Circle(Circle circle){
        super(circle);
        if(circle != null){
            this.radius = circle.radius;
        }
    }
    public double length(){
        return 2 * pi * radius;
    }

    @Override
    public FigurePrototype clone() {
        return new Circle(this);
    }
}

class Rectangle extends FigurePrototype{
    private double width;
    private double length;

    public Rectangle(double width, double length, String color){
        super(width * length, color);
        this.length = length;
        this.width = width;
    }

    public Rectangle(Rectangle rectangle){
        super(rectangle);
        if(rectangle != null){
            this.width = rectangle.width;
            this.length = rectangle.length;
        }
    }

    @Override
    public FigurePrototype clone() {
        return new Rectangle(this);
    }

    public void printInfo(){
        System.out.println("Width: " + width);
        System.out.println("Length: " + length);
        System.out.println("Square: " + square);
        System.out.println("Color: " + color + "\n");
    }
}

public class PrototypeDemo {
    public static void main(String[] args) {
        Rectangle rect1 = new Rectangle(5, 12, "Black");
        rect1.printInfo();
        Rectangle rect2 = (Rectangle) rect1.clone();
        rect2.printInfo();
        System.out.println(rect1.equals(rect2));
    }
}
