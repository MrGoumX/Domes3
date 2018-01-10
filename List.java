import java.io.PrintStream;
import java.util.NoSuchElementException;

public class List<T> implements ListInt<T> {
    private QueueNode<T> first, last;
    private int size;

    //Checks if queue is empty
    @Override
    public boolean isEmpty() {
        return first == null;
    }

    //Adds item in the back of the queue
    @Override
    public void put(T item) {
        QueueNode<T> temp = new QueueNode<T>(item);
        if (isEmpty()) {
            first = last = temp;
            size++;
        } else {
            last.next = temp;
            last = temp;
            size++;
        }
    }

    //Removes item from the front of the queue
    @Override
    public T get() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException();
        T removed = first.getElement();
        if (first == last) {
            first = last = null;
        } else {
            first = first.next;
        }
        size--;
        return removed;
    }

    //Returns the first item of the queue without removing it
    @Override
    public T peek() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException();
        return first.getElement();
    }

    //Prints the queue
    @Override
    public void printQueue(PrintStream stream) {
        if (isEmpty()) {
            System.out.printf("The Queue is empty");
            return;
        }
        QueueNode<T> temp = first;
        while (temp != null) {
            System.out.printf("%s ", temp.getElement());
            temp = temp.getNext();
        }
        System.out.printf(System.lineSeparator());
    }

    //Returns the size of the queue
    @Override
    public int size() {
        return size;
    }
}
