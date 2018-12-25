package com.MyLibraryWebApplication.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.MyLibraryWebApplication.shared.Book;

import java.util.ArrayList;

public interface DownloadLibServiceAsync {
    void getBooks(AsyncCallback<ArrayList<Book>> async);
}
