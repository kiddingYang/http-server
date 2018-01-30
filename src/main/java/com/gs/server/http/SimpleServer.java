package com.gs.server.http;

import com.gs.server.http.Util.IoUtils;
import sun.nio.ch.IOUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer implements Server {


    private ServerStatus serverStatus = ServerStatus.STOPED;
    private final int port;
    private ServerSocket serverSocket;

    public SimpleServer(ServerConfig serverConfig) {
        this.port = serverConfig.getPort();
    }

    public void start() {

        Socket socket = null;
        try {
            this.serverStatus  = ServerStatus.STARTED;
            serverSocket = new ServerSocket(port);
            System.out.println("start server ...");
            for (;;) {
                socket = serverSocket.accept();
                System.out.println(socket.getInetAddress() + ":" + socket.getPort());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtils.closeQuietly(socket);
        }


    }

    public void stop() {

        if(serverSocket != null ) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.serverStatus = ServerStatus.STOPED;
        System.out.println("stop server ...");
    }

    @Override
    public ServerStatus getStatus() {
        return serverStatus;
    }

    public int getPort() {
        return port;
    }
}
