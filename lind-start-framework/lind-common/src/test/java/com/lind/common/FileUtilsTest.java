package com.lind.common;

import com.lind.common.util.FileUtils;
import lombok.SneakyThrows;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class FileUtilsTest {
    static String path = "D:\\tools\\jdk8-windowsx64.zip";
    static String pathObj = "D:\\jar.zip";

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
        byte[]  buffer = FileUtils.readResourceByteArrayNIO(path);
        FileUtils.writeResourceFromByteArrayNIO(pathObj, buffer);
        System.out.println("NIO:" + stopwatch.getTime(TimeUnit.MILLISECONDS) + "ms");
    }
}
