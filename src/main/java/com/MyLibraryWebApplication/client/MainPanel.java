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
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;


import java.util.*;

import static com.MyLibraryWebApplication.client.view.Table.BookTable;

public class MainPanel implements EntryPoint {

    private static int lastOrderIndex=-1;
    private static boolean[] orders = {true, true, true, true, true};
    private final static ListDataProvider<Book> bookListDataProvider = new ListDataProvider<>();

    private static AsyncDataProvider<Book> asyncDataProvider;

    private static DownloadLibServiceAsync DownloadLibServiceAsync = GWT.create(DownloadLibService.class);
    private static SortByServiceAsync SortLibServiceAsync = GWT.create(SortByService.class);
    private static DeleteBookServiceAsync DeleteBookServiceAsync = GWT.create(DeleteBookService.class);

    public final static DateTimeFormat PUBLISH_DATE_FORMAT = DateTimeFormat.getFormat("dd-MM-yyyy");
    public final static DateTimeFormat UPDATE_DATE_FORMAT = DateTimeFormat.getFormat("HH:mm:ss dd-MM-yyyy");

    public void onModuleLoad() {
        asyncDataProvider=new AsyncDataProvider<Book>() {
            @Override
            protected void onRangeChanged(HasData<Book> display) {
                final int start = display.getVisibleRange().getStart();
            }
        };

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
            Book selected = ((SingleSelectionModel<Book>)bookTable.getSelectionModel()).getSelectedObject();
            if(selected!=null) {
                bookListDataProvider.getList().removeIf(e -> e.getId() == selected.getId());
                deleteBook(selected.getId());
                ((SingleSelectionModel<Book>) bookTable.getSelectionModel()).clear();
            }
        });
        //adding pager
        {
            HorizontalPanel pagerPanel = new HorizontalPanel();
            SimplePager pager = new SimplePager();
            pager.setDisplay(bookTable);
            pager.setPageSize(4);
            mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
            pagerPanel.add(pager);
            mainPanel.add(pagerPanel);
        }
        //adding buttons
        {
            mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
            hPanel.setSpacing(6);
            hPanel.add(addButton);
            hPanel.add(deleteButton);
            mainPanel.add(hPanel);
        }
        RootPanel.get("Table").add(mainPanel);
    }



    private static void deleteBook(int id){
        if(DeleteBookServiceAsync==null){
            DeleteBookServiceAsync=GWT.create(DeleteBookService.class);
        }
        AsyncCallback<Void> callback = new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("DeleteBook doesnt work");
                caught.printStackTrace();
            }

            @Override
            public void onSuccess(Void result) {
                Window.alert("Deleted");
            }
        };
        DeleteBookServiceAsync.deleteBook(id, callback);
    }

    private static void downloadLibrary(){
        if (DownloadLibServiceAsync == null) {
            DownloadLibServiceAsync = GWT.create(DownloadLibService.class);
        }
        AsyncCallback<ArrayList<Book>> callback = new AsyncCallback<ArrayList<Book>>() {
            public void onFailure(Throwable caught) {
                Window.alert("Download doesnt work!");
                caught.printStackTrace();
            }

            public void onSuccess(ArrayList<Book> result) {
                //Window.alert("Received " + result.get(0).toString());
                bookListDataProvider.getList().clear();
                bookListDataProvider.getList().addAll(result);
            }
        };
        // Make the call to the stock price service.
        DownloadLibServiceAsync.getBooks(callback);
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
