import java.lang.reflect.Method;

public class BookStoreReport {
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private String store;
    private String location;

    BookStoreReport(String csvString){
        String[] variables = {"","","","","",""};
        StringBuilder stringBuilder = new StringBuilder(csvString);
        for (int x = 0; x<6;x++) {
            int stop = stringBuilder.indexOf("*")+1;
            variables[x] = stringBuilder.toString().substring(0,stop-1);
            stringBuilder.delete(0,stop);
        }
        isbn=variables[0];
        title=variables[1];
        author=variables[2];
        publisher=variables[3];
        store=variables[4];
        location=variables[5];
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getLocation() {
        return location;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getStore() {
        return store;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "isbn: " + isbn + " Title: " + title + " Author: " + author + " Publisher: " + publisher + " Store: " +
                store + " location: " + location;
    }
}
