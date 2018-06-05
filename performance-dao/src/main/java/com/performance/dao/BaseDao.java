package com.performance.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T, ID extends Serializable> {
    /**
     * 插入一个实体
     *
     * @param t
     * @return
     */
    int insert(T t);

    /**
     * 批量插入数据
     * @param t
     * @return
     */
    int insertBatch(List<T> t);

    /**
     * 根据主键更新一个实体
     *
     * @param t
     * @return
     */
    int updateById(T t);

    /**
     * 根据主键删除一个实体
     *
     * @param id
     * @return 影响条数
     */
    int deleteById(ID id);

    /**
     * 根据主键查询一个实体
     *
     * @param id
     * @return
     */
    T selectById(ID id);

    /**
     * 根据实体属性值查询所有实体
     *
     * @param t
     * @return
     */
    List<T> selectList(T t);

    /**
     * 查询对象总数
     * @param t
     * @return
     */
    Long selectCount(T t);
}
