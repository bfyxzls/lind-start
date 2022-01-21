package com.lind.start.test;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.ServerStatistics;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class InfinispanTest {
  private RemoteCacheManager cacheManager;
  private RemoteCache<String, Object> cache;

  @Test
  public void demoAuthHotRod() {
    ConfigurationBuilder builder = new ConfigurationBuilder();
    builder.addServer()
        .host("192.168.60.137")
        .port(Integer.parseInt("11222"))
        .security()
        .authentication()
        .saslMechanism("DIGEST-MD5")
        .username("admin")
        .password("admin");
    ;
    cacheManager = new RemoteCacheManager(builder.build());
    cache = cacheManager.getCache("default");
    cache.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
    System.out.println("Dumping cache Data");
    System.out.println("==========================");
    Set set = this.cache.keySet();
    Iterator i = set.iterator();
    while (i.hasNext()) {
      System.out.println(i.next());
    }
    //Print cache statistics
    ServerStatistics stats = cache.stats();
    for (Map.Entry stat : stats.getStatsMap().entrySet()) {
      System.out.println(stat.getKey() + " : " + stat.getValue());
    }
  }


  @Test
  public void demoNoAuthHotRod() {
    ConfigurationBuilder builder = new ConfigurationBuilder();
    builder.addServer()
        .host("192.168.60.137")
        .port(Integer.parseInt("11222"));
    cacheManager = new RemoteCacheManager(builder.build());
    cache = cacheManager.getCache("default");
    cache.put(UUID.randomUUID().toString(), "hello");
    System.out.println("Dumping cache Data");
    System.out.println("==========================");
    Set set = this.cache.keySet();
    Iterator i = set.iterator();
    while (i.hasNext()) {
      System.out.println(i.next());
    }
    //Print cache statistics
    ServerStatistics stats = cache.stats();
    for (Map.Entry stat : stats.getStatsMap().entrySet()) {
      System.out.println(stat.getKey() + " : " + stat.getValue());
    }
  }
}
