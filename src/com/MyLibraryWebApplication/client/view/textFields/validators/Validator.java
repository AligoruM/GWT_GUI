package com.MyLibraryWebApplication.client.view.textFields.validators;

public abstract class Validator {

    public String errorMessage;

    public abstract boolean validate(String value);

    public abstract String getErrorMessage();
}
