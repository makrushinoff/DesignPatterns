package DesignPatterns.Creational;

class Database{
    private static Database instance;
    private String value;
    private Database(String str){
        value = str;
    }

    public static Database getInstance(String str){
        if(instance == null){
            instance = new Database(str);
        }
        return instance;
    }
    public String getValue(){
        return value;
    }
}

public class SingletonDemo {
    public static void main(String[] args) {
        System.out.println("Here should be 2 the same strings");
        Database data1 = Database.getInstance("I am the one database!");
        Database data2 = Database.getInstance("No, I am the one!");
        System.out.println(data1.getValue());
        System.out.println(data2.getValue());

    }
}
