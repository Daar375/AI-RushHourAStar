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
    LinkedList<Vehicle> cars;
    Vehicle colisionVehiculo;

    private Integer tamañoTablero;
    


    // MÉTODOS --------------------------------------------------------------------
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
        
    }
    
    public boolean isSolved(){
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
