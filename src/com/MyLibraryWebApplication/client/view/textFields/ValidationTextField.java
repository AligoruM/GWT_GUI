package com.MyLibraryWebApplication.client.view.textFields;

import com.google.gwt.user.client.ui.TextBox;
import com.MyLibraryWebApplication.client.view.textFields.validators.Validator;

import java.util.ArrayList;

public class ValidationTextField extends TextBox {
    private static final String TEXTBOX_VALIDATION_ERROR_STYLE = "error-text-box";
    private String errorMessage = "";
    private ArrayList<Validator> validators = new ArrayList<Validator>();

    public ValidationTextField() {
    }

    public void addValidator(Validator validator) {
        validators.add(validator);
    }

    public boolean validate() {
        boolean validationResult = true;
        for (Validator validator : validators) {
            validationResult = validator.validate(getValue().trim());
            if (!validationResult) {
                errorMessage = validator.getErrorMessage();
                break;
            }
            errorMessage = validator.getErrorMessage();
        }
        setErrorStyles(validationResult);
        return validationResult;
    }

    private void setErrorStyles(boolean validationResult) {
        if (validationResult) {
            removeStyleName(TEXTBOX_VALIDATION_ERROR_STYLE);
            setTitle("");
        } else {
            addStyleName(TEXTBOX_VALIDATION_ERROR_STYLE);
            setTitle(errorMessage);
        }
    }

    @Override
    public void setValue(String s) {
        removeStyleDependentName(TEXTBOX_VALIDATION_ERROR_STYLE);
        super.setValue(s);
    }

    @Override
    public String getValue() {
        return super.getValue().trim();
    }
}

