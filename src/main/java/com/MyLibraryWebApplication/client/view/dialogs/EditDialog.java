package com.MyLibraryWebApplication.client.view.dialogs;

import com.MyLibraryWebApplication.client.services.UpdateBookService;
import com.MyLibraryWebApplication.client.services.UpdateBookServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.MyLibraryWebApplication.client.MainPanel;
import com.MyLibraryWebApplication.shared.Book;
import com.MyLibraryWebApplication.client.view.textFields.validators.DateValidator;
import com.MyLibraryWebApplication.client.view.textFields.validators.NotEmptyValidator;
import com.MyLibraryWebApplication.client.view.textFields.validators.NumberValidator;
import com.MyLibraryWebApplication.client.view.textFields.ValidationTextField;
import com.google.gwt.view.client.ListDataProvider;

import java.util.Date;

public class EditDialog extends DialogBox{
    private UpdateBookServiceAsync UpdateBookService = GWT.create(UpdateBookService.class);

    private ValidationTextField nameBox = new ValidationTextField();
    private ValidationTextField authorBox = new ValidationTextField();
    private ValidationTextField pageBox = new ValidationTextField();
    private ValidationTextField publicDateBox = new ValidationTextField();

    public EditDialog(Book book, ListDataProvider<Book> dataProvider, CellTable<Book> table) {
        int selectedRow = dataProvider.getList().indexOf(book);
        setPopupPosition(Window.getClientWidth()/2-130, Window.getClientHeight()/2-300);
        setText("Editing " +book.getName());
        setAnimationEnabled(true);

        nameBox.setText(book.getName());
        nameBox.addValidator(new NotEmptyValidator());
        authorBox.setText(book.getAuthor());
        authorBox.addValidator(new NotEmptyValidator());
        pageBox.setText(Integer.toString(book.getPageNumber()));
        pageBox.addValidator(new NumberValidator());
        publicDateBox.setText(MainPanel.PUBLISH_DATE_FORMAT.format(book.getPublishDate()));
        publicDateBox.addValidator(new DateValidator());

        Button ok = new Button("OK");
        ok.addClickHandler(event -> {
            if(pageBox.validate() && publicDateBox.validate() && nameBox.validate() && authorBox.validate()) {
                dataProvider.getList().get(selectedRow).setName(nameBox.getValue());
                dataProvider.getList().get(selectedRow).setAuthor(authorBox.getValue());
                dataProvider.getList().get(selectedRow).setPageNumber(Integer.parseInt(pageBox.getValue()));
                dataProvider.getList().get(selectedRow).setPublishDate(MainPanel.PUBLISH_DATE_FORMAT.parse(publicDateBox.getText()));
                dataProvider.getList().get(selectedRow).setUpdateDate(new Date());
                table.getSelectionModel().setSelected(null,false);
                updateBook(dataProvider.getList().get(selectedRow));
                hide();
                table.redraw();
            }
        });

        Button cancel = new Button("Cancel");
        cancel.addClickHandler(event -> hide());

        FlexTable layout = new FlexTable();
        layout.setCellSpacing(6);
        FlexTable.FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

        layout.setHTML(0, 0, "Editing");
        cellFormatter.setColSpan(0, 0, 2);
        cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

        layout.setHTML(1, 0, "Name:");
        layout.setWidget(1, 1, nameBox);
        layout.setHTML(2, 0, "Author:");
        layout.setWidget(2, 1, authorBox);
        layout.setHTML(3, 0, "Pages:");
        layout.setWidget(3, 1, pageBox);
        layout.setHTML(4, 0, "Publ date:");
        layout.setWidget(4, 1, publicDateBox);

        HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.setSpacing(6);
        hPanel.add(ok);
        hPanel.add(cancel);
        layout.setWidget(5,0, hPanel);
        cellFormatter.setColSpan(5, 0, 2);
        cellFormatter.setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_CENTER);

        setWidget(layout);
        show();
    }

    private void updateBook(Book book){
        if (UpdateBookService == null) {
            UpdateBookService = GWT.create(UpdateBookService.class);
        }
        AsyncCallback<Void> callback = new AsyncCallback<Void>() {
            public void onFailure(Throwable caught) {
                Window.alert("updateBook doesnt work!");
                caught.printStackTrace();
            }

            public void onSuccess(Void result) {
                Window.alert("Updated");
            }
        };
        // Make the call to the stock price service.
        UpdateBookService.updateBook(book, callback);
    }
}
