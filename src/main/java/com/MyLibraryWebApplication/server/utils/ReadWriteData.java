package com.MyLibraryWebApplication.server.utils;

import com.MyLibraryWebApplication.shared.Book;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;

public class ReadWriteData {
    public static ArrayList<Book> read(String path) {
        ArrayList<Book> books = null;
        try(Reader reader = new FileReader(path)){
            Gson gson = new Gson();
            books = gson.fromJson(reader, new TypeToken<ArrayList<Book>>(){}.getType());
        }catch(IOException e){
            e.printStackTrace();
        }
        return books;
    }
    
    public static void write(ArrayList<Book> books, String path){
        try (Writer writer = new FileWriter(path)){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(books, writer);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
