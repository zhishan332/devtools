package com.yermoon.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class BaseDao {
    // 使用log4j记录日志
    private static final Logger logger = LoggerFactory.getLogger(BaseDao.class);
    // 连接驱动
    private static final String DRIVER = "org.sqlite.JDBC";

    public static final String DBPATH = "C:\\code\\idea\\mycode\\sqlite\\tool.s3db";
    // 连接路径
    private static final String URL = "jdbc:sqlite:" + DBPATH;
    // 用户名
    private static final String USERNAME = "";
    // 密码
    private static final String PASSWORD = "";

    //静态代码块
    static {
        try {
            // 加载驱动
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.error("加载驱动失败", e);
        }
    }

    /*
     * 获取数据库连接
     */
    public Connection getConnection() {
        Connection conn = null;
        logger.debug("开始连接数据库");
        try {
            //与数据库建立连接
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("数据库连接失败！", e);
        }
        logger.debug("数据库连接成功");
        return conn;
    }

    /*
     * 关闭数据库连接，注意关闭的顺序
     */
    public void close(ResultSet rs, PreparedStatement ps, Connection conn) {
        //注意：最后打开的最先关闭
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("关闭ResultSet失败", e);
            }
        }
        if (ps != null) {
            try {
                ps.close();
                ps = null;
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("关闭PreparedStatement失败", e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("关闭Connection失败", e);
            }
        }
    }

    /*
     * 查询方法
     *
     * sql: 要执行的sql语句
     * handler：自定义接口
     * obj：可变参数列表
     */
    public <T> T query(String sql, ResultSetHandler<T> handler, Object... obj) {
        Connection conn = getConnection(); //获得连接
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //创建PreparedStatement对象
            ps = conn.prepareStatement(sql);
            //为查询语句设置参数
            setParameter(ps, obj);
            //获得ResultSet结果集
            rs = ps.executeQuery();
            //返回对象
            return handler.callback(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("数据库操作异常", e);
        } finally {
            //关闭连接
            close(rs, ps, conn);
            logger.debug("释放资源成功");
        }
        return null;
    }

    /*
     * 增加、修改、删除,的方法
     *
     * obj: 可变参数列表
     */
    public int update(String sql, Object... obj) {
        Connection conn = getConnection(); //获得连接
        PreparedStatement ps = null;
        int rows = 0;
        try {
            //创建PreparedStatement对象
            ps = conn.prepareStatement(sql);
            //为查询语句设置参数
            setParameter(ps, obj);
            //获得受影响的行数
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("数据库操作异常", e);
        } finally {
            //关闭连接
            close(null, ps, conn);
            logger.debug("释放资源成功");
        }
        return rows;
    }

    /*
     * 为预编译对象设置参数
     */
    public void setParameter(PreparedStatement ps, Object... obj)
            throws SQLException {
        if (obj != null && obj.length > 0) {
            //循环设置参数
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
        }
    }
}

