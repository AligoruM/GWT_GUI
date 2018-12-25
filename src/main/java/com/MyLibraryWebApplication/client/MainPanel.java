package com.MyLibraryWebApplication.client;

import com.MyLibraryWebApplication.client.services.*;
import com.MyLibraryWebApplication.client.view.dialogs.AddDialog;
import com.MyLibraryWebApplication.shared.Book;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;


import java.util.*;

import static com.MyLibraryWebApplication.client.view.Table.BookTable;

public class MainPanel implements EntryPoint {

    private static int lastOrderIndex=-1;
    private static boolean[] orders = {true, true, true, true, true};
    private final static ListDataProvider<Book> bookListDataProvider = new ListDataProvider<>();

    private static DownloadLibServiceAsync downloadLibServiceAsync = GWT.create(DownloadLibService.class);
    private static UploadLibServiceAsync UploadLibSvc = GWT.create(UploadLibService.class);
    private static SortByServiceAsync SortLibServiceAsync = GWT.create(SortByService.class);

    public final static DateTimeFormat PUBLISH_DATE_FORMAT = DateTimeFormat.getFormat("dd-MM-yyyy");
    public final static DateTimeFormat UPDATE_DATE_FORMAT = DateTimeFormat.getFormat("HH:mm:ss dd-MM-yyyy");

    public void onModuleLoad() {
        downloadLibrary();
        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.setBorderWidth(2);
        CellTable<Book> bookTable = BookTable();
        mainPanel.add(bookTable);
        bookListDataProvider.addDataDisplay(bookTable);

        HorizontalPanel hPanel = new HorizontalPanel();
        Button addButton = new Button("Add book");
        Button deleteButton = new Button("Delete Book");

        addButton.addClickHandler(event -> new AddDialog(bookListDataProvider));
        deleteButton.addClickHandler(event -> {
           bookListDataProvider.getList().remove(bookTable.getKeyboardSelectedRow());
           uploadLibrary(bookListDataProvider.getList());
        });

        mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        hPanel.setSpacing(6);
        hPanel.add(addButton);
        hPanel.add(deleteButton);
        mainPanel.add(hPanel);

        RootPanel.get("Table").add(mainPanel);
    }



    public static void uploadLibrary(List<Book> books){
        if(UploadLibSvc==null){
            UploadLibSvc=GWT.create(UploadLibService.class);
        }

        AsyncCallback<Void> callback = new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Upload doesnt work");
                caught.printStackTrace();
            }

            @Override
            public void onSuccess(Void result) {

            }
        };
        UploadLibSvc.uploadBooks(new ArrayList<>(books), callback);
    }

    private static void downloadLibrary(){
        if (downloadLibServiceAsync == null) {
            downloadLibServiceAsync = GWT.create(DownloadLibService.class);
        }
        AsyncCallback<ArrayList<Book>> callback = new AsyncCallback<ArrayList<Book>>() {
            public void onFailure(Throwable caught) {
                Window.alert("Download doesnt work!");
                caught.printStackTrace();
            }

            public void onSuccess(ArrayList<Book> result) {
                bookListDataProvider.getList().addAll(result);
            }
        };
        // Make the call to the stock price service.
        downloadLibServiceAsync.getBooks(callback);
    }

    public static void sortLibrary(int index){

        if (SortLibServiceAsync == null) {
            SortLibServiceAsync = GWT.create(SortByService.class);
        }
        AsyncCallback<ArrayList<Book>> callback = new AsyncCallback<ArrayList<Book>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Sorts doesnt work");
                caught.printStackTrace();
            }

            @Override
            public void onSuccess(ArrayList<Book> result) {
                bookListDataProvider.getList().clear();
                bookListDataProvider.getList().addAll(result);
            }
        };
        SortLibServiceAsync.sortByColumnIndex(index, orders[index], callback);
        orders[index]=!orders[index];
        if(index!=lastOrderIndex){
            orders[lastOrderIndex]=true;
            lastOrderIndex=index;
        }
    }


    public static ListDataProvider<Book> getBookListDataProvider() {
        return bookListDataProvider;
    }
}
