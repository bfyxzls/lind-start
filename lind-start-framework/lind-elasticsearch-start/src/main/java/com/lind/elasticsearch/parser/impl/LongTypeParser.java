package com.lind.elasticsearch.parser.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lind.elasticsearch.exception.FieldValidException;
import com.lind.elasticsearch.parser.FieldTypeParser;
import com.lind.elasticsearch.util.ParserUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;


public class LongTypeParser extends BaseAbstractTypeParser implements FieldTypeParser<Object> {
    @Override
    public Object parser(Object source) {
        if (ObjectUtils.isEmpty(source)) {
            return null;
        }
        try {
            String s = source.toString().trim();
            if (ParserUtils.isArray(s)) {
                List<Long> longs = getObjectMapper().readValue(s, new TypeReference<List<Long>>() {
                });
                return longs;
            }
            return Long.parseLong(source.toString());
        } catch (Exception e) {
            throw new FieldValidException(source, e);
        }
    }
}
