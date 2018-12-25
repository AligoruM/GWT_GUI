package com.MyLibraryWebApplication.server.services;

import com.MyLibraryWebApplication.shared.Book;
import com.MyLibraryWebApplication.server.utils.ReadWriteData;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.MyLibraryWebApplication.client.services.UploadLibService;

import java.util.ArrayList;

public class UploadLibServiceImpl extends RemoteServiceServlet implements UploadLibService {
    @Override
    public void uploadBooks(ArrayList<Book> books) {
        String path = getServletContext().getRealPath( "/") + "\\data.json";
        ReadWriteData.write(books, path);
    }
}