package com.yermoon.dao;

import com.ohdb.OhBaseDao;
import com.yermoon.entity.DbSrcEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * mybatis的数据操作
 *
 * @author wangqing
 * @since 14-4-17 下午3:36
 */
@Component("mybatisDao")
public class MybatisDao {

    public int insertDbsrc(DbSrcEntity dbSrcEntity) {
        OhBaseDao<DbSrcEntity> dao = new OhBaseDao<DbSrcEntity>();
        return dao.insert(dbSrcEntity);
    }

    public List<DbSrcEntity> findAll() {
        OhBaseDao<DbSrcEntity> dao = new OhBaseDao<DbSrcEntity>();
        return dao.find(new DbSrcEntity());
    }

    public int deleteById(String id) {
        DbSrcEntity dbSrcEntity = new DbSrcEntity();
        dbSrcEntity.setId(id);
        OhBaseDao<DbSrcEntity> dao = new OhBaseDao<DbSrcEntity>();
        return dao.delete(dbSrcEntity);
    }

    public DbSrcEntity findById(String id) {
        DbSrcEntity dbSrcEntity = new DbSrcEntity();
        dbSrcEntity.setId(id);
        OhBaseDao<DbSrcEntity> dao = new OhBaseDao<DbSrcEntity>();
        List<DbSrcEntity> list = dao.find(dbSrcEntity);
        if (list != null && !list.isEmpty()) return list.get(0);
        return null;
    }
}
