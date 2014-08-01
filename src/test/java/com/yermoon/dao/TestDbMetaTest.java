package com.yermoon.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * To change this template use File | Settings | File Templates.
 *
 * @author wangqing
 * @since 14-4-18 下午2:41
 */
public class TestDbMetaTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testOracle() throws Exception {
        String jdbcUrl="jdbc:oracle:thin:@192.168.232.81:1521/ORCL";
        String user="waybill";
        String password="123456";
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
        DatabaseMetaData dbMetaData = conn.getMetaData();
        ResultSet tableRet = dbMetaData.getTables(null,"WAYBILL","%",new String[]{"TABLE"});
        List<String> tables=new ArrayList<String>();
        while(tableRet.next()) {
            System.out.println(tableRet.getString("TABLE_NAME"));
            tables.add(tableRet.getString("TABLE_NAME"));
        }
        tableRet.close();
        conn.close();

    }
    @Test
    public void testMysql() throws Exception {
        String jdbcUrl="jdbc:mysql://192.168.232.82:3306/qc?characterEncoding=UTF-8";
        String user="root";
        String password="123456";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
        DatabaseMetaData dbMetaData = conn.getMetaData();
        ResultSet tableRet = dbMetaData.getTables(null,"%","%",new String[]{"TABLE"});
        List<String> tables=new ArrayList<String>();
        while(tableRet.next()) {
            System.out.println(tableRet.getString("TABLE_NAME"));
            tables.add(tableRet.getString("TABLE_NAME"));
        }
        tableRet.close();
        conn.close();

    }
}

    