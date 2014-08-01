package com.yermoon.dao;

import com.yermoon.entity.DbSrcEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * mybatis的数据操作
 *
 * @author wangqing
 * @since 14-4-17 下午3:36
 */
@Component("mybatisDao")
public interface MybatisDao {
    public int insertDbsrc(DbSrcEntity dbSrcEntity);

    public List<DbSrcEntity> findAll();

    @CacheEvict(value = "MybatisDaoCache")
    public int deleteById(int id);

    @Cacheable(value = "MybatisDaoCache")
    public DbSrcEntity findById(int id);
}
