
    import java.io.*;
import java.util.*;

public class assignment_4java_code implements Comparable<Book> {
    int bookId;
    String title;
    String author;

    Book(int id, String t, String a) {
        bookId = id;
        title = t;
        author = a;
    }

    public int compareTo(Book b) {
        return this.title.compareTo(b.title);
    }
}

 class LibraryManager {
    Scanner sc = new Scanner(System.in);
    ArrayList<Book> books = new ArrayList<>();
    HashMap<Integer, Integer> issued = new HashMap<>();

     void addBook() {
        try {
            System.out.print("Enter Book ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Title: ");
            String t = sc.nextLine();
            System.out.print("Enter Author: ");
            String a = sc.nextLine();
            books.add(new Book(id, t, a));
            System.out.println("Book Added\n");
        } catch (Exception e) {
            System.out.println("Invalid Input");
            sc.nextLine();
        }
    }

     void searchBook() {
        try {
            System.out.print("Enter Book ID to search: ");
            int id = sc.nextInt();
            int i = 0;
            while (i < books.size()) {
                Book b = books.get(i);
                if (b.bookId == id) {
                    System.out.println("ID: " + b.bookId);
                    System.out.println("Title: " + b.title);
                    System.out.println("Author: " + b.author);
                    if (issued.containsKey(id)) System.out.println("Status: Issued");
                    else System.out.println("Status: Available");
                    return;
                }
                i++;
            }
            System.out.println("Book not found");
        } catch (Exception e) {
            System.out.println("Invalid Input");
            sc.nextLine();
        }
    }

     void sortBooks() {
        Collections.sort(books);
        int i = 0;
        System.out.println("\nSorted Books:");
        while (i < books.size()) {
            Book b = books.get(i);
            System.out.println(b.bookId + " - " + b.title + " - " + b.author);
            i++;
        }
        System.out.println();
    }

    public void issueBook() {
        try {
            System.out.print("Enter Book ID to issue: ");
            int id = sc.nextInt();
            System.out.print("Enter Member ID: ");
            int m = sc.nextInt();

            int i = 0;
            while (i < books.size()) {
                Book b = books.get(i);
                if (b.bookId == id) {
                    if (issued.containsKey(id)) {
                        System.out.println("Already issued");
                        return;
                    }
                    issued.put(id, m);
                    System.out.println("Book Issued\n");
                    return;
                }
                i++;
            }
            System.out.println("Book not found");
        } catch (Exception e) {
            System.out.println("Invalid Input");
            sc.nextLine();
        }
    }

     void returnBook() {
        try {
            System.out.print("Enter Book ID to return: ");
            int id = sc.nextInt();
            if (issued.containsKey(id)) {
                issued.remove(id);
                System.out.println("Book Returned\n");
            } else {
                System.out.println("Book is not issued");
            }
        } catch (Exception e) {
            System.out.println("Invalid Input");
            sc.nextLine();
        }
    }

     void menu() {
        int ch = 0;
        while (ch != 6) {
            System.out.println("1. Add Book");
            System.out.println("2. Search Book");
            System.out.println("3. Sort Books");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            try {
                ch = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid Input");
                sc.nextLine();
                continue;
            }

            if (ch == 1) addBook();
            else if (ch == 2) searchBook();
            else if (ch == 3) sortBooks();
            else if (ch == 4) issueBook();
            else if (ch == 5) returnBook();
            else if (ch == 6) System.out.println("Exiting");
            else System.out.println("Invalid Choice");
        }
    }

    public static void main(String[] args) {
        LibraryManager obj = new LibraryManager();
        obj.menu();
    }
}
    

