package com.yermoon.service.impl;


import java.util.ArrayList;
import java.util.List;

/**
 * To change this template use File | Settings | File Templates.
 *
 * @author wangqing
 * @since 14-4-22 上午11:31
 */
public class MysqlSqlServiceImpl extends BaseSqlService {

    /**
     * 获取添加字段的SQL
     *
     * @return 标准添加字段SQL和方言添加字段SQL
     */
    @Override
    public List<String> getAddColumnSql() {
        List<String> sql = new ArrayList<String>();
        sql.add("alter table food add foodname varchar(100) not null default 'other'");
        return sql;
    }

    /**
     * 获取删除字段的SQL
     *
     * @return 标准删除字段SQL和方言删除字段SQL
     */
    @Override
    public List<String> getDelColumnSql() {
        List<String> sql = new ArrayList<String>();
        sql.add("alter table student drop column  age ");
        return sql;
    }

    /**
     * 获取更新字段的SQL
     *
     * @return 标准更新字段SQL和方言更新字段SQL
     */
    @Override
    public List<String> getUpdateColumnSql() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * 获取新增表结构的SQL
     *
     * @return 标准新增表结构SQL和方言新增表结构SQL
     */
    @Override
    public List<String> getAddTableSql() {
        List<String> sql = new ArrayList<String>();
        sql.add("create table food\n" +
                "(\n" +
                "  id int  not null auto_increment,\n" +
                "  remark varchar(20) not null unique,\n" +
                "  primary key(id)         \n" +
                ")engine=innodb default charset=utf8;");
        return null;
    }

    /**
     * 获取删除表的SQL
     *
     * @return 标准删除表SQL和方言删除表SQL
     */
    @Override
    public List<String> getDelTableSql() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * 获取更新表的SQL
     *
     * @return 标准更新表SQL和方言更新表SQL
     */
    @Override
    public List<String> getUpdateTableSql() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * 获取新增Sequence的SQL
     *
     * @return 标准新增Sequence SQL和方言 Sequence SQL
     */
    @Override
    public List<String> getAddSeqSql() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * 获取删除Sequence的SQL
     *
     * @return 标准 Sequence 删除sql 和方言 Sequence 删除SQL
     */
    @Override
    public List<String> getDeleteSeqSql() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * 获取新增索引的SQL
     *
     * @return 标准新增索引SQL和方言新增索引SQL
     */
    @Override
    public List<String> getAddIndexSql() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * 获取删除索引的SQL
     *
     * @return 标准删除索引SQL和方言删除索引SQL
     */
    @Override
    public List<String> getDeleteIndexSql() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * 获取查询索引的SQL
     *
     * @return 标准查询索引SQL和方言查询索引SQL
     */
    @Override
    public List<String> getQueryIndexSql() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * 获取导出Table数据SQL
     *
     * @return 标准导出Table数据SQL和方言导出Table数据SQL
     */
    @Override
    public List<String> getExpTableSql() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
