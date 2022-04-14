/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rushhour;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;



/**
 *
 * @author JP
 */
public class Parser {
    
    
    public LinkedList<Vehicle> parseFile(String filename){
        LinkedList<Vehicle> lista = new LinkedList<>();
        try {
          File archivo = new File(filename);
          Scanner reader = new Scanner(archivo);
          while (reader.hasNextLine()) {
            String data = reader.nextLine();
            //lista.add(por definir)
            System.out.println(data);
          }
          reader.close();
        } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
        return lista;
    }
}