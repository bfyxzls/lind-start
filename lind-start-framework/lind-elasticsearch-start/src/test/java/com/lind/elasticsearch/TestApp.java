package com.lind.elasticsearch;

import com.lind.elasticsearch.entity.EsDto;
import com.lind.elasticsearch.entity.EsPathHandlerEntity;
import com.lind.elasticsearch.entity.Reply;
import com.lind.elasticsearch.repository.EsPathHandlerRepository;
import com.lind.elasticsearch.repository.EsRepo;
import com.lind.elasticsearch.util.EsPageUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.ParsedSum;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest()
@RunWith(SpringRunner.class) //让测试在Spring容器环境下执行
@Slf4j
public class TestApp {

  @Autowired
  EsRepo testDao;
  @Autowired
  ElasticsearchRestTemplate elasticsearchTemplate;
  @Autowired
  EsPathHandlerRepository esPathHandlerRepository;

  /**
   * 索引初始化.
   * 对于开启了@RunWith(SpringRunner.class)注入之后，这个索引初始化的代码就不需要了.
   */
  @Before
  public void initIndexMapping() {
    System.out.println("init...");
    elasticsearchTemplate.deleteIndex(EsDto.class);
    if (!elasticsearchTemplate.indexExists(EsDto.class)) {
      // 建立空索引
      if (elasticsearchTemplate.createIndex(EsDto.class)) {
        // 建立mapping
        elasticsearchTemplate.putMapping(EsDto.class);
      }
    }
  }

  @SneakyThrows
  @Test
  public void insertEsPathHandlerEntity() {
    EsPathHandlerEntity esPathHandlerEntity = new EsPathHandlerEntity();
    esPathHandlerEntity.setBody("");
    esPathHandlerEntity.setCreateBy("zzl");
    esPathHandlerEntity.setCreateTime(new Date());
    esPathHandlerEntity.setIp("192.168.6.3");
    esPathHandlerEntity.setPathDescribes("用户中心");
    esPathHandlerEntity.setPath("/user/info");
    esPathHandlerEntity.setRequestMethod("get");
    esPathHandlerEntity.setProject("v6");
    esPathHandlerEntity.setTimestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+0800").format(new Date()));
    esPathHandlerRepository.save(esPathHandlerEntity);
  }

  @Test
  public void insert() {
    List<EsDto> list = new ArrayList<>();
    list.add(new EsDto("张三", 41, 1, "北京丰台东高地", "中国生产吴虹飞", new EsDto.Address("北京", "北京", "丰台")));
    list.add(new EsDto("李四", 31, 1, "北京房山张谢", "中国生产吴虹飞", new EsDto.Address("北京", "北京", "房山")));
    list.add(new EsDto("王洁", 21, 0, "河北唐山周庄", "中国生产吴虹飞", new EsDto.Address("河北", "唐山", "滦县")));
    list.add(new EsDto("赵新", 30, 0, "河北唐山周庄", "中国生产吴虹飞", new EsDto.Address("河北", "唐山", "滦县")));
    list.add(new EsDto("赵一迪", 12, 1, "河北唐山周庄", "中国生产吴虹飞", new EsDto.Address("河北", "唐山", "滦县")));
    list.add(new EsDto("王使", 32, 1, "北京房山张谢", "中国生产吴虹飞", new EsDto.Address("北京", "北京", "房山")));

    testDao.saveAll(list);
  }

  /**
   * 分词查询
   */
  @Test
  public void matchQuery() {
    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    boolQueryBuilder.must(QueryBuilders.matchQuery("memo", "中国共享网站"));
    Pageable pageable = EsPageUtils.getPageable(0, 10);
    SearchQuery searchQuery = queryBuilder.withQuery(boolQueryBuilder)
        .withSourceFilter(null) //返回所有字段
        .withPageable(pageable)
        .build();
    Page<EsDto> page = testDao.search(searchQuery);
    List<EsDto> content = page.getContent();
    log.info("content.len:{}", content.size());
    for (EsDto dto : content) {
      log.info(dto.toString());
    }
  }

