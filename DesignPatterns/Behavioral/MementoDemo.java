package DesignPatterns.Behavioral;

import java.util.ArrayList;

class Notes{
    private ArrayList<String> strings = new ArrayList<>();
    private int numPages;

    public Notes(int numPages, ArrayList<String> strings){
        this.numPages = numPages;
        this.strings = strings;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public void setStrings(ArrayList<String> strings) {
        this.strings = strings;
    }

    public ArrayList<String> getStrings() {
        return strings;
    }

    public Memento save(){
        return new Memento(this, numPages, strings);
    }

    public void printStrings(){
        for(int i = 0; i < strings.size(); i++)
            System.out.println(strings.get(i));
    }
}

class Memento{
    private Notes notes;
    private ArrayList<String> strings = new ArrayList<>();
    private int numPages;

    public Memento(Notes notes, int numPages, ArrayList<String> strings){
        this.notes = notes;
        this.numPages = numPages;
        this.strings = strings;
    }

    public void restore(){
        notes.setNumPages(numPages);
        notes.setStrings(strings);
    }

    public Notes getNotes() {
        return notes;
    }
}

public class MementoDemo {
    public static void main(String[] args) {
        ArrayList<String> strings1 = new ArrayList<>();
        strings1.add("Hello world!");
        strings1.add("I am your father!");
        strings1.add("Money is strength!");
        Notes notes = new Notes(60, strings1);
        System.out.println("After initialization: ");
        notes.printStrings();
        Memento memento = notes.save();
        ArrayList<String> strings2 = new ArrayList<>();
        strings2.add("One word is two words");
        strings2.add("And two words is three words");
        notes.setStrings(strings2);
        System.out.println("\nAfter reset: ");
        notes.printStrings();
        memento.restore();
        System.out.println("\nAfter restore: ");
        notes.printStrings();
    }
}
