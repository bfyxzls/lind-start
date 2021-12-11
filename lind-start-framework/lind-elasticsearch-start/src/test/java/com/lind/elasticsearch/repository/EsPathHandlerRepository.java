package com.lind.elasticsearch.repository;

import com.lind.elasticsearch.entity.EsPathHandlerEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsPathHandlerRepository extends ElasticsearchRepository<EsPathHandlerEntity, String> {
}
