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
    public  LinkedList<Vehicle> colisionVehiculo = new LinkedList<Vehicle> ();
    
    
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
                if (canMoveDown(nuevoVehiculo)) {
                    nuevoVehiculo.moveDown();
                    posibleMoves.add(new Puzzle(tamañoTablero, nuevosVehiculos,exitX,exitY));
                    nuevosVehiculos = cloneCars(nuevosVehiculos);
                    
                }
                nuevosVehiculos = cloneCars(vehiculos);
                nuevoVehiculo = nuevosVehiculos.get(i);
                if (canMoveUp(nuevoVehiculo)) {
                    nuevoVehiculo.moveUp();
                    posibleMoves.add(new Puzzle(tamañoTablero, nuevosVehiculos,exitX,exitY));
                    nuevosVehiculos = cloneCars(nuevosVehiculos);
                    
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

    public Vehicle crashCars(int x, int y,Vehicle currentCar) {
        LinkedList<Vehicle> crashes = new LinkedList<Vehicle>();
        for (Vehicle car : cars) {

            if (car.isHorizontal()) {
               if (currentCar.isHorizontal()) {
                   if (!currentCar.type.equals(car.type) &&(x >= car.posX&&x <= car.posX + car.size-1 ) && (y == car.posY)) {
                        return car;
                }
               }
                   else if (!currentCar.type.equals(car.type) &&(x >= car.posX&&x <= car.posX + car.size-1 ) && (y <= car.posY && y + currentCar.size-1 >= car.posY )) {
                            return car;

                }
            } else if (car.isVertical()) {
                if(currentCar.isVertical()){
                    if (!currentCar.type.equals(car.type) &&(y >= car.posY&&y <= car.posY + car.size-1 ) && (x == car.posX)) {
                        return car;

                }
                }else if (!currentCar.type.equals(car.type) &&(y >= car.posY&&y <= car.posY + car.size-1 ) && (x <= car.posX && x + currentCar.size-1 >= car.posX )) {
                        return car;

                } {
                    
                }
              
            }
        }
        return null;
    }

    public float getF() { //costo
        
        if(alreadyScored){
            return score;
        }
        Vehicle carro = getObjectiveCar().clone();
        int count = 0; //distancia del objetivo al destino
        int carBlock = 0; //carros bloqueando        

        for (int i = 0; i < 5; i++) {
            if (carro.isHorizontal()) {
                if (carro.posX != exitX ) {
                    if(crashCars(carro.posX, carro.posY,carro)!=null)
                        colisionVehiculo.add(crashCars(carro.posX, carro.posY,carro));
                     carBlock++;
                 
                    carro.moveRight();
                    count++;

                } 
            }
            if (carro.isVertical()) {
                if (carro.posY != exitY) {
                    if(crashCars(carro.posX, carro.posY,carro)!=null){
                       colisionVehiculo.add(crashCars(carro.posX, carro.posY,carro));
                        carBlock++;
                    }

                    carro.moveDown();
                    count++;
                } 
            }
        }

        float generalBlocks=0;
        for (Vehicle blockers : (LinkedList<Vehicle>)colisionVehiculo.clone()){
         generalBlocks= recursiveBlocks(blockers)/10;
        }   
        blocks = carBlock;
        distance = count;
         
        alreadyScored = true;
        return score+generalBlocks;
    }

    
    public float recursiveBlocks(Vehicle vehiculo){
        float generalBlocks = 0;
            LinkedList<Vehicle> collisions = new  LinkedList<Vehicle>();
            Vehicle clone =  vehiculo.clone();
            if (clone.isHorizontal()){
            while(clone.posX<5){
                        Vehicle cotemp = crashCars(clone.posX, clone.posY,clone);
                        if(!colisionVehiculo.contains(cotemp)&&cotemp!=null){
                            collisions.add(cotemp);
                                                        colisionVehiculo.add(cotemp);

                            generalBlocks++;
                            break;
                        }
                                                    clone.moveRight();

                }
            
              while(clone.posX!=0){
                        Vehicle cotemp = crashCars(clone.posX, clone.posY,clone);    
                        if(!colisionVehiculo.contains(cotemp)&&cotemp!=null){
                            collisions.add(cotemp);
                                                        colisionVehiculo.add(cotemp);

                            generalBlocks++;
                            break;
                        }
                                                    clone.moveLeft();

                }
            }
            else{
               while(clone.posY<5){
                        Vehicle cotemp = crashCars(clone.posX, clone.posY,clone);    
                        if(!colisionVehiculo.contains(cotemp)&&cotemp!=null){
                            collisions.add(cotemp);
                                                        colisionVehiculo.add(cotemp);

                            generalBlocks++;
                            break;
                        }
                                                    clone.moveDown();

                }
            
              while(clone.posY!=0){
                        Vehicle cotemp = crashCars(clone.posX, clone.posY,clone);    
                        if(!colisionVehiculo.contains(cotemp)&&cotemp!=null){
                            collisions.add(cotemp);
                            colisionVehiculo.add(cotemp);
                            generalBlocks++;
                            break;
                        }
                                                    clone.moveUp();

                }
                }
            
            
        
        if(collisions.size()!=0){
            for (Vehicle blockers : collisions){
                generalBlocks =+ recursiveBlocks(blockers)/10;
                
            }
        }
        return generalBlocks;
    }
    public Vehicle getObjectiveCar() {
        return cars.get(0);
    }

    public boolean canMoveDown(Vehicle car) {
        if (car.posY + car.size < tamañoTablero && crashCars(car.posX, car.posY + 1,car)==null) {
            return true;
        }
        return false;
    }

    public boolean canMoveUp(Vehicle car) {
        if (car.posY > 0 && crashCars(car.posX, car.posY - 1,car)==null) {
            return true;
        }
        return false;
    }

    public boolean canMoveRight(Vehicle car) {

        if (car.posX + car.size < tamañoTablero && crashCars(car.posX + 1, car.posY,car)==null) {
            return true;
        }
        return false;
    }

    public boolean canMoveLeft(Vehicle car) {
        if (car.posX > 0 && crashCars(car.posX - 1, car.posY,car)==null) {
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