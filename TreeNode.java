public class TreeNode {
    private int id;
    private String city;
    private TreeNode l,r;
    private int N;
    private List booklist = new List();

    TreeNode(int id, String city){
        this.id = id;
        this.city = city;
    }

    public void raiseTreeSize(){
        N++;
    }

    public void reduceTreeSize(){
        N--;
    }

    public TreeNode getRight(){
        return r;
    }

    public TreeNode getLeft(){
        return l;
    }

    public int getTreeSize(){
        return N;
    }

    public String getCity(){
        return city;
    }

    public List getList(){
        return booklist;
    }

    public int getID(){
        return id;
    }

    public void setLeft(TreeNode l){
        this.l = l;
    }

    public void setRight(TreeNode r){
        this.r = r;
    }

    public void printBooks(){
        booklist.printList(System.out);
    }

    public void insertBook(int ISBN, int copies){
        ListNode temp = booklist.search(ISBN);
        if(temp!=null){
            temp.getElement().updateCopies(copies);
        }
        else{
            BookInfo toIns = new BookInfo(ISBN, copies);
            booklist.put(toIns);
        }
    }

    public boolean removeBook(int ISBN){
        ListNode temp = booklist.search(ISBN);
        if(temp!=null){
            if(temp.getElement().getCopies()!=0){
                temp.getElement().reduceCopies();
                if(temp.getElement().getCopies()==0){
                    booklist.remove(temp.getElement());
                }
                return true;
            }
        }
        return false;
    }

    public void searchBook(int isbn) {
        ListNode temp = booklist.search(isbn);
        if(temp!=null) {
            System.out.println("Book with ISBN " + temp.getElement().getISBN() + " has " + temp.getElement().getCopies() + " copies.");
        }
        else System.out.println("There is no such book in the warehouse");
    }

    public int getBookCopies(int isbn) {
        ListNode temp = booklist.search(isbn);
        int copies = 0;
        if(temp!=null){
            copies = temp.getElement().getCopies();
        }
        return copies;
    }
}
