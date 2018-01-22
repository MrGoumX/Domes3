import java.io.PrintStream;

public interface STInt {

    public void insertWarehouse(int nodeid, String name);

    public void insertBookAtWarehouse(int nodeid, int ISBN, int copies);

    public void removeWarehouse(int nodeid);

    public void removeBook(int nodeid, int ISBN);

    public void searchByWarehouse(int nodeid);

    public void searchBookInWarehouse(int nodeid, int ISBN);

    public void searchBook(int ISBN);

    public void printTree(PrintStream stream);
}
