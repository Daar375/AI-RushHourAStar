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
    public Vehicle colisionVehiculo;
    private int destino; //cordenada final
    private Integer tamañoTablero;
    


    // MÉTODOS --------------------------------------------------------------------
    public Puzzle(){
        //constructor
    }
    
    
    public Integer getNumVehiculos() {
        return numVehiculos;
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
    
    public ArrayList<Puzzle>  posibleMoves(){
        return null;
        
        /*
        public ArrayList<Puzzle> posibleMoves(){
		ArrayList<Puzzle> posibleMoves = new ArrayList<>();
                cars = new LinkedList<Vehicle>;
		for (int i = 0; i < cars.size(); i++) {
			Vehicle car = cars.get(i);
			if(car.isVertical()){
				LinkedList<Vehicle> newcars = cloneCars(cars);
				Vehicle newcar = newcars.get(i);
				while(puzzle.canMoveDown(newcar)){
					newcar.moveDown();
					neighbors.add(new Puzzle(this.puzzle.tamañoTablero, newcars));
					newcars = cloneCars(newcars);
					newcar = newcars.get(i);
				}
				newcars = cloneCars(cars);
				newcar = newcars.get(i);
				while(puzzle.canMoveUp(newcar)){
					newcar.moveUp();
					neighbors.add(new Puzzle(this.puzzle.tamañoTablero, newcars));
					newcars = cloneCars(newcars);
					newcar = newcars.get(i);
				}
			}
			else if(car.isHorizontal()){
				LinkedList<Car> newcars = cloneCars(cars);
				Car newcar = newcars.get(i);
				while(puzzle.canMoveRight(newcar)){
					newcar.moveRight();
					neighbors.add(new Puzzle(this.puzzle.gridSize, newcars));
					newcars = cloneCars(newcars);
					newcar = newcars.get(i);
				}
				newcars = cloneCars(cars);
				newcar = newcars.get(i);
				while(puzzle.canMoveLeft(newcar)){
					newcar.moveLeft();
					neighbors.(new Puzzle(this.puzzle.gridSize, newcars));
					newcars = cloneCars(newcars);
					newcar = newcars.get(i);
				}
			}
		}
		
		return posibleMoves;
	}
        
        
        */
        
    }
    
    public boolean isSolved(){
        if (getObjectiveCar().posX == tamañoTablero || getObjectiveCar().posX == -1 ||
            getObjectiveCar().posY == tamañoTablero || getObjectiveCar().posY == -1){
                return true;
            }
        return false;
    }


    public boolean crashCars(int x, int y) {
		for(Vehicle car : cars){
			if(car.isHorizontal()){
				if(x == car.posX && y >= car.posY && y < car.posY  + car.size){
					colisionVehiculo = car;
					return true;
				}
			}
			else if(car.isVertical()){
				if(y == car.posY && x >= car.posX && x < car.posX + car.size){
					colisionVehiculo = car;
					return true;
				}
			}
		}
		return false;
	}

    
    public int getF(){ //costo
        Vehicle carro = getObjectiveCar();
        int count = 0; //distancia del objetivo al destino
        int carBlock = 0; //carros bloqueando        
           
        for(int i = 0; i < 5; i++){
            if (carro.isHorizontal()){
                if(destino == 6 && carro.posX < 6){
                    if (i > 1 && crashCars(carro.posX, i)){
                        carBlock++;
                    }
                    carro.moveRight();
                    count++;

                } else{
                    if (i < 4 && crashCars(carro.posX, i)){
                        carBlock++;
                    }                    
                    carro.moveLeft();
                    count++;
                }
            }
            if (carro.isVertical()){
                if(destino == 6 && carro.posY < 6){
                    if (i > 1 && crashCars(carro.posY, i)){
                        carBlock++;
                    }                    
                    carro.moveRight();
                    count++;
                } else{
                    if (i < 4 && crashCars(carro.posY, i)){
                        carBlock++;
                    }                      
                    carro.moveLeft();
                    count++;
                }                
            }
        }
        return carBlock + count;
    }
    
    public Vehicle getObjectiveCar(){
		return cars.get(0);
    }    
    
    public boolean canMoveDown(Vehicle car) {
		if(car.posX + car.size < tamañoTablero && !crashCars(car.posX+car.size, car.posY))
			return true;
		return false;
	}
	
	public boolean canMoveUp(Vehicle car) {
		if(car.posX > 0 && !crashCars(car.posX-1, car.posY))
			return true;
		return false;
	}
	
	public boolean canMoveRight(Vehicle car) {
		if(car.posY + car.size < tamañoTablero && !crashCars(car.posX, car.posY+car.size))
			return true;
		return false;
	}
	
	public boolean canMoveLeft(Vehicle car) {
		if(car.posY > 0 && !crashCars(car.posX, car.posY-1))
			return true;
		return false;
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