  /**
   * 精确查找
   */
  @Test
  public void termQuery() {
    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    // matchQuery先分词再检索,termQuery精确检索， matchQuery将搜索词分词，再与目标查询字段进行匹配，若分词中的任意一个词与目标字段匹配上，则可查询到。
    // 分词：把一个长的句子，分成若干个词，进行检索
    // termQuery本会对查询条件进行分词，但如果你的查询内容字段是分词的，它是对进行内容内容查找的
    //  termQuery如果希望对内容也进行精确检索，需要加keyword关键字
    // must表示与，should表示或
    boolQueryBuilder.must(QueryBuilders.termQuery("memo.keyword", "中国"));
    Pageable pageable = EsPageUtils.getPageable(0, 10);
    String[] fieldNames = new String[]{"name", "age", "memo"};
    SearchQuery searchQuery = queryBuilder.withQuery(boolQueryBuilder)
        .withSourceFilter(new FetchSourceFilter(fieldNames, null))
        .withSourceFilter(null) //返回所有字段
        .withPageable(pageable)
        .build();
    Page<EsDto> page = testDao.search(searchQuery);
    List<EsDto> content = page.getContent();
    log.info("content.len:{}", content.size());
    if (content.size() > 0) {
      for (EsDto dto : content) {
        log.info(dto.toString());
      }
    }
  }

  /**
   * 聚合.
   */
  @Test
  public void aggregate() {
    // 创建一个查询条件对象
    BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
    // 拼接查询条件
    queryBuilder.should(QueryBuilders.termQuery("creator", "1"));
    // 创建聚合查询条件
    TermsAggregationBuilder sexAgg = AggregationBuilders
        .terms("sex") // 别名名
        .field("sex");// 字段
    SumAggregationBuilder sexSumAgg = AggregationBuilders
        .sum("ageSum") // 别名
        .field("age"); // 字段
    sexAgg.subAggregation(sexSumAgg);
    // 创建查询对象
    SearchQuery build = new NativeSearchQueryBuilder()
        .withQuery(queryBuilder) //添加查询条件
        .addAggregation(sexAgg) // 添加聚合条件
        .withPageable(PageRequest.of(0, 1)) //符合查询条件的文档分页，如果文档比较大，可以把这个分页改小（不是聚合的分页）
        .build();
    // 执行查询
    AggregatedPage<EsDto> testEntities = elasticsearchTemplate.queryForPage(build, EsDto.class);
    // 取出聚合结果
    Aggregations entitiesAggregations = testEntities.getAggregations();
    Terms terms = (Terms) entitiesAggregations.asMap().get("sex");

    // 遍历取出聚合字段列的值，与对应的数量
    for (Terms.Bucket bucket : terms.getBuckets()) {
      ParsedSum parsedSum = bucket.getAggregations().get("ageSum");//注意从bucket而不是searchResponse
      System.out.println(bucket.getKeyAsString() + "\t" +
          bucket.getDocCount() + "\t" +
          parsedSum.getValueAsString());

    }
  }

  /**
   * 嵌套聚合.
   */
  @Test
  public void aggregateNest() {
    // 创建一个查询条件对象
    BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
    // 拼接查询条件
    queryBuilder.should(QueryBuilders.termQuery("creator", "1"));

    // 创建聚合查询条件
    TermsAggregationBuilder sexAgg = AggregationBuilders
        .terms("sex")
        .field("sex");//keyword表示不使用分词进行聚合,全字匹配
    TermsAggregationBuilder descAgg = AggregationBuilders
        .terms("address")
        .field("address.province.keyword");//keyword表示不使用分词进行聚合,全字匹配
    SumAggregationBuilder ageSumAgg = AggregationBuilders
        .sum("ageSum")
        .field("age");
    //嵌套
    descAgg.subAggregation(ageSumAgg);
    sexAgg.subAggregation(descAgg);

    // 创建查询对象
    SearchQuery build = new NativeSearchQueryBuilder()
        .withQuery(queryBuilder) //添加查询条件
        .addAggregation(sexAgg) // 添加聚合条件
        .withPageable(PageRequest.of(0, 1)) //符合查询条件的文档分页，如果文档比较大，可以把这个分页改小（不是聚合的分页）
        .build();
    // 执行查询
    AggregatedPage<EsDto> testEntities = elasticsearchTemplate.queryForPage(build, EsDto.class);

    // 取出聚合结果
    Aggregations entitiesAggregations = testEntities.getAggregations();
    Terms terms = (Terms) entitiesAggregations.asMap().get("sex");

    // 遍历取出聚合字段列的值，与对应的数量
    for (Terms.Bucket bucket : terms.getBuckets()) {
      Terms descTerms = (Terms) bucket.getAggregations().asMap().get("address");
      for (Terms.Bucket descTermsBucket : descTerms.getBuckets()) {
        ParsedSum parsedSum = descTermsBucket.getAggregations().get("ageSum");//注意从bucket而不是searchResponse
        System.out.println(bucket.getKeyAsString() + "\t" +
            bucket.getDocCount() + "\t" +
            descTermsBucket.getKeyAsString() + "\t" +
            descTermsBucket.getDocCount() + "\t" +
            parsedSum.getValueAsString());
      }
    }
  }

