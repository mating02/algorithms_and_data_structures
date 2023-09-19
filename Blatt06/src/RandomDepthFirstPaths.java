import java.util.*;

public class RandomDepthFirstPaths {
    private boolean[] marked;    // marked[v] = is there an s-v path?
    private int[] edgeTo;        // edgeTo[v] = last edge on s-v path
    private final int s;         // source vertex
    public int[] distTo;
    private Queue<Integer> preorder;   // vertices in preorder
    private Queue<Integer> postorder;

    /**
     * Computes a path between {@code s} and every other vertex in graph {@code G}.
     * @param G the graph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public RandomDepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        postorder = new LinkedList<Integer>();
        preorder  = new LinkedList<Integer>();
        validateVertex(s);
    }

    public void randomDFS(Graph G) {
        randomDFS(G,s);
    }

    // depth first search from v
    private void randomDFS(Graph G, int v) {
        // TODO
        int count = 0;
        marked[v] = true;
        if(v == s){
            preorder.add(v);
        }
        Collections.shuffle(G.adj(v));
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                preorder.add(w);
                edgeTo[w] = v;
                count = distTo[v];
                count++;
                distTo[w] = count;
                randomDFS(G, w);
            }
        }
        postorder.add(v);
    }

    public void randomNonrecursiveDFS(Graph G) {
        // TODO
    }

    /**
     * Is there a path between the source vertex {@code s} and vertex {@code v}?
     * @param v the vertex
     * @return {@code true} if there is a path, {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns a path between the source vertex {@code s} and vertex {@code v}, or
     * {@code null} if no such path.
     * @param  v the vertex
     * @return the sequence of vertices on a path between the source vertex
     *         {@code s} and vertex {@code v}, as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     *
     * This method is different compared to the original one.
     */
    public List<Integer> pathTo(int v) {
        // TODO
        if(hasPathTo(v)){
            List<Integer> path = new LinkedList<Integer>();
            int node = v;
            path.add(v);
            while(node != s){
                node = edgeTo[node];
                path.add(node);
            }
            return path;
        }
        else{
            return null;
        }
    }

    public int [] edge() {
        return edgeTo;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

}



