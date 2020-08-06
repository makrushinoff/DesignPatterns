package DesignPatterns.Structural;

class RoundHole{
    private double radius;
    private double depth;
    public RoundHole(double radius, double depth){
        this.radius = radius;
        this.depth = depth;
    }

    public double getRadius() {
        return radius;
    }

    public double getDepth() {
        return depth;
    }

    public boolean fits(RoundPeg peg){
        boolean res;
        res = this.getRadius() >= peg.getRadius() && getDepth() == peg.getHeight();
        return res;
    }
}

class RoundPeg {
    private double radius;
    private double height;

    public RoundPeg(){}

    public RoundPeg(double radius, double height){
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double getHeight() {
        return height;
    }

}

class SquarePeg {
    private double width;
    private double height;

    public SquarePeg(double width, double height){
        this.height = height;
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getSquareBase(){
        return Math.pow(width, 2);
    }
}

class SquareToRoundAdapter extends RoundPeg{
    private SquarePeg squarePeg;

    public SquareToRoundAdapter(SquarePeg squarePeg) {
        this.squarePeg = squarePeg;
    }

    @Override
    public double getRadius() {
        return (Math.sqrt(Math.pow((squarePeg.getWidth() / 2), 2) * 2));
    }
}

public class AdapterDemo {
    public static void main(String[] args) {
        RoundHole hole = new RoundHole(5, 10);
        System.out.println("Here is round hole with radius of " + hole.getRadius() + " and depth of " + hole.getDepth());
        RoundPeg roundPeg = new RoundPeg(5, 10);
        System.out.println("Here is round peg with radius of " + roundPeg.getRadius() + " and height of " + roundPeg.getHeight());
        if(hole.fits(roundPeg))
            System.out.println("Peg can be putted into the hole");

        SquarePeg squarePeg1 = new SquarePeg(4, 10);
        System.out.println("Here is square peg with width of " + squarePeg1.getWidth() + " and height of " + squarePeg1.getHeight());
        SquarePeg squarePeg2 = new SquarePeg(10, 12);
        System.out.println("Here is square peg with width of " + squarePeg2.getWidth() + " and height of " + squarePeg2.getHeight());

        SquareToRoundAdapter adapterSq1 = new SquareToRoundAdapter(squarePeg1);
        SquareToRoundAdapter adapterSq2 = new SquareToRoundAdapter(squarePeg2);

        if(hole.fits(adapterSq1))
            System.out.println("Square peg 1 can pe adapted and putted");
        else
            System.out.println("Square peg 1 can NOT pe adapted and putted");

        if(hole.fits(adapterSq2))
            System.out.println("Square peg 2 can pe adapted and putted");
        else
            System.out.println("Square peg 2 can NOT pe adapted and putted");


    }
}
