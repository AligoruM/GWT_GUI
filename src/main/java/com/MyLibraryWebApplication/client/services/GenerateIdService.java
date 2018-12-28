package com.MyLibraryWebApplication.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;

@RemoteServiceRelativePath("generateIdService")
public interface GenerateIdService extends RemoteService {
    Integer getId();
}
