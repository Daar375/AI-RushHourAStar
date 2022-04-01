package rushhour;

import java.util.ArrayList;
import rushhour.Puzzle;

//import puzzle

public class Astar {
    
    public ArrayList <Puzzle> opened;
    public ArrayList <Puzzle> closed;
    
    public Astar(){
        opened = new ArrayList<Puzzle>();
        closed = new ArrayList<Puzzle>();
    }

    //g: numero de carros bloqueando
    //h: distancia hacia el objetivo 
     public ArrayList<Puzzle>  heuristica(Puzzle puzzle){
        Boolean loop = true;
        int bloqueos;
        Puzzle current;
        Puzzle nodo;
        opened.add(puzzle);

        while(loop) {
            current = opened.get(0);
            int val = current.getF();
            int valNodo;
            for(int i = 0; i<opened.size(); i++){ //obtenemos la opcion con menor costo
                nodo = opened.get(i);
                valNodo = nodo.getF();
                if(val > valNodo){
                    current = opened.get(i);
                }
            }
            opened.remove(current);
            closed.add(current);

            if(current.isSolved()){
                return closed;
            }
            for (Puzzle moves : current.posibleMoves()){
                if(opened.contains(moves)){
                    Puzzle openNode = comparePuzzles (opened,moves ) ;
                    if(openNode.getF() < moves.getF()){ 
                        opened.remove(openNode);
                        opened.add(moves);
                    }
                } else if (!closed.contains(moves)){
                    opened.add(moves);
                }
            }

        }
        return null;



        
    }
     
     
     private Puzzle comparePuzzles(ArrayList<Puzzle> list, Puzzle find ){
        return null;
         
    }
}