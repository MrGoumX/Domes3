import java.io.PrintStream;
import java.util.Scanner;
public class SystemMenu {
    public static void main(String[] args){
        String name;
        int ans, id, ISBN, copies;
        ST RBST = new ST();
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("Please choose an option:");
            System.out.println("1. Insert a Warehouse");
            System.out.println("2. Insert a Book at a Warehouse");
            System.out.println("3. Remove a Warehouse");
            System.out.println("4. Remove a Book from a Warehouse");
            System.out.println("5. Search by Warehouse");
            System.out.println("6. Search book in a Warehouse");
            System.out.println("7. Search a book in all Warehouses");
            System.out.println("8. Print the tree");
            System.out.println("0. Exit the program");
            ans = Integer.parseInt(scan.nextLine());
            if(ans == 1){
                System.out.println("Please give the warehouse a name");
                while(true){
                    name = scan.nextLine();
                    if(name.matches("[A-Za-z]+")){
                        break;
                    }
                    else System.out.println("Name does not match the given regex");
                }
                System.out.println("Please give a unique ID");
                id = Integer.parseInt(scan.nextLine());
                RBST.insertWarehouse(id, name);
            }
            else if(ans == 2){
                System.out.println("Please give a Warehouse ID");
                id = Integer.parseInt(scan.nextLine());
                System.out.println("Please give the ISBN of the book");
                ISBN = Integer.parseInt(scan.nextLine());
                System.out.println("Please enter the copies of the book");
                copies = Integer.parseInt(scan.nextLine());
                RBST.insertBookAtWarehouse(id, ISBN, copies);
            }
            else if(ans == 3){
                System.out.println("Please give a Warehouse ID to remove");
                id = Integer.parseInt(scan.nextLine());
                RBST.removeWarehouse(id);
            }
            else if(ans == 4){
                System.out.println("Please give a Warehouse ID");
                id = Integer.parseInt(scan.nextLine());
                System.out.println("Please give the ISBN of the book");
                ISBN = Integer.parseInt(scan.nextLine());
                RBST.removeBook(id, ISBN);
            }
            else if(ans == 5){
                System.out.println("Please give a Warehouse ID");
                id = Integer.parseInt(scan.nextLine());
                RBST.searchByWarehouse(id);
            }
            else if(ans == 6){
                System.out.println("Please give a Warehouse ID");
                id = Integer.parseInt(scan.nextLine());
                System.out.println("Please give the ISBN of the book");
                ISBN = Integer.parseInt(scan.nextLine());
                RBST.searchBookInWarehouse(id, ISBN);
            }
            else if(ans == 7){
                System.out.println("Please give the ISBN of the book");
                ISBN = Integer.parseInt(scan.nextLine());
                RBST.searchBook(ISBN);
            }
            else if(ans == 8){
                RBST.printTree(System.out);
            }
            else if(ans == 0){
                scan.close();
                break;
            }
        }
    }
}
