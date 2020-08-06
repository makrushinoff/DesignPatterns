package DesignPatterns.Creational;

import java.util.Scanner;

enum Type {
    classic, electric, acoustic,
}

enum BodyMaterials{
    linden, spruce, maple;
}

enum Shape{
    flat, broad, rounded;
}

enum StringMaterials{
    nylon, steel;
}

enum Color{
    glossy, matt, metallic;
}

class Sing implements Runnable{
    Thread trd;
    int i = 0;
    boolean stop = false;
    String[] song = new String[100];
    boolean changed = false;
    Sing(){
        trd = new Thread(this);
        trd.start();
        full_song();
    }

    @Override
    public void run() {
        while (!stop){
            try{
                Thread.sleep(2000);
            }
            catch (InterruptedException exc){
                System.out.println(exc);
            }
            gonext();
        }
    }

    synchronized void gonext(){
        i++;
        changed = true;
        notify();
    }

    synchronized void waitForChange(){
        try{
            while(!changed){
                wait();
            }
            changed = false;
        }
        catch (InterruptedException exc){
            System.out.println(exc);
        }
    }

    public void getString(){
        System.out.println(song[i]);
    }

    public void cancel(){
        stop = true;
    }

    private void full_song(){
        song[0] = "Без тебя жизни нет,";
        song[1] = "Я не могу найти.";
        song[2] = "Сколько не жмись к стене,";
        song[3] = "Сквозь неё не пройти.";
        song[4] = "Ни одного пути";
        song[5] = "Грешной души в зиме.";
        song[6] = "Без тебя смысла нет,";
        song[7] = "Без тебя жизни нет.";
        song[8] = "Сколько не жмись к стене,";
        song[9] = "Сквозь неё не пройти.";
        song[10] = "Без тебя жизни нет,";
        song[11] = "Я не могу найти.";
        song[12] = "Ни одного пути";
        song[13] = "Грешной души в зиме.";
        song[14] = "Без тебя смысла нет,";
        song[15] = "Без тебя жизни нет.";
        song[16] = "Жизни нет\n";
        song[17] = "Сдаётся мне,";
        song[18] = "Теперь это надолго";
        song[19] = "Переживаю словно";
        song[20] = "Переплываю Волгу.";
        song[21] = "";
        song[22] = "Она на шелке, я на иголках";
        song[23] = "";
        song[24] = "Вдребезги мост, осколки Сколоково";
        song[25] = "Она такая что не хватет слов";
        song[26] = "";
        song[27] = "Теперь я знаю, как мне повезло холодам назло";
        song[28] = "Будто бы кто-то отодавинул заслон";
        song[29] = "И как же далеко меня унесло!";
        song[30] = "Танцуют листья наветру.";
        song[31] = "Я без неё не тру - ";
        song[32] = "Я без неё вру!";
        song[33] = "Бывает врут, но я не вижу никого вокруг.";
        song[34] = "И тем теплее, чем ближе к костру!";
        song[35] = "";
        song[36] = "Гори, гори, моя Звезда - да, да!";
        song[37] = "";
        song[38] = "Всё неспроста и мне не перестать.";
        song[39] = "Стали листать вместе страницы - лица, места.";
        song[40] = "Вереницы стран, вместе в астрал!\n";
        song[41] = "Без тебя жизни нет,";
        song[42] = "Я не могу найти.";
        song[43] = "Сколько не жмись к стене,";
        song[44] = "Сквозь неё не пройти.";
        song[45] = "Ни одного пути";
        song[46] = "Грешной души в зиме.";
        song[47] = "Без тебя смысла нет,";
        song[48] = "Без тебя жизни нет.";
        song[49] = "Сколько не жмись к стене,";
        song[50] = "Сквозь неё не пройти.";
        song[51] = "Без тебя жизни нет,";
        song[52] = "Я не могу найти.";
        song[53] = "Ни одного пути";
        song[54] = "Грешной души в зиме.";
        song[55] = "Без тебя смысла нет,";
        song[56] = "Без тебя жизни нет.";
        song[57] = "Жизни нет\n";
        song[58] = "";
        song[59] = "Я не всегда милый, такой как песнях.";
        song[60] = "";
        song[61] = "Про знаменитый переход на Пресне.";
        song[62] = "Главное - честно плаваем в бездне.";
        song[63] = "Было бы пресно без,";
        song[64] = "С тобою интересней!";
        song[65] = "";
        song[66] = "В тебе тепло, как в пластинках Боба:";
        song[67] = "I wanna Lova one!";
        song[68] = "Нам повезло, мы это знает оба!";
        song[69] = "Сдаёться мне, что";
        song[70] = "Это любовь до гроба!";
        song[71] = "-Согласны ли вы?  - Ааа, еще бы";
        song[72] = "Вот эта рука твоя! Вот тебе сердце, не теряй!";
        song[73] = "Дотянем до Января, а там - свалим на моря!";
        song[74] = "Ты и я... \"Два сапога - пара!\", - так говорят.";
        song[75] = "Вроде не врут. Вот это замут! Я ближе хочу быть к костру!";
        song[76] = "В свете фонаря блестит асфальт.";
        song[77] = "Я тебя люблю - ты просто знай!";
        song[78] = "";
        song[79] = "Сдаётся мне, теперь это надолго.";
        song[80] = "";
        song[81] = "Гори! Гори, не догорай.";
        song[82] = "Без тебя жизни нет,";
        song[83] = "Я не могу найти.";
        song[84] = "Сколько не жмись к стене,";
        song[85] = "Сквозь неё не пройти.";
        song[86] = "Ни одного пути";
        song[87] = "Грешной души в зиме.";
        song[88] = "Без тебя смысла нет,";
        song[89] = "Без тебя жизни нет.";
        song[90] = "Сколько не жмись к стене,";
        song[91] = "Сквозь неё не пройти.";
        song[92] = "Без тебя жизни нет,";
        song[93] = "Я не могу найти.";
        song[94] = "Ни одного пути";
        song[95] = "Грешной души в зиме.";
        song[96] = "Без тебя смысла нет,";
        song[97] = "Без тебя жизни нет.";
        song[98] = "Жизни нет\n";
        song[99] = "Я люблююююююю тебееее";
    }
}

