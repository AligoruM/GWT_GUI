package com.MyLibraryWebApplication.client.services;

import com.MyLibraryWebApplication.shared.Book;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;

public interface SortByServiceAsync {
    void sortByColumnIndex(int index, boolean ascend, AsyncCallback<ArrayList<Book>> async);
}
