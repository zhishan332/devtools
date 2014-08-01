package com.yermoon.vo;

/**
 * 字段信息
 *
 * @author wangqing
 * @since 14-4-17 下午2:03
 */
public class JColumn {
    private String columnName;
    private String javaName;
    private boolean isPkColumn;
    private String javaType;
    private String jdbcType;
    private String remark;

    public String getJavaName() {
        return javaName;
    }

    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    public boolean isPkColumn() {
        return isPkColumn;
    }

    public void setPkColumn(boolean pkColumn) {
        isPkColumn = pkColumn;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
