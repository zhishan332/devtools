package com.yermoon.service;

import com.yermoon.vo.HttpRespons;

/**
 * Http工具集业务处理
 *
 * @author wangqing
 * @since 14-4-16 下午1:42
 */
public interface HttpService {
    /**
     * 发送Post请求
     *
     * @param url   请求地址
     * @param param 请求参数
     * @return 响应对象，包含状态码等信息
     * @throws Exception
     */
    public HttpRespons doPost(String url, String param) throws Exception;

    /**
     * 发送Post请求
     *
     * @param url   请求地址
     * @param param 请求参数
     * @return 响应对象，包含状态码等信息
     * @throws Exception
     */
    public HttpRespons doGet(String url, String param) throws Exception;

}
