
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class onlineBookstoreTest {

    private ArrayList<Book> library = new ArrayList<>();
    private ArrayList<Book> cart = new ArrayList<>();
    private onlineBookstore mainProgram = new onlineBookstore();

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setup() {
        Book book1 = new Book("Author1", "Book1", new Date(2018, 1, 1));
        Book book2 = new Book("Author2", "Book2", new Date(2019, 1, 1));
        Book book3 = new Book("Author3", "Book3", new Date(2020, 1, 1));
        library.add(book1);
        library.add(book2);
        library.add(book3);
        mainProgram.Library = library;
        mainProgram.Cart = cart;
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of SearchForBook method, of class MainProgram.
     */
    @Test
    public void testSearchForBook() {
        // Test searching for a book by title
        String title = "Book1";
        ArrayList<Book> result = mainProgram.SearchForBook(title);
        assertEquals(1, result.size());
        assertEquals("Book1", result.get(0).getTitle());

        // Test searching for a book by author
        String author = "Author2";
        result = mainProgram.SearchForBook(author);
        assertEquals(1, result.size());
        assertEquals("Author2", result.get(0).getAuthor());

        // Test searching for a non-existent book
        String searchTerm = "NonExistentBook";
        result = mainProgram.SearchForBook(searchTerm);
        assertEquals(0, result.size());
    }

    /**
     * Test of DeleteBook method, of class MainProgram.
     */
    @Test
    public void testDeleteBook() {
        // Add a book to the cart
        Book bookToDelete = library.get(0);
        cart.add(bookToDelete);
        mainProgram.Cart = cart;
        assertEquals(1, cart.size());

        // Delete the book from the cart
        mainProgram.DeleteBook(bookToDelete);
        assertEquals(0, cart.size());
    }

    /**
     * Test of addBookToCart method, of class MainProgram.
     */
    @Test
    public void testAddBookToCart() {
        // Add a book to the cart
        Book bookToAdd = library.get(0);
        mainProgram.addBookToCart(bookToAdd);
        assertEquals(1, cart.size());
        assertEquals("Book1", cart.get(0).getTitle());
    }

    /**
     * Test of addBookToWebsite method, of class MainProgram.
     */
    @Test
    public void testAddBookToWebsite() {
        // Add a book to the website
        Book bookToAdd = new Book("NewBook", "NewAuthor", new Date(2022, 1, 1));
        mainProgram.addBookToWebsite(bookToAdd);
        assertEquals(4, library.size());
        assertEquals("NewBook", library.get(3).getAuthor());
    }

}
