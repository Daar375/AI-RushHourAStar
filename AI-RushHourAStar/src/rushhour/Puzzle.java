/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package rushhour;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Jose Rivera
 */
public class Puzzle {

    // VARIABLES ------------------------------------------------------------------
    private String nombre;
    //private Node nodoInit;

    private Integer contBusqueda;

    private Integer numVehiculos;
    // private Vehicle Vehiculos[]; Revisar

    public LinkedList<Vehicle> cars;
    public float score;
    public LinkedList<Vehicle> colisionVehiculo = new LinkedList<Vehicle>();

    public int exitX;
    public int exitY;
    public int depth=0;

    private float blocks;
    private float distance;

    private int destino; //cordenada final
    private Integer tamañoTablero;
    private int[][] matrix;
    private boolean alreadyScored = false;

    // CONSTRUCTOR ----------------------------------------------------------------
    public Puzzle(int pTamañoTablero, LinkedList<Vehicle> pNuevosVehiculos, int winX, int winY,int depthIn) {
        super();
        exitX = winX;
        exitY = winY;
        depth=depthIn;
        this.tamañoTablero = pTamañoTablero;
        this.cars = pNuevosVehiculos;
        numVehiculos = pNuevosVehiculos.size();
    }

    // MÉTODOS --------------------------------------------------------------------
    public Puzzle() {
        //constructor
    }

    public Integer getNumVehiculos() {
        return numVehiculos;
    }

    public int[][] getMatrix() {
        if (matrix == null) {
            matrix = toMatrix();
        }
        return matrix;
    }

    public Integer getContBusqueda() {
        return contBusqueda;
    }

    //////// Posible funcion para el movimiento de los carros ////////
    public void incrementarContBusqueda(int cont) {
        contBusqueda += cont;
    }
    ////////////////////////

