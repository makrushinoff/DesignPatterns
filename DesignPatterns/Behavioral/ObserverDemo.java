package DesignPatterns.Behavioral;

import org.w3c.dom.events.Event;

import java.util.ArrayList;
import java.util.HashMap;

enum Events{
    newMagazine, newFruit;
}

enum Products{
    magazine(100), fruit(50);
    private int cost;

    Products(int cost){
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}

interface EventListener{
    void update();
    Events getDesireEvent();
}

class EventManager{
    private HashMap<Events, EventListener> listeners = new HashMap<>();

    public void subscribe(Events event, EventListener listener){
        listeners.put(event, listener);
    }

    public void unsubscribe(Events event, EventListener listener){
        listeners.remove(event, listener);
    }

    public void notifying(Events event){
        for(EventListener listener  : listeners.values()){
            if(listener.getDesireEvent() == event){
                listener.update();
            }
        }
    }
}

class MagazineListener implements EventListener{
    private final String email;
    private final Events desireEvent = Events.newMagazine;
    private ArrayList<Products> magazines = new ArrayList<>();
    private int cash;
    private boolean flag = false;

    public MagazineListener(String email, int cash){
        this.email = email;
        this.cash = cash;
    }

    @Override
    public void update() {
        if(cash > 100){
            magazines.add(Products.magazine);
            cash -= Products.magazine.getCost();
            flag = true;
        }
    }

    public void wasBought(){
        System.out.println("Was magazine bought: " + flag);
    }

    public Events getDesireEvent() {
        return desireEvent;
    }
}

class FruitListener implements EventListener{
    private String address;
    private final Events desireEvent = Events.newFruit;
    private ArrayList<Products> fruits = new ArrayList<>();
    private int cash;

    public FruitListener(String address, int cash){
        this.address = address;
        this.cash = cash;
    }

    @Override
    public void update() {
        while(cash > 0){
            fruits.add(Products.fruit);
            cash -= Products.fruit.getCost();
        }
    }

    public void printData(){
        System.out.println("Amount of fruits has bought: " + fruits.size());
    }

    public Events getDesireEvent() {
        return desireEvent;
    }
}

class Market {
    private EventManager manager;
    private ArrayList<Products> products = new ArrayList<>();

    public Market(EventManager manager){
        this.manager = manager;
    }

    public void addMagazine(){
        products.add(Products.magazine);
        manager.notifying(Events.newMagazine);
    }

    public void addFruit(){
        products.add(Products.fruit);
        manager.notifying(Events.newFruit);
    }

}

public class ObserverDemo {
    public static void main(String[] args) {
        EventManager manager = new EventManager();
        Market market = new Market(manager);
        MagazineListener magazineListener = new MagazineListener("abba@bk.ua", 5000);
        FruitListener fruitListener = new FruitListener("labba, 44", 600);

        manager.subscribe(magazineListener.getDesireEvent(), magazineListener);
        manager.subscribe(fruitListener.getDesireEvent(), fruitListener);

        market.addFruit();
        market.addMagazine();

        magazineListener.wasBought();
        fruitListener.printData();

    }
}
