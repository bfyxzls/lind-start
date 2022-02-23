package com.lind.elasticsearch.parser;

/**
 * @author 赵阳
 * @Description TODO
 * @date 2020/7/28 13:42
 **/
public interface FieldTypeParser<T> {


    /**
     * 解析字段
     *
     * @param source
     * @return
     */
    T parser(Object source);

}
