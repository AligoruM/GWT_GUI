package com.MyLibraryWebApplication.server.services;

import com.MyLibraryWebApplication.server.utils.ReadWriteData;
import com.MyLibraryWebApplication.shared.Book;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.MyLibraryWebApplication.client.services.AddBookService;

import java.util.ArrayList;

public class AddBookServiceImpl extends RemoteServiceServlet implements AddBookService {
    @Override
    public void addBook(Book book) {
        String path = getServletContext().getRealPath( "/") + "\\data.json";
        ArrayList<Book> books = ReadWriteData.read(path);
        books.add(book);
        ReadWriteData.write(books, path);
    }
}