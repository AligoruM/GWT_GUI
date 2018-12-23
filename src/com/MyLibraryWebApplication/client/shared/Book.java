package com.MyLibraryWebApplication.client.shared;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {
    private int id;
    private String author;
    private String name;
    private int pageNumber;
    private Date publishDate;
    private Date updateDate;

    public Book() {
    }

    public Book(int id, String author, String name, int pageNumber, Date publishDate, Date updateDate) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.pageNumber = pageNumber;
        this.publishDate = publishDate;
        this.updateDate = updateDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", pageNumber=" + pageNumber +
                ", publishDate=" + publishDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
