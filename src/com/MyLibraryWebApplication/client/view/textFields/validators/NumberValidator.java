package com.MyLibraryWebApplication.client.view.textFields.validators;

public class NumberValidator extends Validator {

    public boolean validate(String value) {
        if (value.matches("[0-9]+")) {
            errorMessage = "";
            return true;
        } else {
            errorMessage = "Enter positive integer";
            return false;
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
