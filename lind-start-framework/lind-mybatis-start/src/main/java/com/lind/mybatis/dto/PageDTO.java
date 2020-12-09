package com.lind.mybatis.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页参数.
 */
@Data
public class PageDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 页号.
     */
    private int pageNumber = 1;

    /**
     * 页面记录数.
     */
    private int pageSize = 10;

    /**
     * 排序字段.
     */
    private String sort;

    /**
     * 排序方式 asc/desc.
     */
    private String order;
}
