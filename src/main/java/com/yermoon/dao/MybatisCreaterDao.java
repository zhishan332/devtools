package com.yermoon.dao;

import com.yermoon.entity.DbSrcEntity;
import com.yermoon.utils.BaseDao;
import com.yermoon.utils.ResultSetHandler;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Mybatis业务DAO
 *
 * @author wangqing
 * @since 15-5-7 下午5:48
 */
@Repository
public class MybatisCreaterDao extends BaseDao {

    //插入数据，并返回自动增长的的主键值
    public void insertDbsrc(DbSrcEntity dbSrcEntity) {
        Connection conn = getConnection(); //获得连接
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "insert into mybatis_db(dbtype,jdbcurl,username,password) values (?,?,?,?)";
        try {
            // 创建能返回自动生成的主键的值的预编译对象
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, dbSrcEntity.getDbType());
            ps.setString(2, dbSrcEntity.getJdbcUrl());
            ps.setString(3, dbSrcEntity.getUserName());
            ps.setString(4, dbSrcEntity.getPassword());
            int rows = ps.executeUpdate();
            // 获得自动增长的的主键值
            rs = ps.getGeneratedKeys();
            rs.next();
            //获得id
            int id = rs.getInt(1);
            System.out.println("成功添加：" + rows + "条数据，id是：" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, ps, conn);
        }
    }

    public int deleteById(int id) {
        String sql = "delete from  mybatis_db where id =?";
        return update(sql, id);
    }

    public DbSrcEntity findById(int id) {
        String sql = "select * from mybatis_db where id=?";
        //匿名实现自定义接口
        ResultSetHandler<DbSrcEntity> handler = new ResultSetHandler<DbSrcEntity>() {
            DbSrcEntity dbSrcEntity = new DbSrcEntity();

            @Override
            public DbSrcEntity callback(ResultSet rs) {
                try {
                    while (rs.next()) {
                        dbSrcEntity.setDbType(rs.getInt("dbtype"));
                        dbSrcEntity.setId(rs.getInt("id"));
                        dbSrcEntity.setJdbcUrl(rs.getString("jdbcurl"));
                        dbSrcEntity.setUserName(rs.getString("username"));
                        dbSrcEntity.setPassword(rs.getString("password"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return dbSrcEntity;
            }
        };
        return query(sql, handler, id);
    }

    public List<DbSrcEntity> findAll() {
        String sql = "select * from mybatis_db";
        //匿名实现自定义接口
        ResultSetHandler<List<DbSrcEntity>> handler = new ResultSetHandler<List<DbSrcEntity>>() {
            @Override
            public List<DbSrcEntity> callback(ResultSet rs) {
                List<DbSrcEntity> list = new ArrayList<DbSrcEntity>(); //用于存放结果的集合，User类型
                DbSrcEntity dbSrcEntity = null;
                try {
                    while (rs.next()) {
                        dbSrcEntity = new DbSrcEntity();
                        dbSrcEntity.setDbType(rs.getInt("dbtype"));
                        dbSrcEntity.setId(rs.getInt("id"));
                        dbSrcEntity.setJdbcUrl(rs.getString("jdbcurl"));
                        dbSrcEntity.setUserName(rs.getString("username"));
                        dbSrcEntity.setPassword(rs.getString("password"));
                        list.add(dbSrcEntity); //添加到list集合
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return list;
            }
        };
        return query(sql, handler);
    }
}
