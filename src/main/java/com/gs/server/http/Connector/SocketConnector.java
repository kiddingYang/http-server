package com.gs.server.http.Connector;

import com.gs.server.http.Connector.Exception.ConnectorException;
import com.gs.server.http.ServerStatus;
import com.gs.server.http.Util.IoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketConnector extends Connector {

    private static final Logger logger = LoggerFactory.getLogger(SocketConnector.class);
    private ServerSocket serverSocket;


    private volatile boolean start = false;
    private final int port ;

    public SocketConnector(int port) {
        this.port = port;
    }

    @Override
    protected void init() throws ConnectorException {
        try {
            this.serverSocket = new ServerSocket(port);
            this.start = true;
            logger.info("init connector ..... ");
        } catch (IOException e) {
            throw new ConnectorException(e);
        }

    }

    @Override
    protected void acceptConnect() throws ConnectorException {

        new Thread(() -> {

            while(start) {

                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                    logger.info("新增连接... {} , port :{} ",socket.getInetAddress(),socket.getPort());
                } catch (IOException e) {
                   throw new ConnectorException(e);
                } finally {
                    IoUtils.closeQuietly(socket);
                }
            }
        }).start();

    }

    @Override
    public void stop() {
        this.start = false;
        IoUtils.closeQuietly(serverSocket);
    }
}
