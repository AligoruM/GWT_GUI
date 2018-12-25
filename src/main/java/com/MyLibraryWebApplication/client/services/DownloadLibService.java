package com.MyLibraryWebApplication.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.MyLibraryWebApplication.shared.Book;

import java.util.ArrayList;

@RemoteServiceRelativePath("DownloadLib")
public interface DownloadLibService extends RemoteService {
    ArrayList<Book> getBooks();
}
