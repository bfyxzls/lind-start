package com.lind.start.test;

import com.lind.common.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.ClassUtils;

import java.io.IOException;

@Slf4j
public class FileTest {
    String path = ClassUtils.getDefaultClassLoader().getResource("pkg.txt").getPath();

    @Test
    public void readFile() throws IOException {
        log.info(new String(FileUtils.readResourceByteArray(path)));
    }

    @Test
    public void readFileNIO() throws IOException {
        log.info(new String(FileUtils.readResourceByteArrayNIO(path)));
    }

    @Test
    public void readFileBigNIO() throws IOException {
        log.info(new String(FileUtils.readResourceByteArrayBigFileNIO(path)));
    }

    @Test
    public void writeFile() {
        String hello = "hello world!";
        FileUtils.writeResourceFromByteArrayNIO(ClassUtils.getDefaultClassLoader().getResource("").getPath() + "/test.txt", hello.getBytes());
    }
}
