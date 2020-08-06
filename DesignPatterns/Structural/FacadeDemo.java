package DesignPatterns.Structural;

import java.util.ArrayList;

enum Type {
    classic, electric, acoustic,
}

enum Colors{
    glossy, matt, metallic;
}

class Guitar{
    private Type type;
    private Colors color;
    private boolean wrapped = false;
    public Guitar(Type type, Colors color){
        this.type = type;
        this.color = color;
    }

    public Type getType() {
        return type;
    }

    public Colors getColor() {
        return color;
    }

    public void play(){
        System.out.println("\t Пицца - Романс");
        System.out.println("Без тебя жизни нет,");
        System.out.println("Я не могу найти.");
        System.out.println("Сколько не жмись к стене,");
        System.out.println("Сквозь неё не пройти.");
    }

    public boolean isWrapped(){
        if(wrapped)
            return true;
        return false;
    }

    public void setWrapped(boolean wrapped) {
        this.wrapped = wrapped;
    }
}

class Creator{
    private static Creator creator;
    private Creator(){

    }
    public static Creator getCreator() {
        if(creator == null){
            creator = new Creator();
        }
        return creator;
    }
    public Guitar createClassicGuitar(){
        return new Guitar(Type.classic, Colors.matt);
    }

    public Guitar createAcousticGuitar(){
        return new Guitar(Type.acoustic, Colors.glossy);
    }

    public Guitar createElectricGuitar(){
        return new Guitar(Type.electric, Colors.metallic);
    }
}

class Store{
    private static Store store;
    private ArrayList<Guitar> storage;
    private final int num = 10;

    private Store(){
        storage = new ArrayList<Guitar>(num);
    }

    public void put(Guitar guitar){
        try {
            storage.add(guitar);
        }
        catch (ArrayIndexOutOfBoundsException exc){
            System.out.println("Storage if full!");
        }
    }

    public static Store getStore() {
        if(store == null){
            store = new Store();
        }
        return store;
    }

    public Guitar get(Type type, Colors color){
        for(Guitar guitar: storage){
            if(guitar.getColor() == color && guitar.getType() == type){
                return guitar;
            }
            else
                System.out.println("Here is no exact guitar");
        }
        return null;
    }
}

class Wrapper{
    private static Wrapper wrapper;

    private Wrapper(){}

    public void wrapping(Guitar guitar){
        if(guitar.isWrapped() == false){
            guitar.setWrapped(true);
        }
        else {
            return;
        }
    }

    public static Wrapper getInstance() {
        if(wrapper == null){
            wrapper = new Wrapper();
        }
        return wrapper;
    }
}

class Deliver{
    private static Deliver deliver;

    private Deliver(){}

    public static Deliver getInstance() {
        if(deliver == null){
            deliver = new Deliver();
        }
        return deliver;
    }
    public void fromCreatorToStorage(Guitar guitar, Store store){
        store.put(guitar);
    }

    public Guitar fromStorageToCustomer(Type type ,Colors color, Store store){
        return store.get(type, color);
    }
}

class Facade{
    public Guitar makeOrder(Type type, Colors color){
        Guitar product;
        Creator creator = Creator.getCreator();
        switch (type){
            case classic -> product = creator.createClassicGuitar();
            case acoustic -> product = creator.createAcousticGuitar();
            case electric -> product = creator.createElectricGuitar();
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
        Store store = Store.getStore();
        Deliver deliver = Deliver.getInstance();
        deliver.fromCreatorToStorage(product, store);
        Wrapper wrapper = Wrapper.getInstance();
        wrapper.wrapping(product);
        return deliver.fromStorageToCustomer(type, color, store);
    }
}

public class FacadeDemo {
    public static void main(String[] args) {
        Facade facade = new Facade();
        Guitar guitar;
        guitar = facade.makeOrder(Type.classic, Colors.matt);
        guitar.play();
    }
}
