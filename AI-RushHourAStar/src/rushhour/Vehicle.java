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
        this.posY++;
    }

    public void moveUp() {
        this.posY--;
    }

    public void moveRight() {
        this.posX++;
    }

    public void moveLeft() {
        this.posX--;
    }

    public Vehicle clone() {
        return new Vehicle(this.type, this.orientation, this.size, this.posX, this.posY);
    }
    
    public boolean equals(Vehicle car){
        return this.posX == car.posX && this.posY == car.posY && this.size == car.size && this.type.equals(car.type);
    }
}