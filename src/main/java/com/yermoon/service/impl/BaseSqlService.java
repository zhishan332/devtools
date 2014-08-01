package com.yermoon.service.impl;

import com.yermoon.service.SqlService;

import java.util.ArrayList;
import java.util.List;

/**
 * To change this template use File | Settings | File Templates.
 *
 * @author wangqing
 * @since 14-4-22 下午1:49
 */
public abstract class BaseSqlService implements SqlService {
    /**
     * 获取插入数据的SQL
     *
     * @return 标准插入SQL和方言插入SQL
     */
    public List<String> getInsertSql() {
        List<String> sql = new ArrayList<String>();
        sql.add("insert into table1(field1,field2) values(value1,value2)");
        return sql;
    }

    /**
     * 获取删除数据的SQL
     *
     * @return 标准删除SQL和方言删除SQL
     */
    public List<String> getDeleteSql() {
        List<String> sql = new ArrayList<String>();
        sql.add("delete from table1 where 范围 ");
        return sql;
    }

    /**
     * 获取更新数据的SQL
     *
     * @return 标准更新SQL和方言更新SQL
     */
    public List<String> getUpdateSql() {
        List<String> sql = new ArrayList<String>();
        sql.add("update table1 set field1=value1 where 范围 ");
        return sql;
    }

    /**
     * 获取查询数据的SQL
     *
     * @return 标准查询SQL和方言查询SQL
     */
    public List<String> getQuerySql() {
        List<String> sql = new ArrayList<String>();
        sql.add("select * from table as t where t.id=值");
        return sql;
    }
}
