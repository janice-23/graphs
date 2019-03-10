package graph;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* See restrictions in Graph.java. */

/** A partial implementation of Graph containing elements common to
 *  directed and undirected graphs.
 *
 *  @author JaniceNg
 */
abstract class GraphObj extends Graph {

    /** A new, empty Graph. */
    GraphObj() {
        _edge = new ArrayList<int[]>();
        _verticies = new ArrayList<Integer>();
    }

    @Override
    public int vertexSize() {
        return _verticies.size();
    }

    @Override
    public int maxVertex() {
        if (_verticies.isEmpty()) {
            return 0;
        } else {
            return Collections.max(_verticies);

        }
    }

    @Override
    public int edgeSize() {
        return _edge.size();
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
        int count = 0;
        for (int j = 0; j < _edge.size(); j += 1) {
            if (_edge.get(j)[0] == v) {
                count++;
            } else if (!isDirected()) {
                if (_edge.get(j)[1] == v) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public abstract int inDegree(int v);

    @Override
    public boolean contains(int u) {
        int i = 0;
        while (i < _verticies.size()) {
            if (_verticies.get(i) == u) {
                return true;
            }
            i += 1;
        }
        return false;
    }

    @Override
    public boolean contains(int u, int v) {
        if (!contains(u)) {
            return false;
        } else if (!contains(v)) {
            return false;
        }
        for (int y = 0; y < _edge.size(); y += 1) {
            if (_edge.get(y)[0] == u && _edge.get(y)[1] == v) {
                return true;
            }
            if (!isDirected()) {
                if (_edge.get(y)[0] == v && _edge.get(y)[1] == u) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int add() {
        int track = 0;
        while (track < vertexSize() && _verticies.get(track) == track + 1) {
            track++;
        }
        _verticies.add(track, track + 1);
        return track + 1;
    }

    @Override
    public int add(int u, int v) {
        int count = 0;
        while (count < _edge.size()) {
            if (_edge.get(count)[0] == u && _edge.get(count)[1] == v) {
                return edgeId(u, v);
            }
            count += 1;
        }
        int [] newedgelist = new int[2];
        newedgelist[0] = u;
        newedgelist[1] = v;
        _edge.add(newedgelist);
        return edgeId(u, v);
    }

    @Override
    public void remove(int v) {
        int i = 0;
        while (i < _verticies.size()) {
            if (_verticies.get(i) == v) {
                _verticies.remove(i);
            } else {
                i += 1;
            }
        }
        i = 0;
        while (i < _edge.size()) {
            if (_edge.get(i)[0] == v || _edge.get(i)[1] == v) {
                _edge.remove(i);
            } else {
                i += 1;
            }
        }
    }


    @Override
    public void remove(int u, int v) {
        for (int j = 0; j < _edge.size(); j += 1) {
            if (_edge.get(j)[0] == u && _edge.get(j)[1] == v) {
                _edge.remove(j);
            }
        }
    }

    @Override
    public Iteration<Integer> vertices() {
        return Iteration.iteration(_verticies.iterator());
    }

    /** Helper function for predecessors to get successor
     * Y of V, indexing from 0.
     * @return
     */
    int helpersuccessor(int v, int y) {
        int count = y;
        for (int i = 0; i < _edge.size(); i += 1) {
            if (_edge.get(i)[0] == v) {
                if (count == 0) {
                    return _edge.get(i)[1];
                } else {
                    count -= 1;
                }
            } else if (_edge.get(i)[1] == v && !isDirected()) {
                if (count == 0) {
                    return _edge.get(i)[0];
                } else {
                    count -= 1;
                }
            }
        }
        return 0;
    }
    @Override
    public Iteration<Integer> successors(int v) {
        ArrayList<Integer> successorslist = new ArrayList<Integer>();
        for (int j = 0; j < outDegree(v); j += 1) {
            successorslist.add(helpersuccessor(v, j));
        }
        Iterator<Integer> iter = successorslist.iterator();
        return Iteration.iteration(iter);

    }

    @Override
    public abstract Iteration<Integer> predecessors(int v);

    @Override
    public Iteration<int[]> edges() {
        return Iteration.iteration(_edge.iterator());
    }

    @Override
    protected void checkMyVertex(int v) {
        if (!contains(v)) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    protected int edgeId(int u, int v) {
        if (isDirected()) {
            u -= 1;
            v -= 1;
            return (u + v) * (u + v + 1) / 2 + v;
        } else {
            int max = Math.max(u, v);
            int min = Math.min(u, v);
            return (max + min) * (max + min + 1) / 2 + min;
        }
    }


    /** Arraylist containing edges in graph between pair of verticies.*/
    private ArrayList<int[]> _edge;

    /** Arraylist containing verticies in graph.*/
    private ArrayList<Integer> _verticies;

    /** Returns number of edges. */
    public ArrayList<int[]> numEdges() {
        return _edge;
    }
    /** Returns number of verticies. */
    public ArrayList<Integer> numVerticies() {
        return _verticies;
    }
}
