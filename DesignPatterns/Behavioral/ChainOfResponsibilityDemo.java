package DesignPatterns.Behavioral;

import com.sun.tools.javac.Main;

import javax.imageio.stream.ImageInputStream;
import java.util.HashMap;
import java.io.*;

abstract class Middleware{
    private Middleware next;

    public Middleware linkWith(Middleware next){
        this.next = next;
        return next;
    }

    public abstract boolean check(String email, String password);

    protected boolean checkNext(String email, String password){
        if(next == null){
            return true;
        }
        return next.check(email, password);
    }
}

class ThrottlingMiddleware extends Middleware{
    private int requestPerMinute;
    private int request;
    private long currentTime;

    public ThrottlingMiddleware(int requestPerMinute){
        this.requestPerMinute = requestPerMinute;
        this.currentTime = System.currentTimeMillis();
    }

    @Override
    public boolean check(String email, String password) {
        if(System.currentTimeMillis() > currentTime + 60_000){
            request = 0;
            currentTime = System.currentTimeMillis();
        }

        request++;

        if(request > requestPerMinute){
            System.out.println("Request limit exceeded");
            Thread.currentThread().stop();
        }
        return checkNext(email, password);
    }
}

class Server {
    private HashMap<String, String> users = new HashMap<>();
    private Middleware middleware;

    public void setMiddleware(Middleware middleware){
        this.middleware = middleware;
    }

    public boolean logIn(String email, String password){
        if(middleware.check(email, password)){
            System.out.println("Authorization success");
            return true;
        }
        return false;
    }

    public void register(String email, String password){
        users.put(email, password);
    }

    public boolean hasEmail(String email){
        return users.containsKey(email);
    }

    public boolean isValidPassword(String email, String password){
        return users.get(email).equals(password);
    }
}

class UserExistMiddleware extends Middleware{
    private Server server;

    public UserExistMiddleware(Server server){
        this.server = server;
    }

    @Override
    public boolean check(String email, String password) {
        if(!server.hasEmail(email)){
            System.out.println("No email in database!");
            return false;
        }
        if(!server.isValidPassword(email, password)){
            System.out.println("Wrong password!");
            return false;
        }
        return checkNext(email, password);
    }
}

class RoleCheckMiddleware extends Middleware{
    @Override
    public boolean check(String email, String password) {
        if(email.equals("admin")){
            System.out.println("Hello, admin!");
            return false;
        }
        else{
            System.out.println("Hello, " + email + "!");
            return checkNext(email, password);
        }
    }
}

public class ChainOfResponsibilityDemo {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Server server;

    private static void init(){
        server = new Server();
        server.register("admin", "admin1234");
        server.register("user228", "qwerty");

        Middleware middleware = new ThrottlingMiddleware(2);
        middleware.linkWith(new UserExistMiddleware(server)).linkWith(new RoleCheckMiddleware());

        server.setMiddleware(middleware);


    }

    public static void main(String[] args) throws IOException{
        init();

        boolean success;
        do{
            System.out.println("Enter email: ");
            String email = reader.readLine();
            System.out.println("Enter password: ");
            String password = reader.readLine();
            success = server.logIn(email, password);
        }while(!success);
    }
}
