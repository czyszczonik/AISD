package Lista4;

public class BST {
    private Node root;
    public BST(){
        root =  null;
    }

    public void insert(String key){
        if(root == null){
            root = new Node(key,null);
        } else {
            insert(key,root);
        }
    }

    public void delete(String key){
        Node node = findNode(key);
        //Node exists.
        if(node == null){
            return;
        }
        //Node are leaf.
        if(node.getRightNode() ==  null && node.getLeftNode() == null){
            node = node.getParent();
            if(node.getLeftNode().getKey().equals(key)){
                node.setLeftNode(null);
                return;
            } else {
                node.setRightNode(null);
                return;
            }
        }
        //Node have only one child right or left.
        if((node.getLeftNode() == null && node.getRightNode() != null)){
            Node parent = node.getParent();
            parent.setRightNode(node.getRightNode());
            node.getRightNode().setParent(parent);
            return;
        } else if((node.getLeftNode() != null && node.getRightNode() == null)){
            Node parent = node.getParent();
            parent.setLeftNode(node.getRightNode());
            node.getLeftNode().setParent(parent);
            return;
        }
        //Node have two children
        Node successorNode = getSuccessorNode(node);
        node.setKey(successorNode.getKey());
        successorNode.getParent().setLeftNode(null);
    }
    private void insert(String key, Node node){
        if(compare(key,node.getKey())){
            if(node.getRightNode() == null){
                node.setRightNode(new Node(key,node));
            } else {
                insert(key,node.getRightNode());
            }
        } else {
            if(node.getLeftNode() == null){
                node.setLeftNode(new Node(key,node));
            } else {
                insert(key,node.getLeftNode());
            }
        }
    }

    public int find(String key){
        if(root == null){
            return 0;
        } else {
            return find(key, root);
        }
    }


    private int find(String key, Node node){
        if(key.equals(node.getKey())){
            return 1;
        }
        if(compare(key,node.getKey())){
            if(node.getRightNode() == null){
                return 0;
            } else {
                return find(key,node.getRightNode());
            }
        } else {
            if(node.getLeftNode() == null){
                return 0;
            } else {
                return find(key,node.getLeftNode());
            }
        }
    }

    public String inOrder(Node node){
        if (node == null){
            return "";
        }
        String returned =  inOrder(node.getLeftNode()) + node.getKey() + " ";
        returned += inOrder(node.getRightNode());
        return returned;
    }

    public String minValue(Node root){
        if(root == null || root.getKey()==null){
            return "";
        } else {
            return minValueNode(root).getKey();
        }
    }
    private Node minValueNode(Node node){
        while (node.getLeftNode() != null){
            node = node.getLeftNode();
        }
        return node;
    }

    public String maxValue(Node root){
        if(root == null || root.getKey()==null){
            return "";
        } else {
            return maxValueNode(root).getKey();
        }
    }
    private Node maxValueNode(Node node){
        while (node.getRightNode() != null){
            node = node.getRightNode();
        }
        return node;
    }

    public String getSuccessor(String k){
        Node node = findNode(k);
        if(node == null){
            return "";
        } else {
            return getSuccessorNode(node).getKey();
        }
    }
    private Node getSuccessorNode(Node node){
        if(node.getRightNode() != null){
            if(node.getRightNode() != null){
                return minValueNode(node.getRightNode());
            } else {
                Node parent = node.getParent();
                while (parent != null && node == parent.getRightNode()){
                    node = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }
        return node;
    }
    private Node findNode(String key){
        if(root == null){
            return null;
        } else {
            return findNode(key,root);
        }
    }
    private Node findNode(String key, Node node){
        if(key.equals(node.getKey())){
            return node;
        }
        if(compare(key,node.getKey())){
            if(node.getRightNode() == null){
                return null;
            } else {
                return findNode(key,node.getRightNode());
            }
        } else {
            if(node.getLeftNode() == null){
                return null;
            } else {
                return findNode(key,node.getLeftNode());
            }
        }
    }

    /**
     * Function compare two strings in lexicographical order.
     * When String 1 is greater than String 2 function returns true,
     * else function returns false.
     * @param String1 String
     * @param String2 String
     * @return Boolean
     */
    private boolean compare(String String1, String String2){
        return String1.compareTo(String2) > 0;
    }


    public Node getRoot(){
        return root;
    }


}
