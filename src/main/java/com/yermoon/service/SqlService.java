package com.yermoon.service;

import java.util.List;

/**
 * SQL快捷操作生产语句
 * 部分获取SQL方法可能无法返回标准SQL
 *
 * @author wangqing
 * @since 14-4-22 上午11:07
 */
public interface SqlService {
    /**
     * 获取插入数据的SQL
     *
     * @return 标准插入SQL和方言插入SQL
     */
    public List<String> getInsertSql();

    /**
     * 获取删除数据的SQL
     *
     * @return 标准删除SQL和方言删除SQL
     */
    public List<String> getDeleteSql();

    /**
     * 获取更新数据的SQL
     *
     * @return 标准更新SQL和方言更新SQL
     */
    public List<String> getUpdateSql();

    /**
     * 获取查询数据的SQL
     *
     * @return 标准查询SQL和方言查询SQL
     */
    public List<String> getQuerySql();

    /**
     * 获取添加字段的SQL
     *
     * @return 标准添加字段SQL和方言添加字段SQL
     */
    public List<String> getAddColumnSql();

    /**
     * 获取删除字段的SQL
     *
     * @return 标准删除字段SQL和方言删除字段SQL
     */
    public List<String> getDelColumnSql();

    /**
     * 获取更新字段的SQL
     *
     * @return 标准更新字段SQL和方言更新字段SQL
     */
    public List<String> getUpdateColumnSql();

    /**
     * 获取新增表结构的SQL
     *
     * @return 标准新增表结构SQL和方言新增表结构SQL
     */
    public List<String> getAddTableSql();

    /**
     * 获取删除表的SQL
     *
     * @return 标准删除表SQL和方言删除表SQL
     */
    public List<String> getDelTableSql();

    /**
     * 获取更新表的SQL
     *
     * @return 标准更新表SQL和方言更新表SQL
     */
    public List<String> getUpdateTableSql();

    /**
     * 获取新增Sequence的SQL
     *
     * @return 标准新增Sequence SQL和方言 Sequence SQL
     */
    public List<String> getAddSeqSql();

    /**
     * 获取删除Sequence的SQL
     *
     * @return 标准 Sequence 删除sql 和方言 Sequence 删除SQL
     */
    public List<String> getDeleteSeqSql();

    /**
     * 获取新增索引的SQL
     *
     * @return 标准新增索引SQL和方言新增索引SQL
     */
    public List<String> getAddIndexSql();

    /**
     * 获取删除索引的SQL
     *
     * @return 标准删除索引SQL和方言删除索引SQL
     */
    public List<String> getDeleteIndexSql();

    /**
     * 获取查询索引的SQL
     *
     * @return 标准查询索引SQL和方言查询索引SQL
     */
    public List<String> getQueryIndexSql();

    /**
     * 获取导出Table数据SQL
     *
     * @return 标准导出Table数据SQL和方言导出Table数据SQL
     */
    public List<String> getExpTableSql();
}
