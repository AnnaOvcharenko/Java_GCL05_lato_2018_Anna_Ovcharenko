package model;

import javax.persistence.*;

public class Book {
    private int book_id;
    private String title;
    private String autor;

    public Book( String title, String autor) {
        this.title = title;
        this.autor = autor;
    }

    public Book() {
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}

