package com.MyLibraryWebApplication.client.services;

import com.MyLibraryWebApplication.shared.Book;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.ArrayList;

@RemoteServiceRelativePath("UploadLibService")
public interface UploadLibService extends RemoteService {
    void uploadBooks(ArrayList<Book> books);

}
