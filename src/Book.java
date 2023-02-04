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


    @Override
    public String toString() {

        return
            "\nBook: " + Title.replaceAll("_"," ") +
            "\nWritten by: " +Author.replaceAll("_" , " ")+
            "\nPublish Date: " +
            String.format((Published.getYear() + 1900) + "-" + (Published.getMonth() + 1) +
                    "-" + Published.getDate()) + "\n";
}
}
