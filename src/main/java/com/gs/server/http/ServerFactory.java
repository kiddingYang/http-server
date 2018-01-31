package com.gs.server.http;

import com.gs.server.http.Connector.Connector;

import java.util.ArrayList;
import java.util.List;

public class ServerFactory {



    public static Server getServer(ServerConfig serverConfig) {

        return new SimpleServer(serverConfig);
    }

}
