package com.lind.elasticsearch;

import com.lind.elasticsearch.entity.EsBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Document(indexName = "esdto", type = "esdto")
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class EsDto extends EsBaseEntity implements Serializable {

    /**
     * analyzer表示开启分词功能.
     */
    @Field(type = FieldType.Keyword, analyzer = "ik_max_word")
    private String name;
    private Integer age;
    @Field(type = FieldType.Keyword)
    private String sex;
    @Field(type = FieldType.Keyword, analyzer = "ik_max_word")
    private String desc;
}