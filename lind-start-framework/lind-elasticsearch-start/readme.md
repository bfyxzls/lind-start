# es说明
## 实体上的注解
在test项目里，当没有添加`@RunWith(SpringRunner.class)`时，通过insert插入实体时建立的索引是有问题的,
（解决方法是手动运行createIndex和putMapping）,当然，测试用例里如果开启了`@RunWith(SpringRunner.class)`,那索引就是自动建立的。
```aidl
/**
 * 注意：在test项目里，当没有添加@RunWith(SpringRunner.class)时，通过insert插入实体时建立的索引是有问题的（解决方法是手动运行createIndex和putMapping），
 */
@Document(indexName = "esdto", shards = 1, replicas = 0)
@Setting(settingPath = "mapping/es-setting.json")//@Setting里的配置会覆盖@Document里的配置
public class EsDto extends EsBaseEntity implements Serializable {
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
```
## Document与Setting注解的优先级
当你实体上有`@Document`和`@Setting`注解时，如果你有这种配置`@Document(indexName = "esdto", shards = 1, replicas = 0)`，那么
你需要在`@Setting`里也进行配置，负责将会使用@Setting的默认配置覆盖，即shards将会变成5个。
```aidl
# es-setting.json
{
  "index": {
    // 分页数
    "max_result_window": 1000000,
    // 翻页最大值
    "number_of_shards": 1,
    // 分片数
    "number_of_replicas": 1
  }
}
```
## keyword和text类型
### keyword
1. 不进行分词，直接索引,支持模糊、支持精确匹配，支持聚合、排序操作。
2. keyword类型的最大支持的长度为——32766个UTF-8类型的字符,可以通过设置ignore_above指定自持字符长度，超过给定长度后的数据将不被索引，无法通过term精确匹配检索返回结果。
```aidl
使用场景：
存储邮箱号码、url、name、title，手机号码、主机名、状态码、邮政编码、标签、年龄、性别等数据。
用于筛选数据(例如: select * from x where status='open')、排序、聚合(统计)。
直接将完整的文本保存到倒排索引中。
```

### text
1. 支持分词，全文检索,支持模糊、精确查询,不支持聚合,排序操作;
2. text类型的最大支持的字符长度无限制,适合大字段存储；
```aidl
使用场景：
    存储全文搜索数据, 例如: 邮箱内容、地址、代码块、博客文章内容等。
    默认结合standard analyzer(标准解析器)对文本进行分词、倒排索引。
    默认结合标准分析器进行词命中、词频相关度打分。
```
### 结论
* text类型：会分词，先把对象进行分词处理，然后再再存入到es中。
当使用多个单词进行查询的时候，当然查不到已经分词过的内容！
* keyword：不分词，没有把es中的对象进行分词处理，而是存入了整个对象！
这时候当然可以进行完整地查询！默认是256个字符！

## termQuery和matchQuery
###　termQuery
termQuery不分词，只查询lind is a5这个词
boolQueryBuilder.must(QueryBuilders.termQuery("memo.keyword", "我是中国人民共和国的公民"));
> 由于memo是分词字段，所以，要想精确查询需要加上keyword
###　matchQuery
matchQuery先分词，再按着分词结果查询：boolQueryBuilder.must(QueryBuilders.matchQuery("memo", "l我是中国人民共和国的公民"));
他会查询l我是中国人民共和国的公民分词后的结果，如我，是，中国，人民等词
