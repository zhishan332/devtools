package com.yermoon.service.impl;

import com.yermoon.service.DataBaseService;
import com.yermoon.utils.CamelCaseUtils;
import com.yermoon.vo.DataBase;
import com.yermoon.vo.DataBaseType;
import com.yermoon.vo.JColumn;
import com.yermoon.vo.JTable;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.yermoon.utils.ConvertUtils.getJavaTypeFromSqlType;
/**
 * To change this template use File | Settings | File Templates.
 *
 * @author wangqing
 * @since 14-4-18 上午10:03
 */
@Service("dataBaseService")
public class DataBaseServiceImpl implements DataBaseService {

    @Override
    public JTable getTable(DataBase dataBase, String tableName) throws Exception {
        loadDriver(dataBase.getDataBaseType());
        Connection conn = DriverManager.getConnection(dataBase.getJdbcUrl(),dataBase.getUserName(),dataBase.getPassword());
        String sql = "select * from " + tableName;
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.execute();
        ResultSetMetaData mata = stat.getMetaData();
        JTable jTable = new JTable();
        jTable.setTableName(tableName);
        Map<String, JColumn> jColumnMap = new LinkedHashMap<String, JColumn>();
        for (int i = 1; i <= mata.getColumnCount(); i++) {
            JColumn jColumn = new JColumn();
            jColumn.setColumnName(mata.getColumnName(i).toLowerCase());
            jColumn.setJdbcType(mata.getColumnTypeName(i));
            jColumn.setJavaType(getJavaTypeFromSqlType(mata.getColumnType(i)));
            jColumn.setJavaName(CamelCaseUtils.toCamelCase(mata.getColumnName(i).toLowerCase()));
            jColumnMap.put(mata.getColumnName(i).toLowerCase(), jColumn);
        }
        jTable.setjColumnMap(jColumnMap);
        DatabaseMetaData dbMeta = conn.getMetaData();
        // 没有catalog或schema，都设置为null
        ResultSet pkRSet = dbMeta.getPrimaryKeys(null, null, tableName);
        while (pkRSet.next()) {
            jTable.setPkColumnName(pkRSet.getString(4).toLowerCase());
            jTable.setPkJavaName(CamelCaseUtils.toCamelCase(pkRSet.getString(4)));
            jTable.getjColumnMap().get(pkRSet.getString(4).toLowerCase()).setPkColumn(true);
            break;
        }
        //获取tableName表列信息 ，获取备注还需要继续实现，目前不实现了
        ResultSet columnSet = dbMeta.getColumns(null, "%", tableName, "%");
        while (columnSet.next()) {
            jColumnMap.get(columnSet.getString("COLUMN_NAME").toLowerCase()).setRemark(columnSet.getString("REMARKS"));
        }
        stat.close();
        conn.close();
        return jTable;
    }

    @Override
    public List<String> getTableNames(DataBase dataBase) throws Exception {
        loadDriver(dataBase.getDataBaseType());
        Connection conn = DriverManager.getConnection(dataBase.getJdbcUrl(), dataBase.getUserName(), dataBase.getPassword());
        DatabaseMetaData dbMetaData = conn.getMetaData();
        String schem="%";
        if(dataBase.getDataBaseType().equals(DataBaseType.Oracle)){
            schem=dataBase.getUserName().toUpperCase();
        }
        ResultSet tableRet = dbMetaData.getTables(null,schem,"%",new String[]{"TABLE"});
        List<String> tables=new ArrayList<String>();
        while(tableRet.next()) {
            tables.add(tableRet.getString("TABLE_NAME"));
        }
        tableRet.close();
        conn.close();
        return tables;
    }

    private void loadDriver(DataBaseType dataBaseType) throws ClassNotFoundException {
        switch (dataBaseType){
            case Mysql:
                Class.forName("com.mysql.jdbc.Driver");
                break;
            case Oracle:
                Class.forName("oracle.jdbc.driver.OracleDriver");
                break;
            case SqlServer:
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                break;
            case Db2:
                Class.forName("com.ibm.db2.jcc.DB2Driver");
                break;
            default:
                throw new RuntimeException("不支持的数据库类型");
        }
    }

}
