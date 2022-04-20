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
            Scanner parser = new Scanner(new File(filename));
            
            String eval;
            int counter = 0;
            int x = 0;
            int y = 0;
            int size = 0;
            String type = "";
            String orientation = "";
            Vehicle car;
            while (parser.hasNext()) {
                eval = parser.next();
                if(!eval.equals("exit")){
                    for(String elem : eval.split(",")){
                        switch(counter%5){
                            case 0:

                                type = elem;
                                counter++;
                                break;
                            case 1:
                              
                                x = Integer.parseInt(elem);
                                counter++;
                                break;
                            case 2:
                              
                                y = Integer.parseInt(elem);
                                counter++;
                                break;                            
                            case 3:
                                
                                orientation = elem;
                                counter++;
                                break;
                            case 4:
                                
                                size = Integer.parseInt(elem);
                                car = new Vehicle(type, orientation, size, x, y);
                                lista.add(car);
                                counter++;
                                break;
                        
                        }

                    }

                } else{
                    break;
                }
            }
            parser.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return lista;
    }
    
    public int[] parseExit(String filename){
        int[] exit = new int[2];
        String eval;
        int x = 0;
        int y = 0;
        int counter = 0;
        try {
            Scanner parser = new Scanner(new File(filename));  
            while (parser.hasNext()) {
                eval = parser.next();
                for(String elem : eval.split(",")){ 
                    if(counter == 2){
                        y = Integer.valueOf(elem);
                        break;
                    }                    
                    if(counter == 1){
                        x = Integer.valueOf(elem);
                        counter++;
                    }                    
                    if (elem.equals("exit")){                        
                        counter++;
                    }

                }                  
            }
            parser.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        exit[0] = x;
        exit[1] = y;

        return exit;
    }
    
}