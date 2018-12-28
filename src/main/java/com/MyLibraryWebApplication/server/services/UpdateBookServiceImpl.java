package com.MyLibraryWebApplication.server.services;

import com.MyLibraryWebApplication.server.utils.ReadWriteData;
import com.MyLibraryWebApplication.shared.Book;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.MyLibraryWebApplication.client.services.UpdateBookService;

import java.util.ArrayList;

public class UpdateBookServiceImpl extends RemoteServiceServlet implements UpdateBookService {
    @Override
    public void updateBook(Book book) {
        String path = getServletContext().getRealPath( "/") + "\\data.json";
        ArrayList<Book> books = ReadWriteData.read(path);

        books.forEach(e->{
            if(e.getId()==book.getId()) {
                e.setAuthor(book.getAuthor());
                e.setName(book.getName());
                e.setPageNumber(book.getPageNumber());
                e.setPublishDate(book.getPublishDate());
                e.setPublishDate(book.getUpdateDate());
            }
        });


        ReadWriteData.write(books, path);
    }
}