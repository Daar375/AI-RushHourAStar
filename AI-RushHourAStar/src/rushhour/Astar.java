//import puzzle

public class Astar {
    
    public List <Puzzle> opened;
    public List <Puzzle> closed;
    
    public Astar(){
        opened = new arraylist<Puzzle>();
        closed = new arraylist<Puzzle>();
    }

    //g: numero de carros bloqueando
    //h: distancia hacia el objetivo 
    public heuristica(Puzzle puzzle){
        bool loop = true;
        int bloqueos;
        Puzzle current;
        Puzzle nodo;
        opened.add(puzzle);

        while(loop) {
            current = opened.get(0);
            int val = current.getBloqueos() + current.getDistancia();
            int valNodo;
            for(int i = 0; i<opened.size(); i++){ //obtenemos la opcion con menor costo
                nodo = opened.get(i);
                valNodo = nodo.getBloqueos() + nodo.getDistancia();
                if(val > valNodo){
                    current = opened.get(i);
                }
            }
            opened.remove(current);
            closed.add(current);

            if(current.isSolved()){
                return closed;
            }
            int f;
            for (Puzzle moves : current.posibleMoves()){
                f = moves.getBloqueos + moves.getDistancia;
                if(opened.contains(moves)){
                    openNode = opened.get(moves);
                    if(true){ //cambiar a if openNode tiene mayor costo
                        opened.remove(openNode);
                        opened.add(moves);
                    }
                } else if (!closed.contains(moves)){
                    opened.add(moves);
                }
            }
            



        }



        
    }
}