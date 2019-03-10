package graph;

import org.junit.Test;
import static org.junit.Assert.*;


/** Unit tests for the Graph class.
 *  @author JaniceNg
 */
public class TraversalTesting {
    @Test
    public void testBFT() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 6);
        g.add(1, 3);
        g.add(3, 4);
        g.add(6, 4);
        g.add(3, 2);
        g.add(4, 1);
        BreadthFirstTraversal bft = new BreadthFirstTraversal(g);
        bft.traverse(1);
        assertEquals(true, bft.marked(1));
        assertEquals(true, bft.marked(3));
        assertEquals(false, bft.marked(0));

    }
    @Test
    public void testDFT() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(0, 1);
        g.add(1, 2);
        g.add(3, 7);
        g.add(7, 5);
        g.add(5, 6);
        DepthFirstTraversal dft = new DepthFirstTraversal(g);
        dft.traverse(5);
        assertEquals(false, dft.marked(2));
        assertEquals(true, dft.marked(5));
        assertEquals(true, dft.marked(6));
        assertEquals(false, dft.marked(3));
        dft.traverse(1);
        assertEquals(false, dft.marked(0));
        assertEquals(true, dft.marked(2));
    }
}
