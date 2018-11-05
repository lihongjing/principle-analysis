package com.online.springioc.logger;

import org.slf4j.LoggerFactory;

/**
 * Created by lihongjing on 2018/8/7.
 */
public class LoggerUtil {
    private static org.slf4j.Logger loggerSlf4j;

    private LoggerUtil(){

    }

    public static org.slf4j.Logger getLogger(Class zz){
        if (loggerSlf4j == null){
            loadLoggerSlf4j(zz);
        }
        return loggerSlf4j;
    }

    private static void loadLoggerSlf4j(Class zz){
        loggerSlf4j = LoggerFactory.getLogger(zz);
    }
}
