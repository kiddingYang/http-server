package com.gs.server.http.Connector;

import com.gs.server.http.Connector.Exception.ConnectorException;

public abstract class Connector implements LifeCycle {

    @Override
    public void start() {
        init();
        acceptConnect();

    }

    protected abstract void init() throws ConnectorException;

    protected abstract void acceptConnect() throws ConnectorException;

}
