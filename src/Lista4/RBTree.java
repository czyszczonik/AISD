package Lista4;

import static Lista4.Color.BLACK;
import static Lista4.Color.RED;

public class RBTree {
    private RBNODE nil = new RBNODE();
    private RBNODE root = nil;

    public RBTree() {
        root.setLeft(nil);
        root.setRight(nil);
        root.setParent(nil);
    }

    public RBNODE getRoot() {
        return root;
    }


    private void leftRotate(RBNODE node){
        RBNODE rightNode = node.getRight();
        node.setRight(rightNode.getLeft());
        if (!isNil(rightNode.getLeft())) {
            rightNode.getLeft().setParent(node);
        }
        rightNode.setParent(node.getParent());
        // node is root
        if (isNil(node.getParent())) {
            root = rightNode;
        } else if (node.getParent().getLeft() == node) {
            //node is left child
            node.getParent().setLeft(rightNode);
        } else {
            //node is rightNode child
            node.getParent().setRight(rightNode);
        }
        rightNode.setLeft(node);
        node.setParent(rightNode);
    }


    private void rightRotate(RBNODE node){
        RBNODE leftNode = node.getLeft();
        node.setLeft(leftNode.getRight());
        if (!isNil(leftNode.getRight()))
            leftNode.getRight().setParent(node);
        leftNode.setParent(node.getParent());
        // node is root
        if (isNil(node.getParent())) {
            root = leftNode;
        } else if (node.getParent().getRight() == node) {
            //node is right child
            node.getParent().setRight(leftNode);
        } else {
            //node is left child
            node.getParent().setLeft(leftNode);
        }
        leftNode.setRight(node);
        node.setParent(leftNode);

    }




    public void insert(String key) {
        insert(new RBNODE(nil,key,RED));
    }

    private void insert(RBNODE inserted) {
        RBNODE node1 = nil;
        RBNODE node2 = root;
        //goin to right leaf
        while (!isNil(node2)){
            node1 = node2;
            if (inserted.getKey().compareTo(node2.getKey()) < 0){
                node2 = node2.getLeft();
            } else {
                node2 = node2.getRight();
            }
        }
        inserted.setParent(node1);
        if (isNil(node1)) {
            // root is nil
            root = inserted;
        } else if (inserted.getKey().compareTo(node1.getKey()) < 0) {
            //inserted is left child
            node1.setLeft(inserted);
        } else {
            //inserted is right child
            node1.setRight(inserted);
        }
        inserted.setLeft(nil);
        inserted.setRight(nil);
        insertFixUp(inserted);
    }


    private void insertFixUp(RBNODE node){

        RBNODE uncle;
        while (node.getParent().getColor() == RED){
            // node parent is left child of node grandparent
            if (node.getParent() == node.getParent().getParent().getLeft()){
                uncle = node.getParent().getParent().getRight();
                if (uncle.getColor() == RED){
                    node.getParent().setColor(BLACK);
                    uncle.setColor(BLACK);
                    node.getParent().getParent().setColor(RED);
                    node = node.getParent().getParent();
                }
                // Case 2: if uncle is black & node is a right child
                else if (node == node.getParent().getRight()){

                    // leftRotaet around node's parent
                    node = node.getParent();
                    leftRotate(node);
                }

                // Case 3: else uncle is black & node is a left child
                else{
                    // recolor and rotate round node's grandpa
                    node.getParent().color = BLACK;
                    node.getParent().getParent().color = RED;
                    rightRotate(node.getParent().getParent());
                }
            } else{
                //node is right child of its parent
                // Initialize uncle to node's uncle
                uncle = node.getParent().getParent().getLeft();

                // Case 1: if uncle is red...recolor
                if (uncle.getColor() == RED){
                    node.getParent().color = BLACK;
                    uncle.color = BLACK;
                    node.getParent().getParent().color = RED;
                    node = node.getParent().getParent();
                }

                // Case 2: if uncle is black and node is a left child
                else if (node == node.getParent().getLeft()){
                    // rightRotate around node's parent
                    node = node.getParent();
                    rightRotate(node);
                }
                // Case 3: if uncle  is black and node is a right child
                else{
                    // recolor and rotate around node's grandpa
                    node.getParent().color = BLACK;
                    node.getParent().getParent().color = RED;
                    leftRotate(node.getParent().getParent());
                }
            }
        }
        // Color root black at all times
        root.color = BLACK;

    }// end insertFixUp(RBNODE node)

    // @param: node, a RBNODE
    // @param: node, the node with the smallest key rooted at node
    public RBNODE minValue(RBNODE node){

        // while there is a smaller key, keep going left
        while (!isNil(node.getLeft()))
            node = node.getLeft();
        return node;
    }// end minValue(RBNODE node)


    public String getSuccessor(String k){
        RBNODE node = search(k);
        if(node == null){
            return "";
        } else {
            return getSuccessor(node).getKey();
        }
    }



