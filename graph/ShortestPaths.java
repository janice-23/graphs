package graph;

/* See restrictions in Graph.java. */


import java.util.List;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.LinkedList;


/** The shortest paths through an edge-weighted graph.
 *  By overrriding methods getWeight, setWeight, getPredecessor, and
 *  setPredecessor, the client can determine how to represent the weighting
 *  and the search results.  By overriding estimatedDistance, clients
 *  can search for paths to specific destinations using A* search.
 *  @author JaniceNg
 */
public abstract class ShortestPaths {

    /**
     * The shortest paths in G from SOURCE.
     */
    public ShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /**
     * A shortest path in G from SOURCE to DEST.
     */
    public ShortestPaths(Graph G, int source, int dest) {
        _G = G;
        _source = source;
        _dest = dest;
        _tree = new TreeSet<Integer>(new CompareWeight());
    }

    /**
     * Initialize the shortest paths.  Must be called before using
     * getWeight, getPredecessor, and pathTo.
     */
    public void setPaths() {
        _tree.add(_source);
        for (int i = 0; i < _G.maxVertex() + 1; i += 1) {
            setWeight(i, Double.POSITIVE_INFINITY);
        }
        setWeight(_source, 0);
        while (!_tree.isEmpty()) {
            int a = _tree.pollFirst();
            if (a == _dest) {
                return;
            }
            for (int b: _G.successors(a)) {
                double dist1 = getWeight(a) + getWeight(a, b);
                double dist2 = getWeight(b);
                if (dist1 < dist2) {
                    _tree.remove(b);
                    setWeight(b, dist1);
                    _tree.add(b);
                    setPredecessor(b, a);
                }
            }
        }
    }

    /**
     * Returns the starting vertex.
     */
    public int getSource() {
        return _source;
    }

    /**
     * Returns the target vertex, or 0 if there is none.
     */
    public int getDest() {
        return _dest;
    }

    /**
     * Returns the current weight of vertex V in the graph.  If V is
     * not in the graph, returns positive infinity.
     */
    public abstract double getWeight(int v);

    /**
     * Set getWeight(V) to W. Assumes V is in the graph.
     */
    protected abstract void setWeight(int v, double w);

    /**
     * Returns the current predecessor vertex of vertex V in the graph, or 0 if
     * V is not in the graph or has no predecessor.
     */
    public abstract int getPredecessor(int v);

    /**
     * Set getPredecessor(V) to U.
     */
    protected abstract void setPredecessor(int v, int u);

    /**
     * Returns an estimated heuristic weight of the shortest path from vertex
     * V to the destination vertex (if any).  This is assumed to be less
     * than the actual weight, and is 0 by default.
     */
    protected double estimatedDistance(int v) {
        return 0.0;
    }

    /**
     * Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     * not in the graph, returns positive infinity.
     */
    protected abstract double getWeight(int u, int v);

    /**
     * Returns a list of vertices starting at _source and ending
     * at V that represents a shortest path to V.  Invalid if there is a
     * destination vertex other than V.
     */
    public List<Integer> pathTo(int v) {
        LinkedList<Integer> shortpath = new LinkedList<>();
        while ((getPredecessor(v) != 0)) {
            shortpath.addFirst(v);
            v = getPredecessor(v);
        }
        shortpath.addFirst(_source);
        return shortpath;

    }

    /**
     * Returns a list of vertices starting at the source and ending at the
     * destination vertex. Invalid if the destination is not specified.
     */
    public List<Integer> pathTo() {
        return pathTo(getDest());
    }


    /**
     * The graph being searched.
     */
    protected final Graph _G;
    /**
     * The starting vertex.
     */
    private final int _source;
    /**
     * The target vertex.
     */
    private final int _dest;
    /**
     * Fringe Tree Set used for traversing.
     */
    protected TreeSet<Integer> _tree;
    /**
     *  Tree Set of visited nodes.
     */
    protected TreeSet<Integer> _visited;
    /**
     *  Tree Set of unvisited nodes.
     */
    protected TreeSet<Integer> _unvisited;

    /**
     * Comparator to compare Weights,
     * ordering of edges by their weights.
     */
    private class CompareWeight implements Comparator<Integer> {
        @Override
        public int compare(Integer v1, Integer v2) {
            if (getWeight(v1) + estimatedDistance(v1)
                    == getWeight(v2) + estimatedDistance(v2)) {
                if (v1 > v2) {
                    return 1;
                } else if (v2 > v1) {
                    return -1;
                } else {
                    return 0;
                }
            } else {
                if (getWeight(v1) + estimatedDistance(v1)
                        < getWeight(v2) + estimatedDistance(v2)) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
    }
}
