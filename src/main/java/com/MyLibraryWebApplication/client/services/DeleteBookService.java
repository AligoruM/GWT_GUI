package com.MyLibraryWebApplication.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;

@RemoteServiceRelativePath("DeleteBook")
public interface DeleteBookService extends RemoteService {
    void deleteBook(int id);
    /**
     * Utility/Convenience class.
     * Use DeleteBookService.App.getInstance() to access static instance of DeleteBookAsync
     */
}
