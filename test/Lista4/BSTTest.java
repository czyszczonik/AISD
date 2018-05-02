package Lista4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BSTTest {

    BST bst;

    @Before
    public void setUp(){
        bst = new BST();
        bst.insert("G");
        bst.insert("B");
        bst.insert("A");
        bst.insert("Q");
        bst.insert("GG");
        bst.insert("QQ");
        bst.insert("BB");
    }

    @Test
    public void insertTest(){
        assertEquals("G", bst.getRoot().getKey());
        assertEquals("B",bst.getRoot().getLeftNode().getKey());
        assertEquals("BB",bst.getRoot().getLeftNode().getRightNode().getKey());
        assertEquals("A",bst.getRoot().getLeftNode().getLeftNode().getKey());
        assertEquals("Q",bst.getRoot().getRightNode().getKey());
        assertEquals("GG",bst.getRoot().getRightNode().getLeftNode().getKey());
        assertEquals("QQ",bst.getRoot().getRightNode().getRightNode().getKey());
    }

    @Test
    public void inOrderTest(){
        assertEquals("A B BB G GG Q QQ ", bst.inOrder(bst.getRoot()));
    }

    @Test
    public void findTest(){
        assertEquals(1,bst.find("A"));
        assertEquals(0,bst.find(""));
        assertEquals(0,bst.find("X"));
    }

    @Test
    public void minTest(){
        assertEquals("A",bst.minValue(bst.getRoot()));
    }

    @Test
    public void maxTest(){
        assertEquals("QQ",bst.maxValue(bst.getRoot()));
    }

    @Test
    public void successorTest(){
        assertEquals("GG",bst.getSuccessor("G"));
    }

    @Test
    public void twoChildNodeDeleteTest(){
        bst.delete("G");
        assertEquals("GG",bst.getRoot().getKey());
        assertEquals("A B BB GG Q QQ ",bst.inOrder(bst.getRoot()));
    }
    @Test
    public void oneChildren(){
        bst.delete("G");
        bst.delete("Q");
        assertEquals(bst.getRoot().getRightNode().getKey(),"QQ");
        assertEquals("GG", bst.getRoot().getKey());
        assertEquals("A B BB GG QQ ",bst.inOrder(bst.getRoot()));
    }
    @Test
    public void leafDelete(){
        bst.delete("A");
        assertEquals("B BB G GG Q QQ ", bst.inOrder(bst.getRoot()));
        assertNull(bst.getRoot().getLeftNode().getLeftNode());
    }
}
