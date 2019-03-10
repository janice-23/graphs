package graph;

import org.junit.Test;
import static org.junit.Assert.*;


/** Unit tests for the Graph class.
 *  @author JaniceNg
 */
public class GraphTest {

    @Test
    public void emptyGraph() {
        DirectedGraph g = new DirectedGraph();
        assertEquals("Initial graph has vertices", 0, g.vertexSize());
        assertEquals("Initial graph has edges", 0, g.edgeSize());
    }

    @Test
    public void testInsert() {
        DirectedGraph g = new DirectedGraph();
        UndirectedGraph ug = new UndirectedGraph();
    }

    @Test
    public void undirectcontains() {
        UndirectedGraph undirected = new UndirectedGraph();
        assertFalse(undirected.contains(1, 5));
        assertFalse(undirected.contains(4, 2));
        assertFalse(undirected.contains(5, 3));
        assertFalse(undirected.contains(1, 6));
        assertFalse(undirected.contains(2, 2));
    }

    @Test
    public void undirectcontains2() {
        UndirectedGraph undirected = new UndirectedGraph();
        assertFalse(undirected.contains(1, 3));
        assertFalse(undirected.contains(4, 2));
        assertFalse(undirected.contains(3, 3));
        assertFalse(undirected.contains(4, 6));
        assertFalse(undirected.contains(3, 1));
    }

    @Test
    public void directedcontains() {
        DirectedGraph directed = new DirectedGraph();
        assertFalse(directed.contains(4, 1));
        assertFalse(directed.contains(3, 4));
        assertFalse(directed.contains(5, 2));
        assertFalse(directed.contains(14, 9));
    }
    @Test
    public void undirectedSuccessor2() {
        UndirectedGraph undirected = new UndirectedGraph();
        undirected.add();
        undirected.add();
        undirected.add();
        undirected.add();
        undirected.add();
        undirected.add(1, 3);
        undirected.add(2, 4);
        undirected.add(1, 2);
        undirected.add(4, 5);
        assertEquals(3, undirected.helpersuccessor(1, 0));
        assertEquals(4, undirected.helpersuccessor(2, 0));
        assertEquals(2, undirected.helpersuccessor(1, 1));
        assertEquals(0, undirected.helpersuccessor(6, 1));
        assertEquals(5, undirected.helpersuccessor(4, 1));
        undirected.remove(1, 3);
        assertEquals(0, undirected.helpersuccessor(3, 0));
        assertEquals(2, undirected.helpersuccessor(1, 0));
    }

    @Test
    public void directedsuccessortest() {
        DirectedGraph directed = new DirectedGraph();
        directed.add();
        directed.add();
        directed.add();
        directed.add();
        directed.add();
        directed.add(1, 2);
        directed.add(1, 3);
        directed.add(1, 4);
        directed.add(3, 4);
        directed.add(2, 5);
        assertEquals(2, directed.helpersuccessor(1, 0));
        assertEquals(3, directed.helpersuccessor(1, 1));
        assertEquals(4, directed.helpersuccessor(1, 2));
        assertEquals(4, directed.helpersuccessor(1, 2));
        assertEquals(0, directed.helpersuccessor(1, 3));
        assertEquals(5, directed.helpersuccessor(2, 0));
        assertEquals(4, directed.helpersuccessor(3, 0));
        directed.remove(1, 3);
        assertEquals(4, directed.helpersuccessor(1, 1));
        assertEquals(0, directed.helpersuccessor(5, 0));

    }

    @Test
    public void undirectededgecheck() {
        UndirectedGraph undirected = new UndirectedGraph();
        undirected.add();
        undirected.add();
        undirected.add(1, 1);
        undirected.add(2, 1);
        assertEquals(2, undirected.edgeSize());
        undirected.remove(2, 1);
        assertEquals(1, undirected.edgeSize());
    }

    @Test
    public void directedgecheck() {
        DirectedGraph directed = new DirectedGraph();
        directed.add();
        directed.add();
        directed.add(1, 1);
        directed.add(1, 2);
        assertEquals(2, directed.edgeSize());

    }

