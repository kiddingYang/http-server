package com.gs.server.http;

public class ServerFactory {

    public static Server getServer(ServerConfig serverConfig) {
        return new SimpleServer(serverConfig);
    }

}
