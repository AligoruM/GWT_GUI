package com.MyLibraryWebApplication.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DeleteBookServiceAsync {
    void deleteBook(int id, AsyncCallback<Void> async);
}
