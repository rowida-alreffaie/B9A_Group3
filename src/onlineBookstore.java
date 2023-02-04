import java.io.*;
import java.util.*;

public class onlineBookstore {
    static ArrayList<Book> Library = new ArrayList();
    static ArrayList<Book> Cart = new ArrayList();

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("Books.txt");

        if (!file.exists()) {
            System.out.println("Please Check The Input File");
            return;
        }

        Scanner input = new Scanner(file);

        AddAllBooks(input);

        input = new Scanner(System.in);

        System.out.println("\n\nAre You: \n1: Employee \n2: Customer");

        boolean flag = true;

        for (; flag; ) {
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    EmployeeOperations(input);
                    flag = false;
                    break;
                case 2:
                    CustomerOperations(input);
                    flag = false;
                    break;
                default:
                    System.out.println("Sorry, Wrong Choice.");
                    System.out.println("Please Reenter Your Choice.");
                    break;
            }
        }
    }


//----------------------------------------------------------------------------------------------
//                                Customer Operations
//----------------------------------------------------------------------------------------------

    /**
     * Method That Represent The Driver Of The customer's Operations
     *
     * @param input Scanner
     */
    private static void CustomerOperations(Scanner input) {
        while (true) {

            CustomerMenu();
            System.out.print("Please, Select Your Choice: ");
            int choice = input.nextInt();

            if (Cart.size() != 0) {
                switch (choice) {
                    case 1:
                        AddBookToCart(input);
                        break;
                    case 2:
                        DeleteBook(input);
                        break;
                    case 3:
                        SearchForBook(input);
                        break;
                    case 4:
                        System.out.println("\nThank You For Shopping With Us.\n\n");
                        return;

                    case 5:
                        return;
                }
            } else {

                switch (choice) {
                    case 1:
                        AddBookToCart(input);
                        break;
                    case 2:
                        SearchForBook(input);
                        break;
                    case 3:
                        return;
                }
            }
        }
    }

    /**
     * Method To Search For A Book\s By Asking The User To Enter The Title Of The Book,
     * Or By The Name Of The Author And Display All The Matches
     *
     * @param input Scanner
     */
    private static void SearchForBook(Scanner input) {
        System.out.print("\nPlease, Enter The Book Title Or The Author: ");
        input.nextLine();
        String Name = input.nextLine().trim().replaceAll(" ", "_");
        int Counter = 0;


        for (int i = 0; i < Library.size(); i++) {
            if (Name.equalsIgnoreCase(Library.get(i).Title) || Name.equalsIgnoreCase(Library.get(i).Author)) {
                System.out.println(++Counter + ": " + Library.get(i));
            }
        }

        if (Counter == 0) {
            System.out.println("\nSorry, There Is No Books By This Title Or Author.\n");

        }
    }

    //----------------------------------------------------------------------------------------------

    /**
     * Method To Delete A Book From The Cart By Displaying The Cart,
     * Then Ask The User To Select The Number Of The Book He\She Wants To Delete
     */
    private static void DeleteBook(Scanner input) {
    
    }

    //----------------------------------------------------------------------------------------------

    /**
     * Method To Add A Book From The Library Into The Cart By Displaying The Library, T
     * hen Ask The User To Select The Number OF The Book He\She Wants To Add
     *
     * @param input Scanner
     */
    private static void AddBookToCart(Scanner input) {

        DisplayLibrary();
        System.out.print("Please, Select The Book You Want To Add (Press 0 To Go Back). ");

        while (true) {

            int selected = input.nextInt();
            if (selected == 0) {

                break;

            } else if (1 <= selected && selected <= Library.size()) {

                System.out.print("Are You Sure You Want To Add The Book " + Library.get(selected - 1).Title.replaceAll("_", " ") + " (yes/no)?");
                while (true) {

                    String choice = input.next();
                    if (choice.equalsIgnoreCase("yes")) {

                        Cart.add(Library.get(selected - 1));
                        System.out.println("Book Add successfully To The Cart!");
                        break;

                    } else if (choice.equalsIgnoreCase("no")) {

                        break;

                    } else {

                        System.out.println("\nWrong Input, Try Again!\n");

                    }
                }

                break;

            } else {

                System.out.println("\nWrong Input, Try Again!\n");

            }
        }
    }

    //----------------------------------------------------------------------------------------------

    /**
     * Method To Display The Whole Library
     */
    private static void DisplayLibrary() {
        if (Library.isEmpty()) {

            System.out.println("There are No Books Available!");

        } else {

            int Counter = 1;
            for (Book book : Library) {

                System.out.println(Counter++ + ". " + book);

            }
        }
    }

    //----------------------------------------------------------------------------------------------

    /**
     * Method To Display The Menu For The Customer, It Has 2 Menus
     * The First If The Cart Is Empty So, No Need To Give "DeleteFromCart" & "CheckOut" As An Option,
     * The Second Menu Will Only Shows "AddBookToCart", "SearchFroBook", And "Quit".
     */
    private static void CustomerMenu() {
        if (Cart.size() != 0) {
            System.out.println("1: Add Book To Cart." +
                    "\n2: Delete Book from Cart." +
                    "\n3: Search For Book." +
                    "\n4: Check Out." +
                    "\n5: Quit.");
            return;
        }
        System.out.println("1: Add Book To Cart." +
                "\n2: Search For Book." +
                "\n3: Quit.");


    }


