package rushhour;

public class Vehicle {

    private String orientation;

    public String type;

    public int size;
    public int posX;
    public int posY;

    public Vehicle(String type, String orientation, int size, int posX, int posY) {
        this.type = type;
        this.orientation = orientation;
        this.size = size;
        this.posX = posX;
        this.posY = posY;
    }

    public boolean isHorizontal() {
        return orientation.equals("h");
    }

    public boolean isVertical() {
        return orientation.equals("v");
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void moveDown() {
        this.posX++;
    }

    public void moveUp() {
        this.posX--;
    }

    public void moveRight() {
        this.posY++;
    }

    public void moveLeft() {
        this.posY--;
    }

    public Vehicle clone() {
        return new Vehicle(this.type, this.orientation, this.size, this.posX, this.posY);
    }

}