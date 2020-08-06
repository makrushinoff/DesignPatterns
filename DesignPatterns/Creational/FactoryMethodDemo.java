package DesignPatterns.Creational;

interface Book{
    void setInformation();
}

class ScienceBook implements Book{
    @Override
    public void setInformation() {
        System.out.println("Desigh Patterns");
    }
}

class FictionBook implements Book{

    @Override
    public void setInformation() {
        System.out.println("451 gradus` on Fahrenheit");
    }
}

abstract class Factory{

    public abstract Book createBook();
}

class FictionMarket extends Factory{

    @Override
    public Book createBook() {
        return new FictionBook();
    }
}

class ScienceMarket extends Factory{

    @Override
    public Book createBook() {
        return new ScienceBook();
    }
}

public class FactoryMethodDemo {
    public static void main(String[] args) {

    }
}
