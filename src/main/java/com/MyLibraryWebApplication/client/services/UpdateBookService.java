package com.MyLibraryWebApplication.client.services;

import com.MyLibraryWebApplication.shared.Book;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;

@RemoteServiceRelativePath("UpdateBookService")
public interface UpdateBookService extends RemoteService {
    void updateBook(Book book);

    /**
     * Utility/Convenience class.
     * Use UpdateBookService.App.getInstance() to access static instance of UpdateBookServiceAsync
     */
}
