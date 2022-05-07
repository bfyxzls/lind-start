package com.lind.common.thread.nio;

public class Test {
    @org.junit.Test
    public void main() {
        FileReader fileReader = new FileReader("D:\\个人技术资料\\爬虫补470万数据到雷达表source_filter_data\\test.txt", 100, 3);
        fileReader.registerHanlder(new FileLineDataHandler());
        fileReader.startRead();
    }
}

