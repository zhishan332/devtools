package com.yermoon.dao;

import java.sql.ResultSet;

//自定义接口
public interface ResultSetHandler<T> {
    public T callback(ResultSet rs);
}