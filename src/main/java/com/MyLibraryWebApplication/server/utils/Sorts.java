package com.MyLibraryWebApplication.server.utils;

import com.MyLibraryWebApplication.shared.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sorts {
    public static ArrayList<Book>sortBy(int columnIndex, ArrayList<Book> books, boolean ascend){
        switch(columnIndex){
            case(0):{//name
                books.sort(Comparator.comparing(Book::getName));
                break;
            }
            case(1):{//author
                books.sort(Comparator.comparing(Book::getAuthor));
                break;
            }
            case(2): {//publ date
                books.sort(Comparator.comparing(Book::getPublishDate));
                break;
            }
            case(3): {//pages
                books.sort(Comparator.comparing(Book::getPageNumber));
                break;
            }
            case(4): {//update date
                books.sort(Comparator.comparing(Book::getUpdateDate));
                break;
            }
            default:
                return books;

        }
        if(!ascend) {
            Collections.reverse(books);
        }
        return books;
    }
}
