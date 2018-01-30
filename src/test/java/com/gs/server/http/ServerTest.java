package com.gs.server.http;

import com.gs.server.http.Util.IoUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class ServerTest {


    private static Server server;

    private static final int time_out = 500;


    private static Logger logger = LoggerFactory.getLogger(ServerTest.class);

    @BeforeClass
    public static void init() {
        ServerConfig serverConfig = new ServerConfig();
        server = ServerFactory.getServer(serverConfig);
    }


    @Test
    public void testServerAcceptRequest() {

        if (server.getStatus().equals(ServerStatus.STOPED)) {

            new Thread(() -> server.start()).start();


            while (server.getStatus().equals(ServerStatus.STOPED)) {
                try {
                    Thread.sleep(time_out);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        Socket socket = new Socket();
        SocketAddress endpoint = new InetSocketAddress("localhost",
                ServerConfig.DEFAULT_PORT);

        try {
            // 试图发送请求到服务器，超时时间为TIMEOUT
            socket.connect(endpoint, time_out);
            assertTrue("服务器启动后，能接受请求", socket.isConnected());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            IoUtils.closeQuietly(socket);
        }

    }


    @AfterClass
    public static void destroy() {
        server.stop();
    }
}
