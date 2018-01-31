package com.gs.server.http;

public class SocketConnectorConfig {

    private final int port;

    public SocketConnectorConfig(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }
}
