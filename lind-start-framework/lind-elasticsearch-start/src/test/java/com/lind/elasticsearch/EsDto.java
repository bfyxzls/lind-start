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

@Data
@Document(indexName = "esdto", shards = 1, replicas = 0)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
//@Mapping(mappingPath = "mapping/es-esdto.json")
public class EsDto extends EsBaseEntity implements Serializable {

    /**
     * keyword类型不自动分词；text类型会自动分词.
     * analyzer表示开启分词功能.
     *
     * @filed里的analyzer是不行的，只能通过@Mapping实现,而且它会复盖实体的@field注解
     */
    @Field(type = FieldType.Keyword)
    private String name;
    @Field(type = FieldType.Integer)
    private Integer age;
    @Field(type = FieldType.Integer)
    private Integer sex;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String desc;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String memo;
    @Field(type = FieldType.Nested, fielddata = true)
    private Address address;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Address {
        @Field(type = FieldType.Keyword)
        private String province;
        @Field(type = FieldType.Keyword)
        private String city;
        @Field(type = FieldType.Keyword)
        private String district;
    }
}