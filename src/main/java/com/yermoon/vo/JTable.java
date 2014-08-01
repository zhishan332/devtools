package com.yermoon.vo;

import java.util.Map;

/**
 * 表信息
 *
 * @author wangqing
 * @since 14-4-17 下午2:02
 */
public class JTable {
    private String tableName;
    private String pkColumnName;
    private String pkJavaName;
    private Map<String, JColumn> jColumnMap;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, JColumn> getjColumnMap() {
        return jColumnMap;
    }

    public void setjColumnMap(Map<String, JColumn> jColumnMap) {
        this.jColumnMap = jColumnMap;
    }

    public String getPkColumnName() {
        return pkColumnName;
    }

    public void setPkColumnName(String pkColumnName) {
        this.pkColumnName = pkColumnName;
    }

    public String getPkJavaName() {
        return pkJavaName;
    }

    public void setPkJavaName(String pkJavaName) {
        this.pkJavaName = pkJavaName;
    }

}
