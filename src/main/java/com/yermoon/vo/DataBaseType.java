package com.yermoon.vo;

/**
 * 数据库类型
 *
 * @author wangqing
 * @since 14-4-17 上午11:30
 */
public enum DataBaseType {
    Mysql(1), Oracle(2), SqlServer(3), Db2(4), Other(100);
    private int typeCode; //数据库代码

    DataBaseType(int typeCode) {
        this.typeCode = typeCode;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

}
