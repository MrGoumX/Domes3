import java.time.chrono.IsoChronology;

public class BookInfo {
    private int copies = 0;
    private int ISBN;

    BookInfo(int ISBN,int copies){
        this.ISBN = ISBN;
        this.copies = copies;
    }

    public void setISBN(int ISBN){
        this.ISBN = ISBN;
    }

    public void updateCopies(int copies){
        this.copies += copies;
    }

    public void reduceCopies(){
        this.copies--;
    }

    public int getISBN(){
        return ISBN;
    }

    public int getCopies(){
        return copies;
    }
}
