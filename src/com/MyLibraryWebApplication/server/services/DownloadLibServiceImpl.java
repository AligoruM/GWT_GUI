package com.MyLibraryWebApplication.server.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.MyLibraryWebApplication.client.services.DownloadLibService;
import com.MyLibraryWebApplication.client.shared.Book;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class DownloadLibServiceImpl extends RemoteServiceServlet implements DownloadLibService {

    @Override
    public ArrayList<Book> getBooks() {

        ArrayList<Book> books=null;

        try(Reader reader = new FileReader("data.json")){
            Gson gson = new Gson();
            books = gson.fromJson(reader, new TypeToken<List<Book>>(){}.getType());
        }catch(IOException e){
            e.printStackTrace();
        }

        return books;
    }
}
