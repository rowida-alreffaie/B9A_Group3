
import java.util.Date;

public class Book {

    String Author;
    String Title;
    Date Published;

    public Book(String author, String title, Date published) {
        Author = author;
        Title = title;
        Published = published;
    }

    public String getAuthor() {
        return Author;
    }

    public String getTitle() {
        return Title;
    }

    public Date getPublished() {
        return Published;
    }

    @Override
    public String toString() {

        return "\nBook: " + Title.replaceAll("_", " ")
                + "\nWritten by: " + Author.replaceAll("_", " ")
                + "\nPublish Date: "
                + String.format((Published.getYear()) + "-" + (Published.getMonth())
                        + "-" + Published.getDate()) + "\n";
    }
}
