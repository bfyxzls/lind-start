package com.lind.common;

import com.lind.common.huffman.BetterHuffman;
import com.lind.common.huffman.HumanByte;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.Base64;

public class HuffmanTest {
    @Test
    public void huffmanByte() {
        HumanByte humanByte = new HumanByte();
        String code = "uB6Vcav7HOH/doTTPPupPaI4blPYnq3/kSyAODXhCjtgJFjFdjB8Rz8kP0xMTBtm";
        String huffmanCode = humanByte.encode(code.getBytes());
        System.out.println("huffman:" + huffmanCode);
        System.out.println("code:" + new String(humanByte.decode(huffmanCode)));

    }

    @SneakyThrows
    @Test
    public void betterhuffman() {
        String code = "v6_12345678_D_ff_20201231_999";

        String huffmanCode = Base64.getEncoder().encodeToString(BetterHuffman.compress(code));
        System.out.println("huffman:" + huffmanCode);

        System.out.println("code:" + BetterHuffman.expand(Base64.getDecoder().decode(huffmanCode)));

    }
}
