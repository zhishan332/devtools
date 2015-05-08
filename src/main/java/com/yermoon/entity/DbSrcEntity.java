package com.yermoon.entity;

import java.io.Serializable;

/**
 * 数据源
 * 对应数据表：mybatis_db
 *
 * @author wangqing
 * @since 14-4-17 下午3:37
 */
public class DbSrcEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private Integer dbType;
    private String jdbcUrl;
    private String userName;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
