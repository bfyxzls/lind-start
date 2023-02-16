package com.lind.pk;

import java.io.IOException;

/**
 * @author lind
 * @date 2023/2/16 11:04
 * @since 1.0.0
 */
public class HbaseApp {

    public static void main(String[] args) throws IOException {
        System.out.println("ok2"+HbaseUtils.createTable("student","info"));

    }
}
