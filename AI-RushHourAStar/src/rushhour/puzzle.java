/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package rushhour;

/**
 *
 * @author Jose Rivera
 */
public class puzzle {    
    
    private String nombre;
    //private Node nodoInit;
    
    private Integer contBusqueda;
    
    private Integer numVehiculos;
    private Integer posicionFija[];
    private Integer tamañoVehiculo[];
    private String orientacionVehiculo[];
    
    private Integer tamañoTablero;
    
    public Integer getNumVehiculos() {
        return numVehiculos;
    }
    
    public Integer getposicionFija(int posicion) {
        return posicionFija[posicion];
    }
    
    public Integer getTamañoVehiculo(int tamaño) {
        return tamañoVehiculo[tamaño];
    }
    
    public String getOrientacionVehiculo(int orientacion) {
        return orientacionVehiculo[orientacion];
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
