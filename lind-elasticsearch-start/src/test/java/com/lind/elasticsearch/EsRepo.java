package com.lind.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsRepo extends ElasticsearchRepository<EsDto, Long> {
}