class Guitar{
    private final Type type;
    private final BodyMaterials bodyMaterial;
    private final Shape shape;
    private final StringMaterials strings;
    private final Color color;

    Guitar(Type type, BodyMaterials bodyMaterial, Shape shape, StringMaterials strings, Color color){
        this.bodyMaterial = bodyMaterial;
        this.color = color;
        this.type = type;
        this.shape = shape;
        this.strings = strings;
    }

    public BodyMaterials getBodyMaterial() {
        return bodyMaterial;
    }

    public Color getColor() {
        return color;
    }

    public Shape getShape() {
        return shape;
    }

    public StringMaterials getStrings() {
        return strings;
    }

    public Type getType() {
        return type;
    }

    public void play(){
        for(int k = 0; k < 30; k++){
            System.out.println("");
        }
        Sing sl = new Sing();
        for(int j = 0; j < 100; j++){
            sl.getString();
            sl.waitForChange();
        }
        sl.cancel();
    }
}

interface Builder{
    void setType(Type type);
    void setSoundBoard(BodyMaterials bodyMaterial);
    void setShape(Shape shape);
    void setStrings(StringMaterials strings);
    void setColor(Color color);
}

class GuitarBuilder implements Builder{
    private Type type;
    private BodyMaterials bodyMaterial;
    private Shape shape;
    private StringMaterials strings;
    private Color color;

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public void setSoundBoard(BodyMaterials bodyMaterial) {
        this.bodyMaterial = bodyMaterial;
    }

    @Override
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void setStrings(StringMaterials strings) {
        this.strings = strings;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
    public void getInfo(){
        System.out.println("Desired product: ");
        System.out.println("Type: " + type);
        System.out.println("Body materials: " + bodyMaterial);
        System.out.println("Shape: " + shape);
        System.out.println("Color shade: " + color);
        System.out.println("String materials: " + strings);
        System.out.println("\nDesired product has been created!");
    }
    public Guitar getResult(){
        return new Guitar(type, bodyMaterial, shape, strings, color);
    }
}

class Director{

    public void constructClassicGuitar(GuitarBuilder builder){
        builder.setType(Type.classic);
        builder.setColor(Color.matt);
        builder.setShape(Shape.rounded);
        builder.setSoundBoard(BodyMaterials.maple);
        builder.setStrings(StringMaterials.nylon);
    }
    public void constructAcousticGuitar(GuitarBuilder builder){
        builder.setType(Type.acoustic);
        builder.setColor(Color.glossy);
        builder.setShape(Shape.broad);
        builder.setSoundBoard(BodyMaterials.spruce);
        builder.setStrings(StringMaterials.steel);
    }
    public void constructElectricGuitar(GuitarBuilder builder){
        builder.setType(Type.electric);
        builder.setColor(Color.metallic);
        builder.setShape(Shape.flat);
        builder.setSoundBoard(BodyMaterials.linden);
        builder.setStrings(StringMaterials.steel);
    }
}

public class BuilderDemo {

    public static void main(String[] args) {
        System.out.println("Available types of a guitar: 1) Acoustic; 2) Classic; 3) Electric.");
        System.out.println("Input desired type of guitar(Enter 1, 2 or 3): ");
        Director director = new Director();
        GuitarBuilder builder = new GuitarBuilder();
        Scanner input = new Scanner(System.in);
        int type = input.nextInt();
        switch (type){
            case 2 -> director.constructClassicGuitar(builder);
            case 1 -> director.constructAcousticGuitar(builder);
            case 3 -> director.constructElectricGuitar(builder);
        }
        Guitar guitar = builder.getResult();
        builder.getInfo();
        System.out.println("Do you want to see how this guitar plays? (Enter Yes/No)");
        Scanner answer = new Scanner(System.in);
        String ans = answer.nextLine();
        if(ans.equals("No"))
            System.exit(0);
        else if(ans.equals("Yes")){
            guitar.play();
        }
    }
}
