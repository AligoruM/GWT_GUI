package com.MyLibraryWebApplication.client.view.textFields.validators;

public class DateValidator extends Validator{
    public boolean validate(String value) {
        if (value.matches("\\d{2}-\\d{2}-\\d{4}")) {
            errorMessage = "";
            return true;
        } else {
            errorMessage = "Enter date with format DD-MM-YYYY";
            return false;
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
