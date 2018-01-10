public class ListNode<T> {
    private T element;
    protected QueueNode<T> next;

    //Constructors
    public QueueNode(T element) {
        this(element, null);
    }

    public QueueNode(T element, QueueNode<T> next) {
        this.element = element;
        this.next = next;
    }

    //Returns the element of the the QueueNode
    T getElement() {
        return element;
    }

    //Returns the next QueueNode
    QueueNode<T> getNext() {
        return next;
    }
}
