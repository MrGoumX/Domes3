import java.io.PrintStream;
import java.util.NoSuchElementException;

public class List implements ListInt{
    private ListNode first, last;
    private int size;

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public void put(BookInfo item) {
        ListNode temp = new ListNode(item);
        if(isEmpty()){
            first = last = temp;
            size++;
        }
        else{
            if(temp.getElement().getISBN() < first.getElement().getISBN()){
                temp.next = first;
                first = temp;
                size++;
            }
            else{
                ListNode p = null;
                ListNode c = first;
                while(c!=null){
                    if(c.getElement().getISBN() == temp.getElement().getISBN()){
                        System.out.println("No duplicates allowed");
                        return;
                    }
                    if(c.getElement().getISBN() < temp.getElement().getISBN()){
                        p = c;
                        c = c.next;
                    }
                    break;
                }
                p.next = temp;
                temp.next = c;
                c = temp;
                size++;
            }
        }
    }

    @Override
    public void remove(BookInfo item) throws NoSuchElementException {
        if(isEmpty()) throw new NoSuchElementException();
        if(first.getElement().getISBN() == item.getISBN()){
            first = first.next;
            size--;
        }
        else{
            ListNode p = null;
            ListNode c = first;
            while(c!=null){
                if(c.getElement().getISBN() == item.getISBN()){
                    p.next = c.next;
                    size--;
                }
                p = c;
                c = c.next;
            }
        }
    }

    @Override
    public ListNode search(int item) throws NoSuchElementException {
        ListNode temp = first;
        while (temp != null){
            if(temp.getElement().getISBN() == item){
                return temp;
            }
            temp = temp.getNext();
        }
        return null;
    }

    @Override
    public void printList(PrintStream stream) {
        if(isEmpty()){
            System.out.println("The List is empty");
        }
        ListNode temp = first;
        while(temp!=null){
            System.out.println("Book with ISBN " + temp.getElement().getISBN() + " has " + temp.getElement().getCopies() + " copies.");
            temp = temp.getNext();
        }
    }

    @Override
    public int size() {
        return size;
    }
}
