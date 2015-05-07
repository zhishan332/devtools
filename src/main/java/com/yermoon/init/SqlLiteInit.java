package com.yermoon.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * sql lite初始化
 *
 * @author wangqing
 * @since 15-5-7 下午5:22
 */
@Component
public class SqlLiteInit {
    private final Logger log = LoggerFactory.getLogger(SqlLiteInit.class);
    public static String DBPATH = "";

    @PostConstruct
    public void init() {
        log.info("初始化存储环境>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        DBPATH = new File("C:\\code\\idea\\mycode\\sqlite\\tool.s3db").getAbsolutePath();
        System.out.println("DBPATH"+DBPATH);
        File ff = new File(DBPATH);
        if (ff.exists()) return;
    }


}
