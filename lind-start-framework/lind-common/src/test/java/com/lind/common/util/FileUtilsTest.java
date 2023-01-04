package com.lind.common.util;

import lombok.SneakyThrows;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class FileUtilsTest {
    static String path = "D:\\tools\\jdk8-windowsx64.zip";
    static String pathObj = "D:\\jar.zip";
    private static Logger logger = LoggerFactory.getLogger(FileUtilsTest.class);
    private volatile AtomicLong maxFileId = new AtomicLong();

    @SneakyThrows
    @Test
    public void bio() {
        StopWatch stopwatch = StopWatch.createStarted();
        byte[] buffer = FileUtils.readResourceByteArray(path);
        FileUtils.writeResourceFromByteArrayNIO(pathObj, buffer);
        stopwatch.stop();
        System.out.println(stopwatch.getTime(TimeUnit.MILLISECONDS) + "ms");
    }

    @SneakyThrows
    @Test
    public void nio() {
        StopWatch stopwatch = StopWatch.createStarted();
        byte[] buffer = FileUtils.readResourceByteArrayNIO(path);
        FileUtils.writeResourceFromByteArrayNIO(pathObj, buffer);
        System.out.println("NIO:" + stopwatch.getTime(TimeUnit.MILLISECONDS) + "ms");
    }

    public synchronized long nextDiskFileId() {
        return maxFileId.incrementAndGet();
    }

    @Test
    public void generateId() {
        for (int i = 0; i < 10; i++) {
            System.out.println(String.format("data.%020d", nextDiskFileId()));
        }
    }

    /**
     * 读取资源文件
     */
    @Test
    public void readResourceStream() throws IOException {

        InputStream resource = FileUtilsTest.class.getClassLoader().getResourceAsStream("zzl.html");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        int bufSize = 1024;
        byte[] buffer = new byte[bufSize];
        int len = 0;
        while (-1 != (len = resource.read(buffer, 0, bufSize))) {
            bos.write(buffer, 0, len);
        }
        logger.info(new String(bos.toByteArray()));
    }
}
