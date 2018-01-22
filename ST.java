import java.io.PrintStream;

public class ST implements STInt{
    private TreeNode head = null;

    ST(){
    }

    private int getNumberOfWarehouses(){
        return head.getTreeSize();
    }

    private TreeNode insertToRefPoint(TreeNode currentRefPoint, int nodeid, String name){
        if(currentRefPoint == null) return new TreeNode(nodeid, name);
        if(nodeid < currentRefPoint.getID()){
            currentRefPoint.setLeft(insertToRefPoint(currentRefPoint.getLeft(), nodeid, name));
            currentRefPoint = rotateRight(currentRefPoint);
        }
        else{
            currentRefPoint.setRight(insertToRefPoint(currentRefPoint.getRight(), nodeid, name));
            currentRefPoint = rotateLeft(currentRefPoint);
        }
        currentRefPoint.raiseTreeSize();
        return currentRefPoint;
    }

    private TreeNode insertRandomly(TreeNode currentRefPoint, int nodeid, String name){
        if(currentRefPoint==null){
            return new TreeNode(nodeid, name);
        }
        if(Math.random()*(currentRefPoint.getTreeSize()+1)<1) return insertToRefPoint(currentRefPoint, nodeid, name);
        if(nodeid < currentRefPoint.getID()){
            currentRefPoint.setLeft(insertRandomly(currentRefPoint.getLeft(),nodeid, name));
        }
        else{
            currentRefPoint.setRight(insertRandomly(currentRefPoint.getRight(), nodeid, name));
        }
        return currentRefPoint;
    }

    private TreeNode rotateLeft(TreeNode referencePoint){
        TreeNode toRise = referencePoint.getRight();
        referencePoint.setRight(toRise.getRight());
        toRise.setLeft(referencePoint);
        return toRise;
    }

    private TreeNode rotateRight(TreeNode referencePoint){
        TreeNode toRise = referencePoint.getLeft();
        referencePoint.setLeft(toRise.getRight());
        toRise.setRight(referencePoint);
        return toRise;
    }

    public void insertWarehouse(int nodeid, String name){
        if(searchAndReturnWarehouse(nodeid)!=null){
            System.out.println("Warehouse ID Collision Found, aborting warehouse insertion.");
        }
        else{
            head = insertRandomly(head, nodeid, name);

        }
    }

    @Override
    public void insertBookAtWarehouse(int nodeid, int ISBN, int copies) {
        TreeNode targetWarehouse = searchAndReturnWarehouse(nodeid);
        if(targetWarehouse==null){
            System.out.println("Warehouse ID not found, aborting book insertion.");
        }
        else{
            targetWarehouse.insertBook(ISBN, copies);
        }
    }

    @Override
    public void removeWarehouse(int nodeid) {
        head = removeWarehouse(head, nodeid);
    }

    public void removeBook(int nodeid, int ISBN){
        TreeNode targetWarehouse = searchAndReturnWarehouse(nodeid);
        if(targetWarehouse==null){
            System.out.println("Warehouse ID not found, aborting book removal.");
        }
        else{
            if(!targetWarehouse.removeBook(ISBN)){
                System.out.println("ISBN not found in Warehouse, aborting book removal.");
            }
        }
    }

    public void searchByWarehouse(int nodeid){
        TreeNode fin = head;
        while(fin!=null){
            if(nodeid == fin.getID()){
                System.out.println("Warehouse " + fin.getID() + " located in " + fin.getCity()+":");
                fin.printBooks();
                return;
            }
            fin = nodeid < fin.getID() ? fin.getLeft() : fin.getRight();
        }
        System.out.println("Error 404 Warehouse not found");
    }

    private TreeNode searchAndReturnWarehouse(int nodeid){
        TreeNode fin = head;
        while(fin!=null){
            if(nodeid == fin.getID()){
                return fin;
            }
            fin = nodeid < fin.getID() ? fin.getLeft() : fin.getRight();
        }
        return null;
    }

    public void searchBookInWarehouse(int nodeid, int ISBN){
        TreeNode warehouseToCheck = searchAndReturnWarehouse(nodeid);
        if(warehouseToCheck==null){
            System.out.println("Warehouse ID not found, aborting book search.");
        }
        else{
            warehouseToCheck.searchBook(ISBN);
        }
    }

    public void searchBook(int ISBN){
        searchBook(head, ISBN);
    }

    private void searchBook(TreeNode currentNode, int ISBN){
        int currentCopies = currentNode.getBookCopies(ISBN);
        if(currentCopies > 0){
            System.out.println("Warehouse ID: " + currentNode.getID() + " City: " + currentNode.getCity() + ", Copies: " + currentCopies);
        }
        if(currentNode.getLeft() != null){
            searchBook(currentNode.getLeft(), ISBN);
        }
        if(currentNode.getRight() != null){
            searchBook(currentNode.getRight(), ISBN);
        }
    }

    private void printIndex(TreeNode head){
        if(head==null) return;
        printIndex(head.getLeft());
        System.out.println("Warehouse with ID " + head.getID() + " located in " + head.getCity());
        printIndex(head.getRight());
    }

    public void printTree(PrintStream stream){
        printIndex(head);
    }

    private TreeNode removeWarehouse(TreeNode head, int nodeid){
        if(head==null) return null;
        if(nodeid < head.getID()){
            head.setLeft(removeWarehouse(head.getLeft(), nodeid));
        }
        else if(nodeid > head.getID()){
            head.setRight(removeWarehouse(head.getRight(), nodeid));
        }
        else{
            head = joinLR(head.getLeft(), head.getRight());
            if(head!=null){
                head.reduceTreeSize();
            }
        }
        return head;
    }

    private TreeNode joinLR(TreeNode left, TreeNode right){
        if(left==null)return right;
        if(right==null)return left;
        int N = left.getTreeSize() + right.getTreeSize();
        if(Math.random()*N<1.0*left.getTreeSize()){
            left.setRight(joinLR(left.getRight(), right));
            return left;
        }
        else{
            right.setLeft(joinLR(left, right.getLeft()));
            return right;
        }
    }
}
