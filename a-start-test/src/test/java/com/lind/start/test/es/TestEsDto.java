package com.lind.start.test.es;

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
public class TestEsDto extends EsBaseEntity implements Serializable {


    @Field(type = FieldType.Keyword)
    private String name;
    private Integer age;
    @Field(type = FieldType.Keyword)
    private String sex;
    @Field(type = FieldType.Keyword)
    private String desc;
}