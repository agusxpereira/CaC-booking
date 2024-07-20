package com.bookbox;

public class Book {
    private int book_id;		
	private String title;
	private String author;	
	private int pages;	
	private String about;	
	private String genre;
    private String image;

    public Book(){

    }

    public Book(int book_id, String title, String author, int pages, String about, String genre, String image) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.about = about;
        this.genre = genre;
        this.image = image;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    } 

    
};
