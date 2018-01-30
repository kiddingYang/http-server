package com.gs.server.http;

public class ServerConfig {

    public static final int DEFAULT_PORT = 8888;

    private final int port;

    public ServerConfig(int port) {
        this.port = port;
    }

    public ServerConfig() {
        this.port = DEFAULT_PORT;
    }

    public int getPort() {
        return port;
    }
}
