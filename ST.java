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
        //If isbn exists, add the copies otherwise insert isbn to list.
      }

      boolean removeBook(int isbn) {
        //If isbn exists, remove just one copy.
        //If copies reach 0, remove isbn from list.
        //If isbn doesn't exist, return false
      }

      void searchBook(int isbn) {
        //If isbn exists, print isbn along with num of copies
        //If isbn doesn't exist, print error msg
      }

      int getBookCopies(int isbn) {
        //If isbn exists, return num of copies;
        //If isbn doesn't exist, return 0;
      }
    }

    protected TreeNode head;

    public int getNumberOfWarehouses() {
        return head.getTreeSize();
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
        System.out.println("Warehouse ID Collision Found, aborting warehouse insertion.");
      } else {
        head = insertRandomly(head, nodeid, name);
      }
    }

    public void insertBookAtWarehouse(int nodeid, int isbn, int copies) {
      TreeNode targetWarehouse = searchAndReturnWarehouse(nodeid);
      if (targetWarehouse == null) {
        System.out.println("Warehouse ID not found, aborting book insertion.");
      } else {
        targetWarehouse.insertBook(isbn, copies);
      }
    }

    // public void removeWarehouse(int nodeid) {
    //   TreeNode toRemove = searchAndReturnWarehouse(nodeid);
    //   if (targetWarehouse == null) {
    //     System.out.println("Warehouse ID not found, aborting book insertion.");
    //   } else if (targetWarehouse.getLeft() != null && targetWarehouse.getRight() != null) {
    //
    //   } else {
    //     toRemove = toRemove.getRight() == null?toRemove.getLeft():toRemove.getRight();
    //   }
    // }

    public void removeBook(int nodeid, int isbn) {
      TreeNode targetWarehouse = searchAndReturnWarehouse(nodeid);
      if (targetWarehouse == null) {
        System.out.println("Warehouse ID not found, aborting book removal.");
      } else {
        if (!targetWarehouse.removeBook(isbn)) {
          System.out.println("ISBN not found in Warehouse, aborting book removal.");
        }
      }
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

    public void searchBookInWarehouse(int nodeid, int isbn) {
      TreeNode warehouseToCheck = searchAndReturnWarehouse(nodeid);
      if (warehouseToCheck == null) {
        System.out.println("Warehouse ID not found, aborting book search.");
      } else {
        warehouseToCheck.searchBook(isbn);
      }
    }

    public void searchBook(int isbn) {
      searchBook(head, isbn);
    }

    public void searchBook(TreeNode currentNode, int isbn) {
      int currentCopies = currentNode.getBookCopies(isbn);
      if (currentCopies > 0) {
        System.out.println("Warehouse ID: " + currentNode.getID() + " City: " + currentNode.getCity() + " Copies: " + currentCopies);
      }
      if (currentNode.getLeft() != null) {
        searchBook(currentNode.getLeft(), isbn);
      } else if (currentNode.getRight() != null) {
        searchBook(currentNode.getRight(), isbn);
      }
    }
}
