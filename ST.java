public class ST{
    private class TreeNode {
      int id;
      String city;
      TreeNode l,p,r;
      int N;
      List booklist;
      double priority;

      TreeNode(int id, String city) {
        this.id = id;
        this.city = city;
        this.priority = Math.floor(1 + Math.random()*100);
      }

      TreeNode(int id, String city, TreeNode p) {
        this.id = id;
        this.city = city;
        this.p = p;
        this.priority = Math.floor(1 + Math.random()*100);
      }

      void raiseTreeSize() {
        N++;
      }

      TreeNode getRight() {
        return r;
      }

      TreeNode getParent() {
        return p;
      }

      TreeNode getLeft() {
        return l;
      }

      void getTreeSize() {
        return N;
      }

      String getCity() {
        return city;
      }

      List getList(){
        return booklist;
      }

      int getID() {
        return id;
      }

      void setLeft(TreeNode l) {
        this.l = l;
      }

      void setRight(TreeNode r) {
        this.r = r;
      }

      void printBooks() {
        if (booklist.size()!=0) {
          for(int i = 0; i < booklist.size(); i++) {
            System.out.println("Book with id " + booklist.get(i).getISBN() + " has " + booklist.get(i).getCopies() + " copies left in the warehouse");
          }
        }
      }

      void insertBook(int isbn, int copies) {

      }
    }

    protected TreeNode head;

    public ST() {
        this(new DefaultComparator());
    }

    public int size() {
        return head.getTreeSize();
    }

    public void searchByWarehouse(int nodeid) {
      TreeNode fin = head;
      while (fin != null) {
        if (nodeid == fin.getID()) {
          fin.printBooks();
          return;
        }
        fin = nodeid < fin.getID() ? fin.getLeft() : fin.getRight();
      }
      System.out.println("Error 404 Warehouse not found.");
    }

    private TreeNode searchAndReturnWarehouse(int nodeid) {
      TreeNode fin = head;
      while (fin != null) {
        if (nodeid == fin.getID()) {
          return fin;
        }
        fin = nodeid < fin.getID() ? fin.getLeft() : fin.getRight();
      }
      return null;
    }

    private TreeNode insertToRefPoint(TreeNode currentRefPoint, int nodeid, String name) {
      if (currentRefPoint == null) return new TreeNode(nodeid, name);
      if (nodeid < currentRefPoint.getID()) {
        currentRefPoint.left = insertToRefPoint(currentRefPoint.left, nodeid, name);
        currentRefPoint = rotateRight(currentRefPoint);
      } else {
        currentRefPoint.right = insertToRefPoint(currentRefPoint.right, nodeid, name);
        currentRefPoint = rotateLeft(currentRefPoint);
      }
      return currentRefPoint;
    }

    public TreeNode insertRandomly(TreeNode currentRefPoint, int nodeid, String name) {
      if (currentRefPoint == null) return new TreeNode(nodeid, name);
      if (Math.random()*(currentRefPoint.getTreeSize()+1) < 1) return insertToRefPoint(currentRefPoint, nodeid, name);
      if (nodeid < currentRefPoint.getID()) currentRefPoint.left = insertRandomly(currentRefPoint.left, nodeid, name);
      else currentRefPoint.right = insertRandomly(currentRefPoint.right, nodeid, name);
      currentRefPoint.raiseTreeSize();
      return currentRefPoint;
    }

    private TreeNode rotateLeft(TreeNode referencePoint) {
      TreeNode toRise = referencePoint.right;
      referencePoint.right = toRise.left;
      toRise.left = referencePoint;
      return toRise;
    }

    private TreeNode rotateRight(TreeNode referencePoint) {
      TreeNode toRise = referencePoint.left;
      referencePoint.left = toRise.right;
      toRise.right = referencePoint;
      return toRise;
    }

    public void insertWarehouse(int nodeid, String name) {
      if (searchAndReturnWarehouse(nodeid) != null) {
        System.out.println("Warehouse ID Collision Found, aborting insertion.");
      } else {
        head = insertRandomly(head, nodeid, name);
      }
    }

    public void insertBookAtWarehouse(int nodeid, int isbn, int copies) {
      TreeNode targetWarehouse = searchAndReturnWarehouse(nodeid);
      if (targetWarehouse == null) {
        System.out.println("Invalid Warehouse ID specified, aborting book insertion.");
      } else {
        targetWarehouse.insertBook(isbn, copies);
      }
    }

    public void removeWarehouse(int nodeid) {

    }

    /**
     * Inserts an element in the tree and returns the new node.
     * @param element The element to insert
     * @return The node that hosts element, or null if element wasn’t found.
     */
    public Node insert(Object element) {
        if (element == null) throw new IllegalArgumentException();
        Node n = root;
        Node p = null;
        int result = 0;
        while (n != null) {
            // Compare element with the element in the current subtree
            result = cmp.compare(element, n.element);
            if (result == 0) return null;
            // Go left or right based on comparison result
            // Keep a reference in the last non
            // node encountered
            p = n;
            n = result < 0 ? n.left : n.right;
        }
        // Create and connect a new node
        Node node = new Node(element);
        node.parent = p;
        // The new node must be a left child of p
        if (result < 0) {
            p.left = node;
        }
        // The new node must be a right child of p
        else if (result > 0) {
            p.right = node;
        }
        // The tree is empty; root must be set
        else {
            root = node;
        }
        ++size;
        return node;
    }
    /*
    * Recursively inserts an element in the tree and returns the new node.
    */
    private Node insertRecursive(Node p, Object element, Node parent){
        if (p == null){
            Node node = new Node(element);
            node.parent = parent;
            ++size;
            return node;
        }
        int result = cmp.compare(element, p.element);
        if (result == 0) return p;
        if (result<0)
            p.left = insertRecursive(p.left, element, p);
        else
            p.right = insertRecursive(p.right, element, p);
        return p;
    }
    public void insertRecursive(Object element){
        root = insertRecursive(root, element, null);
    }
    /*
    * Recursively adds an element in the root of tree by doing rotations.
    */
    private Node rootInsert(Node p, Object element, Node parent){
        if (p == null){
            Node node = new Node(element);
            node.parent = parent;
            ++size;
            return node;
        }
        int result = cmp.compare(element, p.element);
        if (result == 0) return p;
        if (result<0){
            p.left = rootInsert(p.left, element, p);
            p = rotateRight(p);
        } else {
            p.right = rootInsert(p.right, element, p);
            p = rotateLeft(p);
        }
        return p;
    }

    public void rootInsert(Object element){
        root = rootInsert(root, element, null);
    }

    /**
     * Performs a left rotation.
     * @param pivot The node to rotate.
     */
    private Node rotateLeft(Node pivot) {
        Node parent = pivot.parent;
        Node child = pivot.right;
        if (parent == null) {
            root = child;
        } else if (parent.left == pivot) {
            parent.left = child;
        } else {
            parent.right = child;
        }
        child.parent = pivot.parent;
        pivot.parent = child;
        pivot.right = child.left;
        if (child.left != null) child.left.parent = pivot;
        child.left = pivot;
        return child;
    }
    /**
     * Performs a right rotation.
     * @param pivot The node to rotate.
     */
    private Node rotateRight(Node pivot) {
        Node parent = pivot.parent;
        Node child = pivot.left;
        if (parent == null) {
            root = child;
        } else if (parent.left == pivot) {
            parent.left = child;
        } else {
            parent.right = child;
        }
        child.parent = pivot.parent;
        pivot.parent = child;
        pivot.left = child.right;
        if (child.right != null) child.right.parent = pivot;
        child.right = pivot;
        return child;
    }
    /**
     * remove a node from the tree
     */
    public boolean remove(Object element) {
        Node n = find(element);
        if (n == null) return false;
        remove(n);
        return true;
    }
    /**
     * Removes a given node from the tree.
     * @throws NullPointerException if p is null.
     * @param p The node to remove.
     */
    private void remove(Node p) {
        // If p has two children find its successor, then remove it
        if (p.left != null && p.right != null) {
            Node succ = succ(p);
            p.element = succ.element;
            p = succ;
        }
        Node parent = p.parent;
        Node child = p.left != null ? p.left : p.right;
        // The root is being removed
        if (parent == null) {
            root = child;
        }
        // Bypass p
        else if (p == parent.left) {
            parent.left = child;
        } else {
            parent.right = child;
        }
        if (child != null) {
            child.parent = parent;
        }
        // Dispose p
        p.unlink();
        --size;
    }

    /**
     * Finds the inorder successor of a ginen node.
     * @param q The node whose successor to find
     * @throws NullPointerException if q is null
     * @return The successor of q, or null if q is the last node
     */
    private Node succ(Node q) {
        // The successor is the leftmost leaf of q’s right subtree
        if (q.right != null) {
            Node p = q.right;
            while (p.left != null) p = p.left;
            return p;
        }
        // The successor is the nearest ancestor on the right
        else {
            Node p = q.parent;
            Node ch = q;
            while (p != null && ch == p.right) {
                ch = p;
                p = p.parent;
            }
            return p;
        }
    }
    /**
     * Finds the inorder predecessor of a ginen node.
     * @param q The node whose predecessor to find
     * @return The predecessor of q, or null if q is the first node
     */
    private Node pred(Node q) {
        // The successor is the rightmost leaf of q’s left subtree
        if (q.left != null) {
            Node p = q.left;
            while (p.right != null) p = p.right;
            return p;
        }
        // The successor is the nearest ancestor on the right
        else {
            Node p = q.parent;
            Node ch = q;
            while (p != null && ch == p.left) {
                ch = p;
                p = p.parent;
            }
            return p;
        }
    }

    private String toStringR(Node h){
        if (h == null) return "";
        String s = toStringR(h.left);
        s += h.element.toString() + " ";
        s += toStringR(h.right);
        return s;
    }

    public String toString(){
        return toStringR(root);
    }
