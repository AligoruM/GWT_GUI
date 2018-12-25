package com.MyLibraryWebApplication.client.view.textFields.validators;

public class NotEmptyValidator extends Validator {
    public boolean validate(String value) {
        if (!value.isEmpty()) {
            errorMessage = "";
            return true;
        } else {
            errorMessage = "Cannot be empty";
            return false;
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
