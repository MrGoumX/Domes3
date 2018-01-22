import java.io.PrintStream;
import java.util.NoSuchElementException;

/**
 * Defines the methods for a List that handles BookInfo entries
 */
public interface ListInt {

    /**
     * @return true if the List is empty
     */
    public boolean isEmpty();

    /**
     * insert a BookInfo entry to the List
     */
    public void put(BookInfo item);

    /**
     * remove and return the item of the List
     * @return item of the List
     * @throws NoSuchElementException if the List is empty
     */
    public void remove(BookInfo item) throws NoSuchElementException;

    /**
     * Searches through the list to find a specific item
     * @param item
     * @return The specific node if found, else returns null
     * @throws NoSuchElementException if the list is empty
     */
    public ListNode search(int item) throws NoSuchElementException;

    /**
     * print the elements of the queue, starting from the oldest
     * item, to the print stream given as argument. For example, to
     * print the elements to the
     * standard output, pass System.out as parameter. E.g.,
     * printQueue(System.out);
     */
    public void printList(PrintStream stream);

    /**
     * return the size of the queue, 0 if it is empty
     * @return number of elements in the queue
     */
    public int size();
}
