package com.gs.server.http.Connector.Exception;

public class ConnectorException extends RuntimeException {

    public ConnectorException() {
    }

    public ConnectorException(String message) {
        super(message);
    }

    public ConnectorException(Throwable cause) {
        super(cause);
    }
}
