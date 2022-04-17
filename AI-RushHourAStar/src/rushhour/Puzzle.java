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
    public int score;
    public Vehicle colisionVehiculo;
    
    
    public int exitX;
    public int exitY;

    private int blocks;
        private int distance;

    private int destino; //cordenada final
    private Integer tamañoTablero;
    private int[][] matrix;
    private boolean alreadyScored = false;
    
    // CONSTRUCTOR ----------------------------------------------------------------
    public Puzzle(int pTamañoTablero, LinkedList<Vehicle> pNuevosVehiculos,int winX, int winY) {
        super();
        exitX= winX;
        exitY= winY;

        this.tamañoTablero = pTamañoTablero;
        this.cars = pNuevosVehiculos;
        numVehiculos= pNuevosVehiculos.size(); 
    }

    // MÉTODOS --------------------------------------------------------------------
    public Puzzle() {
        //constructor
    }

    public Integer getNumVehiculos() {
        return numVehiculos;
    }
    public int[][] getMatrix() {
        if(matrix == null){
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

    public ArrayList<Puzzle> posibleMoves() {
        ArrayList<Puzzle> posibleMoves = new ArrayList<>();
        LinkedList<Vehicle> vehiculos = cars;
        for (int i = 0; i < vehiculos.size(); i++) {
            Vehicle vehiculo = vehiculos.get(i);
            if (vehiculo.isVertical()) {
                LinkedList<Vehicle> nuevosVehiculos = cloneCars(vehiculos);
                Vehicle nuevoVehiculo = nuevosVehiculos.get(i);
                while (canMoveDown(nuevoVehiculo)) {
                    nuevoVehiculo.moveDown();
                    posibleMoves.add(new Puzzle(tamañoTablero, nuevosVehiculos,exitX,exitY));
                    nuevosVehiculos = cloneCars(nuevosVehiculos);
                    nuevoVehiculo = nuevosVehiculos.get(i);
                }
                nuevosVehiculos = cloneCars(vehiculos);
                nuevoVehiculo = nuevosVehiculos.get(i);
                while (canMoveUp(nuevoVehiculo)) {
                    nuevoVehiculo.moveUp();
                    posibleMoves.add(new Puzzle(tamañoTablero, nuevosVehiculos,exitX,exitY));
                    nuevosVehiculos = cloneCars(nuevosVehiculos);
                    nuevoVehiculo = nuevosVehiculos.get(i);
                }
            } else if (vehiculo.isHorizontal()) {
                LinkedList<Vehicle> nuevosVehiculos = cloneCars(vehiculos);
                Vehicle nuevoVehiculo = nuevosVehiculos.get(i);
                while (canMoveRight(nuevoVehiculo)) {
                    nuevoVehiculo.moveRight();
                    posibleMoves.add(new Puzzle(tamañoTablero, nuevosVehiculos,exitX,exitY));
                    nuevosVehiculos = cloneCars(nuevosVehiculos);
                    nuevoVehiculo = nuevosVehiculos.get(i);
                }
                nuevosVehiculos = cloneCars(vehiculos);
                nuevoVehiculo = nuevosVehiculos.get(i);
                while (canMoveLeft(nuevoVehiculo)) {
                    nuevoVehiculo.moveLeft();
                    posibleMoves.add(new Puzzle(tamañoTablero, nuevosVehiculos,exitX,exitY));
                    nuevosVehiculos = cloneCars(nuevosVehiculos);
                    nuevoVehiculo = nuevosVehiculos.get(i);
                }
            }
        }
        return posibleMoves;
    }

    public boolean isSolved() {
        if(getObjectiveCar().isHorizontal()){
            if (getObjectiveCar().posX == exitX || getObjectiveCar().posX+getObjectiveCar().size-1 == exitX) {
                return true;
        }
        }else{
            if ( getObjectiveCar().posY == exitY || getObjectiveCar().posY+getObjectiveCar().size-1== exitY) {
                return true;
            }
        }
            
        
        return false;
    }

    public int crashCars(int x, int y,Vehicle currentCar) {
        int crashes = 0;
        for (Vehicle car : cars) {

            if (car.isHorizontal()) {
                if (!currentCar.equals(car) &&(x >= car.posX&&x <= car.posX + car.size-1 ) && (y <= car.posY && y + currentCar.size-1 >= car.posY )) {
                    colisionVehiculo = car;
                    crashes++;
                }
            } else if (car.isVertical()) {
                if (!currentCar.equals(car) &&(y >= car.posY&&y <= car.posY + car.size-1 ) && (x <= car.posX && x + currentCar.size-1 >= car.posX )) {
                    colisionVehiculo = car;
                    crashes++;
                }
            }
        }
        return crashes;
    }

    public int getF() { //costo
        
        if(alreadyScored){
            return score;
        }
        Vehicle carro = getObjectiveCar().clone();
        int count = 0; //distancia del objetivo al destino
        int carBlock = 0; //carros bloqueando        

        for (int i = 0; i < 5; i++) {
            if (carro.isHorizontal()) {
                if (carro.posX != exitX ) {
                    carBlock+=crashCars(carro.posX, carro.posY,carro);
                 
                    carro.moveRight();
                    count++;

                } 
            }
            if (carro.isVertical()) {
                if (carro.posY != exitY) {
                    carBlock+=crashCars(carro.posX, carro.posY,carro);

                    carro.moveDown();
                    count++;
                } 
            }
        }
        blocks = carBlock;
        distance = count;

        alreadyScored = true;
        score = carBlock+count;
        if(blocks == 1){
            System.out.println("a");
        }
        return carBlock + count;
    }

    public Vehicle getObjectiveCar() {
        return cars.get(0);
    }

    public boolean canMoveDown(Vehicle car) {
        if (car.posY + car.size < tamañoTablero && crashCars(car.posX, car.posY + 1,car)==0) {
            return true;
        }
        return false;
    }

    public boolean canMoveUp(Vehicle car) {
        if (car.posY > 0 && crashCars(car.posX, car.posY - 1,car)==0) {
            return true;
        }
        return false;
    }

    public boolean canMoveRight(Vehicle car) {

        if (car.posX + car.size < tamañoTablero && crashCars(car.posX + 1, car.posY,car)==0) {
            return true;
        }
        return false;
    }

    public boolean canMoveLeft(Vehicle car) {
        if (car.posX > 0 && crashCars(car.posX - 1, car.posY,car)==0) {
            return true;
        }
        return false;
    }

    private LinkedList<Vehicle> cloneCars(LinkedList<Vehicle> vehiculos) {
        LinkedList<Vehicle> nuevosVehiculos = new LinkedList<>();
        for (Vehicle vehiculo : vehiculos) {
            nuevosVehiculos.add(vehiculo.clone());
        }
        return nuevosVehiculos;
    }

    private int[][] toMatrix(){
            int[][] res = new int[6][6];
            int carNumber = 1;
            for(Vehicle car: cars){

                res[ car.posY][car.posX] = carNumber;
                int tempLenght  = car.size-1; 
                if(car.isHorizontal()){
                    while(tempLenght !=0){
                       res[ car.posY][car.posX+tempLenght] = carNumber;
                    tempLenght--;
                    }
                }else{
                    while(tempLenght !=0){
                       res[car.posY+tempLenght][car.posX ] = carNumber;
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