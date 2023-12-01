package model;

import java.util.ArrayList;
import java.util.List;

import model.RecommBook;

public class RecommBook {
	String isbn;
    String title;
    String author;
    String cover_path;
    Double avgstar;
    
    public RecommBook(String isbn, String title, String author, String cover_path, Double avgstar) {
        super();
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.cover_path = cover_path;
        this.avgstar = avgstar;
    }
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCover_path() {
        return cover_path;
    }

    public void setCover_path(String cover_path) {
        this.cover_path = cover_path;
    }

    public Double getAvgstar() {
        return avgstar;
    }

    public void setAvgstar(Double avgstar) {
        this.avgstar = avgstar;
    }
    
    public static List<RecommBook> getRecommBooks() {
        List<RecommBook> recommBooks = new ArrayList<>();

        RecommBook book1 = new RecommBook(null, null, null, null, null);
        book1.setCover_path("../img/book1.jpg");
        book1.setTitle("JAVA 마스터");
        book1.setAuthor("송미영");
        book1.setAvgstar(3.0);
        recommBooks.add(book1);

        RecommBook book2 = new RecommBook(null, null, null, null, null);
        book2.setCover_path("../img/book2.jpg");
        book2.setTitle("JAVA의 정석");
        book2.setAuthor("남궁성");
        book2.setAvgstar(3.0);
        recommBooks.add(book2);

        RecommBook book3 = new RecommBook(null, null, null, null, null);
        book3.setCover_path("../img/book3.jpg");
        book3.setTitle("Power JAVA");
        book3.setAuthor("천인국");
        book3.setAvgstar(3.0);
        recommBooks.add(book3);

        return recommBooks;
    }
}
