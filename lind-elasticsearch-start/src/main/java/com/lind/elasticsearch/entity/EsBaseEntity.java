package com.lind.elasticsearch.entity;

import com.lind.common.util.SnowFlakeUtil;
import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
public class EsBaseEntity {

    public static final String dateTimeFormat = "yyyy/MM/dd||yyyy-MM-dd" +
            "||yyyy-MM-dd HH:mm:ss||yyyy/MM/dd HH:mm:ss" +
            "||yyyy-MM-dd HH:mm:ss.SSS||yyyy/MM/dd HH:mm:ss.SSS" +
            "||yyyy-MM-dd'T'HH:mm:ss.SSS";
    /**
     * 创建时间.
     */
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = dateTimeFormat)
    @CreatedDate
    protected String createTime;
    /**
     * 创建人.
     */
    @Field(type = FieldType.Keyword)
    @CreatedBy
    protected String creator;
    /**
     * 更新时间.
     */
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = dateTimeFormat)
    @LastModifiedDate
    protected String updateTime;
    /**
     * 更新人.
     */
    @Field(type = FieldType.Keyword)
    @LastModifiedBy
    protected String updateUser;
    /**
     * 删除标记.
     */
    @Field(type = FieldType.Keyword)
    protected boolean delFlag;
    /**
     * 主键.
     */
    @Id
    private String id = String.valueOf(SnowFlakeUtil.getFlowIdInstance().nextId());

}
