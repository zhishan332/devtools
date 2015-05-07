package com.yermoon.service;

import com.yermoon.entity.DbSrcEntity;

import java.util.List;

/**
 * Mybatis工具集的业务处理
 *
 * @author wangqing
 * @since 14-4-16 下午2:15
 */
public interface MybatisService {
    /**
     * 创建新的数据源
     *
     * @param dbSrcEntity 数据源
     * @throws Exception 数据库连接异常时
     */
    public void createDbSrc(DbSrcEntity dbSrcEntity) throws Exception;

    /**
     * 查询所有的数据源
     *
     * @return 数据源列表
     * @throws Exception 数据库连接异常时
     */
    public List<DbSrcEntity> findAllDbSrc() throws Exception;

    /**
     * 删除指定数据源
     *
     * @param id 主键
     * @throws Exception 数据库连接异常时
     */
    public void deleteDbSrc(int id) throws Exception;

    /**
     * 根据数据源查询表名列表
     *
     * @param dbsrcId
     * @return
     * @throws Exception
     */
    public List<String> findAllTables(int dbsrcId) throws Exception;

    /**
     * 查询单个数据源
     *
     * @param dbsrcId
     * @return
     * @throws Exception
     */
    public DbSrcEntity getDbSrcEntity(int dbsrcId) throws Exception;

    /**
     * 生成XML文件
     *
     * @param dataBase    数据源
     * @param table       表名
     * @param path        生成路径 默认C：\\
     * @throws Exception
     */
    public void createConfig(DbSrcEntity dataBase, String table, String path) throws Exception;

    /**
     * 生成XML文件
     *
     * @param dataBase    数据源
     * @param path        生成路径 默认C：\\
     * @throws Exception
     */
    public void createConfig(DbSrcEntity dataBase, String path) throws Exception;
}
