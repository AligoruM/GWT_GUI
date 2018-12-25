package com.MyLibraryWebApplication.client.view.dialogs;

import com.MyLibraryWebApplication.client.MainPanel;
import com.MyLibraryWebApplication.shared.Book;
import com.MyLibraryWebApplication.client.view.textFields.ValidationTextField;
import com.MyLibraryWebApplication.client.view.textFields.validators.DateValidator;
import com.MyLibraryWebApplication.client.view.textFields.validators.NotEmptyValidator;
import com.MyLibraryWebApplication.client.view.textFields.validators.NumberValidator;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.view.client.ListDataProvider;

import java.util.Date;

public class AddDialog {
    interface AddDialogUiBinder extends UiBinder<HTMLPanel, AddDialog> {
    }

    @UiField
    ValidationTextField nameBox;
    @UiField
    ValidationTextField authorBox;
    @UiField
    ValidationTextField pagesBox;
    @UiField
    ValidationTextField dateBox;
    @UiField
    Button submitButton;
    @UiField
    Button cancelButton;
    @UiField
    DialogBox dialogPanel;

    private static AddDialogUiBinder ourUiBinder = GWT.create(AddDialogUiBinder.class);
    private ListDataProvider<Book> listDataProvider;

    public AddDialog(ListDataProvider<Book> list) {
        HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
        pagesBox.addValidator(new NumberValidator());
        dateBox.addValidator(new DateValidator());
        nameBox.addValidator(new NotEmptyValidator());
        authorBox.addValidator(new NotEmptyValidator());
        listDataProvider = list;
        dialogPanel.setAnimationEnabled(true);
        dialogPanel.setPopupPosition(Window.getClientWidth()/2-130, Window.getClientHeight()/2-300);
        dialogPanel.show();
    }
    @UiHandler("cancelButton")
    void doClickCancel(ClickEvent click){
        dialogPanel.hide();
    }
    @UiHandler("submitButton")
    void doClickSubmit(ClickEvent click){
        if(nameBox.validate() && authorBox.validate() && pagesBox.validate() && dateBox.validate()){
            Book book = new Book(1, authorBox.getValue(), nameBox.getValue(), Integer.parseInt(pagesBox.getValue()),
                    MainPanel.PUBLISH_DATE_FORMAT.parse(dateBox.getText()), new Date());
            listDataProvider.getList().add(book);
            MainPanel.uploadLibrary(listDataProvider.getList());
            dialogPanel.hide();
        }
    }
}