//----------------------------------------------------------------------------------------------
//                                Employee Operations
//----------------------------------------------------------------------------------------------

    /**
     * Method That Represent The Driver Of The Employee's Operations
     *
     * @param input Scanner
     * @throws FileNotFoundException Exception If The File Can't Be
     *                               Found In The Invoked Method "AddBookToWebsite()"
     */
    private static void EmployeeOperations(Scanner input) throws FileNotFoundException {
        while (true) {
            EmployeeMenu();

            System.out.print("Please Select An Operation From The Menu: ");
            int option = input.nextInt();

            switch (option) {
                case 1:
                    AddBookToWebsite(input);
                    break;
                case 2:
                    return;

            }
        }
    }

    //----------------------------------------------------------------------------------------------

    /**
     * Method Reads The Book's Information And Add It To The Website And Into The DataBase By Over Writing
     * The DataBase "Books.txt"
     *
     * @param input Scanner
     * @throws FileNotFoundException Exception If The File Can't Be Found
     */
    private static void AddBookToWebsite(Scanner input) throws FileNotFoundException {
        System.out.print("Please, Enter The Title Of The Book.");
        input.nextLine();
        String Title = input.nextLine().trim().replaceAll(" ", "_");
        System.out.print("\nPlease, Enter The Author Of The Book.");
        String Author = input.nextLine().trim().replaceAll(" ", "_");

        System.out.print("\nPlease, Enter The Published Date Of The Book(Year Month Day).");
        int year = input.nextInt();
        int Month = input.nextInt();
        int Day = input.nextInt();

        Date tempDate = new Date(year - 1900, Month - 1, Day);


        Book tempBook = new Book(Author, Title, tempDate);

        Library.add(tempBook);


        //To Update The DataBase
        PrintWriter pen = new PrintWriter("Books.txt");
        pen.println("#Title   Author   Publish-Date: year month day");
        for (Book book : Library) {
            pen.println(book.Title + " " + book.Author + " " +
                    (book.Published.getYear() + 1900) + " " + (book.Published.getMonth() + 1) + " " +
                    book.Published.getDate());
        }
        pen.flush();
        pen.close();

        System.out.println("The Website And The DataBase Has Been Updated");
        System.out.println("the library contains: ");
        int Counter = 1;
        for (Book book : Library) {
            System.out.println(Counter++ + ". " + book);
        }

    }

    //----------------------------------------------------------------------------------------------

    /**
     * Method To Display The Employee's Menu
     */
    private static void EmployeeMenu() {
        System.out.println("1: Add Book To The Library." +
                "\n2: Quit.");
    }

    //----------------------------------------------------------------------------------------------

    /**
     * Method To Read All The Books From The DateBase "Books.txt" And Add It To The Library
     *
     * @param input Scanner
     */
    private static void AddAllBooks(Scanner input) {

        for (; input.hasNext(); ) {

            String Line = input.nextLine().trim();
            if (Line.startsWith("#")) {
                continue;
            }

            String temp[] = Line.split("\\s+");

            String Title = temp[0];
            String Author = temp[1];

            int year = Integer.parseInt(temp[2]);
            int month = Integer.parseInt(temp[3]);
            int day = Integer.parseInt(temp[4]);
            Date published = new Date(year - 1900, month - 1, day);


            Book tempBook = new Book(Author, Title, published);
            Library.add(tempBook);
        }

    }
}
