package Lista4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RBTEST {
    RBTree bst;

    @Before
    public void setUp() {
        bst = new RBTree();
        bst.insert("G");
        bst.insert("B");
        bst.insert("A");
        bst.insert("Q");
        bst.insert("GG");
        bst.insert("QQ");
        bst.insert("BB");
    }

    @Test
    public void insertTest() {
        assertEquals("B", bst.getRoot().getKey());
        assertEquals("A", bst.getRoot().getLeft().getKey());
        assertEquals("GG", bst.getRoot().getRight().getKey());
        assertEquals("G", bst.getRoot().getRight().getLeft().getKey());
        assertEquals("Q", bst.getRoot().getRight().getRight().getKey());
        assertEquals("QQ", bst.getRoot().getRight().getRight().getRight().getKey());
        assertEquals("BB", bst.getRoot().getRight().getLeft().getLeft().getKey());
    }

    @Test
    public void inOrderTest() {
        assertEquals("A B BB G GG Q QQ ", bst.inOrder(bst.getRoot()));
    }
    @Test
    public void inOrderSplitTest() {
        assertEquals("A;B;BB;G;GG;Q;QQ;", bst.inOrderSplit(bst.getRoot()));
    }

    @Test
    public void findTest() {
        assertEquals(1, bst.find("A"));
        assertEquals(0, bst.find(""));
        assertEquals(0, bst.find("X"));
    }

    @Test
    public void minTest() {
        assertEquals("A", bst.minValue(bst.getRoot()).getKey());
    }

    @Test
    public void maxTest() {
        assertEquals("QQ", bst.maxValue(bst.getRoot()));
    }

    @Test
    public void successorTest(){
        assertEquals("GG",bst.getSuccessor("G"));
    }

    @Test
    public void twoChildNodeDeleteTest() {
        bst.delete("G");
        assertEquals("B", bst.getRoot().getKey());
        assertEquals("GG", bst.getRoot().getRight().getKey());
        assertEquals("A B BB GG Q QQ ", bst.inOrder(bst.getRoot()));
    }

    @Test
    public void oneChildren() {
        bst.delete("G");
        bst.delete("Q");
        assertEquals(bst.getRoot().getRight().getKey(), "GG");
        assertEquals("B", bst.getRoot().getKey());
        assertEquals("A B BB GG QQ ", bst.inOrder(bst.getRoot()));
    }

    @Test
    public void leafDelete() {
        bst.delete("A");
        assertEquals("B BB G GG Q QQ ", bst.inOrder(bst.getRoot()));
        assertNull(bst.getRoot().getLeft().getLeft().getKey());
    }
}
