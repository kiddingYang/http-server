package com.gs.server.http.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

public class IoUtils {
    private static Logger logger = LoggerFactory.getLogger(IoUtils.class);

    /**
     * 安静地关闭，不抛出异常
     * @param closeable
     */
    public static void closeQuietly(Closeable closeable) {
        if(closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
        }
    }
}
