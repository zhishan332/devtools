package com.yermoon.utils;

import com.yermoon.vo.DataBaseType;

import java.sql.Types;

/**
 * To change this template use File | Settings | File Templates.
 *
 * @author wangqing
 * @since 14-4-18 上午10:05
 */
public class ConvertUtils {


    public static String getJavaTypeFromSqlType(int sqlType) {
        String jtype;
        switch (sqlType) {
            case Types.BIT:
                jtype = "Boolean";
                break;
            case Types.ARRAY:
                jtype = "Array";
                break;
            case Types.BINARY:
                jtype = "byte[]";
                break;
            case Types.BLOB:
                jtype = "Blob";
                break;
            case Types.CLOB:
                jtype = "Clob";
                break;
            case Types.LONGVARCHAR:
                jtype = "String";
                break;
            case Types.BOOLEAN:
                jtype = "Boolean";
                break;
            case Types.SMALLINT:
                jtype = "Short";
                break;
            case Types.REAL:
                jtype = "Float";
                break;
            case Types.VARCHAR:
                jtype = "String";
                break;
            case Types.BIGINT:
                jtype = "Long";
                break;
            case Types.CHAR:
                jtype = "String";
                break;
            case Types.DATE:
                jtype = "Date";
                break;
            case Types.TIMESTAMP:
                jtype = "Timestamp";
                break;
            case Types.DECIMAL:
                jtype = "BigDecimal";
                break;
            case Types.DOUBLE:
                jtype = "Double";
                break;
            case Types.FLOAT:
                jtype = "Double";
                break;
            case Types.INTEGER:
                jtype = "Integer";
                break;
            case Types.NCHAR:
                jtype = "String";
                break;
            case Types.NUMERIC:
                jtype = "BigDecimal";
                break;
            case Types.NVARCHAR:
                jtype = "String";
                break;
            case Types.TIME:
                jtype = "Time";
                break;
            case Types.TINYINT:
                jtype = "Integer";
                break;
            default:
                jtype = "String";
        }
        return jtype;
    }

    public static DataBaseType getDbTypeByCode(int code) {
        for (DataBaseType dbt : DataBaseType.values()) {
            if (dbt.getTypeCode() == code) return dbt;
        }
        return null;
    }
}
