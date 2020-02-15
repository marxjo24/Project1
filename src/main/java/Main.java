import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.lang.management.BufferPoolMXBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Statement stmt;
    private static Connection conn;
    public static void main(String[] args) throws IOException, CsvValidationException, SQLException, ClassNotFoundException {
        connect();
        Gson gson = new Gson();
        CsvParser csvP = new CsvParser("src/Data/BookStore_Report.csv");
        List<BookStoreReport> bookStoreReportList = csvP.toClass();

        for(BookStoreReport bookStoreReport : bookStoreReportList){
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO book (isbn,publisher_name,author_name,book_title) values (?,?,?,?)");
            preparedStatement.setString(1,bookStoreReport.getIsbn());
            preparedStatement.setString(2,bookStoreReport.getPublisher());
            preparedStatement.setString(3,bookStoreReport.getAuthor());
            preparedStatement.setString(4,bookStoreReport.getTitle());
            preparedStatement.execute();
        }

        JsonReader jread = new JsonReader(new FileReader("src/Data/authors.json"));
        AuthorParser[] authors = gson.fromJson(jread, AuthorParser[].class);
        for (AuthorParser element : authors) {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO author (author_name) values (?)");
            preparedStatement.setString(1,element.getName());
            preparedStatement.execute();
        }


    }
    public static void connect() throws ClassNotFoundException, SQLException {
        final String JDBC_DRIVER = "org.sqlite.core.DB";
        final String DB_URL = "jdbc:sqLite:src/Data/BookStore.db";
        final String USER = "";
        final String PASS = "";


        try{

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
        } catch(Exception e){
            System.out.println("Could not connect to database");
        }
    }
}
