package com.altamob.offertest.util;

import java.io.Closeable;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author WangPengfei
 * @date 2018/7/2
 */
public class IoUtils {

    private static final Logger logger = LoggerFactory.getLogger(IoUtils.class);

    public static void safeClose(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void safeClose(AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
