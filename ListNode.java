public class ListNode {
    private BookInfo element;
    protected ListNode next;

    public ListNode(BookInfo element){
        this(element, null);
    }

    public ListNode(BookInfo element, ListNode next){
        this.element = element;
        this.next = next;
    }

    public BookInfo getElement(){
        return element;
    }

    public ListNode getNext() {
        return next;
    }
}
