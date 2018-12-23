package com.MyLibraryWebApplication.client;

import com.google.gwt.cell.client.DateCell;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.MyLibraryWebApplication.client.services.DownloadLibService;
import com.MyLibraryWebApplication.client.services.DownloadLibServiceAsync;
import com.MyLibraryWebApplication.client.shared.Book;
import com.MyLibraryWebApplication.client.view.EditDialog;

import java.util.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MainPanel implements EntryPoint {

    private List<Book> books = new ArrayList<>();
    private DownloadLibServiceAsync DwnlLibSvc = GWT.create(DownloadLibService.class);

    private static final List<Book> BOOK_LIST = Arrays.asList(
            new Book(0, "Ser", "Metro 2033", 480, new Date(), new Date()),
            new Book(1, "Ser", "Metro 2035", 435, new Date(), new Date()),
            new Book(2, "Ser", "Metro 2034", 542, new Date(), new Date()));

    private final ListDataProvider<Book> bookListDataProvider = new ListDataProvider<>();

    public static final DateTimeFormat PUBLISH_DATE_FORMAT = DateTimeFormat.getFormat("dd-MM-yyyy");

    public void onModuleLoad() {
        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.setBorderWidth(3);
        mainPanel.setWidth("600");
        CellTable<Book> bookTable = BookTable();
        mainPanel.add(bookTable);

        HorizontalPanel hPanel = new HorizontalPanel();
        Button addButton = new Button("Add book");
        Button deleteButton = new Button("Delete Book");

        mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        hPanel.setSpacing(6);

        hPanel.add(addButton);
        hPanel.add(deleteButton);

        mainPanel.add(hPanel);


        // Add the widgets to the root panel.
        RootPanel.get().add(mainPanel);
    }

    public CellTable<Book> BookTable() {

        CellTable<Book> table = new CellTable<>();
        downloadLibrary();
        //bookListDataProvider.getList().addAll(BOOK_LIST);
        bookListDataProvider.getList().addAll(books);
        bookListDataProvider.addDataDisplay(table);

        table.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
        table.setWidth("600");

        TextColumn<Book> nameColumn = new TextColumn<Book>() {
            @Override
            public String getValue(Book object) {
                return object.getName();
            }
        };
        table.addColumn(nameColumn, "Name");

        ColumnSortEvent.ListHandler<Book> nameSortHandler = new ColumnSortEvent.ListHandler<>(bookListDataProvider.getList());
        nameColumn.setSortable(true);
        nameSortHandler.setComparator(nameColumn, Comparator.comparing(Book::getName));
        table.addColumnSortHandler(nameSortHandler);

        TextColumn<Book> authorColumn = new TextColumn<Book>() {
            @Override
            public String getValue(Book object) {
                return object.getAuthor();
            }
        };
        table.addColumn(authorColumn, "Author");

        TextColumn<Book> dateColumn = new TextColumn<Book>() {
            @Override
            public String getValue(Book object) {
                return PUBLISH_DATE_FORMAT.format(object.getPublishDate());
            }
        };
        table.addColumn(dateColumn, "Date");

        TextColumn<Book> pageCount = new TextColumn<Book>() {
            @Override
            public String getValue(Book object) {
                return Integer.toString(object.getPageNumber());
            }
        };
        table.addColumn(pageCount, "Pages");

        DateCell updateDateCell = new DateCell();
        Column<Book, Date> updateDateColumn = new Column<Book, Date>(updateDateCell) {
            @Override
            public Date getValue(Book object) {
                return object.getUpdateDate();
            }
        };
        table.addColumn(updateDateColumn, "Update date");

        final SingleSelectionModel<Book> selectionModel = new SingleSelectionModel<>();
        table.setSelectionModel(selectionModel);

        table.addDomHandler(event -> {
            Book selected = selectionModel.getSelectedObject();

            if (selected != null) {
                new EditDialog(selected, BOOK_LIST, table).ShowEditDialog();
                selectionModel.clear();
            }

        }, DoubleClickEvent.getType());

        table.setRowCount(bookListDataProvider.getList().size(), true);
        //table.setRowData(0, BOOK_LIST);
        return table;
    }

    private void downloadLibrary(){

        if (DwnlLibSvc == null) {
            DwnlLibSvc = GWT.create(DownloadLibService.class);
        }
        AsyncCallback<ArrayList<Book>> callback = new AsyncCallback<ArrayList<Book>>() {
            public void onFailure(Throwable caught) {
                Window.alert("nothing work!");
            }

            public void onSuccess(ArrayList<Book> result) {
                bookListDataProvider.getList().addAll(result);
            }
        };
        // Make the call to the stock price service.
        DwnlLibSvc.getBooks(callback);
    }

}
