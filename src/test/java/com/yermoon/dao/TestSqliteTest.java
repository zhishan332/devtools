package com.yermoon.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangqing
 * Date: 14-4-9
 * Time: 下午5:48
 * sqlLite数据库连接测试
 */
public class TestSqliteTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void test() throws Exception {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:D:/programs/sqlite/tool.s3db");
        PreparedStatement dd = conn.prepareStatement("select * from test");
        ResultSet res = dd.executeQuery();
        while (res.next()) {
            String name = res.getString("name");
            System.out.println(name);
        }
    }
    @Test
    public void testFile()throws Exception{
        File ff=new File("D:/programs/sqlite/tool.db");
        System.out.println(ff.exists());
    }
}

    