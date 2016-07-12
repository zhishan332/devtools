//package com.yermoon.init;
//
//import com.ohdb.OhDataBase;
//import com.ohdb.OhDataBaseManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
///**
// * sql lite初始化
// *
// * @author wangqing
// * @since 15-5-7 下午5:22
// */
//@Component
//public class OdbInit {
//    private final Logger log = LoggerFactory.getLogger(OdbInit.class);
//
//    @PostConstruct
//    public void init() {
//        OhDataBaseManager.getInstance().setOhDataBase(new OhDataBase("devtools"));
//        OhDataBaseManager.getInstance().init();
//        log.info("ohdb初始化成功>>>>>>>>>>>>>>>>>>>>>>>>>>");
//    }
//
//}
