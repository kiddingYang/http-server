package com.gs.server.http;

public interface Server {

    void start();

    void stop();

    ServerStatus getStatus();

}
