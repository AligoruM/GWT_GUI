package com.MyLibraryWebApplication.server.utils;

import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        //ArrayList<Book> books = ReadWriteData.read(getServletContext().getRealPath( "/") + "\\data.json");
        Integer[] arr = {0,1,2};
        HashSet<Integer> IDs = new HashSet<>(Arrays.asList(arr));
        //books.forEach(e-> IDs.add(e.getId()));
        int i=-1;
        while(i<Integer.MAX_VALUE){
            if (!IDs.contains(++i)) {
                System.out.println(i);
                break;
            }
        }
        System.out.println(-1);
    }
}
