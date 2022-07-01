package com.lind.mybatis.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lind.common.util.CopyUtils;
import com.lind.mybatis.dto.PageData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static com.lind.mybatis.config.Constant.ASC;
import static com.lind.mybatis.config.Constant.LIMIT;
import static com.lind.mybatis.config.Constant.ORDER;
import static com.lind.mybatis.config.Constant.ORDER_FIELD;
import static com.lind.mybatis.config.Constant.PAGE;


/**
 * @param <M> 仓储对象
 * @param <T> 实体对象
 * @author lind
 * @date 2022/7/1 9:27
 * @description 业务基类
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> implements BaseService<T> {

    @Autowired
    protected M baseDao;

    /**
     * 获取分页对象
     *
     * @param params            分页查询参数
     * @param defaultOrderField 默认排序字段
     * @param isAsc             排序方式
     */
    protected IPage<T> getPage(Map<String, Object> params, String defaultOrderField, boolean isAsc) {
        //分页参数
        long curPage = 1;
        long limit = 10;

        if (params.get(PAGE) != null) {
            curPage = Long.parseLong((String) params.get(PAGE));
        }
        if (params.get(LIMIT) != null) {
            limit = Long.parseLong((String) params.get(LIMIT));
        }

        //分页对象
        Page<T> page = new Page<>(curPage, limit);

        //分页参数
        params.put(PAGE, page);

        //排序字段
        String orderField = (String) params.get(ORDER_FIELD);
        String order = (String) params.get(ORDER);

        //前端字段排序
        if (StringUtils.isNotBlank(orderField) && StringUtils.isNotBlank(order)) {
            if (ASC.equalsIgnoreCase(order)) {
                return page.addOrder(OrderItem.asc(orderField));
            } else {
                return page.addOrder(OrderItem.desc(orderField));
            }
        }

        //没有排序字段，则不排序
        if (StringUtils.isBlank(defaultOrderField)) {
            return page;
        }

        //默认排序
        if (isAsc) {
            page.addOrder(OrderItem.asc(defaultOrderField));
        } else {
            page.addOrder(OrderItem.desc(defaultOrderField));
        }

        return page;
    }

    /**
     * 返回分页结果
     *
     * @param list
     * @param total
     * @param target
     * @param <T>
     * @return
     */
    protected <T> PageData<T> getPageData(List<?> list, long total, Class<T> target) {
        List<T> targetList = CopyUtils.copyListProperties(list, target);

        return new PageData<>(targetList, total);
    }

    /**
     * 返回分页结果
     *
     * @param page
     * @param target
     * @param <T>
     * @return
     */
    protected <T> PageData<T> getPageData(IPage page, Class<T> target) {
        return getPageData(page.getRecords(), page.getTotal(), target);
    }

    @Override
    public BaseMapper<T> getRepository() {
        return baseDao;
    }
}
