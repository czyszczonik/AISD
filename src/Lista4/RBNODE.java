package Lista4;

class RBNODE {

    public RBNODE left, right, parent;
    public String key;
    public Color color;

    public RBNODE(){}
    public RBNODE(String key){
        this.key = key;
    }

    public RBNODE(RBNODE parent, String key, Color color) {
        this.right = this.left = null;
        this.parent = parent;
        this.key = key;
        this.color = color;
    }

    public RBNODE getLeft() {
        return left;
    }

    public void setLeft(RBNODE left) {
        this.left = left;
    }

    public RBNODE getRight() {
        return right;
    }

    public void setRight(RBNODE right) {
        this.right = right;
    }

    public RBNODE getParent() {
        return parent;
    }

    public void setParent(RBNODE parent) {
        this.parent = parent;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
