package com.lind.common.kv;

import com.lind.common.minibase.Bytes;

import java.io.IOException;

public class Entity implements Comparable<Entity> {
  // key长度
  public static final int RAW_KEY_LEN_SIZE = 4;
  // value长茺
  public static final int VAL_LEN_SIZE = 4;
  // id长度
  public static final int SEQ_ID_SIZE = 8;
  private byte[] key;
  private byte[] value;
  private long sequenceId;

  public Entity(byte[] key, byte[] value, long sequenceId) {
    assert key != null;
    assert value != null;
    assert sequenceId >= 0;
    this.key = key;
    this.value = value;
    this.sequenceId = sequenceId;
  }

  public static Entity create(byte[] key, byte[] value, long sequenceId) {
    return new Entity(key, value, sequenceId);
  }

  /**
   * 返回k/v的长度：key长度位+value长度位+key真实长度+value真实长度
   *
   * @return
   */
  public int getSerializeSize() {
    return RAW_KEY_LEN_SIZE + VAL_LEN_SIZE + getRawKeyLen() + value.length;
  }

  /**
   * 返回key的长度:key长度+操作长度+增量
   *
   * @return
   */
  private int getRawKeyLen() {
    return key.length + SEQ_ID_SIZE;
  }

  @Override
  public int compareTo(Entity o) {
    return 0;
  }

  public byte[] toBytes() throws IOException {
    int rawKeyLen = getRawKeyLen();
    int pos = 0;
    byte[] bytes = new byte[getSerializeSize()];

    // Encode raw key length
    byte[] rawKeyLenBytes = Bytes.toBytes(rawKeyLen);
    System.arraycopy(rawKeyLenBytes, 0, bytes, pos, RAW_KEY_LEN_SIZE);
    pos += RAW_KEY_LEN_SIZE;

    // Encode value length.
    byte[] valLen = Bytes.toBytes(value.length);
    System.arraycopy(valLen, 0, bytes, pos, VAL_LEN_SIZE);
    pos += VAL_LEN_SIZE;

    // Encode key
    System.arraycopy(key, 0, bytes, pos, key.length);
    pos += key.length;

    // Encode sequenceId
    byte[] seqIdBytes = Bytes.toBytes(sequenceId);
    System.arraycopy(seqIdBytes, 0, bytes, pos, seqIdBytes.length);
    pos += seqIdBytes.length;

    // Encode value
    System.arraycopy(value, 0, bytes, pos, value.length);
    return bytes;
  }

}
