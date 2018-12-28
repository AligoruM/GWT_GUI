package com.MyLibraryWebApplication.client.services;

import com.MyLibraryWebApplication.shared.Book;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AddBookServiceAsync {
    void addBook(Book book, AsyncCallback<Void> async);
}