    private RBNODE getSuccessor(RBNODE node){
        if(node.getRight()== nil || node.getRight() == null){
            if(node.getRight() != nil && node.getRight() != null){
                return minValue(node.getRight());
            } else {
                RBNODE parent = node.getParent();
                while (parent != null && node == parent.getRight()){
                    node = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }
        return node;
    }


    // @param: z, the RBNODE which is to be removed from the the tree
    // Remove's z from the RedBlackTree rooted at root
    public void remove(RBNODE v){

        RBNODE z = search(v.key);

        // Declare variables
        RBNODE x = nil;
        RBNODE y = nil;

        // if either one of z's children is nil, then we must remove z
        if (isNil(z.getLeft()) || isNil(z.getRight()))
            y = z;

            // else we must remove the successor of z
        else y = getSuccessor(z);

        // Let x be the left or right child of y (y can only have one child)
        if (!isNil(y.getLeft()))
            x = y.getLeft();
        else
            x = y.getRight();

        // link x's parent to y's parent
        x.parent = y.getParent();

        // If y's parent is nil, then x is the root
        if (isNil(y.getParent()))
            root = x;

            // else if y is a left child, set x to be y's left sibling
        else if (!isNil(y.getParent().getLeft()) && y.getParent().getLeft() == y)
            y.getParent().left = x;

            // else if y is a right child, set x to be y's right sibling
        else if (!isNil(y.getParent().getRight()) && y.getParent().getRight() == y)
            y.getParent().right = x;

        // if y != z, trasfer y's satellite data into z.
        if (y != z){
            z.key = y.key;
        }

        // If y's color is black, it is a violation of the
        // RedBlackTree properties so call removeFixup()
        if (y.getColor() == BLACK)
            removeFixup(x);
    }// end remove(RBNODE z)


    // @param: node, the child of the deleted node from remove(RBNODE v)
    // Restores the Red Black properties that may have been violated during
    // the removal of a node in remove(RBNODE v)
    private void removeFixup(RBNODE node){

        RBNODE temporary;

        // While we haven't fixed the tree completely...
        while (node != root && node.getColor() == BLACK){

            // if node is it's parent's left child
            if (node == node.getParent().getLeft()){

                // set temporary = node's sibling
                temporary = node.getParent().getRight();

                // Case 1, temporary's color is red.
                if (temporary.getColor() == RED){
                    temporary.color = BLACK;
                    node.getParent().color = RED;
                    leftRotate(node.getParent());
                    temporary = node.getParent().getRight();
                }

                // Case 2, both of temporary's children are black
                if (temporary.getLeft().getColor() == BLACK &&
                        temporary.getRight().getColor() == BLACK){
                    temporary.color = RED;
                    node = node.getParent();
                }
                // Case 3 / Case 4
                else{
                    // Case 3, temporary's right child is black
                    if (temporary.getRight().getColor() == BLACK){
                        temporary.getLeft().color = BLACK;
                        temporary.color = RED;
                        rightRotate(temporary);
                        temporary = node.getParent().getRight();
                    }
                    // Case 4, temporary = black, temporary.right = red
                    temporary.color = node.getParent().getColor();
                    node.getParent().color = BLACK;
                    temporary.getRight().color = BLACK;
                    leftRotate(node.getParent());
                    node = root;
                }
            }
            // if node is it's parent's right child
            else{

                // set temporary to node's sibling
                temporary = node.getParent().getLeft();

                // Case 1, temporary's color is red
                if (temporary.getColor() == RED){
                    temporary.color = BLACK;
                    node.getParent().color = RED;
                    rightRotate(node.getParent());
                    temporary = node.getParent().getLeft();
                }

                // Case 2, both of temporary's children are black
                if (temporary.getRight().getColor() == BLACK &&
                        temporary.getLeft().getColor() == BLACK){
                    temporary.color = RED;
                    node = node.getParent();
                }

                // Case 3 / Case 4
                else{
                    // Case 3, temporary's left child is black
                    if (temporary.getLeft().getColor() == BLACK){
                        temporary.getRight().color = BLACK;
                        temporary.color = RED;
                        leftRotate(temporary);
                        temporary = node.getParent().getLeft();
                    }

                    // Case 4, temporary = black, and temporary.getLeft() = red
                    temporary.color = node.getParent().getColor();
                    node.getParent().color = BLACK;
                    temporary.getLeft().color = BLACK;
                    rightRotate(node.getParent());
                    node = root;
                }
            }
        }// end while

        // set node to black to ensure there is no violation of
        // RedBlack tree Properties
        node.color = BLACK;
    }// end removeFixup(RBNODE node)


    public void delete(String key){
        RBNODE node = search(key);
        if(node != nil) {
            remove(node);
        }
    }

    // @param: key, the key whose node we want to search for
    // @return: returns a node with the key, key, if not found, returns nil
    // Searches for a node with key k and returns the first such node, if no
    // such node is found returns nil
    public RBNODE search(String key){

        // Initialize a pointer to the root to traverse the tree
        RBNODE current = root;

        // While we haven't reached the end of the tree
        while (!isNil(current)){

            // If we have found a node with a key equal to key
            if (current.key.equals(key))

                // return that node and exit search(int)
                return current;

                // go left or right based on value of current and key
            else if (current.key.compareTo(key) < 0)
                current = current.getRight();

                // go left or right based on value of current and key
            else
                current = current.getLeft();
        }

        // we have not found a node whose key is "key"
        return nil;


    }// end search(int key)

    public int find(String key){
        RBNODE node = search(key);
        if(node != nil){
            return 1;
        } else {
            return 0;
        }
    }

    public String inOrder(RBNODE node){
        if (node == nil){
            return "";
        }
        String returned =  inOrder(node.getLeft()) + node.getKey() + " ";
        returned += inOrder(node.getRight());
        return returned;
    }

    public String inOrderSplit(RBNODE node){
        if (node == nil){
            return "";
        }
        String returned =  inOrderSplit(node.getLeft()) + node.getKey() + ";";
        returned += inOrderSplit(node.getRight());
        return returned;
    }
    public String inOrderSplit(){
        return inOrderSplit(root);
    }


    public String maxValue(RBNODE root){
        if(root == nil){
            return "";
        } else {
            return maxValueNode(root).getKey();
        }
    }
    private RBNODE maxValueNode(RBNODE node){
        while (node.getRight() != nil){
            node = node.getRight();
        }
        return node;
    }

    private boolean isNil(RBNODE node){

        // return appropriate value
        return node == nil;

    }// end isNil(RBNODE node)

}// end class RedBlackTree


