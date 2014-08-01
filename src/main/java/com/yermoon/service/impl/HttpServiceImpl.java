package com.yermoon.service.impl;

import com.yermoon.service.HttpService;
import com.yermoon.utils.HttpRequester;
import com.yermoon.utils.MyAssert;
import com.yermoon.vo.HttpRespons;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Http工具集业务处理默认实现类
 *
 * @author wangqing
 * @since 14-4-16 下午1:42
 */
@Service("httpService")
public class HttpServiceImpl implements HttpService {
    @Resource
    private HttpRequester httpRequester;

    @Override
    public HttpRespons doPost(String url, String param) throws Exception {
        MyAssert.notBlank(url);
        return httpRequester.sendPost(url, param);
    }

    @Override
    public HttpRespons doGet(String url, String param) throws Exception {
        MyAssert.notBlank(url);
        return httpRequester.sendGet(url, param);
    }
}
