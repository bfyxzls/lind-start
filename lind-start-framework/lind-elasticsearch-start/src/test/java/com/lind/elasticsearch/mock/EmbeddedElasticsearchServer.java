package com.lind.elasticsearch.mock;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic;
import pl.allegro.tech.embeddedelasticsearch.PopularProperties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

//@Component //先注释，这个zip包总是下载失败
public class EmbeddedElasticsearchServer {
    static final String ES_PATH = "http://localhost/elasticsearch-6.0.0.zip";
    @Autowired
    EmbeddedElastic embeddedElastic;

    @Bean
    public EmbeddedElastic embeddedElastic() throws MalformedURLException {
        URL downloadUrl = new URL(ES_PATH);
        EmbeddedElastic embeddedElastic = EmbeddedElastic.builder()
                .withElasticVersion("6.0.0")
                .withDownloadUrl(new URL(ES_PATH))
                .withSetting(PopularProperties.HTTP_PORT, 21121)
                .build();
        return embeddedElastic;
    }

    @PostConstruct
    public void startRedis() throws IOException, InterruptedException {
        embeddedElastic.start();

    }

    @PreDestroy
    public void stopRedis() {
        embeddedElastic.stop();
    }
}
