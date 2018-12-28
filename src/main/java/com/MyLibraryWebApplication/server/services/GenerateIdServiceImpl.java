package com.MyLibraryWebApplication.server.services;

import com.MyLibraryWebApplication.server.utils.ReadWriteData;
import com.MyLibraryWebApplication.shared.Book;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.MyLibraryWebApplication.client.services.GenerateIdService;

import java.util.ArrayList;
import java.util.HashSet;

public class GenerateIdServiceImpl extends RemoteServiceServlet implements GenerateIdService {
    @Override
    public Integer getId() {
        ArrayList<Book> books = ReadWriteData.read(getServletContext().getRealPath( "/") + "\\data.json");
        HashSet<Integer> IDs = new HashSet<>();
        books.forEach(e-> IDs.add(e.getId()));
        int i=-1;
        while(i<Integer.MAX_VALUE){
            if (!IDs.contains(++i))
                return i;
        }
        return -1;
    }
}