package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;

/** Represents a general unlabeled directed graph whose vertices are denoted by
 *  positive integers. Graphs may have self edges.
 *
 *  @author JaniceNg
 */
public class DirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public int inDegree(int v) {
        Iteration<Integer> i = predecessors(v);
        int size = 0;
        for (int c : i) {
            size += 1;
        }
        return size;
    }
    /** Helper function for predecessors to get successor
     * * Y of V, indexing from 0.
     * @return
     * */

    private int helperpredecessor(int v, int y) {
        int count = y;
        for (int i = 0; i < numEdges().size(); i += 1) {
            if (numEdges().get(i)[1] == v) {
                if (count == 0) {
                    return numEdges().get(i)[0];
                } else {
                    count -= 1;
                }
            }
        }
        return 0;
    }

    @Override
    public Iteration<Integer> predecessors(int v) {
        ArrayList<Integer> p = new ArrayList<Integer>();
        int k = 0;
        while (helperpredecessor(v, k) != 0) {
            p.add(helperpredecessor(v, k));
            k += 1;
        }
        return Iteration.iteration(p);
    }

}
