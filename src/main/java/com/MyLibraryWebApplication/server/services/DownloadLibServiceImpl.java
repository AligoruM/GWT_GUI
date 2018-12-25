package com.MyLibraryWebApplication.server.services;

import com.MyLibraryWebApplication.server.utils.ReadWriteData;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.MyLibraryWebApplication.client.services.DownloadLibService;
import com.MyLibraryWebApplication.shared.Book;

import java.util.ArrayList;


public class DownloadLibServiceImpl extends RemoteServiceServlet implements DownloadLibService {

    @Override
    public ArrayList<Book> getBooks() {
        String path = getServletContext().getRealPath( "/") + "\\data.json";
        return ReadWriteData.read(path);
    }
}
