package com.yermoon.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 数据源
 *
 * @author wangqing
 * @since 14-4-17 下午3:37
 */
public class DbSrcDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer dbType;
    private String jdbcUrl;
    private String userName;
    private String password;
    private List<String> tables;

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }

    public Integer getDbType() {
        return dbType;
    }

    public void setDbType(Integer dbType) {
        this.dbType = dbType;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
