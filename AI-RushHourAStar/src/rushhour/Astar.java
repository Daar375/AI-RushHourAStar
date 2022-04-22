package rushhour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Astar {
    
    public ArrayList<Puzzle> puzzle_queue= new ArrayList();
	
    public ArrayList<Puzzle> searchAStar(Puzzle puzzle) {
        HashMap<Puzzle, Puzzle> predecessor = new HashMap<>();
        ArrayList<Puzzle> visited = new ArrayList();
        Puzzle path_puzzle =  puzzle;
        Puzzle is_goal = null;
        path_puzzle.score=0;
        puzzle_queue.add(path_puzzle);
        visited.add(path_puzzle);
        while(!puzzle_queue.isEmpty()){
                Puzzle current_puzzle =  puzzle_queue.remove(0);
                if(current_puzzle.isSolved()){ //Si esta resuelto
                        is_goal = current_puzzle;
                        break;
                }
                for(Puzzle element_puzzle : current_puzzle.posibleMoves(0)){ //Para cada movimiento posible se evalua
                        float cost = current_puzzle.score + 1 + element_puzzle.getF();
                        if(comparePuzzles(visited, element_puzzle)==null){
                                element_puzzle.score=cost;
                                puzzle_queue.add(element_puzzle);
                                predecessor.put(element_puzzle, current_puzzle);
                                visited.add(element_puzzle);
                        }
                }
        }

        return getPath(predecessor, is_goal);
    }
        
        
    private ArrayList<Puzzle> getPath(HashMap<Puzzle, Puzzle> pred, Puzzle goal) {

            ArrayList<Puzzle> path =  new ArrayList<>();
            Puzzle u = goal;
            path.add(0, u);
            while(pred.get(u) != null){
                    Puzzle parent = pred.get(u);
                    path.add(0, parent);

                    u = parent;
            }

            return path;
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