  /**
   * 高亮检索.
   */
  @Test
  public void highSearch() {
    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    boolQueryBuilder.must(QueryBuilders.matchQuery("desc", "hello"));//matchQuery先分词再检索,termQuery精确检索
    Pageable pageable = EsPageUtils.getPageable(0, 10);

    // 高亮检索
    String preTag = "<font color='#dd4b39'>";
    String postTag = "</font>";

    SearchQuery searchQuery = queryBuilder.withQuery(boolQueryBuilder)
        .withSourceFilter(null) //返回所有字段
        .withHighlightFields(
            new HighlightBuilder.Field("desc")
                .preTags(preTag)
                .postTags(postTag)
                .numOfFragments(5).fragmentSize(100).noMatchSize(5)
        ) //高亮显示
        .withPageable(pageable)
        .build();

    AggregatedPage<EsDto> pageinfo = elasticsearchTemplate.queryForPage(searchQuery, EsDto.class, new SearchResultMapper() {
      @Override
      public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
        //先来一个容器把数据装起来...
        List<EsDto> gzDataList = new ArrayList<>();
        //我们所有查询的结果都放在这个searchResponse里面
        //我们现在就是要把我们想要的内容从这个searchResponse里面获取到
        SearchHits hits = searchResponse.getHits();
        //如果getTotalHits是0 则表示查询不到数据
        if (hits.getTotalHits() <= 0) {
          return null;
        } else {
          //从里面获取到一条一条的数据了
          for (SearchHit hit : hits) {
            EsDto gzData = new EsDto();
            //还要获取到某个字段的高亮特征 高亮的特征和当前的数据 做一个替换
            HighlightField companyHighlight = hit.getHighlightFields().get("desc");
            if (companyHighlight != null) {
              gzData.setName(companyHighlight.fragments()[0].toString());
            } else {
              gzData.setName((String) hit.getSourceAsMap().get("desc"));
            }
            gzDataList.add(gzData);
          }
        }
        return new AggregatedPageImpl(gzDataList);
      }

      @Override
      public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
        return null;
      }
    });
    pageinfo.forEach(o -> log.info(o.toString()));

  }

  /**
   * 更新.
   */
  @Test
  public void update() {
    String id = "303280141742641152";
    Map<String, Object> sourceMap = new HashMap<>();
    sourceMap.put("name", "占岭");
    IndexRequest indexRequest = new IndexRequest();
    indexRequest.source(sourceMap);
    UpdateQuery updateQuery = new UpdateQueryBuilder()
        .withId(id)
        .withClass(EsDto.class)
        .withIndexRequest(indexRequest).build();
    elasticsearchTemplate.update(updateQuery);
  }

  /**
   * 获取.
   */
  @Test
  public void detail() {
    String id = "303280141742641152";
    Optional<EsDto> optional = testDao.findById(id);
    Assert.notNull(optional.orElse(null));
    log.info(optional.get().toString());
  }


  @Test
  public void aggregateTopHitsTotal() {
    // 创建一个查询条件对象
    BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
    // 拼接查询条件
    queryBuilder.should(QueryBuilders.termQuery("createUser", "1"));
    // 创建聚合查询条件
    TermsAggregationBuilder operateTypeAggBuilder = AggregationBuilders.terms("commentId")
        .field("commentId").size(10000);
    operateTypeAggBuilder.subAggregation(AggregationBuilders.topHits("top").size(2)
        .fetchSource("content", "delFlag"));
    // 创建查询对象
    SearchQuery build = new NativeSearchQueryBuilder()
        .withQuery(queryBuilder) //添加查询条件
        .addAggregation(operateTypeAggBuilder) // 添加聚合条件
        .withPageable(PageRequest.of(0, 1)) //符合查询条件的文档分页，如果文档比较大，可以把这个分页改小（不是聚合的分页）
        .build();

    AggregatedPage<Reply> testEntities = elasticsearchTemplate.queryForPage(build, Reply.class);
    Aggregations entitiesAggregations = testEntities.getAggregations();
    Terms terms = (Terms) entitiesAggregations.asMap().get("commentId");
    // 遍历取出聚合字段列的值，与对应的数量
    for (Terms.Bucket bucket : terms.getBuckets()) {
      // We ask for top_hits for each bucket
      log.info("commentId:{}", bucket.getKeyAsString());
      TopHits topHits = bucket.getAggregations().get("top");
      for (SearchHit hit : topHits.getHits().getHits()) {
        log.info(" -> id [{}], _source [{}]", hit.getId(), hit.getSourceAsString());
      }

    }

  }

}
