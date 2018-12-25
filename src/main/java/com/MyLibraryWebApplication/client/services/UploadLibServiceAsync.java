package com.MyLibraryWebApplication.client.services;

import com.MyLibraryWebApplication.shared.Book;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;

public interface UploadLibServiceAsync {

    void uploadBooks(ArrayList<Book> books, AsyncCallback<Void> async);
}
