package com.yermoon.vo;

/**
 * 数据库基本属性
 *
 * @author wangqing
 * @since 14-4-17 上午11:42
 */
public class DataBase {
    private DataBaseType dataBaseType;
    private String jdbcUrl;
    private String userName;
    private String password;

    /**
     * 获取数据库类型
     *
     * @return 数据库类型
     */
    public DataBaseType getDataBaseType() {
        return dataBaseType;
    }

    /**
     * 设置数据库类型
     *
     * @param dataBaseType 数据库类型
     */
    public void setDataBaseType(DataBaseType dataBaseType) {
        this.dataBaseType = dataBaseType;
    }

    /**
     * 获取JDBCURL
     *
     * @return jdbcurl
     */
    public String getJdbcUrl() {
        return jdbcUrl;
    }

    /**设置JdbcUrl
     *
     * @param jdbcUrl 数据库连接URL
     */
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
