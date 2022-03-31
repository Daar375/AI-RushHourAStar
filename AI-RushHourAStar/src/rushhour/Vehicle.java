package rushhour;
public class Vehicle {
    private String type;
    private int size;
    private int posX;
    private int posY;

    public Vehicle(String type, int size, int posX, int posY) {
        this.type = type;
        this.size = size;
        this.posX = posX;
        this.posY = posY;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPosX() {
        return this.posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

}

    