    @Test
    public void undirectededgecheck2() {
        UndirectedGraph undirected = new UndirectedGraph();
        undirected.add();
        undirected.add();
        undirected.add();
        undirected.add(1, 1);
        undirected.add(2, 1);
        undirected.add(2, 3);
        assertEquals(3, undirected.edgeSize());
        undirected.remove(2, 1);
        assertEquals(2, undirected.edgeSize());
    }

    @Test
    public void directedgecheck2() {
        DirectedGraph directed = new DirectedGraph();
        directed.add();
        directed.add();
        directed.add();
        directed.add();
        directed.add(1, 1);
        directed.add(1, 2);
        directed.add(2, 3);
        directed.add(3, 4);
        assertEquals(4, directed.edgeSize());

    }

    @Test
    public void direct2edgecheck() {
        DirectedGraph directed = new DirectedGraph();
        directed.add();
        directed.add();
        directed.add(1, 1);
        directed.add(1, 2);
        directed.add(1, 4);
        directed.remove(1, 2);
        assertEquals(2, directed.edgeSize());

    }


    @Test
    public void directedGraph() {
        DirectedGraph directed = new DirectedGraph();
        directed.add();
        directed.add();
        directed.add();
        directed.add();
        directed.add();
        directed.remove(1);
        assertFalse(directed.contains(1));
        assertEquals("Number of vertices", 4, directed.vertexSize());
        directed.add();
        assertTrue(directed.contains(1));
        assertEquals("Number of vertices", 5, directed.vertexSize());
        directed.add(1, 2);
        directed.add(1, 3);
        directed.add(3, 4);
        directed.add(4, 2);
        directed.add(3, 2);
        assertEquals("Number of out-edges from Vertex 1",
                2, directed.outDegree(1));
        assertEquals("Number of edges", 5, directed.edgeSize());
        assertEquals("Number of out edges from Vertex2",
                0, directed.outDegree(2));
        directed.remove(3, 2);
        assertFalse(directed.contains(3, 2));
        Iteration<Integer> iter = directed.successors(1);
        int next1 = iter.next();
        assertEquals(2, next1);
        next1 = iter.next();
        assertEquals(3, next1);
        assertFalse(iter.hasNext());
        Iteration<Integer> iter2 = directed.successors(3);
        assertTrue(iter2.hasNext());
        int next2 = iter2.next();
        assertEquals(4, next2);
        Iteration<Integer> iterpre2 = directed.predecessors(2);
        int prenext = iterpre2.next();
        assertEquals(1, prenext);
        int prenext2 = iterpre2.next();
        assertEquals(4, prenext2);
        directed.remove(1, 2);
        assertEquals(3, directed.edgeSize());

    }

    @Test
    public void undirectedGraph() {
        UndirectedGraph undirected = new UndirectedGraph();
        undirected.add();
        undirected.add();
        undirected.add();
        undirected.add();
        undirected.add(1, 2);
        undirected.add(2, 3);
        undirected.add(1, 3);
        undirected.add(1, 4);
        undirected.add(3, 4);
        assertEquals("Number of edges", 5, undirected.edgeSize());
        assertEquals("Number of vertices", 4, undirected.vertexSize());
        assertEquals("Indegree for V1", 3, undirected.inDegree(1));
        assertEquals("Indegree for V4", 2, undirected.inDegree(4));
        assertEquals("Indegree for V3", 3, undirected.inDegree(3));
        undirected.remove(1, 4);
        assertEquals(4, undirected.edgeSize());
        Iteration<Integer>  undirectedpre = undirected.predecessors(1);
        int prenext1 = undirectedpre.next();
        assertEquals(2, prenext1);
        int prenext2 = undirectedpre.next();
        assertEquals(3, prenext2);
        assertFalse(undirectedpre.hasNext());
        Iteration<Integer>  undirectedpre2 = undirected.predecessors(3);
        int pre2next1 = undirectedpre2.next();
        assertEquals(2, pre2next1);
    }

}
