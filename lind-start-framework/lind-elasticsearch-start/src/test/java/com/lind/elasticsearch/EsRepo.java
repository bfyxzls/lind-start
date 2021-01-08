package com.lind.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface EsRepo extends ElasticsearchRepository<EsDto, String> {
}