package com.lind.common;

import cn.hutool.core.lang.Assert;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class InfinispanTest {
  private static DefaultCacheManager cacheManager;
  Logger logger = LoggerFactory.getLogger(InfinispanTest.class);

  private EmbeddedCacheManager container() {
    return new DefaultCacheManager(
        new GlobalConfigurationBuilder().transport().defaultTransport().build(),
        new ConfigurationBuilder().clustering().stateTransfer().awaitInitialTransfer(false).build());
  }

  @Test
  public void initializeCache() {

    if (cacheManager == null) {

      GlobalConfigurationBuilder gcbLocal = new GlobalConfigurationBuilder();
      ConfigurationBuilder builderLocal = new ConfigurationBuilder();
      builderLocal.clustering().cacheMode(CacheMode.LOCAL);
      cacheManager = new DefaultCacheManager(gcbLocal.build(), builderLocal.build());
      cacheManager.getCache();
    }
  }

  @Test
  public void testCacheManagerXmlConfig() {
    container().startCaches("zzl");
    container().getCache("zzl").put("author", "lind");
    Assert.notNull(container().getCache("zzl").get("author"));
    logger.info("author=" + container().getCache("zzl").get("author"));
  }

}

