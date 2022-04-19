package rushhour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import rushhour.Puzzle;


public class Astar {
    
    public ArrayList <Puzzle> opened; //evaluando
    public ArrayList <Puzzle> closed; //evaluados
    
    public Astar(){
        opened = new ArrayList<Puzzle>();
        closed = new ArrayList<Puzzle>();
    }
    	public ArrayList<Puzzle> pq= new ArrayList();;
	public ArrayList<Puzzle> searchAStar(Puzzle puzzle) {
		HashMap<Puzzle, Puzzle> predecessor = new HashMap<>();
                ArrayList<Puzzle> visited = new ArrayList();
		Puzzle src =  puzzle;
		Puzzle goal = null;
		src.score=0;
		pq.add(src);
		visited.add(src);
		while(!pq.isEmpty()){
			Puzzle u =  pq.remove(0);
			if(u.isSolved()){
				goal = u;
				break;
			}
			for(Puzzle v : u.posibleMoves(0)){
				float cost = u.score + 1 + v.getF();
				if(comparePuzzles(visited, v)==null){
					v.score=cost;
					pq.add(v);
					predecessor.put(v, u);
					visited.add(v);
				}
			}
		}
		
		return getPath(predecessor, goal);
	}
        
        
	private ArrayList<Puzzle> getPath(HashMap<Puzzle, Puzzle> pred, Puzzle goal) {
		
		ArrayList<Puzzle> path =  new ArrayList<Puzzle>();
		Puzzle u = goal;
		path.add(0, u);
		while(pred.get(u) != null){
			Puzzle parent = pred.get(u);
                        path.add(0, parent);

			u = parent;
		}

		return path;
	}
    //g: numero de carros bloqueando
    //h: distancia hacia el objetivo 
    
    
     private Puzzle comparePuzzles(ArrayList<Puzzle> list, Puzzle find ){
            for (Puzzle puzzle : list){
                  if(Arrays.deepEquals(puzzle.getMatrix(), find.getMatrix())){
                      return puzzle;
                  }
            }
        return null; //encontrarlo y retornar
         
    }
}