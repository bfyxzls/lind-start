package com.lind.mybatis.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;


/**
 * 业务基类.
 */
@FunctionalInterface
public interface BaseService<E> {

    /**
     * 需要子类中把方法实现注入进来.
     *
     * @return
     */
    BaseMapper<E> getRepository();

    /**
     * 根据ID获取.
     *
     * @param id
     * @return
     */
    default E get(String id) {
        return getRepository().selectById(id);
    }

    /**
     * 保存.
     *
     * @param entity
     * @return
     */
    default Integer insert(E entity) {
        return getRepository().insert(entity);
    }

    /**
     * 修改.
     *
     * @param entity
     * @return
     */
    default Integer update(E entity) {
        return getRepository().updateById(entity);
    }

    /**
     * 修改.
     *
     * @param entity
     * @return
     */
    default Integer update(Wrapper<E> entityWrapper, E entity) {
        return getRepository().update(entity, entityWrapper);
    }

    /**
     * 删除.
     *
     * @param id
     */
    default void delete(String id) {
        getRepository().deleteById(id);
    }

    /**
     * 批量删除.
     *
     * @param idList
     */
    default void delete(List<String> idList) {
        getRepository().deleteBatchIds(idList);
    }


    /**
     * 根据条件查询获取.
     *
     * @param entityWrapper
     * @return
     */
    default List<E> findAll(Wrapper<E> entityWrapper) {
        return getRepository().selectList(entityWrapper);
    }

    /**
     * 分页获取.
     *
     * @param pageNum       .
     * @param pageSize      .
     * @param entityWrapper .
     * @return
     */
    default IPage<E> findAll(int pageNum, int pageSize, Wrapper<E> entityWrapper) {
        Page<E> page = new Page<>(pageNum, pageSize);
        IPage<E> results = getRepository().selectPage(page, entityWrapper);
        return results;
    }


    /**
     * 获取查询条件的结果数
     *
     * @param entityWrapper
     * @return
     */
    default long count(Wrapper<E> entityWrapper) {
        return getRepository().selectCount(entityWrapper);
    }
}
