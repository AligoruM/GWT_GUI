package com.MyLibraryWebApplication.client.view;

import com.MyLibraryWebApplication.client.MainPanel;
import com.MyLibraryWebApplication.shared.Book;
import com.MyLibraryWebApplication.client.view.dialogs.EditDialog;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.SingleSelectionModel;

public class Table {
    public static CellTable<Book> BookTable() {
        CellTable<Book> table = new CellTable<>();
        table.setWidth("600");
        table.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
        TextColumn<Book> nameColumn = new TextColumn<Book>() {
            @Override
            public String getValue(Book object) {
                return object.getName();
            }
        };
        Header<String> nameHeader = new Header<String>(new ClickableTextCell()) {
            @Override
            public String getValue() {
                return "Name";
            }
        };
        nameHeader.setUpdater(value -> MainPanel.sortLibrary(0));
        nameColumn.setSortable(true);
        table.addColumn(nameColumn, nameHeader);

        TextColumn<Book> authorColumn = new TextColumn<Book>() {
            @Override
            public String getValue(Book object) {
                return object.getAuthor();
            }
        };
        authorColumn.setSortable(true);
        Header<String> authorHeader = new Header<String>(new ClickableTextCell()) {
            @Override
            public String getValue() {
                return "Author";
            }
        };
        authorHeader.setUpdater(value -> MainPanel.sortLibrary(1));
        nameHeader.setHeaderStyleNames("headers");
        table.addColumn(authorColumn, authorHeader);

        TextColumn<Book> dateColumn = new TextColumn<Book>() {
            @Override
            public String getValue(Book object) {
                return MainPanel.PUBLISH_DATE_FORMAT.format(object.getPublishDate());
            }
        };
        dateColumn.setSortable(true);
        Header<String> dateHeader = new Header<String>(new ClickableTextCell()) {
            @Override
            public String getValue() {
                return "Publ Date";
            }
        };
        dateHeader.setUpdater(value -> MainPanel.sortLibrary(2));
        table.addColumn(dateColumn, dateHeader);

        TextColumn<Book> pageCount = new TextColumn<Book>() {
            @Override
            public String getValue(Book object) {
                return Integer.toString(object.getPageNumber());
            }
        };
        pageCount.setSortable(true);
        Header<String> pageHeader = new Header<String>(new ClickableTextCell()) {
            @Override
            public String getValue() {
                return "Pages";
            }
        };
        pageHeader.setUpdater(value -> MainPanel.sortLibrary(3));
        table.addColumn(pageCount, pageHeader);

        TextColumn<Book> updateDateColumn = new TextColumn<Book>() {
            @Override
            public String getValue(Book object) {
                return MainPanel.UPDATE_DATE_FORMAT.format(object.getUpdateDate());
            }
        };
        updateDateColumn.setSortable(true);
        Header<String> updateDateHeader = new Header<String>(new ClickableTextCell()) {
            @Override
            public String getValue() {
                return "Update Date";
            }
        };
        updateDateHeader.setUpdater(value -> MainPanel.sortLibrary(4));
        table.addColumn(updateDateColumn, updateDateHeader);

        final SingleSelectionModel<Book> selectionModel = new SingleSelectionModel<>();
        table.setSelectionModel(selectionModel);
        table.addDomHandler(event -> {
            Book selected = selectionModel.getSelectedObject();
            if (selected != null && table.getKeyboardSelectedRow()>=0) {
                new EditDialog(selected, MainPanel.getBookListDataProvider().getList(), table);
            }

        }, DoubleClickEvent.getType());

        return table;
    }

}