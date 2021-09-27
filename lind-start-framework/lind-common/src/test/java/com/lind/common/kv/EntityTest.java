package com.lind.common.kv;

import cn.hutool.core.lang.Assert;
import com.lind.common.minibase.Bytes;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.Iterator;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EntityTest {
  @SneakyThrows
  @Test
  public void create() {
    Entity entity = Entity.create(Bytes.toBytes("zzl"), Bytes.toBytes("zhangzhanling"), 1);
    Assert.isTrue(32 == entity.getSerializeSize()); //4+4+3+8+13=32
    byte[] result = entity.toBytes();//前4位3+8=11，再4位zhangzhanling长度为13，再3位存储key的值,再8位是增时不量的值，最后13位是value的值
    Assert.notNull(result);
    Entity entity2 = Entity.parseFrom(result, 0);
    Assert.isTrue(Bytes.toHex(entity2.getKey()).equals("zzl"));
  }

  @SneakyThrows
  @Test
  public void store() {
    ExecutorService pool = Executors.newFixedThreadPool(1);
    Store store = new Store(pool);
    Entity entity = Entity.create(Bytes.toBytes("zzl"), Bytes.toBytes("zhangzhanling"), 1);
    store.add(entity);
    entity = Entity.create(Bytes.toBytes("bobo"), Bytes.toBytes("zhangzhanling"), 2);
    store.add(entity);
    entity = Entity.create(Bytes.toBytes("zhang"), Bytes.toBytes("zhangzhanling"), 3);
    store.add(entity);
  }

  @SneakyThrows
  @Test
  public void skipList() {
    ConcurrentSkipListMap<Entity, Entity> entityConcurrentSkipListMap = new ConcurrentSkipListMap<>();

    Entity entity = Entity.create(Bytes.toBytes("zzl"), Bytes.toBytes("zhangzhanling"), 1);
    entityConcurrentSkipListMap.put(entity, entity);
    Entity entity2 = Entity.create(Bytes.toBytes("zzl2"), Bytes.toBytes("zhangzhanling1"), 2);
    entityConcurrentSkipListMap.put(entity2, entity2);
    Entity entity3 = Entity.create(Bytes.toBytes("zzl3"), Bytes.toBytes("zhangzhanling3"), 3);
    entityConcurrentSkipListMap.put(entity3, entity3);
    Entity entity4 = Entity.create(Bytes.toBytes("zzl4"), Bytes.toBytes("zhangzhanling4"), 4);
    entityConcurrentSkipListMap.put(entity4, entity4);
    System.out.println("entity2:" + entityConcurrentSkipListMap.containsKey(entity2));
    System.out.println("entity4" + entityConcurrentSkipListMap.containsKey(entity4));

    Iterator<ConcurrentSkipListMap
        .Entry<Entity, Entity>> itr
        = entityConcurrentSkipListMap.entrySet().iterator();
    while (itr.hasNext()) {
      ConcurrentSkipListMap.Entry<Entity, Entity> item = itr.next();
      System.out.println(Bytes.toHex(item.getKey().getKey()));
    }
  }

}

