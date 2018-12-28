package com.MyLibraryWebApplication.client.services;

import com.MyLibraryWebApplication.shared.Book;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;

@RemoteServiceRelativePath("AddBookService")
public interface AddBookService extends RemoteService {
    void addBook(Book book);
    /**
     * Utility/Convenience class.
     * Use AddBookService.App.getInstance() to access static instance of AddBookServiceAsync
     */
}
