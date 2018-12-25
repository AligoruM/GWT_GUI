package com.MyLibraryWebApplication.client.services;

import com.MyLibraryWebApplication.shared.Book;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.ArrayList;

@RemoteServiceRelativePath("SortByService")
public interface SortByService extends RemoteService {
    ArrayList<Book> sortByColumnIndex(int index, boolean ascend);
}
