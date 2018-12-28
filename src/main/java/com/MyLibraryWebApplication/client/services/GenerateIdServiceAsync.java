package com.MyLibraryWebApplication.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GenerateIdServiceAsync {
    void getId(AsyncCallback<Integer> async);
}
