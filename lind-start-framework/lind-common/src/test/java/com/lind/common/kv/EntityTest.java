package com.lind.common.kv;

import cn.hutool.core.lang.Assert;
import com.lind.common.minibase.Bytes;
import lombok.SneakyThrows;
import org.junit.Test;

public class EntityTest {
  @SneakyThrows
  @Test
  public void create() {
    Entity entity = Entity.create(Bytes.toBytes("zzl"), Bytes.toBytes("zhangzhanling"), 1);
    Assert.isTrue(32 == entity.getSerializeSize()); //4+4+3+8+13=32
    byte[] result = entity.toBytes();//前4位3+8=11，再4位zhangzhanling长度为13，再3位存储key的值,再8位是增时不量的值，最后13位是value的值
    Assert.notNull(result);
  }
}