    public void resetContBusqueda() {
        contBusqueda = 1;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getTamañoTablero() {
        return tamañoTablero;
    }

    public ArrayList<Puzzle> posibleMoves(int depth) {
        ArrayList<Puzzle> posibleMoves = new ArrayList<>();
        LinkedList<Vehicle> vehiculos = cars;
        for (int i = 0; i < vehiculos.size(); i++) {
            Vehicle vehiculo = vehiculos.get(i);
            if (vehiculo.isVertical()) {
                LinkedList<Vehicle> nuevosVehiculos = cloneCars(vehiculos);
                Vehicle nuevoVehiculo = nuevosVehiculos.get(i);
                while (canMoveDown(nuevoVehiculo) == -2) {
                    nuevoVehiculo.moveDown();
                    posibleMoves.add(new Puzzle(tamañoTablero, nuevosVehiculos, exitX, exitY,depth));
                    nuevosVehiculos = cloneCars(nuevosVehiculos);

                }
                nuevosVehiculos = cloneCars(vehiculos);
                nuevoVehiculo = nuevosVehiculos.get(i);
                while (canMoveUp(nuevoVehiculo) == -2) {
                    nuevoVehiculo.moveUp();
                    posibleMoves.add(new Puzzle(tamañoTablero, nuevosVehiculos, exitX, exitY,depth));
                    nuevosVehiculos = cloneCars(nuevosVehiculos);

                }
            } else if (vehiculo.isHorizontal()) {
                LinkedList<Vehicle> nuevosVehiculos = cloneCars(vehiculos);
                Vehicle nuevoVehiculo = nuevosVehiculos.get(i);
                while (canMoveRight(nuevoVehiculo) == -2) {
                    nuevoVehiculo.moveRight();
                    posibleMoves.add(new Puzzle(tamañoTablero, nuevosVehiculos, exitX, exitY,depth));
                    nuevosVehiculos = cloneCars(nuevosVehiculos);

                }
                nuevosVehiculos = cloneCars(vehiculos);
                nuevoVehiculo = nuevosVehiculos.get(i);
                while (canMoveLeft(nuevoVehiculo) == -2) {
                    nuevoVehiculo.moveLeft();
                    posibleMoves.add(new Puzzle(tamañoTablero, nuevosVehiculos, exitX, exitY,depth));
                    nuevosVehiculos = cloneCars(nuevosVehiculos);

                }
            }
        }
        return posibleMoves;
    }

    public boolean isSolved() {
        if (getObjectiveCar().isHorizontal()) {
            if (getObjectiveCar().posX == exitX || getObjectiveCar().posX + getObjectiveCar().size - 1 == exitX) {
                return true;
            }
        } else {
            if (getObjectiveCar().posY == exitY || getObjectiveCar().posY + getObjectiveCar().size - 1 == exitY) {
                return true;
            }
        }

        return false;
    }

    public int crashCars(int x, int y, Vehicle currentCar) {
        LinkedList<Vehicle> crashes = new LinkedList<Vehicle>();
        int count = 0;
        for (Vehicle car : cars) {

            if (car.isHorizontal()) {
                if (currentCar.isHorizontal()) {
                    if (!currentCar.type.equals(car.type) && ((x >= car.posX && x <= car.posX + car.size - 1)  || (x +currentCar.getSize()-1 >= car.posX && x+currentCar.getSize()-1 <= car.posX + car.size - 1))&& (y == car.posY)) {
                        return count;
                    }
                } else if (!currentCar.type.equals(car.type) && (x >= car.posX && x <= car.posX + car.size - 1) && (y <= car.posY && y + currentCar.size - 1 >= car.posY)) {
                    return count;

                }
            } else if (car.isVertical()) {
                if (currentCar.isVertical()) {
                    if (!currentCar.type.equals(car.type) &&( (y >= car.posY && y <= car.posY + car.size - 1)  || (y +currentCar.getSize()-1>= car.posY && y+currentCar.getSize()-1 <= car.posY + car.size - 1))&& (x == car.posX)) {
                        return count;

                    }
                } else if (!currentCar.type.equals(car.type) && (y >= car.posY && y <= car.posY + car.size - 1) && (x <= car.posX && x + currentCar.size - 1 >= car.posX)) {
                    return count;

                }
                {

                }

            }
            count++;
        }
        return -2;
    }

    public boolean isBlocking(Vehicle carA, Vehicle Blocked) {
        Vehicle carB = Blocked.clone();
        if (carB.isHorizontal()) {
            carB.moveRight();
            if (carA.isHorizontal()) {
                if ((carA.posX >= carB.posX && carA.posX <= carB.posX + carB.size - 1) && (carA.posY == carB.posY)) {
                    return true;
                }
            } else if ((carA.posX >= carB.posX && carA.posX <= carB.posX + carB.size - 1) && (carA.posY <= carB.posY && carA.posY + carA.size - 1 >= carB.posY)) {
                return true;

            }
            carB.moveLeft();
            carB.moveLeft();

            if (carA.isHorizontal()) {
                if ((carA.posX >= carB.posX && carA.posX <= carB.posX + carB.size - 1) && (carA.posY == carB.posY)) {
                    return true;
                }
            } else if ((carA.posX >= carB.posX && carA.posX <= carB.posX + carB.size - 1) && (carA.posY <= carB.posY && carA.posY + carA.size - 1 >= carB.posY)) {
                return true;

            }
        } else if (carB.isVertical()) {
            carB.moveDown();
            if (carA.isVertical()) {
                if ((carA.posY >= carB.posY && carA.posY <= carB.posY + carB.size - 1) && (carA.posX == carB.posX)) {
                    return true;

                }
            } else if ((carA.posY >= carB.posY && carA.posY <= carB.posY + carB.size - 1) && (carA.posX <= carB.posX && carA.posX + carA.size - 1 >= carB.posX)) {
                return true;

            }
            carB.moveUp();
            carB.moveUp();
            if (carA.isVertical()) {
                if ((carA.posY >= carB.posY && carA.posY <= carB.posY + carB.size - 1) && (carA.posX == carB.posX)) {
                    return true;

                }
            } else if ((carA.posY >= carB.posY && carA.posY <= carB.posY + carB.size - 1) && (carA.posX <= carB.posX && carA.posX + carA.size - 1 >= carB.posX)) {
                return true;

            }

        }
        return false;
    }

    public float getF() { //costo

        if (alreadyScored) {
            return score;
        }
        Vehicle carro = getObjectiveCar().clone();
        int count = 0; //distancia del objetivo al destino
        int carBlock = 0; //carros bloqueando        

        for (int i = 0; i < 5; i++) {
            if (carro.isHorizontal()) {
                if (carro.posX != exitX) {
                    if (crashCars(carro.posX, carro.posY, carro) != -2) {
                        colisionVehiculo.add(cars.get(crashCars(carro.posX, carro.posY, carro)));
                        carBlock++;
                    }
                    carro.moveRight();
                    count++;

                }
            }
            if (carro.isVertical()) {
                if (carro.posY != exitY) {
                    if (crashCars(carro.posX, carro.posY, carro) != -2) {
                        colisionVehiculo.add(cars.get(crashCars(carro.posX, carro.posY, carro)));
                        carBlock++;
                    }

                    carro.moveDown();
                    count++;
                }
            }
        }

        float generalBlocks = 0;


        
        LinkedList<Vehicle> tempVehicles = (LinkedList<Vehicle>) colisionVehiculo.clone();
        for (Vehicle blockers : tempVehicles) {
            generalBlocks = generalBlocks + recursiveBlocks(blockers, getObjectiveCar()) / 1000;
        }
        blocks = carBlock;
        distance = count;
        score = carBlock + count + generalBlocks;
        alreadyScored = true;
        return score;
    }

    public float recursiveBlocks(Vehicle vehiculo, Vehicle vehiculoParent) {
        float generalBlocks = 0;
        boolean blockingParent;
        int countSteps = 0;


        LinkedList<Vehicle> collisions = new LinkedList<Vehicle>();
        Vehicle clone = vehiculo.clone();
        if (clone.isHorizontal()) {
            while (clone.posX < 6) {
                blockingParent = isBlocking(clone, vehiculoParent);
                if (blockingParent) {
                    generalBlocks = generalBlocks + 5;
                } else {
                    generalBlocks = countSteps + generalBlocks;
                }
                int block = canMoveRight(clone);
                if (block == -1) {
                    if (blockingParent) {
                        generalBlocks = generalBlocks + 50;
                    }
                    break;

                } else if (block > 0) {
                    Vehicle cotemp = cars.get(block);
                    if (!colisionVehiculo.contains(cotemp) && cotemp != null) {
                        collisions.add(cotemp);
                        colisionVehiculo.add(cotemp);

                        generalBlocks=generalBlocks+3;

                    }
                    countSteps++;
                    clone.moveRight();

                } else if (block == -2) {
                    countSteps++;

                    clone.moveRight();

                } else {
                    break;
                }
            }
            clone = vehiculo.clone();
            countSteps = 0;
            while (clone.posX >= 0) {
                blockingParent = isBlocking(clone, vehiculoParent);
                if (blockingParent) {
                    generalBlocks = generalBlocks + 5;
                } else {
                    generalBlocks = countSteps + generalBlocks;

                }
                int block = canMoveLeft(clone);

                if (block == -1 && isBlocking(clone, vehiculoParent)) {
                    if (blockingParent) {
                        generalBlocks = generalBlocks + 50;
                    }
                    break;

                } else if (block > 0) {
                    Vehicle cotemp = cars.get(block);

                    if (!colisionVehiculo.contains(cotemp) && cotemp != null) {
                        collisions.add(cotemp);
                        colisionVehiculo.add(cotemp);
                        generalBlocks=generalBlocks+3;

                    }
                    countSteps++;
                    clone.moveLeft();

                } else if (block == -2) {
                    countSteps++;

                    clone.moveLeft();

                } else {
                    break;
                }
            }
        } else {
            clone = vehiculo.clone();
            countSteps = 0;
            while (clone.posY < 6) {
                blockingParent = isBlocking(clone, vehiculoParent);
                if (blockingParent) {
                    generalBlocks = generalBlocks + 5;
                } else {
                    generalBlocks = countSteps + generalBlocks;

                }
                int block = canMoveDown(clone);
                if (block == -1) {
                    if (blockingParent) {
                        generalBlocks = generalBlocks + 50;
                    }
                    break;

                } else if (block > 0) {
                    Vehicle cotemp = cars.get(block);

                    if (!colisionVehiculo.contains(cotemp) && cotemp != null) {
                        collisions.add(cotemp);
                        colisionVehiculo.add(cotemp);
                        generalBlocks=generalBlocks+3;

                    }
                    clone.moveDown();

                    countSteps++;

                } else if (block == -2) {
                    clone.moveDown();
                    countSteps++;

                } else {
                    break;
                }
            }
            clone = vehiculo.clone();
            countSteps = 0;
            while (clone.posY >= 0) {
                blockingParent = isBlocking(clone, vehiculoParent);
                if (blockingParent) {
                    generalBlocks = generalBlocks + 5;
                } else {
                    generalBlocks = countSteps + generalBlocks;

                }
                int block = canMoveUp(clone);
                if (block == -1) {
                    if (blockingParent) {
                        generalBlocks = generalBlocks + 50;
                    }
                    break;

                } else if (block > 0 && isBlocking(clone, vehiculoParent)) {
                    Vehicle cotemp = cars.get(block);

                    if (!colisionVehiculo.contains(cotemp) && cotemp != null) {
                        collisions.add(cotemp);
                        colisionVehiculo.add(cotemp);
                        generalBlocks=generalBlocks+3;

                    }
                    clone.moveUp();
                    countSteps++;

                } else if (block == -2) {
                    clone.moveUp();
                    countSteps++;

                } else {
                    break;
                }

            }
        }

        if (collisions.size() != 0) {
            for (Vehicle blockers : collisions) {
                generalBlocks = generalBlocks + recursiveBlocks(blockers, vehiculo);

            }
        }
        return generalBlocks;
    }

    public Vehicle getObjectiveCar() {
        return cars.get(0);
    }

    public int canMoveDown(Vehicle car) {
        if (car.posY + car.size < tamañoTablero) {
            return crashCars(car.posX, car.posY + 1, car);
        }
        return -1;
    }

    public int canMoveUp(Vehicle car) {
        if (car.posY > 0) {
            return crashCars(car.posX, car.posY - 1, car);
        }
        return -1;
    }

    public int canMoveRight(Vehicle car) {

        if (car.posX + car.size < tamañoTablero) {
            return crashCars(car.posX + 1, car.posY, car);
        }
        return -1;
    }

    public int canMoveLeft(Vehicle car) {
        if (car.posX > 0) {
            return crashCars(car.posX - 1, car.posY, car);
        }
        return -1;
    }

    private LinkedList<Vehicle> cloneCars(LinkedList<Vehicle> vehiculos) {
        LinkedList<Vehicle> nuevosVehiculos = new LinkedList<>();
        for (Vehicle vehiculo : vehiculos) {
            nuevosVehiculos.add(vehiculo.clone());
        }
        return nuevosVehiculos;
    }

    private int[][] toMatrix() {
        int[][] res = new int[6][6];
        int carNumber = 1;
        for (Vehicle car : cars) {

            res[car.posY][car.posX] = carNumber;
            int tempLenght = car.size - 1;
            if (car.isHorizontal()) {
                while (tempLenght != 0) {
                    res[car.posY][car.posX + tempLenght] = carNumber;
                    tempLenght--;
                }
            } else {
                while (tempLenght != 0) {
                    res[car.posY + tempLenght][car.posX] = carNumber;
                    tempLenght--;
                }
            }
            carNumber++;

        }
        return res;
    }

    public void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.printf("%4d", matrix[row][col]);
            }
            System.out.println();

        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    /*
    public Node getNodoInit() {
        return nodoInit;
    }
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // TODO code application logic here

    }
}
