package com.example.librarymanagementsystem;

public class BookModel {
String Title, Author, BookId;

public BookModel(){}

    BookModel(String title, String auhtor,String bookId ){
    this.Title = title;
    this.Author = auhtor;
    this.BookId = bookId;
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }
}
