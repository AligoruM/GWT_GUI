package com.MyLibraryWebApplication.client.view.utils;


import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

public class CustomProvider<Book> extends AsyncDataProvider<Book> {
    @Override
    protected void onRangeChanged(HasData display) {
        int start = display.getVisibleRange().getStart();
        int end = start + display.getVisibleRange().getLength();
    }
}
