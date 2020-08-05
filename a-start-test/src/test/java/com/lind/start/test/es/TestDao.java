package com.lind.start.test.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TestDao extends ElasticsearchRepository<TestEsDto, String> {
}