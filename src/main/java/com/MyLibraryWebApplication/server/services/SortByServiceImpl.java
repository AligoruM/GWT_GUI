package com.MyLibraryWebApplication.server.services;

import com.MyLibraryWebApplication.shared.Book;
import com.MyLibraryWebApplication.server.utils.ReadWriteData;
import com.MyLibraryWebApplication.server.utils.Sorts;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.MyLibraryWebApplication.client.services.SortByService;

import java.util.ArrayList;

public class SortByServiceImpl extends RemoteServiceServlet implements SortByService {
    @Override
    public ArrayList<Book> sortByColumnIndex(int index, boolean ascend) {
        String path = getServletContext().getRealPath( "/") + "\\data.json";
        return Sorts.sortBy(index, ReadWriteData.read(path), ascend);
    }
}