package rushhour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import rushhour.Puzzle;


public class Astar {
    
    public ArrayList <Puzzle> opened; //evaluando
    public ArrayList <Puzzle> closed; //evaluados
    
    public Astar(){
        opened = new ArrayList<Puzzle>();
        closed = new ArrayList<Puzzle>();
    }

    //g: numero de carros bloqueando
    //h: distancia hacia el objetivo 
     public ArrayList<Puzzle>  heuristica(Puzzle puzzle,GUI GUI){
        Boolean loop = true;
        int bloqueos;
        Puzzle current;
        Puzzle nodo;
        opened.add(puzzle);
        
        while(loop) {

            current = opened.get(0);

            
            int valNodo;
            for(int i = 0; i<opened.size(); i++){ //obtenemos la opcion con menor costo
                nodo = opened.get(i);
                valNodo = nodo.getF();
                if(current.getF() > valNodo){
                    current = opened.get(i);
                }
            }
            opened.remove(current);
            closed.add(current);
            current.printMatrix(current.getMatrix());

            if(current.isSolved()){
                GUI.DrawGameSequence(closed);
                return closed;
            }
            for (Puzzle moves : current.posibleMoves()){
                Puzzle openNode = comparePuzzles(opened,moves ) ;
                if(openNode != null){
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
            for (Puzzle puzzle : list){
                  if(Arrays.deepEquals(puzzle.getMatrix(), find.getMatrix())){
                      return puzzle;
                  }
            }
        return null; //encontrarlo y retornar
         
    }
}