package com.yermoon.dao;

import com.yermoon.entity.DbSrcEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * mybatis的数据操作
 *
 * @author wangqing
 * @since 14-4-17 下午3:36
 */
@Repository
public interface MybatisDao {

    public int insertDbsrc(DbSrcEntity dbSrcEntity);

    public List<DbSrcEntity> findAll();

    public int deleteById(String id);

    public DbSrcEntity findById(String id);
}
