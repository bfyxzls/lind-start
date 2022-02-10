package com.lind.common.collection;

import cn.hutool.core.lang.Assert;
import org.junit.Test;

import java.util.Optional;

public class OptionalTest {
  @Test
  public void nullable() {
    Optional<Student> student = Optional.ofNullable(null);
    Assert.isFalse(student.isPresent());
  }

  @Test
  public void nullableFalse() {
    Optional<Student> student = Optional.ofNullable(new Student());
    Assert.isTrue(student.isPresent());
  }
}
