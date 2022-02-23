package com.lind.elasticsearch.parser;

/**
 * @author 赵阳
 * @Description TODO
 * @date 2020/7/28 14:13
 **/
public interface FieldTypeParserFactory {

    /**
     * 获取解析器
     *
     * @param parserClazzName
     * @return
     */
    FieldTypeParser getParser(Class<? extends FieldTypeParser> parserClazzName);

}
