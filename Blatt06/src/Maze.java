import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
/**
 * Class that represents a maze with N*N junctions.
 *
 * @author Vera Röhr
 */
public class Maze{
    private final int N;
    private Graph M;    //Maze
    public int startnode;

    public Maze(int N, int startnode) {

        if (N < 0) throw new IllegalArgumentException("Number of vertices in a row must be nonnegative");
        this.N = N;
        this.M= new Graph(N*N);
        this.startnode= startnode;
        buildMaze();
    }

    public Maze (In in) {
        this.M = new Graph(in);
        this.N= (int) Math.sqrt(M.V());
        this.startnode=0;
    }


    /**
     * Adds the undirected edge v-w to the graph M.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, int w) {
        // TODO
        if(v < 0 || v > M.V()){
            throw new IllegalArgumentException("vertex/vertices are not valid");
        }
        if(w < 0 || w > M.V()){
            throw new IllegalArgumentException("vertex/vertices are not valid");
        }
        M.addEdge(v, w);
    }

    /**
     * Returns true if there is an edge between 'v' and 'w'
     * @param v one vertex
     * @param w another vertex
     * @return true or false
     */
    public boolean hasEdge( int v, int w){
        // TODO
        if(v < 0 || v > M.V()){
            return false;
        }
        if(w < 0 || w > M.V()){
            return false;
        }
        if(v == w){
            return true;
        }
        if(M.adj(v).contains(w) || M.adj(w).contains(v)) {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Builds a grid as a graph.
     * @return Graph G -- Basic grid on which the Maze is built
     */
    public Graph mazegrid() {
        // TODO
        Graph grid = new Graph(M.V());
        int k = 1;
        int f = 0;
        for(int j = 0; j < M.V(); j++){
            if(j != (k*N)-1) {
                if(!grid.adj(j).contains(j+1) && !grid.adj(j+1).contains(j)){
                    grid.addEdge(j, j + 1);
                }
            }
            else{
                k++;
            }
            if(j != f*N) {
                if(!grid.adj(j).contains(j-1) && !grid.adj(j-1).contains(j)){
                    grid.addEdge(j, j - 1);
                }
            }
            else{
                f++;
            }
            if(j < (N-1)*N ) {
                if (!grid.adj(j).contains(j+N) && !grid.adj(j+N).contains(j)) {
                    grid.addEdge(j, j + N);
                }
            }
            if(j >= N) {
                if(!grid.adj(j).contains(j-N) && !grid.adj(j-N).contains(j)) {
                    grid.addEdge(j, j - N);
                }
            }
        }

        return grid;
    }

    /**
     * Builds a random maze as a graph.
     * The maze is build with a randomized DFS as the Graph M.
     */
    private void buildMaze() {
        // TODO
        //use grid and do randomdfs
        Graph grid = mazegrid();
        RandomDepthFirstPaths lab = new RandomDepthFirstPaths(grid, 0);
        lab.randomDFS(grid);
        //addedge
        for(int i = M.V()-1; i >= 0; i--){
            if(!hasEdge(i, lab.edge()[i])) {
                M.addEdge(i, lab.edge()[i]);
            }
        }
    }

    /**
     * Find a path from node v to w
     * @param v start node
     * @param w end node
     * @return List<Integer> -- a list of nodes on the path from v to w (both included) in the right order.
     */
    public List<Integer> findWay(int v, int w){
        // TODO
        RandomDepthFirstPaths showmeway = new RandomDepthFirstPaths(M, w);
        showmeway.randomDFS(M);
        return showmeway.pathTo(v);
    }

    /**
     * @return Graph M
     */
    public Graph M() {
        return M;
    }

    public static void main(String[] args) {
        // FOR TESTING
        Maze test = new Maze(4,0);
        GridGraph vis = new GridGraph(test.M(),test.findway(1,11));
    }


}


