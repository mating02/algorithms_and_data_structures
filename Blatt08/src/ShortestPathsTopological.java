import java.util.Arrays;
import java.util.Stack;

public class ShortestPathsTopological {
    private int[] parent;
    private int s;
    private double[] dist;

    public ShortestPathsTopological(WeightedDigraph G, int s) {
        // TODO
        this.s = s;
        parent = new int[G.V()];
        dist = new double[G.V()];
        TopologicalWD topo = new TopologicalWD(G);
        topo.dfs(s);
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        while(topo.order().size() != 0){
            int v = topo.order().pop();
            for(DirectedEdge e : G.incident(v)){
                relax(e);
            }
        }

    }

    public void relax(DirectedEdge e) {
        // TODO
        int v = e.from();
        int w = e.to();
        if(dist[w] > dist[v] + e.weight()){
            dist[w] = dist[v] + e.weight();
            parent[w] = v;
        }
    }

    public boolean hasPathTo(int v) {
        return parent[v] >= 0;
    }

    public Stack<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int w = v; w != s; w = parent[w]) {
            path.push(w);
        }
        path.push(s);
        return path;
    }
}

