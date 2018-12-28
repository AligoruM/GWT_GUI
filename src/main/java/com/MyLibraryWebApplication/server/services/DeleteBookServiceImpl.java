package com.MyLibraryWebApplication.server.services;

import com.MyLibraryWebApplication.server.utils.ReadWriteData;
import com.MyLibraryWebApplication.shared.Book;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.MyLibraryWebApplication.client.services.DeleteBookService;

import java.util.ArrayList;

public class DeleteBookServiceImpl extends RemoteServiceServlet implements DeleteBookService {
    @Override
    public void deleteBook(int id) {
        String path = getServletContext().getRealPath( "/") + "\\data.json";
        ArrayList<Book> books = ReadWriteData.read(path);
        books.removeIf(e-> e.getId() == id);
        ReadWriteData.write(books,path);
    }
}