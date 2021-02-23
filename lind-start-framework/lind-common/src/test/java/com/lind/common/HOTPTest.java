package com.lind.common;

import com.lind.common.encrypt.DESCbcUtils;
import com.lind.common.otp.TimeBasedOneTimePasswordGenerator;
import com.lind.common.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Slf4j
public class HOTPTest extends AbstractTest {
    final static String password = "pkulaw";
    @Autowired
    TimeBasedOneTimePasswordGenerator timeBasedOneTimePasswordGenerator;

    static Stream<Arguments> hotpTestVectorSource() {
        return Stream.of(
                arguments(0, 755224),
                arguments(1, 287082),
                arguments(2, 359152),
                arguments(3, 969429),
                arguments(4, 338314),
                arguments(5, 254676),
                arguments(6, 287922),
                arguments(7, 162583),
                arguments(8, 399871),
                arguments(9, 520489)
        );
    }

    @Test
    public void instantTest() {
        Instant now = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8));// beijing时间
        System.out.println("now:" + now);
    }

    @Test
    public void totpGenerator() throws Exception {
        final Instant now = Instant.now();
        String passKey = String.valueOf(timeBasedOneTimePasswordGenerator.generateOneTimePassword("pkulaw", now));
        System.out.format("Current password: %06d\n", passKey);
    }

    @Test
    public void totpDesGenerator() throws Exception {

        String plaintext = "hello";

        // 加密
        String passKey = String.format("%08d", timeBasedOneTimePasswordGenerator.generateOneTimePassword(password, Instant.now()));
        String result = DESCbcUtils.encrypt(passKey, plaintext);
        log.info("encryptDES result={}", result);
        for (int i = 0; i < 30; i++) {
            // 解密
            passKey = String.format("%08d", timeBasedOneTimePasswordGenerator.generateOneTimePassword(password, Instant.now()));
            log.info("{} decryptDES result={}", i, DESCbcUtils.decrypt(passKey, result));
            TimeUnit.SECONDS.sleep(1);
        }
    }

    @Test
    public void fileEncryption() throws Exception {
        FileUtils.setResourcePath();

        String result = new String(FileUtils.readResourceByteArray("pkg.txt"));
        for (int i = 0; i < 5; i++) {
            // 解密
            String passKey = String.format("%08d", timeBasedOneTimePasswordGenerator.generateOneTimePassword(password, Instant.now()));
            log.info("{} decryptDES result={}", i, DESCbcUtils.decrypt(passKey, result));
            TimeUnit.SECONDS.sleep(1);
        }
    }

}
