package com.yermoon.service;

import com.yermoon.vo.DataBase;
import com.yermoon.vo.JTable;

import java.util.List;

/**
 * To change this template use File | Settings | File Templates.
 *
 * @author wangqing
 * @since 14-4-18 上午10:01
 */
public interface DataBaseService {


    public JTable getTable(DataBase dataBase, String tableName) throws Exception;

    public List<String> getTableNames(DataBase dataBase) throws Exception;
}
