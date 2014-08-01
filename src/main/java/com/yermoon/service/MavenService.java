package com.yermoon.service;

import com.yermoon.dto.Maven;

/**
 * Maven工具集业务处理
 *
 * @author wangqing
 * @since 14-4-16 下午1:42
 */
public interface MavenService {
    /**
     * 导入本地Jar
     *
     * @param maven 待导入Jar包的Maven属性
     * @param path  待导入Jar包路径
     * @throws Exception
     */
    public String implLocalJar(Maven maven, String path) throws Exception;
}
