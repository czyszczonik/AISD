package Lista4;


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
        System.err.println("leftRotate");
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
        System.err.println("rightRotate");

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
        System.err.println("Insert: "+key);
        insert(new RBNODE(nil,key,Color.RED));
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
        System.err.println("InsertFixUp");
        RBNODE uncle;
        while (node.getParent().getColor() == Color.RED){
            if (node.getParent() == node.getParent().getParent().getLeft()){
                // parent is left child of grandparent
                uncle = node.getParent().getParent().getRight();
                if (uncle.getColor() == Color.RED){
                    node.getParent().setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    node.getParent().getParent().setColor(Color.RED);
                    node = node.getParent().getParent();
                } else if (node == node.getParent().getRight()){
                    //uncle is BLACK and node is a right child
                    node = node.getParent();
                    leftRotate(node);
                } else{
                    // uncle is BLACK and node is a left child
                    // recolor and rotate right grandpa
                    node.getParent().setColor(Color.BLACK);
                    node.getParent().getParent().setColor(Color.RED);
                    rightRotate(node.getParent().getParent());
                }
            } else{
                // parent is right child of grandparent
                uncle = node.getParent().getParent().getLeft();
                if (uncle.getColor() == Color.RED){
                    node.getParent().setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    node.getParent().getParent().setColor(Color.RED);
                    node = node.getParent().getParent();
                } else if (node == node.getParent().getLeft()){
                    //uncle is BLACK and node is a left child
                    node = node.getParent();
                    rightRotate(node);
                } else{
                    // uncle is BLACK and node is a left child
                    // recolor and rotate right grandpa
                    node.getParent().setColor(Color.BLACK);
                    node.getParent().getParent().setColor(Color.RED);
                    leftRotate(node.getParent().getParent());
                }
            }
        }
        root.setColor(Color.BLACK);
    }

    public RBNODE minValue(RBNODE node){
        System.err.println("MinValue");
        while (!isNil(node.getLeft()))
            node = node.getLeft();
        return node;
    }

    public String getSuccessor(String key){
        RBNODE node = findNode(key);
        if(node == null){
            return "";
        } else {
            return getSuccessor(node).getKey();
        }
    }

    private RBNODE getSuccessor(RBNODE node){
        System.err.println("Successor");
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

    public void remove(RBNODE node){
        System.err.println("Remove");
        RBNODE toRemove = findNode(node.getKey());
        RBNODE temporary, child = nil;
        //toRemove is leaf or have one child
        if (isNil(toRemove.getLeft()) || isNil(toRemove.getRight())) {
            temporary = toRemove;
        } else {
            //node have both children
            temporary = getSuccessor(toRemove);
        }
        if (!isNil(temporary.getLeft())) {
            child = temporary.getLeft();
        } else {
            child = temporary.getRight();
        }
        // link child's parent to temporary's parent
        child.setParent(temporary.getParent());

        // Children is ROOT
        if (isNil(temporary.getParent())) {
            root = child;
        } else if (!isNil(temporary.getParent().getLeft()) && temporary.getParent().getLeft() == temporary) {
            temporary.getParent().setLeft(child);
        } else if (!isNil(temporary.getParent().getRight()) && temporary.getParent().getRight() == temporary) {
            temporary.getParent().setRight(child);
        }
        if (temporary != toRemove){
            // trasfer temporary's data.
            toRemove.setKey(temporary.getKey());
        }
        if (temporary.getColor() == Color.BLACK) {
            removeFixUp(child);
        }
    }

    private void removeFixUp(RBNODE node){
        System.err.println("Remove Fix Up");

        RBNODE sibling;
        while (node != root && node.getColor() == Color.BLACK){
            if (node == node.getParent().getLeft()){
                // node is left child
                sibling = node.getParent().getRight();
                // Sibling is RED.
                if (sibling.getColor() == Color.RED){
                    sibling.setColor(Color.BLACK);
                    node.getParent().setColor(Color.RED);
                    leftRotate(node.getParent());
                    sibling = node.getParent().getRight();
                }
                if (sibling.getLeft().getColor() == Color.BLACK &&
                        sibling.getRight().getColor() == Color.BLACK){
                    // Both of sibling's children are BLACK
                    sibling.setColor(Color.RED);
                    node = node.getParent();
                } else{
                    if (sibling.getRight().getColor() == Color.BLACK){
                        // Sibling's right child is BLACK
                        sibling.getLeft().setColor(Color.BLACK);
                        sibling.setColor(Color.RED);
                        rightRotate(sibling);
                        sibling = node.getParent().getRight();
                    }
                    // Sibling is BLACK and his right child is RED
                    sibling.setColor(node.getParent().getColor());
                    node.getParent().setColor(Color.BLACK);
                    sibling.getRight().setColor(Color.BLACK);
                    leftRotate(node.getParent());
                    node = root;
                }
            } else{
                // node is right child
                sibling = node.getParent().getLeft();
                // Sibling's is RED
                if (sibling.getColor() == Color.RED){
                    sibling.setColor(Color.BLACK);
                    node.getParent().setColor(Color.RED);
                    rightRotate(node.getParent());
                    sibling = node.getParent().getLeft();
                }

                // Both of sibling's children are BLACK
                if (sibling.getRight().getColor() == Color.BLACK &&
                        sibling.getLeft().getColor() == Color.BLACK){
                    sibling.setColor(Color.RED);
                    node = node.getParent();
                } else{
                    if (sibling.getLeft().getColor() == Color.BLACK){
                        // Sibling's left child is BLACK
                        sibling.getRight().setColor(Color.BLACK);
                        sibling.setColor(Color.RED);
                        leftRotate(sibling);
                        sibling = node.getParent().getLeft();
                    }
                    // Sibling is BLACK and his left child is RED
                    sibling.setColor(node.getParent().getColor());
                    node.getParent().setColor(Color.BLACK);
                    sibling.getLeft().setColor(Color.BLACK);
                    rightRotate(node.getParent());
                    node = root;
                }
            }
        }
        node.setColor(Color.BLACK);
    }

    public void delete(String key)  {
        RBNODE node = findNode(key);
        if(node != nil) {
            remove(node);
        }
    }

    public RBNODE findNode(String key){
        System.err.println("Finding Node");
        RBNODE current = root;
        while (!isNil(current)) {
            if (current.getKey().equals(key)) {
                return current;
            } else if (current.getKey().compareTo(key) < 0) {
                current = current.getRight();
            } else {
                current = current.getLeft();
            }
        }
        return nil;
    }

    public int find(String key){
        System.err.println("Finding Node");
        RBNODE node = findNode(key);
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
        return node == nil;
    }
}


