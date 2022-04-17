/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package rushhour;

import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Math;

/**
 *
 * @author Jose Rivera
 */
public class Puzzle {

    // VARIABLES ------------------------------------------------------------------
    private String nombre;
    private Integer contBusqueda;
    private Integer numVehiculos;
    private Integer tamañoTablero;
    private int[][] matrix;
    private boolean alreadyScored = false;
    
    public LinkedList<Vehicle> cars;
    public int score;
    public Vehicle colisionVehiculo;    
    public int exitX; //cordenada x destino
    public int exitY; //cordenada y destino

    
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
                if (canMoveDown(nuevoVehiculo)) {
                    nuevoVehiculo.moveDown();
                    posibleMoves.add(new Puzzle(tamañoTablero, nuevosVehiculos,exitX,exitY));
                    nuevosVehiculos = cloneCars(nuevosVehiculos);
                    nuevoVehiculo = nuevosVehiculos.get(i);
                }
                nuevosVehiculos = cloneCars(vehiculos);
                nuevoVehiculo = nuevosVehiculos.get(i);
                if (canMoveUp(nuevoVehiculo)) {
                    nuevoVehiculo.moveUp();
                    posibleMoves.add(new Puzzle(tamañoTablero, nuevosVehiculos,exitX,exitY));
                    nuevosVehiculos = cloneCars(nuevosVehiculos);
                    nuevoVehiculo = nuevosVehiculos.get(i);
                }
            } else if (vehiculo.isHorizontal()) {
                LinkedList<Vehicle> nuevosVehiculos = cloneCars(vehiculos);
                Vehicle nuevoVehiculo = nuevosVehiculos.get(i);
                if (canMoveRight(nuevoVehiculo)) {
                    nuevoVehiculo.moveRight();
                    posibleMoves.add(new Puzzle(tamañoTablero, nuevosVehiculos,exitX,exitY));
                    nuevosVehiculos = cloneCars(nuevosVehiculos);

                }
                nuevosVehiculos = cloneCars(vehiculos);
                nuevoVehiculo = nuevosVehiculos.get(i);
                if (canMoveLeft(nuevoVehiculo)) {
                    nuevoVehiculo.moveLeft();
                    posibleMoves.add(new Puzzle(tamañoTablero, nuevosVehiculos,exitX,exitY));
                    nuevosVehiculos = cloneCars(nuevosVehiculos);

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

    public boolean crashCars(int x, int y,Vehicle currentCar) {
        for (Vehicle car : cars) {
            if (car.isHorizontal()) {
                if (!currentCar.equals(car) &&(x >= car.posX&&x <= car.posX + car.size-1 ) && (y <= car.posY && y + currentCar.size-1 >= car.posY ) && (car.type!="player")) {
                    colisionVehiculo = car;
                    return true;
                }
            } else if (car.isVertical()) {
                if (!currentCar.equals(car) &&(y >= car.posY&&y <= car.posY + car.size-1 ) && (x <= car.posX && x + currentCar.size-1 >= car.posX )) {
                    colisionVehiculo = car;
                    return true;
                }
            }
        }
        return false;
    }

    public int getF(Puzzle tablero) { //costo
        
        if(alreadyScored){
            return score;
        }
<<<<<<< HEAD

=======
        // Puzzle currentPuzzleClone = (Puzzle) tablero.clone();
>>>>>>> parent of 6d962e4 (Cambios en getF)
        Vehicle carro = getObjectiveCar();//.clone();
        int count = 0; //distancia del objetivo al destino
        int carBlock = 0; //carros bloqueando    
        
        if(carro.isHorizontal()){
            if(exitX == 5){
                count = exitX - carro.posX;
                while(carro.posX < exitX){
                    if(!canMoveRight(carro)){
                        carBlock++;
                    }
                    carro.moveRight();
                }                
            }
            if(exitX == -1){
                count = Math.abs(exitX - carro.posX);
                while(carro.posX > exitX){
                    if(!canMoveLeft(carro)){
                        carBlock++;
                    }
                    carro.moveLeft();
                }                
            }
        }
        if(carro.isVertical()){
            if(exitY == 5){
                count = exitY - carro.posY;
                while(carro.posY < exitY){
                    if(!canMoveDown(carro)){
                        carBlock++;
                    }
                    carro.moveDown();
                }                
            }
            if(exitY == -1){
                count = Math.abs(exitY - carro.posY);
                while(carro.posY > exitY){
                    if(!canMoveUp(carro)){
                        carBlock++;
                    }
                    carro.moveUp();
                }                
            }
        }
        

        //if(carro.isVertical()){
          //  while(carro.)
        //}
        
        /*
        for (int i = 0; i < 5; i++) {
            if (carro.isHorizontal()) {
                if (exitX == 6 && carro.posX < 6) {
                    if (i > 1 && crashCars(carro.posX, i,carro)) {
                        carBlock++;
                    }
                    carro.moveRight();
                    count++;

                } else {
                    if (i < 4 && crashCars(carro.posX, i,carro)) {
                        carBlock++;
                    }
                    carro.moveLeft();
                    count++;
                }
            }
            if (carro.isVertical()) {
                if (exitY == 6 && carro.posY < 6) {
                    if (i > 1 && crashCars(carro.posY, i,carro)) {
                        carBlock++;
                    }
                    carro.moveRight();
                    count++;
                } else {
                    if (i < 4 && crashCars(carro.posY, i,carro)) {
                        carBlock++;
                    }
                    carro.moveLeft();
                    count++;
                }
            }
        }*/
        alreadyScored = true;
        score = carBlock + count;
        return carBlock + count;
    }

    public Vehicle getObjectiveCar() {
        return cars.get(0);
    }

    public boolean canMoveDown(Vehicle car) {
        if (car.posY + car.size < tamañoTablero && !crashCars(car.posX, car.posY + 1,car)) {
            return true;
        }
        return false;
    }

    public boolean canMoveUp(Vehicle car) {
        if (car.posY > 0 && !crashCars(car.posX, car.posY - 1,car)) {
            return true;
        }
        return false;
    }

    public boolean canMoveRight(Vehicle car) {
        if (car.posX + car.size < tamañoTablero && !crashCars(car.posX + 1, car.posY,car)) {
            return true;
        }
        return false;
    }

    public boolean canMoveLeft(Vehicle car) {
        if (car.posX > 0 && !crashCars(car.posX - 1, car.posY,car)) {
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
                res[car.posX][car.posY] = 1;
                int tempLenght  = car.size-1; 
                if(car.isHorizontal()){
                    while(tempLenght !=0){
                       res[car.posX+tempLenght][car.posY] = 1;
                    tempLenght--;
                    }
                }else{
                    while(tempLenght !=0){
                       res[car.posX][car.posY+tempLenght] = 1;
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

        System.out.println();        System.out.println();

        System.out.println();        System.out.println();

        System.out.println();
}


    /*@Override
    public String toString(){
        String texto = "";
        for (Vehicle car : cars){
            texto.concat(" carro x:");
            texto.concat(String.valueOf(car.posX));
            texto.concat(" y: ");
            texto.concat(String.valueOf(car.posY));
        }
        return texto;
    }*/
}